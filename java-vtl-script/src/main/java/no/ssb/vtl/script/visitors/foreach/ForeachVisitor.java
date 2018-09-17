package no.ssb.vtl.script.visitors.foreach;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.script.VTLDataset;
import no.ssb.vtl.script.operations.foreach.ForeachOperation;
import no.ssb.vtl.script.visitors.AssignmentVisitor;
import no.ssb.vtl.script.visitors.ExpressionVisitor;

import static com.google.common.base.Preconditions.checkNotNull;
import static no.ssb.vtl.parser.VTLParser.RepeatContext;
import static no.ssb.vtl.parser.VTLParser.StatementContext;
import static no.ssb.vtl.parser.VTLParser.VariableContext;

public class ForeachVisitor extends VTLBaseVisitor<ForeachOperation> {

    private final ExpressionVisitor expressionVisitor;

    public ForeachVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = checkNotNull(expressionVisitor);
    }

    @Override
    public ForeachOperation visitRepeat(RepeatContext ctx) {

        ImmutableMap.Builder<String, Dataset> datasets = ImmutableMap.builder();
        for (VariableContext variableCtx : ctx.datasets.variable()) {
            VTLExpression variable = expressionVisitor.visitTypedVariable(
                    variableCtx, VTLDataset.class
            );
            datasets.put(
                    variableCtx.getText(),
                    (Dataset) variable.resolve(expressionVisitor.getBindings()).get()
            );
        }

        // TODO: Handle if only one dataset.
        ImmutableSet.Builder<String> identifiers = ImmutableSet.builder();
        for (VariableContext identifier : ctx.identifiers.variable()) {
            // TODO: handle unescape.
            identifiers.add(identifier.getText());
        }

        ForeachOperation foreachOperation = new ForeachOperation(datasets.build(), identifiers.build());

        foreachOperation.setBlock(bindings -> {
            AssignmentVisitor assignmentVisitor = new AssignmentVisitor(bindings);
            Object last = null;
            for (StatementContext statementContext : ctx.statement()) {
                last = assignmentVisitor.visit(statementContext);
            }
            return VTLDataset.of((Dataset) last);
        });

        return foreachOperation;
    }
}
