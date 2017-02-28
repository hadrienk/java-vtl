package no.ssb.vtl.script.operations;

import com.google.common.base.MoreObjects;
import com.google.common.collect.*;
import no.ssb.vtl.model.*;
import no.ssb.vtl.script.support.CombinedList;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static no.ssb.vtl.model.Component.Role;

/**
 * Fold clause.
 */
public class FoldClause extends AbstractUnaryDatasetOperation {

    private final String dimension;
    private final String measure;
    private final Set<Component> elements;

    public FoldClause(Dataset dataset, String dimensionReference, String measureReference, Set<Component> elements) {
        // TODO: Introduce type here. Elements should be of the type of the Component.

        super(checkNotNull(dataset, "dataset cannot be null"));
        checkArgument(!(this.dimension = checkNotNull(dimensionReference, "dimensionReference cannot be null")).isEmpty(),
                "dimensionReference was empty");
        checkArgument(!(this.measure = checkNotNull(measureReference, "measureReference cannot be null")).isEmpty(),
                "measureReference was empty");
        checkArgument(!(this.elements = checkNotNull(elements, "elements cannot be null")).isEmpty(),
                "elements was empty");
    }

    @Override
    public DataStructure computeDataStructure() {
        DataStructure structure = getChild().getDataStructure();

        /*
        Check that all the elements are contained inside the structure.
            TODO: This is a constraint error.
        */
        checkArgument(
                structure.values().containsAll(elements),
                "the element(s) [%s] were not found in [%s]",
                Sets.difference(elements, structure.keySet()), structure.keySet()
        );

        /*
         Checks that elements are of the same type using a Multimap.
         */
        ListMultimap<Class<?>, Component> classes = ArrayListMultimap.create();
        for (Component element : elements) {
            classes.put(element.getType(), element);
        }
        checkArgument(
                classes.asMap().size() == 1,
                "the element(s) [%s] must be of the same type, found [%s] in dataset [%s]",
                elements, classes, structure
        );

        DataStructure.Builder structureBuilder = DataStructure.builder();
        for (Map.Entry<String, Component> componentEntry : structure.entrySet()) {
            if (!elements.contains(componentEntry.getValue())) {
                structureBuilder.put(componentEntry);
            }
        }

        structureBuilder.put(dimension, Role.IDENTIFIER, String.class);
        structureBuilder.put(measure, Role.MEASURE, classes.keySet().iterator().next());

        return structureBuilder.build();
    }

    @Override
    public Stream<? extends DataPoint> getData() {

        DataStructure dataStructure = getDataStructure();
        DataStructure childStructure = getChild().getDataStructure();

        /* Fold the values using the component in elements. */
        return getChild().getData().flatMap(tuple -> {

            List<DataPoint> foldedDataPoints = Lists.newArrayList();

            List<VTLObject> commonValues = Lists.newArrayList();
            List<List<VTLObject>> foldedValues = Lists.newArrayList();

            /* separate the values that will be folded */
            Map<Component, VTLObject> dataPointMap = childStructure.asMap(tuple);
            for (Map.Entry<Component, VTLObject> entry : dataPointMap.entrySet()) {
                if (!elements.contains(entry.getKey())) {
                    commonValues.add(entry.getValue());
                } else {
                    Object value = entry.getValue().get();
                    if (value != null) {
                        String foldedColumnName = childStructure.getName(entry.getKey());
                        VTLObject dimension = dataStructure.wrap(this.dimension, foldedColumnName);
                        VTLObject measure = dataStructure.wrap(this.measure, value);
                        foldedValues.add(Lists.newArrayList(
                                dimension, measure
                        ));
                    }
                }
            }

            /*
                create the new rows out of the folded values
                TODO: use ordering
             */
            return foldedValues.stream().map(
                    values -> DataPoint.create(new CombinedList<>(commonValues, values))
            );
        });
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return getChild().getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return getChild().getSize().map(size -> size * elements.size());
    }

    @Override
    @Deprecated
    public Stream<DataPoint> get() {
        return getData().map(o -> o);
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        helper.addValue(elements);
        helper.add("identifier", dimension);
        helper.add("measure", measure);
        helper.add("structure", getDataStructure());
        return helper.omitNullValues().toString();
    }
}
