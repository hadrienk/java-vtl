package no.ssb.vtl.script.operations.union;

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

import com.codepoetics.protonpack.selectors.Selector;

import java.util.Comparator;

class MinimumSelector<T> implements Selector<T> {

    private final Comparator<T> comparator;

    public MinimumSelector(Comparator<T> comparator) {
        this.comparator = Comparator.nullsLast(comparator);
    }

    @Override
    public Integer apply(T[] values) {

        // Find the lowest value
        int idx = 0;
        T minimum = values[0];
        for (int i = 1; i < values.length; i++) {
            T current = values[i];

            // Values can be null. This works because comparator
            // is wrapped with Comparator.nullsLast() in the constructor.
            if (current != null && comparator.compare(current, minimum) < 0) {
                idx = i;
                minimum = current;
            }
        }
        return idx;
    }
}
