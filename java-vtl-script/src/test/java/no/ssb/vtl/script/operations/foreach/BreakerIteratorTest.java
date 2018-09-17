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

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.UnmodifiableIterator;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BreakerIteratorTest {

    @Test
    public void testEmpty() {
        BreakerIterator<Integer> iterator = new BreakerIterator<>(
                Collections.emptyIterator(),
                i -> true
        );
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void testAlwaysFalse() {
        BreakerIterator<Integer> iterator = new BreakerIterator<>(
                Iterators.forArray(1, 2),
                i -> false
        );
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void testStopsAfter() {
        UnmodifiableIterator<Integer> source = Iterators.forArray(1, 2);
        PeekingIterator<Integer> peekingSource = Iterators.peekingIterator(source);
        BreakerIterator<Integer> iterator = new BreakerIterator<>(
                peekingSource,
                i -> i < 2
        );
        assertThat(iterator.hasNext()).isTrue();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
        assertThat(source.hasNext()).isFalse();
        assertThat(peekingSource.hasNext()).isTrue();
    }
}