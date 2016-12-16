package kohl.hadrien.vtl.script.visitors.join;

import com.google.common.collect.ImmutableSet;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.KeepOperator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Visit the keep clauses.
 */
public class JoinKeepClauseVisitor  extends VTLBaseVisitor<KeepOperator> {

    private final Dataset dataset;

    public JoinKeepClauseVisitor(Dataset dataset) {
        this.dataset = checkNotNull(dataset);
    }

    @Override
    public KeepOperator visitJoinKeepExpression(VTLParser.JoinKeepExpressionContext ctx) {
        ImmutableSet.Builder<String> components = ImmutableSet.builder();
        for (VTLParser.JoinKeepRefContext joidKeepRefContext : ctx.joinKeepRef()) {
            components.add(joidKeepRefContext.getText());
        }
        return new KeepOperator(dataset, components.build());
    }
}
