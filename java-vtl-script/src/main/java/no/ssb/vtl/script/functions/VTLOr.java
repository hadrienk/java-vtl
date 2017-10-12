package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLBoolean;

public class VTLOr extends AbstractVTLFunction<VTLBoolean> {

    private static final Argument<VTLBoolean> LEFT = new Argument<>("left", VTLBoolean.class);
    private static final Argument<VTLBoolean> RIGHT = new Argument<>("right", VTLBoolean.class);
    private static VTLOr instance;

    public static VTLOr getInstance() {
        if (instance == null)
            instance = new VTLOr();
        return instance;
    }

    private VTLOr() {
        super("and", VTLBoolean.class, LEFT, RIGHT);
    }

    @Override
    protected VTLBoolean safeInvoke(TypeSafeArguments arguments) {
        VTLBoolean booleanNull = VTLBoolean.of((Boolean) null);
        VTLBoolean left = arguments.getNullable(LEFT, booleanNull);
        VTLBoolean right = arguments.getNullable(RIGHT, booleanNull);

        if (left != booleanNull) {
            if (left.get()) {
                return VTLBoolean.of(true);
            } else {
                if (right != booleanNull) {
                    return VTLBoolean.of(left.get() || right.get());
                } else {
                    return booleanNull;
                }
            }
        } else {
            if (right != booleanNull) {
                if (right.get()) {
                    return VTLBoolean.of(true);
                } else {
                    return booleanNull;
                }
            }
            return booleanNull;
        }
    }
}
