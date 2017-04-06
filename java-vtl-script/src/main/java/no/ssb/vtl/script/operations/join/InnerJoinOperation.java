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
    protected BiFunction<DataPoint, DataPoint, DataPoint> getMerger(
            final Dataset leftDataset, final Dataset rightDataset
    ) {
        //final Set<Component> identifiers = getIdentifiers();

        final DataStructure rightStructure = rightDataset.getDataStructure();
        final DataStructure structure = getDataStructure();

        // Create final collection to improve performances.
        final Table<Component, Dataset, Component> componentMap = getComponentMapping();

//        final Map<Component, Component> rightValuesMapping = ImmutableBiMap.copyOf(
//                Maps.filterKeys(
//                        tableMap.column(rightDataset),
//                        Predicates.not(identifiers::contains)
//                )
//        ).inverse();

        return (left, right) -> {

            if (left == null || right == null)
                return null;

            // Put the measures and attributes of the right data point
            // in the left data point.

            Map<Component, VTLObject> leftMap = structure.asMap(left);
            Map<Component, VTLObject> rightMap = rightStructure.asMap(right);
            for (Map.Entry<Component, Component> mapping : componentMap.column(rightDataset).entrySet()) {
                Component from = mapping.getKey();
                Component to = mapping.getValue();
                leftMap.put(to, rightMap.get(from));
            }

            return left;
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
