package no.ssb.vtl.script.operations;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.script.operations.union.ForwardingStream;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * A wrapper around {@link Stream}<{@link DataPoint}> that includes its
 * relations with other streams and operations.
 */
public class VtlStream extends ForwardingStream<DataPoint> {

    private final AbstractDatasetOperation operation;
    private final ImmutableList<Stream<DataPoint>> parents;
    private final Stream<DataPoint> delegate;
    private final Ordering requestedOrdering;
    private final Filtering requestedFiltering;
    private VtlStream child;

    private Ordering postOrdering;
    private Filtering postFiltering;

    public VtlStream(
            AbstractDatasetOperation operation,
            Stream<DataPoint> delegate,
            Stream<DataPoint> parent,
            Ordering requestedOrdering,
            Filtering requestedFiltering,
            Ordering postOrdering,
            Filtering postFiltering
    ) {
        this(operation, delegate, Collections.singletonList(parent), requestedOrdering, requestedFiltering,
                postOrdering, postFiltering);
    }

    public VtlStream(
            AbstractDatasetOperation operation,
            Stream<DataPoint> delegate,
            Collection<Stream<DataPoint>> parents,
            Ordering requestedOrdering,
            Filtering requestedFiltering,
            Ordering postOrdering,
            Filtering postFiltering
    ) {

        this.parents = ImmutableList.copyOf(parents);

        for (Stream<DataPoint> parent : parents) {
            if (parent instanceof VtlStream) {
                ((VtlStream) parent).child = this;
            }
        }

        this.operation = operation;
        this.requestedOrdering = requestedOrdering;
        this.requestedFiltering = requestedFiltering;
        this.postOrdering = postOrdering;
        this.postFiltering = postFiltering;

        this.delegate = delegate;
    }

    @Override
    protected Stream<DataPoint> delegate() {
        return delegate;
    }

    /**
     * Prints the execution plan.
     */
    public String printPlan() {
        return printPlan(0);
    }

    String printPlan(Integer level) {
        String indentation = Strings.repeat("\t", level);

        String plan = indentation + ">" + operation.getClass().getSimpleName();
        plan = plan + " stream " + Integer.toHexString(delegate.hashCode()) + "\n";

        plan = plan + indentation + "filter :" + requestedFiltering.toString() + "\n";
        plan = plan + indentation + "order  :" + requestedOrdering.toString() + "\n";

        if (postFiltering != null) {
            plan = plan + indentation + "post filter  : " + postFiltering.toString() + "\n";
        }
        if (postOrdering != null) {
            plan = plan + indentation + "post ordering: " + postOrdering.toString() + "\n";
        }

        for (Stream<DataPoint> parent : parents) {
            if (parent instanceof VtlStream) {
                plan = plan + ((VtlStream) parent).printPlan(level + 1) + "\n";
            } else {
                plan = plan + indentation + indentation + "root: " + parent.toString() + "\n";
            }
        }

        return plan;

    }
}
