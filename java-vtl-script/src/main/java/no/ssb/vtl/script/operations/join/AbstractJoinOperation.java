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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.VtlOrdering;
import no.ssb.vtl.script.operations.AbstractDatasetOperation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract join operation.
 * <p>
 * Contains the base logic for inner join and outer join operations.
 */
public abstract class AbstractJoinOperation extends AbstractDatasetOperation implements WorkingDataset {

    private static final String ERROR_EMPTY_DATASET_LIST = "join operation impossible on empty dataset list";
    private static final String ERROR_INCOMPATIBLE_TYPES = "incompatible identifier types: %s";
    private static final String ERROR_NO_COMMON_IDENTIFIERS = "could not find common identifiers in the datasets %s";

    private final Table<Component, Dataset, Component> componentMapping;
    protected final ImmutableMap<String, Dataset> datasets;
    private final ImmutableSet<Component> commonIdentifiers;

    private final ComponentBindings joinScope;

    AbstractJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(Lists.newArrayList(checkNotNull(namedDatasets).values()));

        checkArgument(
                !namedDatasets.isEmpty(),
                ERROR_EMPTY_DATASET_LIST
        );
        this.datasets = ImmutableMap.copyOf(checkNotNull(namedDatasets));

        checkNotNull(identifiers);

        this.joinScope = createJoinScope(namedDatasets);

        this.componentMapping = createComponentMapping(this.datasets.values());

        Map<Component, Map<Dataset, Component>> idMap = this.componentMapping.rowMap().entrySet().stream()
                .filter(e -> e.getKey().isIdentifier())
                .filter(e -> e.getValue().size() == datasets.size())
                // identifiers can be from any dataset
                .filter(e -> identifiers.isEmpty() || !Collections.disjoint(e.getValue().values(), identifiers))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // No common identifier
        checkArgument(namedDatasets.size() == 1 || !idMap.isEmpty(), ERROR_NO_COMMON_IDENTIFIERS, namedDatasets);

        this.commonIdentifiers = ImmutableSet.copyOf(idMap.keySet());

        // Type mismatch check
        List<String> typeMismatches = Lists.newArrayList();
        for (Map.Entry<Component, Map<Dataset, Component>> entry : idMap.entrySet()) {
            Component identifier = entry.getKey();

            Multimap<Class<?>, Dataset> typeMap = ArrayListMultimap.create();
            entry.getValue().entrySet().forEach(datasetComponent -> {
                typeMap.put(
                        datasetComponent.getValue().getType(),
                        datasetComponent.getKey()
                );
            });
            if (typeMap.keySet().size() != 1)
                typeMismatches.add(String.format("%s -> (%s)", identifier, typeMap));
        }
        checkArgument(
                typeMismatches.isEmpty(),
                ERROR_INCOMPATIBLE_TYPES,
                String.join(", ", typeMismatches)
        );
    }

    /**
     * Creates a Bindings that contains the unique components of this join operation and the
     * datasets.
     */
    @VisibleForTesting
    static ComponentBindings createJoinScope(Map<String, Dataset> namedDatasets) {
        return new ComponentBindings(namedDatasets);
    }

    /**
     * Create a table that maps the components of the resulting dataset to the component of the underlying
     * datasets.
     *
     * <pre>
     * +------+-----------------+
     * |  re  |     Dataset     |
     * |  su  +-----+-----+-----+
     * |  lt  | ds1 | dsN | ... |
     * +------+-----+-----+-----+
     * | ref  | ref | ref |     |
     * +------+-----+-----+     +
     * | ref  | ref | ref |     |
     * +------+-----+-----+     +
     * | ...  |                 |
     * +------+-----+-----+-----+
     * </pre>
     *
     * @param datasets the datasets
     * @return the component table
     */
    @VisibleForTesting
    static Table<Component, Dataset, Component> createComponentMapping(Iterable<Dataset> datasets) {

        Map<String, Component> seen = Maps.newHashMap();

        ImmutableTable.Builder<Component, Dataset, Component> table;
        table = ImmutableTable.builder();

        for (Dataset dataset : datasets) {
            DataStructure structure = dataset.getDataStructure();
            for (Map.Entry<String, Component> entry : structure.entrySet()) {
                Component component;

                if (entry.getValue().isIdentifier())
                    component = seen.computeIfAbsent(entry.getKey(), s -> entry.getValue());
                else
                    component = entry.getValue();

                table.put(component, dataset, entry.getValue());
            }
        }

        return table.build();
    }


    protected ImmutableSet<Component> getCommonIdentifiers() {
        return this.commonIdentifiers;
    }

    /**
     * Try to create an order that is compatible with the join using the requested order
     * <p>
     * Join operations need the common identifiers to be first.
     *
     * @param structure
     * @param firstComponents
     * @param requestedOrder  the requested order
     */
    @VisibleForTesting
    Optional<Ordering> createCompatibleOrder(DataStructure structure, ImmutableSet<Component> firstComponents, Ordering requestedOrder) {

        Set<Component> identifiers = Sets.newHashSet(firstComponents);

        ImmutableMap.Builder<String, Ordering.Direction> compatibleOrder = ImmutableMap.builder();
        for (String column : requestedOrder.columns()) {

            Ordering.Direction direction = requestedOrder.getDirection(column);

            if (!identifiers.isEmpty() && !identifiers.remove(structure.get(column)))
                return Optional.empty();

            compatibleOrder.put(column, direction);
        }
        return Optional.of(new VtlOrdering(compatibleOrder.build(), structure));
    }

    /**
     * Checks if component name is unique among other datasets
     */
    @VisibleForTesting
    boolean componentNameIsUnique(String datasetName, String componentName) {
        for (String otherDatasetName : datasets.keySet()) {
            if (!datasetName.equals(otherDatasetName)) {
                DataStructure structure = datasets.get(otherDatasetName).getDataStructure();
                if (!Sets.intersection(structure.keySet(), Sets.newHashSet(componentName)).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected DataStructure computeDataStructure() {
        // Optimization.
        if (datasets.size() == 1) {
            return datasets.values().iterator().next().getDataStructure();
        }

        Set<String> ids = Sets.newHashSet();
        DataStructure.Builder newDataStructure = DataStructure.builder();
        for (String datasetName : datasets.keySet()) {
            DataStructure structure = datasets.get(datasetName).getDataStructure();
            for (Map.Entry<String, Component> componentEntry : structure.entrySet()) {
                if (!componentEntry.getValue().isIdentifier()) {
                    if (componentNameIsUnique(datasetName, componentEntry.getKey())) {
                        newDataStructure.put(componentEntry.getKey(), componentEntry.getValue());
                    } else {
                        newDataStructure.put(datasetName.concat("_".concat(componentEntry.getKey())), componentEntry.getValue());
                    }
                } else {
                    if (ids.add(componentEntry.getKey())) {
                        newDataStructure.put(componentEntry);
                    }
                }
            }
        }
        return newDataStructure.build();
    }

    public ComponentBindings getJoinScope() {
        return joinScope;
    }

    public Table<Component, Dataset, Component> getComponentMapping() {
        return componentMapping;
    }

}
