package no.ssb.vtl.script.visitors.join;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import me.yanaga.guava.stream.MoreCollectors;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.CrossJoinOperation;
import no.ssb.vtl.script.operations.join.InnerJoinOperation;
import no.ssb.vtl.script.operations.join.OuterJoinOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import javax.script.Bindings;
import javax.script.ScriptContext;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Visitor that handle the join definition
 */
public class JoinDefinitionVisitor extends VTLBaseVisitor<AbstractJoinOperation> {

    private final ReferenceVisitor referenceVisitor;

    public JoinDefinitionVisitor(ScriptContext context) {
        checkNotNull(context);
        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        referenceVisitor = new ReferenceVisitor(bindings);
    }

    private Dataset getDataset(VTLParser.DatasetRefContext ref) {
        Object referencedObject = referenceVisitor.visit(ref);
        return (Dataset) referencedObject; //TODO: Is this always safe? Hadrien: Yes, DatasetRefContext and ComponentRefContext will return the correct type
    }

    private ImmutableMap<String, Dataset> extractDatasets(List<VTLParser.DatasetRefContext> ctx) {
        return ctx.stream()
                    .collect(MoreCollectors.toImmutableMap(
                            // TODO: Need to support alias here. The spec forgot it.
                            datasetRefContext -> datasetRefContext.variableRef().identifier().getText(),
                            datasetRefContext -> (Dataset) referenceVisitor.visit(datasetRefContext)
                    ));
    }

    private ImmutableSet<Component> extractIdentifierComponents(List<VTLParser.ComponentRefContext> ctx) {
        return ctx.stream()
                    .map(componentRefContext -> (Component) referenceVisitor.visit(componentRefContext))
                    .collect(MoreCollectors.toImmutableSet());
    }

    @Override
    public AbstractJoinOperation visitJoinDefinition(VTLParser.JoinDefinitionContext ctx) {

        Integer joinType = Optional.ofNullable(ctx.type.getType()).orElse(VTLParser.INNER);
        ImmutableMap<String, Dataset> datasets = extractDatasets(ctx.datasetRef());
        ImmutableSet<Component> identifiers = extractIdentifierComponents(ctx.componentRef());

        switch (ctx.type.getType()) {
            case VTLParser.INNER:
                return new InnerJoinOperation(datasets, identifiers);
            case VTLParser.OUTER:
                return new OuterJoinOperation(datasets, identifiers);
            case VTLParser.CROSS:
                return new CrossJoinOperation(datasets, identifiers);

        }
        return super.visitJoinDefinition(ctx);
    }
}
