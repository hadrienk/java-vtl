package no.ssb.vtl.model;

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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Support class that reorder the values in a DataPoint.
 */
public class DatapointNormalizer implements UnaryOperator<DataPoint> {

    private final int[] fromIndices;
    private final int[] toIndices;

    public DatapointNormalizer(DataStructure from, DataStructure to) {
        this(from, to, s -> true);
    }

    public DatapointNormalizer(DataStructure from, DataStructure to, Predicate<String> predicate) {
        checkNotNull(from);
        checkArgument(!from.isEmpty());
        checkNotNull(to);

        ImmutableList<String> fromList = ImmutableSet.copyOf(from.keySet()).asList();
        ImmutableList<String> toList = ImmutableSet.copyOf(to.keySet()).asList();

        // Make sure that both structures contains the columns we are mapping.
        Set<String> filteredFrom = Sets.filter(from.keySet(), predicate::test);
        Set<String> filteredTo = Sets.filter(to.keySet(), predicate::test);
        checkArgument(filteredFrom.containsAll(filteredTo));

        // build indices
        ArrayList<Integer> fromIndices = Lists.newArrayList();
        ArrayList<Integer> toIndices = Lists.newArrayList();
        for (String fromName : filteredFrom) {

            int fromIndex = fromList.indexOf(fromName);
            int toIndex = toList.indexOf(fromName);
            if (fromIndex == toIndex)
                continue;
            fromIndices.add(fromIndex);
            toIndices.add(toIndex);
        }
        this.fromIndices = Ints.toArray(fromIndices);
        this.toIndices = Ints.toArray(toIndices);
    }

    @Override
    public DataPoint apply(DataPoint datapoint) {
        if (fromIndices.length == 0)
            return datapoint;

        DataPoint copy = (DataPoint) datapoint.clone();
        for (int i = 0; i < fromIndices.length; i++)
            datapoint.set(toIndices[i], copy.get(fromIndices[i]));

        return datapoint;
    }
}
