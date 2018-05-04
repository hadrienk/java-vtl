package no.ssb.vtl.script.operations.join;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2018 Hadrien Kohl
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

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Queues;
import no.ssb.vtl.model.DataPoint;

import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static com.google.common.base.Preconditions.checkNotNull;

public class InnerJoinSpliterator<L,R, K, O> implements Spliterator<O> {

    private final Comparator<K> predicate;
    private final BiFunction<L, R, O> merger;

    private final Function<L, K> leftKeyExtractor;
    private final Function<R, K> rightKeyExtractor;

    private Deque<L> leftBuffer = Queues.newArrayDeque();
    private Deque<R> rightBuffer = Queues.newArrayDeque();
    private final PeekingIterator<L> leftIterator;
    private final PeekingIterator<R> rightIterator;

    private Iterator<O> output = Collections.emptyIterator();

    public InnerJoinSpliterator(
            Function<L, K> leftKeyExtractor, Function<R, K> rightKeyExtractor, Comparator<K> predicate,
            BiFunction<L, R, O> merger,
            Spliterator<L> leftSpliterator,
            Spliterator<R> rightSpliterator
    ) {
        this.leftKeyExtractor = leftKeyExtractor;
        this.rightKeyExtractor = rightKeyExtractor;
        this.predicate = checkNotNull(predicate);
        this.merger = checkNotNull(merger);

        this.leftIterator = Iterators.peekingIterator(Spliterators.iterator(leftSpliterator));
        this.rightIterator = Iterators.peekingIterator(Spliterators.iterator(rightSpliterator));
    }

    private <I> K advance(PeekingIterator<I> source, Deque<I> buffer, Function<I, K> keyExtractor, Comparator<K> predicate) {
        buffer.clear();

        if (!source.hasNext())
            return null;

        buffer.addLast(source.next());
        K key = keyExtractor.apply(buffer.getFirst());
        while (source.hasNext() && predicate.compare(key, keyExtractor.apply(source.peek())) == 0) {
            buffer.addLast(source.next());
        }
        return key;
    }

    private K advanceRight() {
        return advance(rightIterator, rightBuffer, rightKeyExtractor, predicate);
    }

    private K advanceLeft() {
        return advance(leftIterator, leftBuffer, leftKeyExtractor, predicate);
    }

    @Override
    public void forEachRemaining(Consumer<? super O> action) {
        K leftKey = advanceLeft();
        K rightKey = advanceRight();
        while (!leftBuffer.isEmpty() && !rightBuffer.isEmpty()) {
            int compare = predicate.compare(leftKey, rightKey);
            if (0 < compare) {
                // left > right (right is behind)
                rightKey = advanceRight();
            } else if (compare < 0) {
                // left < right (left is behind)
                leftKey = advanceLeft();
            } else {
                // output hit
                output = new CartesianIterator<>(leftBuffer, rightBuffer, merger);
                output.forEachRemaining(action);
                leftKey = advanceLeft();
                rightKey = advanceRight();
            }
        }
    }

    @Override
    public boolean tryAdvance(Consumer<? super O> action) {
        if (output.hasNext()) {
            action.accept(output.next());
            return true;
        }

        K leftKey = advanceLeft();
        K rightKey = advanceRight();

        while (!leftBuffer.isEmpty() && !rightBuffer.isEmpty()) {
            int compare = predicate.compare(leftKey, rightKey);
            if (0 < compare) {
                // left > right (right is behind)
                rightKey = advanceRight();
            } else if (compare < 0) {
                // left < right (left is behind)
                leftKey = advanceLeft();
            } else {
                // output hit
                output = new CartesianIterator<>(leftBuffer, rightBuffer, merger);
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
