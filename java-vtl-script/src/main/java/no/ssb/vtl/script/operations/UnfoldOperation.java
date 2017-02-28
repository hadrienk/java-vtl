package no.ssb.vtl.script.operations;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.*;
import no.ssb.vtl.model.Component.Role;

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
public class UnfoldOperation extends AbstractUnaryDatasetOperation {

    private final Component dimension;
    private final Component measure;
    private final Set<String> elements;

    public UnfoldOperation(Dataset dataset, Component dimensionReference, Component measureReference, Set<String> elements) {
        super(checkNotNull(dataset, "dataset cannot be null"));

        this.dimension = checkNotNull(dimensionReference, "dimensionReference cannot be null");
        this.measure = checkNotNull(measureReference, "measureReference cannot be null");
        checkArgument(!(this.elements = checkNotNull(elements, "elements cannot be null")).isEmpty(),
                "elements was empty");
        // TODO: Introduce type here. Elements should be of the type of the Component.
    }

    @Override
    public Stream<? extends DataPoint> getData() {
        // TODO: Handle sorting. Need to request sorting by dimension happens after all others.
        // TODO: Maybe put a filter before.
        // TODO: Expose more powerful methods in Datapoint.
        DataStructure dataStructure = getDataStructure();
        return StreamUtils.aggregate(getChild().getData(), (left, right) -> {
            // Checks if the previous ids (except the one with unfold on) where different.
            Iterator<VTLObject> leftIt = left.iterator();
            Iterator<VTLObject> rightIt = right.iterator();
            while (leftIt.hasNext() && rightIt.hasNext()) {
                VTLObject leftValue = leftIt.next();
                VTLObject rightValue = rightIt.next();
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
        }).map(dataPoints -> {
            // TODO: Naive implementation for now.
            Map<String, Object> map = Maps.newLinkedHashMap();
            for (DataPoint dataPoint : dataPoints) {
                Object unfoldedValue = null;
                String columnName = null;
                for (VTLObject value : dataPoint) {
                    if (dimension == value.getComponent()) {
                        // TODO: Check type of the elements. Can we use element that are not String??
                        if (elements.contains(value.get())) {
                            columnName = (String) value.get();
                            continue;
                        }
                    }
                    if (measure == value.getComponent()) {
                        unfoldedValue = value.get();
                        continue;
                    }
                    if (value.getRole().equals(Role.IDENTIFIER)) {
                        map.put(value.getName(), value.get());
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

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return null;
    }

    @Override
    public Optional<Long> getSize() {
        return null;
    }

    @Override
    public DataStructure computeDataStructure() {
        Dataset dataset = getChild();
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

        DataStructure.Builder newDataStructure = DataStructure.builder();
        for (Map.Entry<String, Component> componentEntry : dataStructure.entrySet()) {;
            Component component = componentEntry.getValue();
            if (component != dimension && component != measure) {
                if (component.isIdentifier()) {
                    newDataStructure.put(componentEntry);
                }
            }
        }

        Class<?> type = measure.getType();
        for (String element : elements) {
            newDataStructure.put(element, Role.MEASURE, type);
        }
        return newDataStructure.build();
    }

    @Override
    @Deprecated
    public Stream<DataPoint> get() {
        return getData().map(o -> o);
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        helper.add("identifier", dimension);
        helper.add("measure", measure);
        helper.addValue(elements);
        helper.add("structure", getDataStructure());
        return helper.omitNullValues().toString();
    }
}
