package no.ssb.vtl.script.operations.hierarchy;

import no.ssb.vtl.model.VTLObject;

import java.util.function.BiFunction;

/**
 * Accumulator used in the hierarchy aggregation.
 */
public interface HierarchyAccumulator<T> {

    public HierarchyAccumulator SUM = new SumHierarchyAccumulator();
    public HierarchyAccumulator PRODUCT = new ProductHierarchyAccumulator();

    VTLObject<T> identity();

    BiFunction<? super VTLObject, ? super VTLObject, ? extends VTLObject> accumulator(Composition sign);

}
