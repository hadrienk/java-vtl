package no.ssb.vtl.script.operations;

import com.codepoetics.protonpack.StreamUtils;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;

import java.util.ArrayList;
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
        Order order = Order.create(getDataStructure()).build();
        return StreamUtils.aggregate(getChild().getData(order).orElse(getChild().getData().sorted(order)), (
                dataPoint, dataPoint2) -> {
            Map<Component, VTLObject> map1 = getDataStructure().asMap(dataPoint);
            Map<Component, VTLObject> map2 = getDataStructure().asMap(dataPoint2);
            return map1.get(groupBy.get(0)).compareTo(map2.get(groupBy.get(0))) == 0;
        })
                .map(dataPoints -> {
                    DataPoint result = getDataStructure().wrap();
                    Map<Component, VTLObject> resultAsMap = getDataStructure().asMap(result);

                    List<VTLNumber> aggregationValues = new ArrayList<>();
                    for (DataPoint dataPoint : dataPoints) {
                        Map<Component, VTLObject> objectMap = getChild().getDataStructure().asMap(dataPoint);
                        for (Map.Entry<Component, VTLObject> entry : objectMap.entrySet()) {
                            if (entry.getKey().equals(aggregationComponent)) {
                                aggregationValues.add(VTLNumber.of((Number) entry.getValue().get()));
                            } else if (groupBy.contains(entry.getKey())) {
                                resultAsMap.put(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                    resultAsMap.put(aggregationComponent, aggregationFunction.apply(aggregationValues));
                    return result;
                });
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
