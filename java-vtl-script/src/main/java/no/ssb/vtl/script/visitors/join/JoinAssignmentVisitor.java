package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.JoinAssignment;
import no.ssb.vtl.script.operations.join.WorkingDataset;
import no.ssb.vtl.script.visitors.ComponentRoleVisitor;
import no.ssb.vtl.script.visitors.ExpressionVisitor;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Optional.ofNullable;

/**
 *
 */
public class JoinAssignmentVisitor extends VTLBaseVisitor<JoinAssignment> {

    private final ExpressionVisitor expressionVisitor;
    private final ComponentRoleVisitor componentRoleVisitor = ComponentRoleVisitor.getInstance();
    private final WorkingDataset workingDataset;

    public JoinAssignmentVisitor(ExpressionVisitor expressionVisitor, WorkingDataset workingDataset) {
        this.expressionVisitor = checkNotNull(expressionVisitor);
        this.workingDataset = workingDataset;
    }



    @Override
    public JoinAssignment visitJoinAssignment(VTLParser.JoinAssignmentContext ctx) {

        VTLExpression2 expression2 = expressionVisitor.visit(ctx.expression());

        Optional<Component.Role> componentRole = ofNullable(componentRoleVisitor.visitComponentRole(ctx.role));
        Boolean implicit = ctx.implicit != null;

        // TODO: Support 'variable' ?
        String componentName = ctx.variable().getText();

        return new JoinAssignment(
                workingDataset,
                expression2,
                componentName,
                componentRole.orElse(Component.Role.MEASURE),
                implicit
        );
    }
}
