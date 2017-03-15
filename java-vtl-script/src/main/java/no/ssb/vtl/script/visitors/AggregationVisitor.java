package no.ssb.vtl.script.visitors;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.Lists;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLParser;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AggregationVisitor extends VTLDatasetExpressionVisitor<AggregationVisitor.AggregationOperation> {
    
    
    private final ReferenceVisitor referenceVisitor;
    
    public AggregationVisitor() {
        referenceVisitor = new ReferenceVisitor();
    }
    
    @Override
    public AggregationOperation visitAggregateSum(VTLParser.AggregateSumContext ctx) {
        Dataset dataset = (Dataset) referenceVisitor.visit(ctx.datasetRef());
        List<Component> components = ctx.aggregationParms().componentRef().stream()
                .map(referenceVisitor::visit)
                .map(o -> (Component) o)
                .collect(Collectors.toList());
        return getSumOperation(dataset, components);
    }
    
    AggregationOperation getSumOperation(Dataset dataset, List<Component> components) {
        return new AggregationOperation(dataset, components);
    }
    
    class AggregationOperation extends AbstractUnaryDatasetOperation{
    
        private final List<Component> components;
    
        public AggregationOperation(Dataset child, List<Component> components) {
            super(child);
            this.components = components;
            
        }
    
        @Override
        protected DataStructure computeDataStructure() {
            //TODO
            return getChild().getDataStructure();
        }
    
        @Override
        public Stream<DataPoint> getData() {
            Order order = Order.create(getDataStructure()).build();
            return StreamUtils.aggregate(getChild().getData(), (dataPoint, dataPoint2) -> {
                System.out.println(String.format("aggregating: %s, %s", dataPoint, dataPoint2));
                Map<Component, VTLObject> map1 = getDataStructure().asMap(dataPoint);
                Map<Component, VTLObject> map2 = getDataStructure().asMap(dataPoint2);
                return map1.get(components.get(0)).compareTo(map2.get(components.get(0))) == 0;
//                return order.compare(dataPoint, dataPoint2) == 0;
            })
                    .map(dataPoints -> {
                        System.out.println("List of datapoints:");
                        VTLNumber sum = VTLNumber.of(0);
                        VTLObject id = null;
                        for (DataPoint dataPoint : dataPoints) {
                            Map<Component, VTLObject> map = getDataStructure().asMap(dataPoint);
                            id = map.get(components.get(0));
                            VTLObject object = map.get(getDataStructure().get("m1"));
                            sum = sum.add((Number) object.get());
                            System.out.println(sum);
                        }
                        return DataPoint.create(Lists.newArrayList(id, sum));
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
}
