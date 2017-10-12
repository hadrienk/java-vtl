package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLNumber;

public class VTLAddition extends AbstractVTLFunction<VTLNumber> {

    private static final Argument<VTLNumber> LEFT = new Argument<>("left", VTLNumber.class);
    private static final Argument<VTLNumber> RIGHT = new Argument<>("right", VTLNumber.class);
    private static VTLAddition instance;

    private VTLAddition() {
        super("+", VTLNumber.class, LEFT, RIGHT);
    }

    public static VTLAddition getInstance() {
        if (instance == null) {
            instance = new VTLAddition();
        }
        return instance;
    }

    @Override
    protected VTLNumber safeInvoke(TypeSafeArguments arguments) {
        VTLNumber left = arguments.get(LEFT);
        VTLNumber right = arguments.get(RIGHT);
        return left.add(right);
    }
}
