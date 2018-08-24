package no.ssb.vtl.script.operations.hierarchy;

import com.google.common.base.MoreObjects;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Streams;
import com.google.common.graph.ImmutableValueGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static no.ssb.vtl.script.operations.hierarchy.HierarchyOperation.ComposedDataPoint;
import static no.ssb.vtl.script.operations.hierarchy.HierarchyOperation.sortTopologically;

/**
 * A hierarchy that uses a set of identifier to create sub graphs.
 */
public class MultiHierarchyOperation extends AbstractUnaryDatasetOperation {

    private final ImmutableSet<Component> group;
    private final Component component;
    private TreeMap<DataPoint, ImmutableValueGraph<VTLObject, Composition>> graphMap;

    public MultiHierarchyOperation(Dataset child, Dataset hierarchy, Component component, Set<Component> group) {
        super(child);
        this.group = ImmutableSet.copyOf(group);
        this.component = component;
    }

    public MultiHierarchyOperation(Dataset child, Dataset hierarchy, String component, Set<String> group) {
        super(child);
        DataStructure structure = child.getDataStructure();
        this.component = structure.get(component);
        this.group = group.stream().map(structure::get).collect(ImmutableSet.toImmutableSet());
    }

    /**
     * Returns an iterator that stops when predicate(T0, Tn) == false
     */
    public static <T> PeekingIterator<T> slice(
            PeekingIterator<T> source,
            BiPredicate<T,T> predicate,
            BiFunction<T, Stream<T>, Stream<T>> handler) {
        Spliterator<T> spliterator;

        if (!source.hasNext()) {
            spliterator = Spliterators.emptySpliterator();
        } else {

            Iterator<T> iterator = new AbstractIterator<T>() {

                T first = source.peek();

                @Override
                protected T computeNext() {
                    if (source.hasNext() && predicate.test(first, source.peek())) {
                        return source.next();
                    } else {
                        return endOfData();
                    }
                }
            };
            spliterator = handler.apply(source.peek(), Streams.stream(iterator)).spliterator();
        }

        return Iterators.peekingIterator(Spliterators.iterator(spliterator));
    }

    public static <T> Stream<T> split(Stream<T> source, BiPredicate<T, T> predicate, BiFunction<T, Stream<T>, Stream<T>> handler) {
        return StreamSupport.stream(() -> {

            PeekingIterator<T> iterator = Iterators.peekingIterator(Spliterators.iterator(source.spliterator()));

            if (!iterator.hasNext()) {
                return Spliterators.emptySpliterator();
            }

            return Spliterators.spliterator(new AbstractIterator<T>() {
                PeekingIterator<T> current;
                @Override
                protected T computeNext() {
                    if ((current == null || !current.hasNext())) {
                        current = slice(iterator, predicate, handler);
                    }
                    if (current.hasNext()) {
                        return current.next();
                    } else {
                        return endOfData();
                    }
                }
            }, Long.MAX_VALUE, 0);
        }, 0, false);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .toString();
    }

    @Override
    protected DataStructure computeDataStructure() {
        return getChild().getDataStructure();
    }

    public Order computeOrder(Order order) {
        Order.Builder builder = Order.create(getDataStructure());
        // Group first.
        builder.putAll(getGroupOrder(order));
        // Then component.
        builder.putAll(getComponentOrder(order));
        // Everything else.
        Order groupAndComponent = builder.build();
        for (Component component : order.keySet()) {
            if (!groupAndComponent.containsKey(component)) {
                builder.put(component, order.get(component));
            }
        }
        return builder.build();
    }

    @Override
    public Stream<DataPoint> getData() {
        Dataset child = getChild();
        DataStructure structure = getDataStructure();

        Order defaultOrder = Order.createDefault(structure);
        Order order = computeOrder(defaultOrder);
        Stream<DataPoint> sortedData = child.getData(order).orElseGet(() -> getData().sorted(order));

        Order groupOrder = getGroupOrder(defaultOrder);

        return split(sortedData, (left, right) -> groupOrder.compare(left, right) == 0, (first, slice) -> {
            // Get the graph for this slice.

            // The graph.
            ImmutableValueGraph<VTLObject, Composition> graph = graphMap.computeIfAbsent(first, this::computeGraph);

            MutableValueGraph<VTLObject, Composition> graphBuilder = ValueGraphBuilder.directed()
                    .allowsSelfLoops(false)
                    .build();

            slice.forEach(dataPoint -> {
                Map<Component, VTLObject> map = structure.asMap(dataPoint);
                VTLObject group = map.get(this.component);
                Set<VTLObject> successors = graph.successors(group);
            });



            ImmutableValueGraph<VTLObject, Composition> graph2 = graphMap.computeIfAbsent(first, this::computeGraph);
            LinkedList<VTLObject> sorted = sortTopologically(graph);

            List<DataPoint> dataPoints = slice.collect(Collectors.toList());

            // Organize the data points in "buckets" for each component. Here we add "sign" information
            // to the data points so that we can use it later when we aggregate.
            Multimap<VTLObject, ComposedDataPoint> buckets = ArrayListMultimap.create();
            for (DataPoint dataPoint : dataPoints) {
                Map<Component, VTLObject> map = structure.asMap(dataPoint);
                VTLObject group = map.get(this.component);
                buckets.put(group, new ComposedDataPoint(dataPoint, Composition.UNION));
            }

            for (VTLObject node : sorted) {
                for (VTLObject successor : graph.successors(node)) {
                    Composition sign = graph.edgeValue(node, successor);
                    for (ComposedDataPoint point : buckets.get(node)) {
                        if (Composition.COMPLEMENT.equals(sign)) {
                            // Invert if complement.
                            buckets.put(successor, ComposedDataPoint.invert(point));
                        } else {
                            buckets.put(successor, new ComposedDataPoint(point, point.sign));
                        }
                    }
                }
            }

            // Put the new "mapped" component
            List<ComposedDataPoint> result = Lists.newArrayList();
            for (Map.Entry<VTLObject, ComposedDataPoint> entry : buckets.entries()) {
                VTLObject group = entry.getKey();
                ComposedDataPoint point = entry.getValue();
                result.add(point);
                structure.asMap(point).put(this.component, group);
            }

            return Stream.empty();

        });
    }

    private ImmutableValueGraph<VTLObject, Composition> computeGraph(DataPoint dataPoint) {
        return null;
    }

    private Order getComponentOrder(Order order) {
        Order.Builder builder = Order.create(getDataStructure());
        builder.put(this.component, order.getOrDefault(component, Order.Direction.ASC));
        return builder.build();
    }

    private Order getGroupOrder(Order order) {
        Order.Builder builder = Order.create(getDataStructure());
        for (Component component : this.group) {
            builder.put(component, order.getOrDefault(component, Order.Direction.ASC));
        }
        return builder.build();
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        return Optional.empty();
    }
}
