package no.ssb.vtl.script.operations.join;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.DatapointNormalizer;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class NewInnerJoinOperation extends AbstractJoinOperation {


    public NewInnerJoinOperation(Map<String, Dataset> namedDatasets) {
        this(namedDatasets, Collections.emptySet());
    }

    public NewInnerJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets.entrySet()
                .stream()
                .sorted(Comparator.comparing(stringDatasetEntry -> stringDatasetEntry.getValue().getSize().orElse(Long.MAX_VALUE))).collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue)), identifiers);
        // We need the identifiers in the case of inner join.
        ComponentBindings joinScope = this.getJoinScope();
        for (Component component : getCommonIdentifiers()) {
            joinScope.put(
                    getDataStructure().getName(component),
                    component
            );
        }
    }

    /**
     * Convert the {@link Order} so it uses the given structure.
     */
    private Order adjustOrderForStructure(Order orders, DataStructure dataStructure) {

        DataStructure structure = getDataStructure();
        Order.Builder adjustedOrders = Order.create(dataStructure);

        // Uses names since child structure can be different.
        for (Component component : orders.keySet()) {
            String name = structure.getName(component);
            if (dataStructure.containsKey(name))
                adjustedOrders.put(name, orders.get(component));
        }
        return adjustedOrders.build();
    }

    @Override
    public Stream<DataPoint> getData() {
        Order order = Order.createDefault(getDataStructure());
        return getData(order, input -> true, Collections.emptySet())
                .orElseThrow(() -> new RuntimeException("could not sort data"));
    }

    /**
     * TODO: Move to the {@link no.ssb.vtl.model.AbstractDatasetOperation}.
     */
    private Stream<DataPoint> getOrSortData(Dataset dataset, Order order, Dataset.Filtering filtering, Set<String> components) {
        Optional<Stream<DataPoint>> sortedData = dataset.getData(order, filtering, components);
        if (sortedData.isPresent()) {
            return sortedData.get();
        } else {
            return dataset.getData().sorted(order).filter(filtering);
        }
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order requestedOrder, Dataset.Filtering filtering, Set<String> components) {

        // Try to create a compatible order. If not, the caller will have to sort
        // the result manually.
        Optional<Order> compatibleOrder = createCompatibleOrder(getDataStructure(), getCommonIdentifiers(), requestedOrder);
        if (!compatibleOrder.isPresent())
            return Optional.empty();

        Order requiredOrder = compatibleOrder.get();

        // Compute the predicate
        Order predicate = computePredicate(requiredOrder);

        Iterator<Dataset> iterator = datasets.values().iterator();
        Dataset left = iterator.next();
        Dataset right = left;

        Table<Component, Dataset, Component> componentMapping = getComponentMapping();
        Stream<DataPoint> result = getOrSortData(
                left,
                adjustOrderForStructure(requiredOrder, left.getDataStructure()),
                filtering,
                components
        ).peek(dataPoint -> {
            dataPoint.ensureCapacity(getDataStructure().size());
            while (dataPoint.size() < getDataStructure().size()) {
                dataPoint.add(VTLObject.NULL);
            }
        });
        while (iterator.hasNext()) {
            left = right;
            right = iterator.next();
            result = StreamSupport.stream(
                    new InnerJoinSpliterator<>(
                            new JoinKeyExtractor(left.getDataStructure(), predicate, componentMapping.column(left)),
                            new JoinKeyExtractor(right.getDataStructure(), predicate, componentMapping.column(right)),
                            predicate,
                            getMerger(left, right),
                            result.spliterator(),
                            getOrSortData(
                                    right,
                                    adjustOrderForStructure(requiredOrder, right.getDataStructure()),
                                    filtering,
                                    components
                            ).spliterator()
                    ), false
            );
        }
        return Optional.of(result);
    }

    /**
     * Compute the predicate.
     *
     * @param requestedOrder the requested order.
     * @return order of the common identifiers only.
     */
    private Order computePredicate(Order requestedOrder) {
        DataStructure structure = getDataStructure();

        // We need to create a fake structure to allow the returned
        // Order to work with the result of the key extractors.
        DataStructure.Builder fakeStructure = DataStructure.builder();
        for (Component component : getCommonIdentifiers()) {
            fakeStructure.put(structure.getName(component), component);
        }

        Order.Builder predicateBuilder = Order.create(fakeStructure.build());
        for (Component component : getCommonIdentifiers()) {
            predicateBuilder.put(component, requestedOrder.get(component));
        }
        return predicateBuilder.build();
    }

    @Override
    protected BiFunction<DataPoint, DataPoint, DataPoint> getMerger(
            final Dataset leftDataset, final Dataset rightDataset
    ) {

        DataStructure structure = getDataStructure();
        ImmutableList<Component> leftList = ImmutableList.copyOf(structure.values());

        DataStructure rightStructure = rightDataset.getDataStructure();
        ImmutableList<Component> rightList = ImmutableList.copyOf(rightStructure.values());

        // Save the indexes of the right data point that need to be moved to the left.
        final ImmutableListMultimap<Integer, Integer> indexMap;

        ImmutableListMultimap.Builder<Integer, Integer> indexMapBuilder = ImmutableListMultimap.builder();
        for (int i = 0; i < rightList.size(); i++) {
            Component rightComponent = rightList.get(i);
            for (int j = 0; j < leftList.size(); j++) {
                Component leftComponent = leftList.get(j);
                if (rightComponent.equals(leftComponent)) {
                    indexMapBuilder.put(i, j);
                }
            }
        }
        indexMap = indexMapBuilder.build();

        return (left, right) -> {

            if (left == null || right == null)
                return null;

            for (Map.Entry<Integer, Integer> entry : indexMap.entries())
                left.set(entry.getValue(), right.get(entry.getKey()));

            return DataPoint.create(left);
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
