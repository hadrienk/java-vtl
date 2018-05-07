package no.ssb.vtl.script.operations.union;

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

import com.codepoetics.protonpack.StreamUtils;
import com.codepoetics.protonpack.selectors.Selector;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.AbstractDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.DatapointNormalizer;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;

/**
 * Union operator
 */
public class UnionOperation extends AbstractDatasetOperation {

    @VisibleForTesting
    UnionOperation(Dataset... dataset) {
        this(asList(dataset));
    }

    public UnionOperation(List<Dataset> datasets) {
        super(datasets);
        Iterator<Dataset> iterator = datasets.iterator();
        DataStructure firstDataStructure = iterator.next().getDataStructure();
        while (iterator.hasNext())
            checkDataStructures(firstDataStructure, iterator.next().getDataStructure());
    }

    @Override
    protected DataStructure computeDataStructure() {
        return getChildren().get(0).getDataStructure();
    }

    private void checkDataStructures(DataStructure baseDataStructure, DataStructure nextDataStructure) {
        // Identifiers and attribute should be equals in name, role and type.
        Set<String> requiredNames = nonAttributeNames(baseDataStructure);
        Set<String> providedNames = nonAttributeNames(nextDataStructure);

        checkArgument(
                requiredNames.equals(providedNames),
                "dataset was incompatible with the required data structure, missing: %s, unexpected %s",
                Sets.difference(requiredNames, providedNames),
                Sets.difference(providedNames, requiredNames)
        );

        Map<String, Component.Role> requiredRoles = Maps.filterKeys(baseDataStructure.getRoles(), requiredNames::contains);
        Map<String, Component.Role> providedRoles = Maps.filterKeys(nextDataStructure.getRoles(), requiredNames::contains);

        checkArgument(
                requiredRoles.equals(providedRoles),
                "dataset was incompatible with the required data structure, missing: %s, unexpected %s",
                Sets.difference(requiredRoles.entrySet(), providedRoles.entrySet()),
                Sets.difference(providedRoles.entrySet(), requiredRoles.entrySet())
        );

        Map<String, Class<?>> requiredTypes = Maps.filterKeys(baseDataStructure.getTypes(), requiredNames::contains);
        Map<String, Class<?>> providedTypes = Maps.filterKeys(nextDataStructure.getTypes(), requiredNames::contains);

        checkArgument(
                requiredTypes.equals(providedTypes),
                "dataset was incompatible with the required data structure, missing: %s, unexpected %s",
                Sets.difference(requiredTypes.entrySet(), providedTypes.entrySet()),
                Sets.difference(providedTypes.entrySet(), requiredTypes.entrySet())
        );

    }

    private Set<String> nonAttributeNames(DataStructure dataStructure) {
        return Maps.filterValues(dataStructure.getRoles(), role -> role != Component.Role.ATTRIBUTE).keySet();
    }

    /**
     * Premeare the children streams.
     *
     * This method makes sure that:
     * <ul>
     *     <li>the orders sent to the children matches with the child's structure</li>
     *     <li>the streams are sorted</li>
     *     <li>the datapoint respect the union's structure</li>
     * </ul>
     *
     */
    List<Stream<DataPoint>> prepareChildren(Order orders, Filtering filtering, Set<String> components) {
        List<Stream<DataPoint>> streams = Lists.newArrayList();
        for (Dataset dataset : getChildren()) {
            Order adjustedOrders = adjustOrderForStructure(orders, dataset.getDataStructure());
            Stream<DataPoint> s = sortIfNeeded(filtering, components, dataset, adjustedOrders);
            streams.add(s.map(new DatapointNormalizer(dataset.getDataStructure(), getDataStructure())));
        }
        return streams;
    }

    /**
     * Manually sort the the stream if the given dataset failed to do so.
     */
    private Stream<DataPoint> sortIfNeeded(Filtering filtering, Set<String> components, Dataset dataset, Order adjustedOrders) {
        Optional<Stream<DataPoint>> stream = dataset.getData(adjustedOrders, filtering, components);
        return stream.orElseGet(() -> getData().sorted(adjustedOrders).filter(filtering));
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order orders, Filtering filtering, Set<String> components) {

        // Optimization.
        if (getChildren().size() == 1)
            return getChildren().get(0).getData(orders, filtering, components);

        // Union requires data to be sorted on all identifiers.
        Order orderWithIdentifiers = createOrderWithIdentifiers(orders);

        List<Stream<DataPoint>> streams = prepareChildren(
                orderWithIdentifiers,
                filtering,
                components
        );

        if (streams.size() == 1)
            return Optional.of(streams.get(0));

        Stream<DataPoint> result = StreamUtils.interleave(
                createSelector(orderWithIdentifiers), streams
        ).map(new DuplicateChecker(orderWithIdentifiers, getDataStructure()));

        return Optional.of(result);
    }

    /**
     * Add missing identifiers in the given {@link Order}.
     */
    private Order createOrderWithIdentifiers(Order orders) {
        DataStructure structure = getDataStructure();
        Order.Builder builder = Order.create(structure);
        builder.putAll(orders);

        for (Component component : structure.values()) {
            if(!component.isIdentifier())
                continue;
            if (orders.containsKey(component))
                continue;

            builder.put(component, orders.getOrDefault(component, Order.Direction.ASC));
        }

        return builder.build();
    }

    /**
     * Convert the {@link Order} so it uses the given structure.
     */
    private Order adjustOrderForStructure(Order orders, DataStructure dataStructure) {

        DataStructure structure = getDataStructure();
        Order.Builder adjustedOrders = Order.create(dataStructure);

        // Uses names since child structure can be different.
        for (Component component : orders.keySet()) {
            adjustedOrders.put(structure.getName(component), orders.get(component));
        }
        return adjustedOrders.build();
    }

    private <T> Selector<T> createSelector(Comparator<T> comparator) {
        return new MinimumSelector<>(comparator);
    }

    @Override
    public Stream<DataPoint> getData() {
        Optional<Stream<DataPoint>> ordered = this.getData(Order.createDefault(getDataStructure()));
        return ordered.orElseThrow(() -> new RuntimeException("could not sort"));
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        Long size = 0L;
        for (Dataset child : getChildren()) {
            Optional<Long> childSize = child.getSize();
            if (!childSize.isPresent())
                return Optional.empty();
            size += childSize.get();
        }
        return Optional.of(size);
    }

}
