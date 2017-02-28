package no.ssb.vtl.script.support;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class JoinSpliterator<L, R, K, O> implements Spliterator<O> {

    final private Comparator<K> comparator;
    final private Spliterator<L> left;
    final private Spliterator<R> right;
    final private Function<L, K> leftKey;
    final private Function<R, K> rightKey;
    final private TriFunction<L, R, Integer, List<O>> compute;
    private boolean hadLeft = false;
    private boolean hadRight = false;
    private Pair pair = null;

    public JoinSpliterator(
            Comparator<K> comparator,
            Spliterator<L> left,
            Spliterator<R> right,
            Function<L, K> leftKey,
            Function<R, K> rightKey,
            TriFunction<L, R, Integer, List<O>> compute) {
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

        if (!hadLeft && !hadRight)
            return false;

        int compare = 0;
        if (!hadLeft && hadRight) {
            compare = 1;
        } else if (hadLeft && !hadRight) {
            compare = -1;
        } else {
            compare = comparator.compare(
                    leftKey.apply(pair.left), rightKey.apply(pair.right)
            );
        }

        // TODO: Might be not needed to return a list.
        List<? extends O> apply = compute.apply(pair.left, pair.right, compare);
        if (apply != null) {
            for (O o : apply) {
                action.accept(o);
            }
        }

        if (compare == 0) {
            hadLeft = advanceLeft();
            hadRight = advanceRight();
        } else if (compare < 0) {
            hadLeft = advanceLeft();
        } else /* if (compare > 0) */ {
            hadRight = advanceRight();
        }
        return true;

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

    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {

        R apply(A a, B b, C c);

        default <V> TriFunction<A, B, C, V> andThen(
                Function<? super R, ? extends V> after) {
            return (A a, B b, C c) -> after.apply(apply(a, b, c));
        }
    }

    private class Pair {
        L left;
        R right;
    }
}
