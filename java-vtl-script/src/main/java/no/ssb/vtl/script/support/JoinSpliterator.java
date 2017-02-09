package no.ssb.vtl.script.support;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class JoinSpliterator<L, R, K, O> implements Spliterator<O> {

    final private Comparator<K> comparator;
    final private Spliterator<L> left;
    final private Spliterator<R> right;
    final private Function<L, K> leftKey;
    final private Function<R, K> rightKey;
    final private BiFunction<L, R, O> compute;
    private boolean hadLeft = false;
    private boolean hadRight = false;
    private Pair pair = null;

    public JoinSpliterator(Comparator<K> comparator, Spliterator<L> left, Spliterator<R> right, Function<L, K> leftKey, Function<R, K> rightKey, BiFunction<L, R, O> compute) {
        this.comparator = comparator;
        this.right = right;
        this.left = left;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.compute = compute;
    }

    boolean advanceLeft() {
        return left.tryAdvance(v -> pair.left = v);
    }

    boolean advanceRight() {
        return right.tryAdvance(v -> pair.right = v);
    }

    @Override
    public boolean tryAdvance(Consumer<? super O> action) {

        if (pair == null) {
            pair = new Pair();
            hadLeft = advanceLeft();
            hadRight = advanceRight();
        }

        while (hadLeft || hadRight) {
            int compare = comparator.compare(
                    leftKey.apply(pair.left), rightKey.apply(pair.right)
            );
            if (compare == 0) {
                // generate.
                action.accept(compute.apply(pair.left, pair.right));
                hadLeft = advanceLeft();
                hadRight = advanceRight();
                return true;
            } else if (compare < 0) {
                hadLeft = advanceLeft();
                hadRight = true;
            } else /* if (compare > 0) */ {
                hadRight = advanceRight();
                hadLeft = true;
            }
        }
        return false;
    }

    @Override
    public Spliterator<O> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.max(left.estimateSize(), right.estimateSize());
    }

    @Override
    public int characteristics() {
        return 0;
    }

    private class Pair {
        L left;
        R right;
    }
}
