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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import no.ssb.vtl.model.AbstractDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.support.JoinSpliterator;

import javax.script.Bindings;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.RandomAccess;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static no.ssb.vtl.model.Order.Direction.ASC;

/**
 * Abstract join operation.
 * <p>
 * Contains the base logic for inner join and outer join operations.
 */
public abstract class AbstractJoinOperation extends AbstractDatasetOperation implements WorkingDataset {

    protected final Table<Component, Dataset, Component> identifierTable;
    final Table<Component, Dataset, Component> identifierTable2;

    // The datasets the join operates on.
    private final Bindings joinScope;
    private final ImmutableMap<String, Dataset> datasets;
    @Deprecated
    private final ImmutableSet<Component> identifiers;

    AbstractJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(Lists.newArrayList(checkNotNull(namedDatasets).values()));

        checkArgument(
                !namedDatasets.isEmpty(),
                "join operation impossible on empty dataset list"
        );
        this.datasets = ImmutableMap.copyOf(checkNotNull(namedDatasets));

        checkNotNull(identifiers);

        this.joinScope = new JoinScopeBindings(this.datasets);

        this.identifiers = createIdentifierSet(identifiers);

        this.identifierTable = this.createIdentifierTable(this.datasets.values(), this.identifiers);
        this.identifierTable2 = createComponentTable(this.datasets.values(), this.identifiers::contains);
    }


    /**
     * Create a table that maps the components of the resulting dataset to the component of the underlying
     * datasets.
     * <p>
     * The components that are
     * <p>
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
     * @param datasets
     * @param isIdentifier
     * @return
     */
    @VisibleForTesting
    static Table<Component, Dataset, Component> createComponentTable(Iterable<Dataset> datasets, Predicate<Component> isIdentifier) {

        Map<String, Component> seen = Maps.newHashMap();

        ImmutableTable.Builder<Component, Dataset, Component> table;
        table = ImmutableTable.builder();

        for (Dataset dataset : datasets) {
            DataStructure structure = dataset.getDataStructure();
            for (Map.Entry<String, Component> entry : structure.entrySet()) {
                Component component;

                if (isIdentifier.test(entry.getValue()))
                    component = seen.computeIfAbsent(entry.getKey(), s -> entry.getValue());
                else
                    component = entry.getValue();

                table.put(component, dataset, entry.getValue());
            }
        }

        return table.build();
    }

    @VisibleForTesting
    static Table<Component, Dataset, Component> createComponentTable(Iterable<Dataset> datasets) {
        return createComponentTable(datasets, Component::isIdentifier);
    }

    @VisibleForTesting
    Table<Component, Dataset, Component> createIdentifierTable(Iterable<Dataset> datasets, Iterable<Component> identifiers) {

        // Create a table that maps the components of the resulting dataset to the component of the underlying
        // datasets
        //
        // +------+-----------------+
        // |  re  |     Dataset     |
        // |  su  +-----+-----+-----+
        // |  lt  | ds1 | dsN | ... |
        // +------+-----+-----+-----+
        // | ref  | ref | ref |     |
        // +------+-----+-----+     +
        // | ref  | ref | ref |     |
        // +------+-----+-----+     +
        // | ...  |                 |
        // +------+-----+-----+-----+
        //
        //

        ImmutableTable.Builder<Component, Dataset, Component> table = ImmutableTable.builder();
        Set<Component> identifiersCopy = Sets.newHashSet(identifiers);

        DataStructure structure = getDataStructure();

        for (Iterator<Component> iterator = identifiersCopy.iterator(); iterator.hasNext(); ) {
            Component identifier = iterator.next();
            for (Dataset dataset : datasets) {
                if (dataset.getDataStructure().containsValue(identifier)) {
                    String name = dataset.getDataStructure().getName(identifier);
                    table.put(structure.get(name), dataset, identifier);
                    iterator.remove();
                    break;
                }
            }
        }

        // TODO: Error message.
        checkArgument(identifiersCopy.isEmpty());
        return table.build();
    }

    @Deprecated
    private ImmutableSet<Component> createIdentifierSet(Set<Component> identifiers) {
        ImmutableMultimap<String, Component> superSet = createCommonIdentifiers();

        HashSet<Component> keySet = Sets.newHashSet(superSet.values());

        // Checks that the datasets have at least one common identifier.
        checkArgument(
                !keySet.isEmpty(),
                "could not find common identifiers in the datasets %s",
                superSet.keySet()
        );

        // Use all common identifiers if identifiers is  empty
        ImmutableSet<Component> result;
        if (!identifiers.isEmpty()) {
            result = ImmutableSet.copyOf(
                    Sets.intersection(
                            identifiers,
                            keySet
                    )
            );
            checkArgument(!result.isEmpty(), "cannot use %s as key",
                    Sets.difference(identifiers, Sets.newHashSet(keySet)));
        } else {
            result = ImmutableSet.copyOf(keySet);
        }
        return result;
    }

    protected abstract BiFunction<JoinDataPoint, JoinDataPoint, JoinDataPoint> getMerger(
            Dataset leftDataset, Dataset rightDataset
    );

    @Deprecated
    protected ImmutableSet<Component> getIdentifiers() {
        return this.identifiers;
    }

    /**
     * Compute a multimap with components eligible as keys.
     */
    private ImmutableMultimap<String, Component> createCommonIdentifiers() {

        Multimap<SuperSetWrapper, Component> superSet = LinkedListMultimap.create();
        for (Dataset dataset : datasets.values()) {
            for (Map.Entry<String, Component> componentEntry : dataset.getDataStructure().entrySet()) {
                Component component = componentEntry.getValue();
                if (component.isIdentifier()) {
                    String name = componentEntry.getKey();
                    Class<?> type = component.getType();
                    superSet.put(
                            new SuperSetWrapper(type, name),
                            component
                    );
                }
            }
        }

        ImmutableMultimap.Builder<String, Component> builder = ImmutableMultimap.builder();
        for (Map.Entry<SuperSetWrapper, Collection<Component>> entry : superSet.asMap().entrySet()) {
            if (entry.getValue().size() >= datasets.size()) {
                builder.putAll(
                        entry.getKey().name,
                        entry.getValue()
                );
            }
        }
        return builder.build();
    }

    private Stream<DataPoint> getSorted(Dataset dataset, Order requiredOrder) {
        Order.Builder builder = createOrder(dataset, requiredOrder);

        return dataset.getData(builder.build()).orElse(dataset.getData().sorted(builder.build()));
    }

    private Comparator<Map<Component, VTLObject>> createKeyComparator(
            Dataset leftDataset, Dataset rightDataset,
            Order order
    ) {

        // Only check the values of the common identifiers.
        HashSet<Component> commonComponents = Sets.newHashSet(createCommonIdentifiers().values());
        final Map<Component, Order.Direction> commonOrder = Maps.filterKeys(order, commonComponents::contains);
        return (left, right) -> {
            int result;
            for (Map.Entry<Component, Order.Direction> entry : commonOrder.entrySet()) {
                Component component = entry.getKey();
                Order.Direction direction = entry.getValue();

                Map<Dataset, Component> map = identifierTable.row(component);
                Component leftComponent = map.get(leftDataset);
                Component rightComponent = map.get(rightDataset);

                VTLObject leftValue = left.get(leftComponent);
                VTLObject rightValue = right.get(rightComponent);

                result = Order.NULLS_FIRST.compare(leftValue, rightValue);

                if (result != 0)
                    return direction == ASC ? result : -1 * result;

            }
            return 0;
        };
    }

    private Order.Builder createOrder(Dataset dataset, Order requiredOrder) {
        // We are just making sure all common identifier are sorted.
        LinkedHashSet<String> identifiersOrder = Sets.newLinkedHashSet(
                createCommonIdentifiers().keySet()
        );
        System.out.printf("Asking for %s sorted with %s\n", dataset, identifiersOrder);

        DataStructure structure = dataset.getDataStructure();
        Order.Builder builder = Order.create(structure);

        for (String id : identifiersOrder)
            builder.put(id, Order.Direction.ASC); // TODO: Can fail.

        return builder;

//        for (Map.Entry<Component, Order.Direction> order : requiredOrder.entrySet()) {
//            if (structure.containsValue(order.getKey())) {
//                String name = structure.getName(order.getKey());
//                identifiersOrder.remove(name);
//                builder.put(name, order.getValue());
//            }
//        }
//        for (String id : identifiersOrder) {
//            if (structure.containsKey(id))
//                builder.put(id, Order.Direction.ASC);
//        }
        //return builder;
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order requestedOrder, Filtering filtering, Set<String> components) {

        // TODO: Filtering
        // TODO: Components

        // Optimization.
        if (datasets.size() == 1) {
            Dataset dataset = datasets.values().iterator().next();
            return Optional.of(getSorted(dataset, requestedOrder));
        }

        Iterator<Dataset> iterator = datasets.values().iterator();

        Dataset left = iterator.next();
        Dataset right = left;

        // Create the resulting data points.
        final DataStructure joinStructure = getDataStructure();
        final DataStructure structure = left.getDataStructure();
        Stream<JoinDataPoint> result = getSorted(left, requestedOrder)
                .map(dataPoint -> {
                    return joinStructure.fromMap(
                            structure.asMap(dataPoint)
                    );
                }).map(JoinDataPoint::new);

        while (iterator.hasNext()) {
            left = right;
            right = iterator.next();
            result = StreamSupport.stream(
                    new JoinSpliterator<>(
                            createKeyComparator(left, right, requestedOrder),
                            result.spliterator(),
                            getSorted(right, requestedOrder).map(JoinDataPoint::new).spliterator(),
                            left.getDataStructure()::asMap,
                            right.getDataStructure()::asMap,
                            getMerger(left, right)
                    ), false
            );
        }
        return Optional.of(result.map(tuple -> tuple));
    }

    @Override
    public Stream<DataPoint> getData() {
        return getData(Order.createDefault(getDataStructure()), null, null).get();
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
                    newDataStructure.put(datasetName.concat("_".concat(componentEntry.getKey())), componentEntry.getValue());
                } else {
                    if (ids.add(componentEntry.getKey())) {
                        newDataStructure.put(componentEntry);
                    }
                }
            }
        }
        return newDataStructure.build();
    }

    public Bindings getJoinScope() {
        return joinScope;
    }


    /**
     * Wrapper that helps with superset calculation.
     */
    private final static class SuperSetWrapper {

        private final Class<?> clazz;
        private final String name;

        private SuperSetWrapper(Class<?> clazz, String name) {
            this.clazz = clazz;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SuperSetWrapper that = (SuperSetWrapper) o;
            return Objects.equal(clazz, that.clazz) &&
                    Objects.equal(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(clazz, name);
        }
    }

    /**
     * Holds the "working dataset" dataPoint.
     */
    static final class JoinDataPoint extends DataPoint implements RandomAccess {

        public JoinDataPoint(List<VTLObject> ids) {
            super(ids);
        }


    }

}
