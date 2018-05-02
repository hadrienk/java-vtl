package no.ssb.vtl.script.operations.join;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Queues;

import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

public class InnerJoinSpliterator<I, O> implements Spliterator<O> {

    private final Comparator<I> predicate;
    private final BiFunction<I, I, O> merger;

    private Deque<I> leftBuffer = Queues.newArrayDeque();
    private Deque<I> rightBuffer = Queues.newArrayDeque();
    private final PeekingIterator<I> leftIterator;
    private final PeekingIterator<I> rightIterator;

    private Iterator<O> output = Collections.emptyIterator();

    public InnerJoinSpliterator(Comparator<I> predicate, BiFunction<I, I, O> merger, Spliterator<I> leftSpliterator, Spliterator<I> rightSpliterator) {
        this.predicate = checkNotNull(predicate);
        this.merger = checkNotNull(merger);
        this.leftIterator = Iterators.peekingIterator(Spliterators.iterator(leftSpliterator));
        this.rightIterator = Iterators.peekingIterator(Spliterators.iterator(rightSpliterator));
    }

    private void advance(PeekingIterator<I> source, Deque<I> buffer) {
        buffer.clear();

        if (!source.hasNext())
            return;

        do {
            buffer.addLast(source.next());
        } while (source.hasNext() && predicate.compare(buffer.getFirst(), source.peek()) == 0);
    }

    private void advanceRight() {
        advance(rightIterator, rightBuffer);
    }

    private void advanceLeft() {
        advance(leftIterator, leftBuffer);
    }

    @Override
    public void forEachRemaining(Consumer<? super O> action) {
        advanceLeft();
        advanceRight();
        while (!leftBuffer.isEmpty() && !rightBuffer.isEmpty()) {
            if (compare()) {
                // output hit
                flushBuffers(action);
                advanceLeft();
                advanceRight();
            }
        }
    }

    private boolean compare() {
        int compare = predicate.compare(
                leftBuffer.getFirst(),
                rightBuffer.getFirst()
        );
        if (0 < compare) {
            // left > right (right is behind)
            advanceRight();
        } else if (compare < 0) {
            // left < right (left is behind)
            advanceLeft();
        }
        return compare == 0;
    }

    private void flushBuffers(Consumer<? super O> action) {
        for (I left : leftBuffer) {
            for (I right : rightBuffer) {
                action.accept(merger.apply(left, right));
            }
        }
    }

    @Override
    public boolean tryAdvance(Consumer<? super O> action) {
        if (output.hasNext()) {
            action.accept(output.next());
            return true;
        }

        advanceLeft();
        advanceRight();

        while (!leftBuffer.isEmpty() && !rightBuffer.isEmpty()) {
            if (compare()) {
                // output hit
                output = Iterators.transform(
                        new CartesianIterator<>(leftBuffer, rightBuffer),
                        input -> merger.apply(input.get(0), input.get(1))
                );
                return tryAdvance(action);
            }
        }

        return !leftBuffer.isEmpty() && !rightBuffer.isEmpty();
    }

    @Override
    public Spliterator<O> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return Spliterator.SORTED & Spliterator.ORDERED;
    }
}
