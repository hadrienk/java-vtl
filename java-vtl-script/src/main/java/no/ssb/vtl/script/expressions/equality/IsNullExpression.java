package no.ssb.vtl.script.expressions.equality;

import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.expressions.LiteralExpression;

public class IsNullExpression extends AbstractEqualityExpression {

    public IsNullExpression(VTLExpression operand) {
        super(operand, new LiteralExpression(VTLObject.NULL));
    }

    @Override
    protected boolean compare(VTLObject leftOperand, VTLObject rightOperand) {
        return leftOperand.get() == rightOperand.get();
    }
}
