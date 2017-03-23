package no.ssb.vtl.script.visitors;

import com.google.common.collect.MoreCollectors;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.AggregationOperation;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.List;
import java.util.stream.Collectors;

public class AggregationVisitor extends VTLDatasetExpressionVisitor<AggregationOperation> {
    
    
    private final ReferenceVisitor referenceVisitor;
    
    public AggregationVisitor() {
        referenceVisitor = new ReferenceVisitor();
    }
    
    @Override
    public AggregationOperation visitAggregateSum(VTLParser.AggregateSumContext ctx) {
        List<Component> groupBy = ctx.aggregationParms().componentRef().stream()
                .map(referenceVisitor::visit)
                .map(o -> (Component) o)
                .collect(Collectors.toList());
        Dataset dataset;
        if (ctx.datasetRef() != null) {
            dataset = (Dataset) referenceVisitor.visit(ctx.datasetRef());
            return getSumOperation(dataset, groupBy);
        } else if (ctx.componentRef() != null) {
            dataset = (Dataset) referenceVisitor.visit(ctx.componentRef().datasetRef());
            Component aggregationComponent = (Component) referenceVisitor.visit(ctx.componentRef());
            getSumOperation(dataset, groupBy, aggregationComponent);
        } throw new ParseCancellationException("Required parameters not found");
    }
    
    AggregationOperation getSumOperation(Dataset dataset, List<Component> groupBy) {
        Component component = dataset.getDataStructure().values().stream()
                .filter(Component::isMeasure)
                .collect(MoreCollectors.onlyElement());
        return getSumOperation(dataset, groupBy, component);
    }
    
    AggregationOperation getSumOperation(Dataset dataset, List<Component> groupBy, Component aggregationComponent) {
        return new AggregationOperation(dataset, groupBy, aggregationComponent,
                vtlNumbers -> vtlNumbers.stream().reduce(VTLNumber::add).get());
    }
    
}
