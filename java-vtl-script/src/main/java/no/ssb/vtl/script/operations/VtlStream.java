package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.DataPoint;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Stream provider that represents how the data stream is computed.
 */
public class VtlStream implements Supplier<Stream<DataPoint>> {

    private final AbstractDatasetOperation operation;
    private final List<VtlStream> parents;

    public VtlStream(
            AbstractDatasetOperation operation,
            List<VtlStream> parents
    ) {
        this.operation = operation;
        this.parents = parents;
    }

    public AbstractDatasetOperation getOperation() {
        return operation;
    }

    public List<VtlStream> getParents() {
        return parents;
    }

    @Override
    public Stream<DataPoint> get() {
        return null;
    }
}
