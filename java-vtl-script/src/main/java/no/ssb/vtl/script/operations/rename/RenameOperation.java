package no.ssb.vtl.script.operations.rename;

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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.OrderingSpecification;
import no.ssb.vtl.script.operations.AbstractUnaryDatasetOperation;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static no.ssb.vtl.model.FilteringSpecification.Clause;
import static no.ssb.vtl.model.FilteringSpecification.Literal;

/**
 * Rename operation.
 */
public class RenameOperation extends AbstractUnaryDatasetOperation {

    private final ImmutableMap<String, String> nameMapping;
    private final ImmutableMap<String, Component.Role> roleMapping;

    public RenameOperation(Dataset child, Map<String, String> nameMapping) {
        this(child, nameMapping, Collections.emptyMap());
    }

    public RenameOperation(Dataset child, Map<String, String> nameMapping, Map<String, Component.Role> roleMapping) {
        super(child);
        this.nameMapping = ImmutableMap.copyOf(nameMapping);
        this.roleMapping = ImmutableMap.copyOf(roleMapping);
    }

    @Override
    protected DataStructure computeDataStructure() {
        DataStructure.Builder structure = DataStructure.builder();
        DataStructure childStructure = getChild().getDataStructure();
        for (Map.Entry<String, Component> componentEntry : childStructure.entrySet()) {
            Component component = componentEntry.getValue();
            if (nameMapping.containsKey(component)) {
                String newName = nameMapping.get(component);
                structure.put(
                        newName,
                        roleMapping.getOrDefault(component, component.getRole()),
                        component.getType()
                );
            } else {
                structure.put(componentEntry);
            }
        }
        return structure.build();
    }

    @Override
    public FilteringSpecification unsupportedFiltering(FilteringSpecification filtering) {
        return getChild().unsupportedFiltering(filtering);
    }

    @Override
    public OrderingSpecification unsupportedOrdering(OrderingSpecification ordering) {
        return getChild().unsupportedOrdering(ordering);
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        for (String from : nameMapping.keySet()) {
            String to = nameMapping.get(from);
            Component.Role role = roleMapping.get(from);
            if (role != null) {
                to = to.concat("(" + role + ")");
            }
            helper.add(from, to);
        }
        return helper.omitNullValues().toString();
    }

    @Override
    public Stream<DataPoint> computeData(Ordering oldOrdering, Filtering oldFiltering, Set<String> oldComponents) {
        Ordering ordering = renameOrdering(oldOrdering);
        Filtering filtering = renameFiltering(oldFiltering);
        Set<String> components = renameComponent(oldComponents);
        return getChild().computeData(ordering, filtering, components);
    }

    private Set<String> renameComponent(Set<String> oldComponents) {
        ImmutableSet.Builder<String> components = ImmutableSet.builder();
        for (String column : oldComponents) {
            String childColumn = nameMapping.getOrDefault(column, column);
            components.add(childColumn);
        }
        return components.build();
    }

    private Filtering renameFiltering(Filtering oldFiltering) {
        for (Clause clause : oldFiltering.getClauses()) {
            for (Literal literal : clause.getLiterals()) {
                String column = literal.getColumn();
                String childColumn = nameMapping.getOrDefault(column, column);
                // TODO
            }
        }
        return oldFiltering;
    }

    /**
     * Make sure that the ordering uses the correct names.
     */
    private Ordering renameOrdering(Ordering oldOrdering) {
        DataStructure childStructure = getChild().getDataStructure();
        Order.Builder ordering = Order.create(childStructure);
        for (String column : oldOrdering.columns()) {
            String childColumn = nameMapping.getOrDefault(column, column);
            Ordering.Direction direction = oldOrdering.getDirection(column);
            ordering.put(childColumn, direction);
        }
        return ordering.build();
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        Dataset child = getChild();
        return child.getDistinctValuesCount().map(oldMap -> {
            ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
            for (String oldName : oldMap.keySet()) {
                Integer count1 = oldMap.get(oldName);
                String name = nameMapping.getOrDefault(oldName, oldName);
                builder.put(name, count1);
            }
            return builder.build();
        });
    }

    @Override
    public Optional<Long> getSize() {
        return getChild().getSize();
    }
}
