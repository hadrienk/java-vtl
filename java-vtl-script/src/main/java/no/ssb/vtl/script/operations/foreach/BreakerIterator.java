package no.ssb.vtl.script.operations.foreach;

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

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.Iterator;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

public final class BreakerIterator<T> extends AbstractIterator<T> {

    private final Predicate<T> breaker;
    private final PeekingIterator<T> source;

    public BreakerIterator(Iterator<T> source, Predicate<T> breaker) {
        this.breaker = checkNotNull(breaker);
        this.source = Iterators.peekingIterator(checkNotNull(source));
    }

    @Override
    protected T computeNext() {
        if (source.hasNext() && breaker.test(source.peek())) {
            return source.next();
        } else {
            return endOfData();
        }
    }
}