package no.ssb.vtl.script.operations.join;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.Iterator;
import java.util.function.BiFunction;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Iterates over Cartesian product of two Iterable.
 */
public class CartesianIterator<L, R, O> extends AbstractIterator<O> {

    private final Iterable<R> rightIterable;
    private final BiFunction<L, R, O> merger;

    private PeekingIterator<L> leftIterator;
    private Iterator<R> rightIterator;

    public CartesianIterator(Iterable<L> leftIterable, Iterable<R> rightIterable, BiFunction<L, R, O> merger) {
        this.rightIterable = checkNotNull(rightIterable);
        this.merger = checkNotNull(merger);

        leftIterator = Iterators.peekingIterator(checkNotNull(leftIterable).iterator());
        rightIterator = rightIterable.iterator();
    }

    @Override
    protected O computeNext() {
        if (!rightIterator.hasNext()) {
                rightIterator = rightIterable.iterator();
        }
        if (leftIterator.hasNext() && rightIterator.hasNext()) {
            R rightValue = rightIterator.next();
            L leftValue = rightIterator.hasNext() ? leftIterator.peek() : leftIterator.next();
            return merger.apply(leftValue, rightValue);
        } else {
            return endOfData();
        }
    }
}

