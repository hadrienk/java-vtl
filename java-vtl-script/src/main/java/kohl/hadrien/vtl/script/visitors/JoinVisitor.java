package kohl.hadrien.vtl.script.visitors;

import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.join.AbstractJoinOperation;
import kohl.hadrien.vtl.script.visitors.join.JoinDefinitionVisitor;

import javax.script.ScriptContext;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A visitor that handles the joins.
 */
public class JoinVisitor extends VTLBaseVisitor<AbstractJoinOperation> {


    private final ScriptContext context;
    private final JoinDefinitionVisitor joinDefinitionVisitor;

    public JoinVisitor(ScriptContext context) {
        this.context = checkNotNull(context);
        joinDefinitionVisitor = new JoinDefinitionVisitor(context);
    }

    @Override
    public AbstractJoinOperation visitJoinExpression(VTLParser.JoinExpressionContext ctx) {

        AbstractJoinOperation dataset = joinDefinitionVisitor.visit(ctx.joinDefinition());

        // Sets up the clauses.
        JoinBodyVisitor clauseVisitor = new JoinBodyVisitor(dataset);
        clauseVisitor.visit(ctx.joinBody());

        return dataset;
    }
}
