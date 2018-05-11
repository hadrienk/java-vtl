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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;

import java.util.Map;
import java.util.function.BiFunction;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Merger that copies the values of the right {@link DataPoint} to the left {@link DataPoint}.
 */
public class InnerJoinMerger implements BiFunction<DataPoint, DataPoint, DataPoint> {

    private final DataStructure leftStructure;
    private final DataStructure rightStructure;
    private final ImmutableListMultimap<Integer, Integer> indexMap;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mapping", indexMap)
                .add("leftStructure", leftStructure)
                .add("rightStructure", rightStructure)
                .toString();
    }

    public InnerJoinMerger(DataStructure leftStructure, DataStructure rightStructure) {
        this.leftStructure = checkNotNull(leftStructure);
        this.rightStructure = checkNotNull(rightStructure);

        ImmutableList<Component> leftList = ImmutableList.copyOf(leftStructure.values());
        ImmutableList<Component> rightList = ImmutableList.copyOf(rightStructure.values());
        indexMap = buildIndices(leftList, rightList);
    }

    private ImmutableListMultimap<Integer, Integer> buildIndices(ImmutableList<Component> leftList, ImmutableList<Component> rightList) {
        ImmutableListMultimap.Builder<Integer, Integer> indexMapBuilder = ImmutableListMultimap.builder();
        // Save the indices of the right data points that need to be moved to the left.
        for (int i = 0; i < rightList.size(); i++) {
            Component rightComponent = rightList.get(i);
            for (int j = 0; j < leftList.size(); j++) {
                Component leftComponent = leftList.get(j);
                if (rightComponent.equals(leftComponent)) {
                    indexMapBuilder.put(i, j);
                }
            }
        }
        return indexMapBuilder.build();
    }


    @Override
    public DataPoint apply(DataPoint left, DataPoint right) {
        for (Map.Entry<Integer, Integer> entry : indexMap.entries()) {
            left.set(entry.getValue(), right.get(entry.getKey()));
        }
        return left;
    }
}
