package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLNumber;

public class VTLMultiplication extends AbstractVTLFunction<VTLNumber> {

    private static final Argument<VTLNumber> LEFT = new Argument<>("left", VTLNumber.class);
    private static final Argument<VTLNumber> RIGHT = new Argument<>("right", VTLNumber.class);
    private static VTLMultiplication instance;

    private VTLMultiplication() {
        super("*", VTLNumber.class, LEFT, RIGHT);
    }

    public static VTLMultiplication getInstance() {
        if (instance == null)
            instance = new VTLMultiplication();
        return instance;
    }

    @Override
    protected VTLNumber safeInvoke(TypeSafeArguments arguments) {
        VTLNumber left = arguments.get(LEFT);
        VTLNumber right = arguments.get(RIGHT);
        return left.multiply(right);
    }
}
