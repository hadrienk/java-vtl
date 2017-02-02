package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.CrossJoinOperation;
import no.ssb.vtl.script.operations.join.InnerJoinOperation;
import no.ssb.vtl.script.operations.join.OuterJoinOperation;

import javax.script.Bindings;
import javax.script.ScriptContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.*;

/**
 * Visitor that handle the join definition
 */
public class JoinDefinitionVisitor extends VTLBaseVisitor<AbstractJoinOperation> {

    private final ScriptContext context;

    public JoinDefinitionVisitor(ScriptContext context) {
        this.context = checkNotNull(context);
    }

    

    @Override
    public AbstractJoinOperation visitJoinDefinitionInner(VTLParser.JoinDefinitionInnerContext ctx) {
        List<VTLParser.DatasetRefContext> datasets = ctx.joinParam().datasetRef();

        Map<String, Dataset> theDatasets = createJoinScope(datasets);
    
        return new InnerJoinOperation(theDatasets);

    }

    @Override
    public AbstractJoinOperation visitJoinDefinitionOuter(VTLParser.JoinDefinitionOuterContext ctx) {
        List<VTLParser.DatasetRefContext> datasets = ctx.joinParam().datasetRef();

        Map<String, Dataset> datasetMap = createJoinScope(datasets);
    
        return new OuterJoinOperation(datasetMap);
    }

    @Override
    public AbstractJoinOperation visitJoinDefinitionCross(VTLParser.JoinDefinitionCrossContext ctx) {
        List<VTLParser.DatasetRefContext> datasets = ctx.joinParam().datasetRef();

        Map<String, Dataset> datasetMap = createJoinScope(datasets);
    
        return new CrossJoinOperation(datasetMap);
    }


    /**
     * Finds the datasets in the context.
     * @param varIDContexts
     */
    Map<String, Dataset> createJoinScope(List<VTLParser.DatasetRefContext> varIDContexts) {
        Map<String, Dataset> joinScope = new HashMap<>();
        
        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        for (VTLParser.DatasetRefContext varIDContext : varIDContexts) {
            String datasetName = varIDContext.getText();
            if (!bindings.containsKey(datasetName)) {
                // TODO: Exception, invalid type.
                throw new RuntimeException(datasetName + " does not exist");
            }
            Object datasetVariable = bindings.get(datasetName);
            if (!(datasetVariable instanceof Dataset)) {
                // TODO: Exception, invalid type.
                throw new RuntimeException(datasetName + " was not a dataset");
            }
            Dataset dataset = (Dataset) datasetVariable;
            joinScope.put(datasetName, dataset);
        }
        
        return joinScope;
    }

}
