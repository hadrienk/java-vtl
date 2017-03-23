package no.ssb.vtl.script.operations.hierarchy;

import no.ssb.vtl.model.VTLObject;

import java.util.function.BiFunction;

/**
 * Sum accumulator.
 */
public class SumHierarchyAccumulator implements HierarchyAccumulator {

    @Override
    public VTLObject identity() {
        return VTLObject.of(0);
    }

    @Override
    public BiFunction<? super VTLObject, ? super VTLObject, ? extends VTLObject> accumulator(Composition sign) {
        switch (sign) {
            case UNION:
                return (left, right) -> VTLObject.of((Integer) left.get() + (Integer) right.get());
            case COMPLEMENT:
                return (left, right) -> VTLObject.of((Integer) left.get() + -1 * (Integer) right.get());
            default:
                throw new IllegalArgumentException(String.format("unknown sign %s", sign));
        }
    }
}
