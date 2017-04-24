package no.ssb.vtl.script.operations.hierarchy;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;

import java.util.Map;
import java.util.function.BiFunction;

import static com.google.common.base.Preconditions.*;

/**
 * Sum accumulator.
 */
public class SumHierarchyAccumulator implements HierarchyAccumulator {

    private static Map<Class<?>, Object> TYPEMAP = ImmutableMap.<Class<?>, Object>builder()
            .put(Long.class, 0L)
            .put(Double.class, 0D)
            .put(Number.class, 0D)
            .build();

    private final Number identity;

    public SumHierarchyAccumulator(Class<?> type) {
        // TODO: Change when the type system supports more.
        identity = checkNotNull((Number) TYPEMAP.get(type));
    }

    @Override
    public VTLObject identity() {
        return VTLNumber.of(identity);
    }

    @Override
    public BiFunction<? super VTLObject, ? super VTLObject, ? extends VTLObject> accumulator(Composition sign) {
        switch (sign) {
            case UNION:
                return (left, right) -> {
                    // TODO: Change when the type system supports more.
                    VTLNumber leftNumber = VTLNumber.of((Number) left.get());
                    VTLNumber rightNumber = VTLNumber.of((Number) right.get());
                    return VTLObject.of(leftNumber.add(rightNumber.get()));
                };
            case COMPLEMENT:
                return (left, right) -> {
                    // TODO: Change when the type system supports more.
                    VTLNumber leftNumber = VTLNumber.of((Number) left.get());
                    VTLNumber rightNumber = VTLNumber.of((Number) right.get());
                    return VTLObject.of(leftNumber.subtract(rightNumber.get()));
                };
            default:
                throw new IllegalArgumentException(String.format("unknown sign %s", sign));
        }
    }
}
