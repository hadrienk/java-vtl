package no.ssb.vtl.script.operations;

import com.google.common.collect.ImmutableList;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.script.operations.union.ForwardingStream;

import java.util.Collection;
import java.util.Collections;
import java.util.ListIterator;
import java.util.stream.Stream;

/**
 * A wrapper around {@link Stream}<{@link DataPoint}> that includes its
 * relations with other streams and operations.
 */
public class VtlStream extends ForwardingStream<DataPoint> {

    // Used by printPlan.
    private static final String H_TEE = "─┬─";
    private static final String V_BAR = " │ ";
    private static final String V_TEE = " ├─";
    private static final String V_END = " └─";
    private static final String H_BAR = "───";
    private static final String SPACE = "   ";
    private final AbstractDatasetOperation operation;
    private final ImmutableList<Stream<DataPoint>> parents;
    private final Stream<DataPoint> delegate;
    private final Ordering requestedOrdering;
    private final Filtering requestedFiltering;
    private VtlStream child;
    private Ordering actualOrdering;
    private Filtering actualFiltering;
    private Statistics statistics;

    public VtlStream(
            AbstractDatasetOperation operation,
            Stream<DataPoint> delegate,
            Stream<DataPoint> parent,
            Ordering requestedOrdering,
            Filtering requestedFiltering,
            Ordering actualOrdering,
            Filtering actualFiltering
    ) {
        this(operation, delegate, Collections.singletonList(parent), requestedOrdering, requestedFiltering,
                actualOrdering, actualFiltering);
    }

    public VtlStream(
            AbstractDatasetOperation operation,
            Stream<DataPoint> delegate,
            Collection<Stream<DataPoint>> parents,
            Ordering requestedOrdering,
            Filtering requestedFiltering,
            Ordering actualOrdering,
            Filtering actualFiltering
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
        this.actualOrdering = actualOrdering;
        this.actualFiltering = actualFiltering;

        this.delegate = delegate;
    }

    public VtlStream getChild() {
        return child;
    }

    public Ordering getOrdering() {
        return requestedOrdering;
    }

    public Filtering getFiltering() {
        return requestedFiltering;
    }

    public AbstractDatasetOperation getOperation() {
        return operation;
    }

    public ImmutableList<Stream<DataPoint>> getParents() {
        return parents;
    }

    @Override
    protected Stream<DataPoint> delegate() {
        return delegate;
    }

    /**
     * Prints the execution plan.
     */
    public String printPlan() {
        return printPlan("");
    }

    String printPlan(String prefix) {

        // TODO: Use StringBuilder or StringBuffer.
        String start = parents.isEmpty() ? H_BAR : H_TEE;

        String opString = String.format(" %s (stream %h)\n", operation.getClass().getSimpleName(), delegate.hashCode());
        String filter = String.format("  filter: %s\n", requestedFiltering.toString());
        String order = String.format("  order : %s\n", requestedOrdering.toString());

        String result = start + opString;
        result = result + prefix + V_BAR + filter;
        result = result + prefix + V_BAR + order;

        ListIterator<Stream<DataPoint>> parentIterator = parents.listIterator();
        while (parentIterator.hasNext()) {
            Stream<DataPoint> parent = parentIterator.next();
            String newPrefix = parentIterator.hasNext() ? V_TEE : V_END;
            String newSpace = parentIterator.hasNext() ? V_BAR : SPACE;
            if (parent instanceof VtlStream) {
                result = result + prefix + newPrefix + ((VtlStream) parent).printPlan(prefix + newSpace);
            } else {
                // TODO: Wrap connector as well.
                result = result + prefix + newPrefix + " Connector\n";
            }
        }
        return result;
    }
}
