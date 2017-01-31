package no.ssb.vtl.script.visitors.join;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.CrossJoinOperation;
import no.ssb.vtl.script.operations.join.InnerJoinOperation;
import no.ssb.vtl.script.operations.join.OuterJoinOperation;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.SimpleBindings;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<VTLParser.VarIDContext> datasets = ctx.joinParam().varID();

        Bindings theDatasets = createJoinScope(datasets);
    
        return new InnerJoinOperation(theDatasets);

    }

    @Override
    public AbstractJoinOperation visitJoinDefinitionOuter(VTLParser.JoinDefinitionOuterContext ctx) {
        List<VTLParser.VarIDContext> datasets = ctx.joinParam().varID();

        Bindings datasetMap = createJoinScope(datasets);
    
        return new OuterJoinOperation(datasetMap);
    }

    @Override
    public AbstractJoinOperation visitJoinDefinitionCross(VTLParser.JoinDefinitionCrossContext ctx) {
        List<VTLParser.VarIDContext> datasets = ctx.joinParam().varID();

        Bindings datasetMap = createJoinScope(datasets);
    
        return new CrossJoinOperation(datasetMap);
    }


    /**
     * Finds the datasets in the context.
     */
    Bindings createJoinScope(List<VTLParser.VarIDContext> varIDContexts) {
        Bindings joinScope = new SimpleBindings();
        Multiset<Component> components = HashMultiset.create();
        
        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        for (VTLParser.VarIDContext varIDContext : varIDContexts) {
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
            Collection<Component> datasetComponents = dataset.getDataStructure().values();
            for (Component component : datasetComponents) {
                joinScope.put(String.format("%s.%s", datasetName, component.getName()), component);
            }
            components.addAll(datasetComponents);
        }
    
        Set<Component> commonIdentifiers = components.entrySet().stream()
                .filter(entry -> entry.getCount() == varIDContexts.size())
                .map(Multiset.Entry::getElement)
                .filter(component -> component.getRole() == Component.Role.IDENTIFIER)
                .collect(Collectors.toSet());
        for (Component component : commonIdentifiers) {
            joinScope.put(component.getName(), component);
        }
        
        return joinScope;
    }

}
