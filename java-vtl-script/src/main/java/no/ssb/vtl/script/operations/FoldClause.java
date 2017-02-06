package no.ssb.vtl.script.operations;

import com.google.common.collect.*;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static no.ssb.vtl.model.Component.Role;

/**
 * Fold clause.
 */
public class FoldClause implements Dataset {

    // Source dataset.
    private final Dataset dataset;

    private final String dimension;
    private final String measure;
    private final Set<Component> elements;

    private DataStructure cache = null;

    public FoldClause(Dataset dataset, String dimensionReference, String measureReference, Set<Component> elements) {
        // TODO: Introduce type here. Elements should be of the type of the Component.

        this.dataset = checkNotNull(dataset, "dataset cannot be null");
        checkArgument(!(this.dimension = checkNotNull(dimensionReference, "dimensionReference cannot be null")).isEmpty(),
                "dimensionReference was empty");
        checkArgument(!(this.measure = checkNotNull(measureReference, "measureReference cannot be null")).isEmpty(),
                "measureReference was empty");
        checkArgument(!(this.elements = checkNotNull(elements, "elements cannot be null")).isEmpty(),
                "elements was empty");
    }

    private DataStructure computeDataStructure() {
        DataStructure dataStructure = dataset.getDataStructure();

        // TODO: Constraint error.
        checkArgument(
                dataStructure.values().containsAll(elements),
                "the element(s) [%s] were not found in [%s]",
                Sets.difference(elements, dataStructure.keySet()), dataStructure.keySet()
        );

        // Checks that elements are of the same type
        ListMultimap<Class<?>, Component> classes = ArrayListMultimap.create();
        for (Component element : elements) {
            classes.put(element.getType(), element);
        }
        checkArgument(
                classes.asMap().size() == 1,
                "the element(s) [%s] must be of the same type, found [%s] in dataset [%s]",
                elements, classes, dataStructure
        );

        Map<String, Component.Role> newRoles = Maps.newLinkedHashMap();
        Map<String, Class<?>> newTypes = Maps.newLinkedHashMap();
        for (Map.Entry<String, Component> componentEntry : dataStructure.entrySet()) {
            if (!elements.contains(componentEntry.getValue())) {
                newRoles.put(componentEntry.getKey(), componentEntry.getValue().getRole());
                newTypes.put(componentEntry.getKey(), componentEntry.getValue().getType());
            }
        }

        newRoles.put(dimension, Role.IDENTIFIER);
        newTypes.put(dimension, String.class);

        newRoles.put(measure, Role.MEASURE);
        newTypes.put(measure, classes.keySet().iterator().next());

        return DataStructure.of(dataStructure.converter(), newTypes, newRoles);
    }

    @Override
    public DataStructure getDataStructure() {
        return cache = (cache == null ? computeDataStructure() : cache);
    }

    @Override
    public Stream<Tuple> get() {
        DataStructure dataStructure = getDataStructure();
        return dataset.get().flatMap(tuple -> {
            List<Tuple> tuples = Lists.newArrayList();
            Map<String, Object> commonValues = Maps.newLinkedHashMap();
            Map<String, Object> foldedValues = Maps.newLinkedHashMap();

            for (DataPoint point : tuple) {
                if (elements.contains(point.getComponent())) {
                    foldedValues.put(point.getName(), point.get());
                } else {
                    commonValues.put(point.getName(), point.get());
                }
            }

            for (Component element : elements) {
                if (foldedValues.containsKey(element.getName()) && foldedValues.get(element.getName()) != null) {
                    Map<String, Object> rowMap = Maps.newLinkedHashMap(commonValues);
                    rowMap.put(dimension, element.getName());
                    rowMap.put(measure, foldedValues.get(element.getName()));
                    tuples.add(dataStructure.wrap(rowMap));
                }
            }
            return tuples.stream();
        });
    }
}
