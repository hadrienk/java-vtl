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

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.JoinSpliterator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CrossJoinOperation extends InnerJoinOperation {

    CrossJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets, Collections.emptySet());
    }

    public CrossJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets, identifiers);
    }

    @Override
    protected JoinSpliterator.TriFunction<JoinDataPoint, JoinDataPoint, Integer, List<JoinDataPoint>> getMerger() {
        return (left, right, compare) -> {
            JoinDataPoint tuple = new JoinDataPoint(Collections.emptyList());
            if (compare == 0) {
                tuple.addAll(left);
                tuple.addAll(right);
            } else if (compare < 0) {
                tuple.addAll(left);
            } else /* if (compare > 0) */ {
                tuple.addAll(right);
            }
            return Collections.singletonList(tuple);
        };
    }
}
