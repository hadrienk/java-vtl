package no.ssb.vtl.script.visitors.join;

import com.google.common.collect.Maps;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.*;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.SimpleBindings;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Visitor that handle the join definition
 */
public class JoinDefinitionVisitor extends VTLBaseVisitor<AbstractJoinOperation> {

    private final ScriptContext context;

    public JoinDefinitionVisitor(ScriptContext context) {
        this.context = checkNotNull(context);
    }

    @Override
    public AbstractJoinOperation visitJoinExpression(VTLParser.JoinExpressionContext ctx) {
        AbstractJoinOperation joinOperation = visit(ctx.joinDefinition());
        JoinBodyVisitor joinBodyVisitor = new JoinBodyVisitor(joinOperation);

        // TODO: Not used?
        JoinClause joinClause = joinBodyVisitor.visitJoinBody(ctx.joinBody());

        return joinOperation;
    }

    @Override
    public AbstractJoinOperation visitJoinDefinitionInner(VTLParser.JoinDefinitionInnerContext ctx) {
        List<VTLParser.VarIDContext> datasets = ctx.joinParam().varID();

        Bindings theDatasets = createJoinScope(datasets);

        InnerJoinOperation joinOperation = new InnerJoinOperation(theDatasets);
        return joinOperation;

    }

    @Override
    public AbstractJoinOperation visitJoinDefinitionOuter(VTLParser.JoinDefinitionOuterContext ctx) {
        List<VTLParser.VarIDContext> datasets = ctx.joinParam().varID();

        Bindings datasetMap = createJoinScope(datasets);

        OuterJoinOperation joinOperation = new OuterJoinOperation(datasetMap);
        return joinOperation;
    }

    @Override
    public AbstractJoinOperation visitJoinDefinitionCross(VTLParser.JoinDefinitionCrossContext ctx) {
        List<VTLParser.VarIDContext> datasets = ctx.joinParam().varID();

        Bindings datasetMap = createJoinScope(datasets);

        CrossJoinOperation joinOperation = new CrossJoinOperation(datasetMap);
        return joinOperation;
    }


    /**
     * Finds the datasets in the context.
     */
    private Bindings createJoinScope(List<VTLParser.VarIDContext> names) {
        Map<String, Dataset> datasets = Maps.newHashMap();
        Bindings joinScope = new SimpleBindings();
        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        for (VTLParser.VarIDContext dataset : names) {
            String datasetName = dataset.getText();
            if (!bindings.containsKey(datasetName)) {
                // TODO: Exception, invalid type.
                throw new RuntimeException(datasetName + " does not exist");
            }
            Object datasetVariable = bindings.get(datasetName);
            if (!(datasetVariable instanceof Dataset)) {
                // TODO: Exception, invalid type.
                throw new RuntimeException(datasetName + " was not a dataset");
            }
            joinScope.put(datasetName, datasetVariable);
//            datasets.put(datasetName, (Dataset) datasetVariable);
        }
        return joinScope;
//        return datasets;
    }

}
