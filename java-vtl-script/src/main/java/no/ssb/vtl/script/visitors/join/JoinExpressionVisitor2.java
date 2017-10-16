package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.script.visitors.ExpressionVisitor;

import javax.script.Bindings;

/**
 * Special type of expression visitor that handles membership inside a join body.
 */
public class JoinExpressionVisitor2 extends ExpressionVisitor {
    public JoinExpressionVisitor2(Bindings scope) {
        super(scope);
    }

//    @Override
//    public VTLExpression2 visitVariable(VTLParser.VariableContext ctx) {
//        String identifier = ctx.getText();
//
//        // Unescape.
//        if (identifier.startsWith("\'") && identifier.endsWith("\'")) {
//            identifier = identifier.substring(1, identifier.length() - 1);
//        }
//
//
//        VTLExpression2 expression2 = super.visitVariable(ctx);
//    }
//
//    @Override
//    public VTLExpression2 visitMembershipExpression(VTLParser.MembershipExpressionContext ctx) {
//        if (ctx.leff)
//    }
}
