package no.ssb.vtl.script.operations.join;
/*-
 * #%L
 * java-vtl-script
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.JoinSpliterator;

import java.util.*;

/**
 * Represent an inner join on datasets.
 */
public class InnerJoinOperation extends AbstractJoinOperation {

    public InnerJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets, Collections.emptySet());
    }

    public InnerJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets, identifiers);
    }

    @Override
    public WorkingDataset workDataset() {
        // TODO: Remove
        return this;
    }

    @Override
    protected JoinSpliterator.TriFunction<JoinTuple, JoinTuple, Integer, List<JoinTuple>> getMerger() {
        ImmutableSet<Component> commonIdentifiers = getIdentifiers();

        return (left, right, compare) -> {
            if (compare == 0) {
                addToLeftAllRightButCommon(commonIdentifiers, left, right);
                return Collections.singletonList(left);
            } else {
                return null;
            }
        };
    }

    private void addToLeftAllRightButCommon(ImmutableSet<Component> commonIdentifiers, JoinTuple left, JoinTuple right) {
        for (DataPoint dp : right) {
            if (!commonIdentifiers.contains(dp.getComponent())) {
                left.add(dp);
            }
        }
    }
}
