package no.ssb.vtl.script.support;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

public class JoinSpliterator<L, R, K, O> implements Spliterator<O> {

    final private Comparator<K> comparator;
    final private Function<L, K> leftKey;
    final private Function<R, K> rightKey;
    final private BiFunction<L, R, O> compute;

    final private Buffer<L> lb;
    final private Buffer<R> rb;

    private final long size;

    private boolean initialized = false;

    public JoinSpliterator(
            Comparator<K> comparator,
            Spliterator<L> left,
            Spliterator<R> right,
            Function<L, K> leftKey,
            Function<R, K> rightKey,
            BiFunction<L, R, O> compute) {
        this.comparator = comparator;
        this.lb = new Buffer<>(left);
        this.rb = new Buffer<>(right);
        this.size = Long.max(left.estimateSize(), right.estimateSize());
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.compute = compute;
    }

    @Override
    public boolean tryAdvance(Consumer<? super O> action) {

        // Needed to avoid starting the stream too early.
        if (!initialized) {
            lb.next();
            rb.next();
            initialized = true;
        }

        // one buffer reached the end.
        if (!(lb.hasMore() && rb.hasMore())) {
            // empty both buffers
            outputAll(action);
            return false;
        }

        if (lb.isEmpty() && lb.next())
            return rb.hasMore();

        if (rb.isEmpty() && rb.next())
            return rb.hasMore();

        // TODO: Use a consumer in the merge function?

        int compare = compare(lb.current(), rb.current());
        if (0 < compare) {
            rb.next(); // left > right (right is behind)
        } else if (compare < 0) {
            lb.next(); // left < right (left is behind)
        } else {
            outputMiss(action); // output left(s) and right(s) we missed
            outputHit(action); // output hit(s)
        }

        return true;

    }

    private void outputAll(Consumer<? super O> action) {
        while (!lb.isEmpty() && lb.next())
            output(lb.pop(), null, action);
        while (!rb.isEmpty() && rb.next())
            output(null, rb.pop(), action);
    }

    private void outputHit(Consumer<? super O> action) {

        List<L> left = Lists.newArrayList(lb.pop());
        List<R> right = Lists.newArrayList(rb.pop());

        // check if the next tuples are identical
        while (lb.next() && compare(lb.current(), right.get(0)) == 0)
            left.add(lb.pop());

        while (rb.next() && compare(left.get(0), rb.current()) == 0)
            right.add(rb.pop());

        // cartesian product
        for (L l : left) {
            for (R r : right) {
                output(l, r, action);
            }
        }
    }

    private void outputMiss(Consumer<? super O> action) {
        while (lb.first() != lb.current())
            output(lb.pop(), null, action);
        while (rb.first() != rb.current())
            output(null, rb.pop(), action);
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
        // prevent split
        return null;
    }

    @Override
    public long estimateSize() {
        return this.size;
    }

    @Override
    public int characteristics() {
        return 0;
    }

    private class Buffer<T> extends ArrayList<T> {

        private static final long serialVersionUID = -1744403577043659072L;

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
            if (!end)
                end = !source.tryAdvance(this::add);
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

        @Override
        public boolean add(T t) {
            pos++;
            return super.add(t);
        }

        @Override
        public boolean addAll(int index, Collection<? extends T> c) {
            pos = pos + c.size();
            return super.addAll(index, c);
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            pos = pos + c.size();
            return super.addAll(c);
        }

        @Override
        public void add(int index, T element) {
            pos++;
            super.add(index, element);
        }

        @Override
        public void clear() {
            pos = -1;
            super.clear();
        }
    }
}
