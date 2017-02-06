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
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Unfold clause.
 */
public class UnfoldClause implements Dataset {

    // Source dataset.
    private final Dataset dataset;

    private final Component dimension;
    private final Component measure;
    private final Set<String> elements;
    private DataStructure cache;

    public UnfoldClause(Dataset dataset, Component dimensionReference, Component measureReference, Set<String> elements) {
        this.dataset = checkNotNull(dataset, "dataset cannot be null");

        this.dimension = checkNotNull(dimensionReference, "dimensionReference cannot be null");
        this.measure = checkNotNull(measureReference, "measureReference cannot be null");
        checkArgument(!(this.elements = checkNotNull(elements, "elements cannot be null")).isEmpty(),
                "elements was empty");
        // TODO: Introduce type here. Elements should be of the type of the Component.
    }

    @Override
    public DataStructure getDataStructure() {
        return cache = (cache == null ? computeDataStructure() : cache);
    }

    private DataStructure computeDataStructure() {
        DataStructure dataStructure = dataset.getDataStructure();

        // TODO: Does those check still make sense with the Reference visitor?
        checkArgument(
                dataStructure.containsValue(dimension),
                "the dimension [%s] was not found in %s", dimension, dataset
        );
        checkArgument(
                dataStructure.containsValue(measure),
                "the measure [%s] was not found in %s", measure, dataset
        );

        checkArgument(
                dimension.isIdentifier(),
                "[%s] in dataset [%s] was not a dimension", dimension, dataset
        );
        checkArgument(
                measure.isMeasure(),
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


        BiFunction<Object, Class<?>, ?> converter = dataStructure.converter();
        Map<String, Component> newDataStructure = Maps.newLinkedHashMap(dataStructure);

        Iterator<Map.Entry<String, Component>> iterator = newDataStructure.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Component> componentEntry = iterator.next();
            Component component = componentEntry.getValue();
            if (component == dimension || component == measure) {
                iterator.remove();
            } else if (!component.isIdentifier()) {
                iterator.remove();
            }
        }

        dataStructure = DataStructure.copyOf(converter, newDataStructure);
        Class<?> type = measure.getType();
        for (String element : elements) {
            dataStructure.addComponent(element, Role.MEASURE, type);
        }
        return dataStructure;
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
                if (dimension == leftValue.getComponent()) {
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
                    if (dimension == dataPoint.getComponent()) {
                        // TODO: Check type of the elements. Can we use element that are not String??
                        if (elements.contains(dataPoint.get())) {
                            columnName = (String) dataPoint.get();
                            continue;
                        }
                    }
                    if (measure == dataPoint.getComponent()) {
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
