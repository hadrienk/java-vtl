package no.ssb.vtl.script.visitors.join;

import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.KeepOperator;

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
        for (VTLParser.ComponentRefContext componentRef : ctx.componentRef()) {
            components.add(componentRef.getText());
        }
        return new KeepOperator(dataset, components.build());
    }
}
