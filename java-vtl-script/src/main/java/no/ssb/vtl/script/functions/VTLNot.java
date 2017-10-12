package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLBoolean;

public class VTLNot extends AbstractVTLFunction<VTLBoolean> {
    private static final Argument<VTLBoolean> OPERAND = new Argument<>("operand", VTLBoolean.class);
    private static VTLNot instance;

    private VTLNot() {
        super("not", VTLBoolean.class, OPERAND);
    }

    public static VTLNot getInstance() {
        if (instance == null)
            instance = new VTLNot();
        return instance;
    }

    @Override
    protected VTLBoolean safeInvoke(TypeSafeArguments arguments) {
        VTLBoolean booleanNull = VTLBoolean.of((Boolean) null);
        VTLBoolean operand = arguments.getNullable(OPERAND, booleanNull);
        return operand == booleanNull ? booleanNull : VTLBoolean.of(!operand.get());
    }
}
