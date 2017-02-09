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

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.JoinSpliterator;

import java.util.Collections;
import java.util.Map;

public class CrossJoinOperation extends InnerJoinOperation {
    public CrossJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets);
    }

    @Override
    protected JoinSpliterator.TriFunction<JoinTuple, JoinTuple, Integer, JoinTuple> getMerger() {
        return (left, right, compare) -> {
            JoinTuple tuple = new JoinTuple(Collections.emptyList());
            if (compare == 0) {
                tuple.addAll(left);
                tuple.addAll(right);
            } else if (compare < 0) {
                tuple.addAll(left);
            } else /* if (compare > 0) */ {
                tuple.addAll(right);
            }
            return tuple;
        };
    }
}
