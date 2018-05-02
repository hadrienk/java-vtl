package no.ssb.vtl.script.operations.join;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Queues;

import java.util.Comparator;
import java.util.Deque;
import java.util.Spliterator;
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

    public InnerJoinSpliterator(Comparator<I> predicate, BiFunction<I, I, O> merger, Spliterator<I> leftSpliterator, Spliterator<I> rightSpliterator) {
        this.predicate = checkNotNull(predicate);
        this.merger = checkNotNull(merger);
        this.leftIterator = Iterators.peekingIterator(new SpliteratorIterator<>(leftSpliterator));
        this.rightIterator = Iterators.peekingIterator(new SpliteratorIterator<>(rightSpliterator));
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
    public boolean tryAdvance(Consumer<? super O> action) {
        advanceLeft();
        advanceRight();

        while (!leftBuffer.isEmpty() && !rightBuffer.isEmpty()) {
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
            } else {
                // output hit
                break;
            }
        }

        for (I left : leftBuffer) {
            for (I right : rightBuffer) {
                action.accept(merger.apply(left, right));
            }
        }

        return !leftBuffer.isEmpty() || !rightBuffer.isEmpty();
    }

    @Override
    public Spliterator<O> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
