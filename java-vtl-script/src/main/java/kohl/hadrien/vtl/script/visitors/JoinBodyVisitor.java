package kohl.hadrien.vtl.script.visitors;

import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.join.AbstractJoinOperation;
import kohl.hadrien.vtl.script.visitors.join.JoinCalcClauseVisitor;

import java.util.Collections;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Visitor for the join clauses.
 * TODO: This must trigger the refactoring to DataPoints.
 */
public class JoinBodyVisitor extends VTLBaseVisitor<Void> {

    private final AbstractJoinOperation joinOperation;
    private final JoinCalcClauseVisitor calcClauseVisitor;


    public JoinBodyVisitor(AbstractJoinOperation joinOperation) {
        this.joinOperation = checkNotNull(joinOperation);

        calcClauseVisitor = new JoinCalcClauseVisitor(Collections.emptyMap());
        // TODO:
        //joinFilter
        //joinKeep
        //joinRename
        //joinDrop
        //joinUnfold
        //joinFold
    }

    @Override
    public Void visitJoinCalcClause(VTLParser.JoinCalcClauseContext ctx) {
        calcClauseVisitor.visit(ctx);
        return null;
    }
}
