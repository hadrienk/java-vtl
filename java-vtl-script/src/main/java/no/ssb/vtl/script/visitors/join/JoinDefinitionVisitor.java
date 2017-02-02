package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.CrossJoinOperation;
import no.ssb.vtl.script.operations.join.InnerJoinOperation;
import no.ssb.vtl.script.operations.join.OuterJoinOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import org.antlr.v4.runtime.RuleContext;

import javax.script.ScriptContext;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

/**
 * Visitor that handle the join definition
 */
public class JoinDefinitionVisitor extends VTLBaseVisitor<AbstractJoinOperation> {

    private final ReferenceVisitor referenceVisitor;
    
    public JoinDefinitionVisitor(ScriptContext context) {
        checkNotNull(context);
        referenceVisitor = new ReferenceVisitor(context.getBindings(ScriptContext.ENGINE_SCOPE));
    }

    

    @Override
    public AbstractJoinOperation visitJoinDefinitionInner(VTLParser.JoinDefinitionInnerContext ctx) {
        Map<String, Dataset> theDatasets = getDatasetParameters(ctx.joinParam());
        return new InnerJoinOperation(theDatasets);
    }
    
    @Override
    public AbstractJoinOperation visitJoinDefinitionOuter(VTLParser.JoinDefinitionOuterContext ctx) {
        Map<String, Dataset> datasetMap = getDatasetParameters(ctx.joinParam());
        return new OuterJoinOperation(datasetMap);
    }
    
    @Override
    public AbstractJoinOperation visitJoinDefinitionCross(VTLParser.JoinDefinitionCrossContext ctx) {
        Map<String, Dataset> datasetMap = getDatasetParameters(ctx.joinParam());
        return new CrossJoinOperation(datasetMap);
    }
    
    Map<String, Dataset> getDatasetParameters(VTLParser.JoinParamContext ctx) {
        return ctx.datasetRef().stream()
                .collect(Collectors.toMap(RuleContext::getText, this::getDataset));
    }
    
    private Dataset getDataset(VTLParser.DatasetRefContext ref) {
        Object referencedObject = referenceVisitor.visit(ref);
        return (Dataset) referencedObject; //TODO: Is this always safe?
    }
    
}
