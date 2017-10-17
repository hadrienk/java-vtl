package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.VTLDataset;
import no.ssb.vtl.script.visitors.ExpressionVisitor;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Special type of expression visitor that handles variable and membership resolution
 * against
 */
@Deprecated
public class JoinExpressionVisitor2 extends ExpressionVisitor {
    public JoinExpressionVisitor2(Bindings scope) {
        super(scope);
    }

    @Override
    public VTLExpression2 visitMembershipExpression(VTLParser.MembershipExpressionContext ctx) {
        VTLExpression2 leftVariable = visit(ctx.leff);
        VTLObject resolved = leftVariable.resolve(getBindings());
        checkArgument(resolved instanceof VTLDataset,
                "[%s] was not a dataset", ctx.leff.getText());

        Dataset dataset = ((VTLDataset) resolved).get();
        dataset.getDataStructure();


        return super.visitMembershipExpression(ctx);
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
