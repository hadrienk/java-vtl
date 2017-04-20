package no.ssb.vtl.script.operations;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class AggregationOperation extends AbstractUnaryDatasetOperation {

    private final List<Component> groupBy;
    private final Component aggregationComponent;
    private final Function<List<VTLNumber>, VTLNumber> aggregationFunction;

    public AggregationOperation(Dataset child, List<Component> groupBy, Component aggregationComponent, Function<List<VTLNumber>, VTLNumber> aggregationFunction) {
        super(child);
        this.groupBy = groupBy;
        this.aggregationComponent = checkNotNull(aggregationComponent);
        this.aggregationFunction = aggregationFunction;
        
    }

    @Override
    protected DataStructure computeDataStructure() {
        DataStructure.Builder newDataStructure = DataStructure.builder();
        for (Map.Entry<String, Component> entry : getChild().getDataStructure().entrySet()) {
            if (groupBy.contains(entry.getValue()) || aggregationComponent.equals(entry.getValue())) {
                newDataStructure.put(entry);
            }
        }
        return newDataStructure.build();
    }

    @Override
    public Stream<DataPoint> getData() {
        Order.Builder builder = Order.create(getDataStructure());
        groupBy.forEach(component -> builder.put(component, Order.Direction.ASC));
        Order order = builder.build();
    
        Stream<DataPoint> data = getChild().getData(order).orElse(getChild().getData().sorted(order));
        Stream<List<DataPoint>> groupedDataPoints = StreamUtils.aggregate(data,
                (dataPoint1, dataPoint2) -> order.compare(dataPoint1, dataPoint2) == 0);
    
        @SuppressWarnings("UnnecessaryLocalVariable")
        Stream<DataPoint> aggregatedDataPoints = groupedDataPoints.map(dataPoints -> {
            Map<Component, VTLObject> resultAsMap = Maps.newHashMap();
            List<VTLNumber> aggregationValues = new ArrayList<>();
            dataPoints.stream()
                    .map(dataPoint ->  getChild().getDataStructure().asMap(dataPoint).entrySet())
                    .flatMap(Collection::stream)
                    .forEach(entry -> {
                        if (entry.getKey().equals(aggregationComponent)) {
                            aggregationValues.add(VTLNumber.of((Number) entry.getValue().get()));
                        } else if (groupBy.contains(entry.getKey())) {
                            resultAsMap.put(entry.getKey(), entry.getValue());
                        }
                    });
            resultAsMap.put(aggregationComponent, aggregationFunction.apply(aggregationValues));
            return getDataStructure().fromMap(resultAsMap);
        });
        
        return aggregatedDataPoints;
    }

    /**
     * Returns the count of unique values by column.
     */
    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return null;
    }

    /**
     * Return the amount of {@link DataPoint} the stream obtained by the
     * method {@link Dataset#getData()} will return.
     */
    @Override
    public Optional<Long> getSize() {
        return null;
    }
}
