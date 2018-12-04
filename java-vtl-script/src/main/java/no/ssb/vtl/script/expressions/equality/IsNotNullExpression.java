package no.ssb.vtl.script.expressions.equality;

import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;

public class IsNotNullExpression extends IsNullExpression {
    public IsNotNullExpression(VTLExpression operand) {
        super(operand);
    }

    @Override
    protected boolean compare(VTLObject leftOperand, VTLObject rightOperand) {
        return !super.compare(leftOperand, rightOperand);
    }
}
