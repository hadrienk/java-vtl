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

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OuterJoinOperation extends AbstractJoinOperation {

    public OuterJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets);
    }

    @Override
    protected JoinSpliterator.TriFunction<JoinTuple, JoinTuple, Integer,JoinTuple> getMerger() {
        return null;
    }

    @Override
    protected Comparator<List<DataPoint>> getKeyComparator() {
        return null;
    }

    @Override
    protected ImmutableSet<Component> getIdentifiers() {
        return null;
    }

    @Override
    public WorkingDataset workDataset() {
        return null;
    }

}
