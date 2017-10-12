package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLBoolean;

import java.util.Arrays;
import java.util.Collections;

public class VTLXor extends AbstractVTLFunction<VTLBoolean> {
    private static final Argument<VTLBoolean> LEFT = new Argument<>("left", VTLBoolean.class);
    private static final Argument<VTLBoolean> RIGHT = new Argument<>("right", VTLBoolean.class);
    private static VTLXor instance;

    private VTLXor() {
        super("and", VTLBoolean.class, LEFT, RIGHT);
    }

    public static VTLXor getInstance() {
        if (instance == null)
            instance = new VTLXor();
        return instance;
    }

    @Override
    protected VTLBoolean safeInvoke(TypeSafeArguments arguments) {
        VTLBoolean booleanNull = VTLBoolean.of((Boolean) null);
        VTLBoolean left = arguments.getNullable(LEFT, booleanNull);
        VTLBoolean right = arguments.getNullable(RIGHT, booleanNull);

        VTLOr or = VTLOr.getInstance();
        VTLAnd and = VTLAnd.getInstance();
        VTLNot not = VTLNot.getInstance();
        return and.invoke(Arrays.asList(
                    or.invoke(Arrays.asList(
                            left,
                            right)
                    ),
                    not.invoke(Collections.singletonList(
                            and.invoke(Arrays.asList(
                                    left,
                                    right)
                            )))
        ));

    }
}
