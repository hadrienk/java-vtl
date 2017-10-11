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

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import javax.script.Bindings;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A bindings wrapper that exposes datasets and their components as {@link no.ssb.vtl.model.VTLTyped}
 */
@Deprecated
final class JoinScopeBindings2 implements Bindings {

    private final ImmutableSet<String> datasetNames;
    private final Map<String, Object> scope = Maps.newHashMap();

    JoinScopeBindings2(Map<String, Dataset> datasets) {
        checkNotNull(datasets);
        this.datasetNames = ImmutableSet.copyOf(datasets.keySet());
        //this.scope.putAll(datasets);

        // Needed so that all id are the same reference.
        // TODO: :´( ...
        Map<String, Dataset> cleanedDatasets = Maps.newLinkedHashMap();
        Map<String, Component> identifiers = Maps.newLinkedHashMap();
        for (Entry<String, Dataset> datasetEntry : datasets.entrySet()) {
            Dataset dataset = datasetEntry.getValue();
            DataStructure structure = dataset.getDataStructure();
            Map<String, Component> measuresAndAttributes = Maps.newLinkedHashMap();
            for (Entry<String, Component> componentEntry : structure.entrySet()) {
                String name = componentEntry.getKey();
                Component component = componentEntry.getValue();
                if (component.isIdentifier()) {
                    identifiers.putIfAbsent(name, component);
                } else {
                    measuresAndAttributes.put(name, component);
                }
            }
            LinkedHashMap<String, Component> newStructure = Maps.newLinkedHashMap();
            newStructure.putAll(identifiers);
            newStructure.putAll(measuresAndAttributes);

            DataStructure finalStructure = DataStructure.copyOf(
                    newStructure
            ).build();
            cleanedDatasets.put(datasetEntry.getKey(), createEmptyDataset(finalStructure));
        }

        this.scope.putAll(cleanedDatasets);
        for (Entry<String, Dataset> datasetEntry : cleanedDatasets.entrySet()) {
            Dataset dataset = datasetEntry.getValue();
            for (Entry<String, Component> componentEntry : dataset.getDataStructure().entrySet()) {
                this.scope.putIfAbsent(componentEntry.getKey(), componentEntry.getValue());
            }
        }
    }

    private Dataset createEmptyDataset(final DataStructure structure) {
        return new Dataset() {
            @Override
            public Stream<DataPoint> getData() {
                return Stream.empty();
            }

            @Override
            public Optional<Map<String, Integer>> getDistinctValuesCount() {
                return Optional.empty();
            }

            @Override
            public Optional<Long> getSize() {
                return Optional.of(0L);
            }

            @Override
            public DataStructure getDataStructure() {
                return structure;
            }

        };
    }

    @Override
    public void clear() {
        scope.clear();
    }

    @Override
    public Set<String> keySet() {
        return scope.keySet();
    }

    @Override
    public Collection<Object> values() {
        return scope.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return scope.entrySet();
    }

    @Override
    public int size() {
        return scope.size();
    }

    @Override
    public boolean isEmpty() {
        return scope.isEmpty();
    }


    @Override
    public Object put(String name, Object value) {
        checkArgument(!datasetNames.contains(name), "dataset with name [%s] exists in the join scope", name);
        return scope.put(name, value);
    }

    @Override
    public void putAll(Map<? extends String, ?> toMerge) {
        Set<? extends String> clashingNames = Sets.intersection(toMerge.keySet(), datasetNames);
        checkArgument(
                clashingNames.isEmpty(),
                "could not add [%s] to the scope", clashingNames
        );
        scope.putAll(toMerge);
    }

    @Override
    public boolean containsKey(Object key) {
        return scope.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return scope.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return scope.get(key);
    }

    @Override
    public Object remove(Object key) {
        checkArgument(!datasetNames.contains(key), "tried to remove the dataset [%s] from the join scope", key);
        return scope.remove(key);
    }
}
