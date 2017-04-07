package no.ssb.vtl.script.operations.hierarchy;

import no.ssb.vtl.model.VTLObject;

import java.util.function.BiFunction;

/**
 * Product accumulator.
 */
public class ProductHierarchyAccumulator implements HierarchyAccumulator {

    @Override
    public VTLObject identity() {
        return VTLObject.of(0);
    }

    @Override
    public BiFunction<? super VTLObject, ? super VTLObject, ? extends VTLObject> accumulator(Composition sign) {
        switch (sign) {
            case UNION:
                return (left, right) -> VTLObject.of((Long) left.get() * (Long) right.get());
            case COMPLEMENT:
                return (left, right) -> VTLObject.of((Long) left.get() * -1 * (Long) right.get());
            default:
                throw new IllegalArgumentException(String.format("unknown sign %s", sign));
        }
    }
}
