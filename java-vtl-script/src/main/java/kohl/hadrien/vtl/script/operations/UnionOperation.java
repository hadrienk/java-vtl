package kohl.hadrien.vtl.script.operations;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import kohl.hadrien.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Arrays.asList;

/**
 * Union operator
 */
public class UnionOperation implements Supplier<Dataset> {

    private final DataStructure dataStructure;
    private final List<Dataset> datasets;
    private Set<Map.Entry<String, Class<? extends Component>>> requiredRoles;

    public UnionOperation(Dataset... dataset) {
        this(asList(dataset));
    }

    public UnionOperation(List<Dataset> datasets) {
        checkArgument(
                !checkNotNull(datasets, "the dataset list was null").isEmpty(),
                "the dataset list was empty"
        );
        this.datasets = datasets;

        Iterator<Dataset> iterator = this.datasets.iterator();
        dataStructure = iterator.next().getDataStructure();
        while (iterator.hasNext())
            checkDataStructures(iterator.next());
    }

    private void checkDataStructures(Dataset dataset) {
        // Identifiers and attribute should be equals in name, role and type.
        Set<String> requiredNames = nonAttributeNames(this.dataStructure);
        Set<String> providedNames = nonAttributeNames(dataset.getDataStructure());

        checkArgument(
                requiredNames.equals(providedNames),
                "dataset %s was incompatible with the required data structure, missing: %s, unexpected %s",
                dataset,
                Sets.difference(requiredNames, providedNames),
                Sets.difference(providedNames, requiredNames)
        );

        Map<String, Class<? extends Component>> requiredRoles;
        requiredRoles = Maps.filterKeys(this.dataStructure.roles(), requiredNames::contains);
        Map<String, Class<? extends Component>> providedRoles;
        providedRoles = Maps.filterKeys(dataset.getDataStructure().roles(), requiredNames::contains);

        checkArgument(
                requiredNames.equals(providedNames),
                "dataset %s was incompatible with the required data structure, missing: %s, unexpected %s",
                dataset,
                Sets.difference(requiredRoles.entrySet(), providedRoles.entrySet()),
                Sets.difference(providedRoles.entrySet(), requiredRoles.entrySet())
        );

        Map<String, Class<?>> requiredTypes;
        requiredTypes = Maps.filterKeys(this.dataStructure.types(), requiredNames::contains);
        Map<String, Class<?>> providedTypes;
        providedTypes = Maps.filterKeys(dataset.getDataStructure().types(), requiredNames::contains);

        checkArgument(
                requiredNames.equals(providedNames),
                "dataset %s was incompatible with the required data structure, missing: %s, unexpected %s",
                dataset,
                Sets.difference(requiredTypes.entrySet(), providedTypes.entrySet()),
                Sets.difference(providedTypes.entrySet(), requiredTypes.entrySet())
        );

    }

    private Set<String> nonAttributeNames(DataStructure dataStructure) {
        return Maps.filterValues(dataStructure.roles(), component -> !Attribute.class.equals(component)).keySet();
    }

    @Override
    public Dataset get() {
        if (datasets.size() == 1)
            return datasets.get(0);

        if (datasets.size() == 2)
            if (datasets.get(0).equals(datasets.get(1)))
                return datasets.get(0);

        return new Dataset() {

            @Override
            public DataStructure getDataStructure() {
                return dataStructure;
            }

            @Override
            public Stream<Tuple> get() {
                // TODO: Attribute propagation.
                Set<Tuple> bucket = Sets.newTreeSet(Dataset.comparatorFor(Identifier.class, Measure.class));
                Set<Tuple> seen = Collections.synchronizedSet(bucket);
                return datasets.stream().flatMap(Supplier::get)
                        .filter((o) -> !seen.contains(o))
                        .peek(bucket::add);
            }
        };
    }

}
