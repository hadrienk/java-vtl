package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.Ordering;

import java.util.List;
import java.util.Spliterator;

/**
 * Subclass of {@link Spliterator} that includes Filtering, Ordering and
 * a the relation of the stream.
 */
public abstract class VtlSpliterator implements Spliterator<DataPoint> {

    private final AbstractDatasetOperation operation;
    private final List<VtlSpliterator> parents;
    private VtlSpliterator child;

    public VtlSpliterator(
            AbstractDatasetOperation operation,
            List<VtlSpliterator> parents
    ) {
        this.operation = operation;
        this.parents = parents;
        for (VtlSpliterator parent : parents) {
            parent.child = this;
        }
    }

    public AbstractDatasetOperation getOperation() {
        return operation;
    }

    public List<VtlSpliterator> getParents() {
        return parents;
    }

    public VtlSpliterator getChild() {
        return child;
    }

    abstract Filtering getFiltering();

    abstract Ordering getOrdering();


}
