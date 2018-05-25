package no.ssb.vtl.script.operations.aggregation;

import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLTyped;

import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractAggregationFunction<T extends VTLObject> implements VTLTyped<T>, Function<List<VTLNumber>, T> {

    private final Class<T> clazz;

    public AbstractAggregationFunction(Class<T> clazz) {
        this.clazz = checkNotNull(clazz);
    }

    @Override
    public Class<T> getVTLType() {
        return clazz;
    }
}
