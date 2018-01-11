package no.ssb.vtl.script.operations.join;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * A dataset that forwards all its method calls to a another but have a different structure.
 */
class DatasetDuplicate implements Dataset {

    private final Dataset delegate;
    private final DataStructure structure;
    private final Map<Component, Component> componentMap;

    DatasetDuplicate(Dataset delegate) {
        this.delegate = delegate;
        DataStructure structure = delegate.getDataStructure();
        this.structure = duplicateStructure(structure);
        this.componentMap = createMap(this.structure, structure);
    }

    private static ImmutableMap<Component, Component> createMap(DataStructure from, DataStructure to) {
        ImmutableMap.Builder<Component, Component> mapBuilder = ImmutableMap.builder();
        for (String key : from.keySet()) {
            mapBuilder.put(
                    from.get(key),
                    to.get(key)
            );
        }
        return mapBuilder.build();
    }

    private static DataStructure duplicateStructure(DataStructure originalStructure) {
        DataStructure.Builder newStructure = DataStructure.builder();
        for (Map.Entry<String, Component> entry : originalStructure.entrySet()) {
            String key = entry.getKey();
            Component.Role role = entry.getValue().getRole();
            Class<?> type = entry.getValue().getType();
            newStructure.put(key, role, type);
        }
        return newStructure.build();
    }

    @Override
    public Stream<DataPoint> getData() {
        return delegate.getData();
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order orders, Filtering filtering, Set<String> components) {
        // Map the order.
        Order.Builder builder = Order.create(delegate.getDataStructure());
        for (Component component : orders.keySet())
            builder.put(componentMap.get(component), orders.get(component));
        return delegate.getData(builder.build(), filtering, components);
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return delegate.getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return delegate.getSize();
    }

    @Override
    public DataStructure getDataStructure() {
        return structure;
    }
}
