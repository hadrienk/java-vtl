package no.ssb.vtl.script.operations.join;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.DatapointNormalizer;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class NewInnerJoinOperation extends AbstractJoinOperation  {


    public NewInnerJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets, Collections.emptySet());
    }

    public NewInnerJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets, identifiers);
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

    private Order addCommonIdentifiersIfMissing(Order requestedOrder) {
        // Get all the requested directions.
        Order.Builder newOrder = Order.create(getDataStructure());
        Sets.SetView<Component> commonComponents = Sets.difference(requestedOrder.keySet(), getCommonIdentifiers());
        for (Component component : commonComponents) {
            newOrder.put(component, requestedOrder.get(component));
        }

        //Add the identifier components that are missing.
        Sets.SetView<Component> missingComponents = Sets.difference(getCommonIdentifiers(), requestedOrder.keySet());
        for (Component component : missingComponents) {
            newOrder.put(component, Order.Direction.ASC);
        }
        return newOrder.build();
    }

    private Stream<DataPoint> normalizeDataset(Dataset dataset, Order requiredOrder) {
        List<String> identifierNames = Lists.transform(
                getCommonIdentifiers().asList(),
                input -> getDataStructure().getName(input)
        );
        Order childOrder = adjustOrderForStructure(requiredOrder, dataset.getDataStructure());
        return dataset.getData(childOrder).get().map(new DatapointNormalizer(dataset.getDataStructure(), getDataStructure(), identifierNames::contains));
    }

    @Override
    public Stream<DataPoint> getData() {
        Order order = Order.createDefault(getDataStructure());
        return getData(order, null, null)
                .orElseThrow(() -> new RuntimeException("could not sort data"));
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order requestedOrder, Dataset.Filtering filtering, Set<String> components) {
        Order requiredOrder = addCommonIdentifiersIfMissing(requestedOrder);

        Iterator<Dataset> iterator = datasets.values().iterator();
        Dataset left = iterator.next();
        Dataset right = left;

        Stream<DataPoint> result = normalizeDataset(left, requiredOrder);
        while (iterator.hasNext()) {
            left = right;
            right = iterator.next();
            result = StreamSupport.stream(
                    new InnerJoinSpliterator<>(
                            requiredOrder,
                            getMerger(left, right),
                            result.spliterator(),
                            normalizeDataset(right, requiredOrder).spliterator()
                    ), false
            );
        }
        return Optional.of(result);
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
        ImmutableMap.Builder<Integer, Integer> indexMapBuilder = ImmutableMap.builder();
        for (Map.Entry<Component, Component> entry : getComponentMapping().column(rightDataset).entrySet()) {
            Component leftComponent = entry.getKey();
            Component rightComponent = entry.getValue();
            indexMapBuilder.put(rightList.indexOf(rightComponent), leftList.lastIndexOf(leftComponent));
        }
        final ImmutableMap<Integer, Integer> indexMap = indexMapBuilder.build();

        return (left, right) -> {

            if (left == null || right == null)
                return null;

            left.ensureCapacity(structure.size());
            while (left.size() < structure.size()) {
                left.add(null);
            }

            for (Map.Entry<Integer, Integer> entry : indexMap.entrySet())
                left.set(entry.getValue(), right.get(entry.getKey()));

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
