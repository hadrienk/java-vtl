package no.ssb.vtl.script.expressions.equality;

import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.expressions.LiteralExpression;

import javax.script.Bindings;

public class IsNullExpression extends AbstractEqualityExpression {

    public IsNullExpression(VTLExpression operand) {
        super(operand, new LiteralExpression(VTLObject.NULL));
    }

    @Override
    public VTLObject resolve(Bindings bindings) {
        // Override since is null needs to bypass the normal null propagation.
        VTLObject leftOperand = getLeftOperand().resolve(bindings);
        return VTLBoolean.of(compare(leftOperand, null));
    }

    @Override
    protected boolean compare(VTLObject leftOperand, VTLObject rightOperand) {
        return leftOperand == VTLObject.NULL || leftOperand.get() == null;
    }
}
