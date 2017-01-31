package no.ssb.vtl.script.operations;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Component.Role;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Iterator;
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
    private Optional<DataStructure> computedDatastructure = Optional.empty();

    public UnfoldClause(Dataset dataset, String dimensionReference, String measureReference, Set<String> elements) {
        this.dataset = checkNotNull(dataset, "dataset cannot be null");

        // Checks not null and not empty.
        checkArgument(!(this.dimension = checkNotNull(dimensionReference, "dimensionReference cannot be null")).isEmpty(),
                "dimensionReference was empty");
        checkArgument(!(this.measure = checkNotNull(measureReference, "measureReference cannot be null")).isEmpty(),
                "measureReference was empty");
        checkArgument(!(this.elements = checkNotNull(elements, "elements cannot be null")).isEmpty(),
                "elements was empty");
        // TODO: Introduce type here. Elements should be of the type of the Component.
    }

    @Override
    public DataStructure getDataStructure() {
        return computedDatastructure.orElseGet(() -> {
            DataStructure dataStructure = dataset.getDataStructure();

            // TODO: Constraint error.
            checkArgument(
                    dataStructure.containsKey(dimension),
                    "the dimension [%s] was not found in %s", dimension, dataset
            );
            checkArgument(
                    dataStructure.get(dimension).isIdentifier(),
                    "[%s] in dataset [%s] was not a dimension", dimension, dataset
            );
            checkArgument(
                    dataStructure.containsKey(measure),
                    "the measure [%s] was not found in %s", measure, dataset
            );
            checkArgument(
                    dataStructure.get(measure).isMeasure(),
                    "[%s] in dataset [%s] was not a measure", measure, dataset
            );

            /*
                The spec is a bit unclear here; it does not say how to handle the measure values
                that are not part of the fold operation. Given the dataset:
                [A:ID, B:ID, C:ME, D:ME, E:AT]

                the result of "unfold B, C to foo, bar" could be either:
                 [A:ID, foo:ME, bar:ME, D:ME] with repeated values or nulls
                or
                 [A:ID, foo:ME, bar:ME]

                 until further clarification, the later is implemented.
             */

            Map<String, Role> newRoles = Maps.newHashMap();
            Map<String, Class<?>> newTypes = Maps.newHashMap();
            for (Map.Entry<String, Component> component : dataStructure.entrySet()) {
                String name = component.getKey();
                if (!dimension.equals(name) && !measure.equals(name)) {
                    if (component.getValue().isIdentifier()) {
                        newRoles.put(name, Role.IDENTIFIER);
                        newTypes.put(name, component.getValue().getType());
                    }
                }
            }

            Class<?> type = dataStructure.get(measure).getType();
            for (String element : elements) {
                newRoles.put(element, Role.MEASURE);
                newTypes.put(element, type);
            }
            computedDatastructure = Optional.of(DataStructure.of(dataStructure.converter(), newTypes, newRoles));
            return computedDatastructure.get();

        });
    }

    @Override
    public Stream<Tuple> get() {
        // TODO: Handle sorting. Need to request sorting by dimension happens after all others.
        // TODO: Maybe put a filter before.
        // TODO: Expose more powerful methods in Datapoint.
        DataStructure dataStructure = getDataStructure();
        return StreamUtils.aggregate(dataset.get(), (left, right) -> {
            // Checks if the previous ids (except the one with unfold on) where different.
            Iterator<DataPoint> leftIt = left.iterator();
            Iterator<DataPoint> rightIt = right.iterator();
            while (leftIt.hasNext() && rightIt.hasNext()) {
                DataPoint leftValue = leftIt.next();
                DataPoint rightValue = rightIt.next();
                if (!leftValue.getRole().equals(Role.IDENTIFIER)) {
                    continue;
                }
                if (dimension.equals(leftValue.getName())) {
                    continue;
                }
                if (!leftValue.equals(rightValue)) {
                    return false;
                }
            }
            return true;
        }).map(tuples -> {
            // TODO: Naive implementation for now.
            Map<String, Object> map = Maps.newLinkedHashMap();
            for (Tuple tuple : tuples) {
                Object unfoldedValue = null;
                String columnName = null;
                for (DataPoint dataPoint : tuple) {
                    if (dimension.equals(dataPoint.getName())) {
                        // TODO: Check type of the elements. Can we use element that are not String??
                        if (elements.contains(dataPoint.get())) {
                            columnName = (String) dataPoint.get();
                            continue;
                        }
                    }
                    if (measure.equals(dataPoint.getName())) {
                        unfoldedValue = dataPoint.get();
                        continue;
                    }
                    if (dataPoint.getRole().equals(Role.IDENTIFIER)) {
                        map.put(dataPoint.getName(), dataPoint.get());
                    }
                }
                map.put(columnName, unfoldedValue);
            }
            // TODO: >_<' ...
            for (String element : elements) {
                map.putIfAbsent(element, null);
            }
            return dataStructure.wrap(map);
        });
    }
}
