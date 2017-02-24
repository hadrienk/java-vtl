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

import com.google.common.collect.Lists;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.JoinSpliterator;

import java.util.*;

public class OuterJoinOperation extends AbstractJoinOperation {

    OuterJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets, Collections.emptySet());
    }

    public OuterJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets, identifiers);
    }

    @Override
    protected JoinSpliterator.TriFunction<JoinDataPoint, JoinDataPoint, Integer, List<JoinDataPoint>> getMerger() {
        Set<Component> identifiers = getIdentifiers();
        return (left, right, compare) -> {

            // TODO: Rewrite this method after implementing the tuple "Map View"

            JoinDataPoint merged;
            ArrayList<VTLObject> ids = Lists.newArrayList();
            for (VTLObject point : left) {
                if (identifiers.contains(point.getComponent())) {
                    ids.add(point);
                }
            }
            if (compare <= 0) {
                merged = new JoinDataPoint(ids);
            } else {
                // Use left components with right values.
                Iterator<VTLObject> idsIterator = ids.iterator();
                List<VTLObject> rightIds = Lists.newArrayList();
                for (VTLObject point : right) {
                    if (identifiers.contains(point.getComponent())) {
                        Component leftComponent = idsIterator.next().getComponent();
                        rightIds.add(new VTLObject(leftComponent) {
                            Object value = point.get();

                            @Override
                            public Object get() {
                                return value;
                            }
                        });
                    }
                }
                merged = new JoinDataPoint(rightIds);
            }


            if (compare == 0) {
                for (VTLObject point : left) {
                    if (!identifiers.contains(point.getComponent())) {
                        merged.add(point);
                    }
                }
                for (VTLObject point : right) {
                    if (!identifiers.contains(point.getComponent()))
                        merged.add(point);
                }
            } else {
                if (compare < 0) {
                    for (VTLObject point : left) {
                        if (!identifiers.contains(point.getComponent())) {
                            merged.add(point);
                        }
                    }
                    for (VTLObject point : right) {
                        if (!identifiers.contains(point.getComponent())) {
                            merged.add(createNull(point));
                        }
                    }
                } else { // (compare > 0) {
                    for (VTLObject point : left) {
                        if (!identifiers.contains(point.getComponent())) {
                            merged.add(createNull(point));
                        }
                    }
                    for (VTLObject point : right) {
                        if (!identifiers.contains(point.getComponent())) {
                            merged.add(point);
                        }
                    }
                }
            }
            return Collections.singletonList(merged);
        };
    }

    private VTLObject createNull(final VTLObject point) {
        return new VTLObject(point.getComponent()) {
            @Override
            public Object get() {
                return null;
            }
        };
    }

    @Override
    public WorkingDataset workDataset() {
        // TODO: Remove this method.
        return this;
    }

}
