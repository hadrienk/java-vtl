package no.ssb.vtl.script.operations;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Component.Role;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Maps.*;

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
        // TODO: Maybe put a filter before.
        // TODO: Expose more powerful methods in Datapoint.

        DataStructure dataStructure = getDataStructure();
        DataStructure childStructure = getChild().getDataStructure();

        Order order = Order.create(
                filterValues(dataStructure, Component::isIdentifier),
                childStructure
        );
        Stream<? extends DataPoint> stream = getChild().getData(order).orElseThrow(RuntimeException::new);

        return StreamUtils.aggregate(stream, (left, right) -> {
            // Checks if the previous ids (except the one with unfold on) where different.
            return order.compare(left, right) == 0;
        }).map(dataPoints -> {
            // TODO: Naive implementation for now.

            DataPoint result = dataStructure.wrap();

            Map<Component, VTLObject> foldedMap = dataStructure.asMap(result);

            Iterator<? extends DataPoint> datapointIterator = dataPoints.iterator();
            while (datapointIterator.hasNext()) {
                Map<Component, VTLObject> dataPoint = childStructure.asMap(datapointIterator.next());

                VTLObject unfoldedValue = null;
                Component unfoldedComponent = null;

                for (Component component : dataPoint.keySet()) {
                    if (component.isIdentifier()) {
                        if (component.equals(dimension)) {
                            VTLObject value = dataPoint.get(component);
                            if (elements.contains(value.get())) {
                                unfoldedComponent = dataStructure.get(value.get());
                            }
                            continue;
                        } else {
                            foldedMap.put(component, dataPoint.get(component));
                        }
                    }
                    if (component.equals(measure)) {
                        unfoldedValue = dataPoint.get(component);
                    }
                }
                foldedMap.put(unfoldedComponent, unfoldedValue);
            }
            return result;
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
        for (Map.Entry<String, Component> componentEntry : dataStructure.entrySet()) {
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
