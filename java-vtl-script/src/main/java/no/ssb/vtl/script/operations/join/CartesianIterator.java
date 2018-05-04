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

