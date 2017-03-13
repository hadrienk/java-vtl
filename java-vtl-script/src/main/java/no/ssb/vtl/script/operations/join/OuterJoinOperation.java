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

import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.support.JoinSpliterator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class OuterJoinOperation extends AbstractJoinOperation {

    OuterJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets, Collections.emptySet());
    }

    public OuterJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets, identifiers);
    }

    @Override
    protected JoinSpliterator.TriFunction<JoinDataPoint, JoinDataPoint, Integer, List<JoinDataPoint>> getMerger(
            final DataStructure leftStructure, final DataStructure rightStructure
    ) {
        final Map<Component, Component> leftToRightIds = Maps.newHashMap();
        final Map<Component, Component> rightToLeftIds = Maps.newHashMap();
        for (Component component : getIdentifiers()) {
            if (leftStructure.containsValue(component)) {
                leftToRightIds.put(component, rightStructure.get(leftStructure.getName(component)));
            }
            if (rightStructure.containsValue(component)) {
                rightToLeftIds.put(component, leftStructure.get(rightStructure.getName(component)));
            }
        }
        return (left, right, compare) -> {

            // Need to operate on a copy
            DataPoint result = leftStructure.wrap();
            Map<Component, VTLObject> resultMap = leftStructure.asMap(result);

            Map<Component, VTLObject> leftMap = leftStructure.asMap(left);
            Map<Component, VTLObject> rightMap = rightStructure.asMap(right);

            if (compare <= 0) {
                resultMap.putAll(leftMap);
            }
            if (compare > 0) {
                for (Map.Entry<Component, VTLObject> entry : rightMap.entrySet()) {
                    resultMap.put(rightToLeftIds.getOrDefault(entry.getKey(), entry.getKey()), entry.getValue());
                }
            }
            if (compare == 0) {
                for (Map.Entry<Component, VTLObject> entry : rightMap.entrySet()) {
                    if (!rightToLeftIds.containsKey(entry.getKey())) {
                        resultMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }

            return Collections.singletonList(new JoinDataPoint(result));
        };
    }

    private VTLObject createNull(final VTLObject point) {
        return VTLObject.of(point.getComponent(), null);
    }

    @Override
    public WorkingDataset workDataset() {
        // TODO: Remove this method.
        return this;
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        if (getChildren().size() == 1) {
            return getChildren().get(0).getDistinctValuesCount();
        } else {
            // TODO
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> getSize() {
        if (getChildren().size() == 1) {
            return getChildren().get(0).getSize();
        } else {
            // TODO
            return Optional.empty();
        }
    }
}
