package no.ssb.vtl.script.operations;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.AbstractDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static java.util.Arrays.*;

/**
 * Union operator
 */
public class UnionOperation extends AbstractDatasetOperation {

    private Set<Map.Entry<String, Class<? extends Component>>> requiredRoles;

    public UnionOperation(Dataset... dataset) {
        this(asList(dataset));
    }

    public UnionOperation(List<Dataset> datasets) {
        super(datasets);
    }
    
    @Override
    protected DataStructure computeDataStructure() {
        Iterator<Dataset> iterator = this.getChildren().iterator();
        DataStructure dataStructure = iterator.next().getDataStructure();
        while (iterator.hasNext())
            checkDataStructures(iterator.next(), dataStructure);
        return dataStructure;
    }
    
    private void checkDataStructures(Dataset dataset, DataStructure dataStructure) {
        // Identifiers and attribute should be equals in name, role and type.
        Set<String> requiredNames = nonAttributeNames(dataStructure);
        Set<String> providedNames = nonAttributeNames(dataset.getDataStructure());

        checkArgument(
                requiredNames.equals(providedNames),
                "dataset %s was incompatible with the required data structure, missing: %s, unexpected %s",
                dataset,
                Sets.difference(requiredNames, providedNames),
                Sets.difference(providedNames, requiredNames)
        );

        Map<String, Component.Role> requiredRoles;
        requiredRoles = Maps.filterKeys(getDataStructure().getRoles(), requiredNames::contains);
        Map<String, Component.Role> providedRoles;
        providedRoles = Maps.filterKeys(dataset.getDataStructure().getRoles(), requiredNames::contains);

        checkArgument(
                requiredNames.equals(providedNames),
                "dataset %s was incompatible with the required data structure, missing: %s, unexpected %s",
                dataset,
                Sets.difference(requiredRoles.entrySet(), providedRoles.entrySet()),
                Sets.difference(providedRoles.entrySet(), requiredRoles.entrySet())
        );

        Map<String, Class<?>> requiredTypes;
        requiredTypes = Maps.filterKeys(getDataStructure().getTypes(), requiredNames::contains);
        Map<String, Class<?>> providedTypes;
        providedTypes = Maps.filterKeys(dataset.getDataStructure().getTypes(), requiredNames::contains);

        checkArgument(
                requiredNames.equals(providedNames),
                "dataset %s was incompatible with the required data structure, missing: %s, unexpected %s",
                dataset,
                Sets.difference(requiredTypes.entrySet(), providedTypes.entrySet()),
                Sets.difference(providedTypes.entrySet(), requiredTypes.entrySet())
        );

    }

    private Set<String> nonAttributeNames(DataStructure dataStructure) {
        return Maps.filterValues(dataStructure.getRoles(), role -> role != Component.Role.ATTRIBUTE).keySet();
    }

    @Override
    public Stream<DataPoint> get() {
        List<Dataset> datasets = getChildren();
        if (datasets.size() == 1) {
            return datasets.get(0).get();
        }

        if (datasets.size() == 2) {
            if (datasets.get(0).equals(datasets.get(1))) {
                return datasets.get(0).get();
            }
        }

        return getData().map(o -> o);
    }
            
    @Override
    public Stream<? extends DataPoint> getData() {
        // TODO: Attribute propagation.
        Set<DataPoint> bucket = Sets.newTreeSet(Dataset.comparatorFor(Component.Role.IDENTIFIER, Component.Role.MEASURE));
        Set<DataPoint> seen = Collections.synchronizedSet(bucket);
        return getChildren().stream().flatMap(Supplier::get)
                .filter((o) -> !seen.contains(o))
                .peek(bucket::add);
    }
    
    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }
    
    @Override
    public Optional<Long> getSize() {
        return Optional.empty();
    }
}
