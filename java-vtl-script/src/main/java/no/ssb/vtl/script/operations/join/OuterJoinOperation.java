package no.ssb.vtl.script.operations.join;

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

import com.google.common.collect.Table;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;

public class OuterJoinOperation extends AbstractJoinOperation {

    OuterJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets, Collections.emptySet());
    }

    public OuterJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets, identifiers);
    }

    @Override
    protected BiFunction<DataPoint, DataPoint, DataPoint> getMerger(
            final Dataset leftDataset, final Dataset rightDataset
    ) {

        final Table<Component, Dataset, Component> componentMapping = getComponentMapping();
        final DataStructure structure = getDataStructure();
        final DataStructure rightStructure = rightDataset.getDataStructure();

        return (left, right) -> {

            /*
             * We overwrite the ids if right != null for simplicity.
             */
            DataPoint result;
            if (left != null) {
                result = DataPoint.create(left);
            } else {
                result = DataPoint.create(structure.size());
            }

            if (right != null) {
                Map<Component, VTLObject> leftMap = structure.asMap(result);
                Map<Component, VTLObject> rightMap = rightStructure.asMap(right);
                for (Map.Entry<Component, Component> mapping : componentMapping.column(rightDataset).entrySet()) {
                    Component to = mapping.getKey();
                    Component from = mapping.getValue();
                    leftMap.put(to, rightMap.get(from));
                }
            }

            return DataPoint.create(result);
        };
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
