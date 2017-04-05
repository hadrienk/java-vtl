package no.ssb.vtl.script.support;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

public class JoinSpliterator<L, R, K, O> implements Spliterator<O> {

    final private Comparator<K> comparator;
    final private Spliterator<L> leftSpliterator;
    final private Spliterator<R> rightSpliterator;
    final private Function<L, K> leftKey;
    final private Function<R, K> rightKey;
    final private BiFunction<L, R, O> compute;

    private Cursor cursor = null;

    public JoinSpliterator(
            Comparator<K> comparator,
            Spliterator<L> left,
            Spliterator<R> right,
            Function<L, K> leftKey,
            Function<R, K> rightKey,
            BiFunction<L, R, O> compute) {
        this.comparator = comparator;
        this.rightSpliterator = right;
        this.leftSpliterator = left;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.compute = compute;
    }

    @Override
    public boolean tryAdvance(Consumer<? super O> action) {

        if (cursor == null) {
            cursor = new Cursor();
            cursor.leftBuf.next();
            cursor.rightBuf.next();
        }

        Buffer<L> lb = cursor.leftBuf;
        Buffer<R> rb = cursor.rightBuf;

        if (!lb.hasMore() || !rb.hasMore()) {

            // Handle end conditions.
            while (!lb.isEmpty() && lb.next())
                output(lb.pop(), null, action);
            while (!rb.isEmpty() && rb.next())
                output(null, rb.pop(), action);
            return false;
        }

        if (lb.isEmpty() && lb.next())
            return rb.hasMore();

        if (rb.isEmpty() && rb.next())
            return rb.hasMore();

        int compare = compare(lb.current(), rb.current());

        // TODO: Use a consumer in the merge function?

        if (compare == 0) {

            // Replay left and right for outer joins.
            while (lb.first() != lb.current())
                output(lb.pop(), null, action);
            while (rb.first() != rb.current())
                output(null, rb.pop(), action);


            List<L> left = Lists.newArrayList(lb.pop());
            List<R> right = Lists.newArrayList(rb.pop());

                /* Check if the next tuples are identical */

            while (lb.next() && compare(lb.current(), right.get(0)) == 0)
                left.add(lb.pop());

            while (rb.next() && compare(left.get(0), rb.current()) == 0)
                right.add(rb.pop());

            for (int i = 0; i < left.size(); i++) {
                for (int j = 0; j < right.size(); j++) {
                    output(left.get(i), right.get(j), action);
                }
            }

        } else {
            if (compare > 0) {
                // left > right (right is behind)
                rb.next();
            } else {
                // left < right (left is behind)
                lb.next();
            }
        }
        return true;

    }

    private int compare(L left, R right) {
        return comparator.compare(leftKey.apply(left), rightKey.apply(right));
    }

    private void output(L left, R right, Consumer<? super O> destination) {
        O apply = compute.apply(left, right);
        if (apply != null)
            destination.accept(apply);
    }

    @Override
    public Spliterator<O> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.max(leftSpliterator.estimateSize(), rightSpliterator.estimateSize());
    }

    @Override
    public int characteristics() {
        return 0;
    }

    private class Buffer<T> extends ArrayList<T> {

        private final Spliterator<T> source;
        private boolean end = false;
        private int pos = -1;

        private Buffer(Spliterator<T> source) {
            this.source = checkNotNull(source);
        }

        public boolean hasMore() {
            return !end;
        }

        public boolean next() {
            if (++pos >= size()) {
                end = !source.tryAdvance(this::add);
                if (end)
                    pos--;
            }
            return !end || !isEmpty();
        }

        public T current() {
            if (pos < 0 || size() <= pos)
                return null;
            return get(pos);
        }

        public T first() {
            if (isEmpty())
                return null;
            return get(0);
        }

        public T pop() {
            if (isEmpty())
                return null;
            return remove(0);
        }

        @Override
        public T remove(int index) {
            T removed = super.remove(index);
            if (index <= pos)
                pos--;
            return removed;
        }

    }

    private class Cursor {
        Buffer<L> leftBuf = new Buffer<>(leftSpliterator);
        Buffer<R> rightBuf = new Buffer<>(rightSpliterator);
    }
}
