package kohl.hadrien.vtl.script.visitors.join;

import com.google.common.collect.Sets;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.DropOperator;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Visit the drop clauses.
 */
public class JoinDropClauseVisitor extends VTLBaseVisitor<DropOperator> {

    private final Dataset dataset;

    public JoinDropClauseVisitor(Dataset dataset) {
        this.dataset = checkNotNull(dataset, "dataset was null");
    }

    @Override
    public DropOperator visitJoinDropExpression(VTLParser.JoinDropExpressionContext ctx) {
        Set<String> components = Sets.newHashSet();
        for (VTLParser.JoinDropRefContext joinDropRefContext : ctx.joinDropRef()) {
            components.add(joinDropRefContext.getText());
        }
        return new DropOperator(dataset, components);
    }
}
