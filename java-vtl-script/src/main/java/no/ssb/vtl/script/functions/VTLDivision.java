package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLNumber;

public class VTLDivision extends AbstractVTLFunction<VTLNumber> {

    private static final Argument<VTLNumber> LEFT = new Argument<>("left", VTLNumber.class);
    private static final Argument<VTLNumber> RIGHT = new Argument<>("right", VTLNumber.class);
    private static VTLDivision instance;

    private VTLDivision() {
        super("/", VTLNumber.class, LEFT, RIGHT);
    }

    public static VTLDivision getInstance() {
        if (instance == null)
            instance = new VTLDivision();
        return instance;
    }

    @Override
    protected VTLNumber safeInvoke(TypeSafeArguments arguments) {
        VTLNumber left = arguments.get(LEFT);
        VTLNumber right = arguments.get(RIGHT);
        return left.divide(right);
    }
}

