package no.ssb.vtl.script.operations;

import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Unfold clause.
 */
public class UnfoldClause implements Dataset {

    // Source dataset.
    private final Dataset dataset;
    private final String dimension;
    private final String measure;
    private final Set<String> elements;


    private Optional<DataStructure> computedDatastructure;

    public UnfoldClause(Dataset dataset, String dimensionReference, String measureReference, Set<String> elements) {
        this.dataset = checkNotNull(dataset);

        // Checks not null and not empty.
        checkArgument(!(this.dimension = checkNotNull(dimensionReference)).isEmpty());
        checkArgument(!(this.measure = checkNotNull(measureReference)).isEmpty());
        checkArgument(!(this.elements = checkNotNull(elements)).isEmpty());
    }

    @Override
    public DataStructure getDataStructure() {
        return computedDatastructure.orElseGet(() -> {
            DataStructure dataStructure = dataset.getDataStructure();

            // TODO: Constrain error.
            checkArgument(
                    dataStructure.containsKey(dimension),
                    "the dimension [%s] was not found in %s", dimension, dataset
            );
            checkArgument(
                    dataStructure.containsKey(measure),
                    "the measure [%s] was not found in %s", measure, dataset
            );

            Map<String, Component.Role> roles = Maps.newHashMap(dataStructure.getRoles());
            Map<String, Class<?>> types = Maps.newHashMap(dataStructure.getTypes());

            roles.remove(dimension);
            roles.remove(measure);
            types.remove(dimension);
            types.remove(measure);

            computedDatastructure = Optional.of(DataStructure.of(dataStructure.converter(), types, roles));
            return computedDatastructure.get();

        });
    }

    @Override
    public Stream<Tuple> get() {
        // TODO: Handle sorting. Need to request sorting by dimension happens after all others.
        Stream<Tuple> tupleStream = dataset.get();
        tupleStream.m
        return null;
    }
}
