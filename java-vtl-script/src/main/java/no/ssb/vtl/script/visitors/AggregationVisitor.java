package no.ssb.vtl.script.visitors;

import com.google.common.collect.MoreCollectors;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.AggregationOperation;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.List;
import java.util.stream.Collectors;

public class AggregationVisitor extends VTLDatasetExpressionVisitor<AggregationOperation> {
    
    
    private final ReferenceVisitor referenceVisitor;
    
    public AggregationVisitor(ReferenceVisitor referenceVisitor) {
        this.referenceVisitor = referenceVisitor;
    }
    
    @Override
    public AggregationOperation visitAggregateSum(VTLParser.AggregateSumContext ctx) {
        Dataset dataset;
        if (ctx.datasetRef() != null) {
            dataset = (Dataset) referenceVisitor.visit(ctx.datasetRef());
            return getSumOperation(dataset, getGroupByComponents(ctx, dataset));
        } else if (ctx.componentRef() != null) {
            dataset = (Dataset) referenceVisitor.visit(ctx.componentRef().datasetRef());
            Component aggregationComponent = getComponentFromDataset(dataset, ctx.componentRef().variableRef());
            return getSumOperation(dataset, getGroupByComponents(ctx, dataset), aggregationComponent);
        } throw new ParseCancellationException("Required parameters not found");
    }
    
    private Component getComponentFromDataset(Dataset dataset, VTLParser.VariableRefContext componentRef) {
        String text = componentRef.getText();
        DataStructure dataStructure = dataset.getDataStructure();
        return dataStructure.get(text);
    }
    
    private List<Component> getGroupByComponents(VTLParser.AggregateSumContext ctx, Dataset dataset) {
        List<Component> idComponents = ctx.aggregationParms().componentRef().stream()
                .map(componentRef -> getComponentFromDataset(dataset, componentRef.variableRef()))
                .collect(Collectors.toList());
        
        Token clause = ctx.aggregationParms().aggregationClause;
        switch (clause.getType()){
            case VTLParser.GROUP_BY:
                return idComponents;
            case VTLParser.ALONG:
                return dataset.getDataStructure().values().stream()
                        .filter(Component::isIdentifier)
                        .filter(component -> !idComponents.contains(component))
                        .collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Unrecognized token: " + clause);
        }
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
