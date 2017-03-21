package no.ssb.vtl.script.operations.hierarchy;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import com.google.common.graph.ImmutableValueGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static java.util.stream.Collectors.*;

public class HierarchyOperation extends AbstractUnaryDatasetOperation {

    private static final String FROM_COLUMN_NAME = "from";
    private static final String TO_COLUMN_NAME = "to";
    private static final String SIGN_COLUMN_NAME = "sign";
    private static final String COLUMN_NOT_FOUND = "could not find the column %s";
    private static final String UNKNOWN_SIGN_VALUE = "unknown sign component %s";
    private static final String CIRCULAR_DEPENDENCY = "the edge %s -(%s)-> %s introduced a loop (%s)";
    private static final Map<String, Composition> COMPOSITION_MAP = ImmutableMap.of(
            "", Composition.UNION,
            "+", Composition.UNION,
            "plus", Composition.UNION,
            "-", Composition.COMPLEMENT,
            "minus", Composition.COMPLEMENT
    );

    private final ImmutableValueGraph<VTLObject, Composition> graph;

    // The components to "group by"
    private final Set<Component> group;

    // The component to map with the hierarchy.
    private final Component component;

    // TODO: Error messages.
    public HierarchyOperation(Dataset dataset, ValueGraph<VTLObject, Composition> hierarchy, Set<Component> group, Component component) {
        super(dataset);

        // TODO: Check that component are in structure in the computeStructure.
        this.group = checkNotNull(group);
        for (Component groupComponent : group) {
            checkArgument(groupComponent.isIdentifier());
        }

        // TODO: Check that component are in structure in the computeStructure.
        this.component = checkNotNull(component);
        checkArgument(component.isIdentifier());

        // TODO: Hierarchy should be typed.
        checkNotNull(hierarchy);
        checkArgument(hierarchy.isDirected());
        checkArgument(!hierarchy.allowsSelfLoops());
        this.graph = ImmutableValueGraph.copyOf(hierarchy);
    }

    public HierarchyOperation(Dataset dataset, Dataset hierarchy, Set<Component> group, Component component) {
        this(dataset, convertToHierarchy(hierarchy), group, component);
    }

    /**
     * Create the directed acyclic graph from the dataset.
     * <p>
     * The dataset is required to have the following columns:
     * from, to, sign.
     *
     * @throws IllegalArgumentException if a circular dependency is found.
     * @throws IllegalArgumentException if from and to are not of the same type.
     */
    private static ValueGraph<VTLObject, Composition> convertToHierarchy(final Dataset hierarchy) {

        // Checks.
        final DataStructure structure = checkNotNull(hierarchy).getDataStructure();
        Component fromComponent = checkNotNull(structure.get(FROM_COLUMN_NAME), COLUMN_NOT_FOUND, FROM_COLUMN_NAME);
        Component toComponent = checkNotNull(structure.get(TO_COLUMN_NAME), COLUMN_NOT_FOUND, TO_COLUMN_NAME);
        Component signComponent = checkNotNull(structure.get(SIGN_COLUMN_NAME), COLUMN_NOT_FOUND, SIGN_COLUMN_NAME);

        // The graph.
        MutableValueGraph<VTLObject, Composition> graph = ValueGraphBuilder.directed()
                .allowsSelfLoops(false)
                .build();

        // Add all the points.
        for (DataPoint point : (Iterable<? extends DataPoint>) hierarchy.getData()::iterator) {

            Map<Component, VTLObject> asMap = structure.asMap(point);

            VTLObject from = asMap.get(fromComponent);
            VTLObject to = asMap.get(toComponent);
            VTLObject sign = asMap.get(signComponent);

            Composition composition = checkNotNull(COMPOSITION_MAP.get(sign.get()), UNKNOWN_SIGN_VALUE, sign);

            List<List<VTLObject>> paths = findPaths(graph, to, from);
            checkArgument(paths.isEmpty(), CIRCULAR_DEPENDENCY, from, composition, to, paths);

            graph.putEdgeValue(from, to, composition);
        }
        return graph;
    }

    static <T> LinkedList<T> sortTopologically(ValueGraph<T, Composition> graph) {
        // Kahn's algorithm
        MutableValueGraph<T, Composition> g = Graphs.copyOf(graph);
        LinkedList<T> sorted = Lists.newLinkedList();
        Deque<T> leaves = Lists.newLinkedList(g.nodes()
                .stream()
                .filter(n -> g.inDegree(n) == 0)
                .collect(toList())
        );
        while (!leaves.isEmpty()) {
            T node = leaves.pop();
            sorted.push(node);
            Set<T> successors = ImmutableSet.copyOf(g.successors(node));
            for (T successor : successors) {
                g.removeEdge(node, successor);
                if (g.inDegree(successor) == 0) {
                    leaves.addLast(successor);
                }
            }
        }
        checkArgument(g.edges().isEmpty(), "the graph contains a circular dependency %s", g);
        Collections.reverse(sorted);
        return sorted;
    }

    /**
     * Try to find a path in the graph between from and to.
     *
     * @return a list (possibly empty) of paths
     */
    static <T> List<List<T>> findPaths(Graph<T> graph, T from, T to) {
        List<List<T>> paths = Lists.newArrayList();
        if (graph.nodes().contains(from) && graph.nodes().contains(to)) {
            // DAG means no loop.
            // Each time we add nodes, check if there is a path
            // already. We don't check if we never saw one of them.

            //LinkedHashSet<String> path = Sets.newLinkedHashSet();
            Deque<T> stack = Lists.newLinkedList();
            stack.push(from);

            while (!stack.isEmpty()) {
                T current = stack.pop();

                if (current.equals(to)) {
                    // return false;
                    ArrayList<T> path = Lists.newArrayList();
                    path.add(from);
                    path.addAll(stack);
                    path.add(to);
                    paths.add(path);
                    continue;
                }
                for (T t : graph.successors(current)) {
                    if (!stack.contains(t)) {
                        stack.push(t);
                    }
                }
            }
        }
        return paths;
    }

    private Order computeOrder() {
        // Sort by all the identifiers we are grouping on but the hierarchy element.
        // The hierarchy element has to be the last one.
        Order.Builder builder = Order.create(getDataStructure());
        for (Component component : group) {
            if (!component.equals(this.component)) {
                builder.put(component, Order.Direction.ASC); // TODO: Could be ASC or DESC
            }
        }
        builder.put(component, Order.Direction.ASC); // TODO: Could be ASC or DESC
        return builder.build();
    }

    @Override
    protected DataStructure computeDataStructure() {
        return getChild().getDataStructure();
    }

    @Override
    public Stream<DataPoint> getData() {

        final DataStructure structure = getDataStructure();
        final Order groupOrder = computeOrder();
        final Order groupPredicate = computePredicate();

        // TODO: Save the graph in the correct order.
        final LinkedList<VTLObject> sorted = sortTopologically(this.graph);

        // Get the data sorted.
        Stream<DataPoint> sortedData = getChild().getData(groupOrder)
                .orElse(getChild().getData().sorted(groupOrder));

        Stream<SignedDataPoint> streamToAggregate = StreamUtils.aggregate(
                sortedData,
                (prev, current) -> groupPredicate.compare(prev, current) == 0
        ).map(dataPoints -> {

            // Organize the data points in "buckets" for each group. Here we add "sign" information
            // to the data points so that we can use it later when we aggregate.
            Multimap<VTLObject, SignedDataPoint> buckets = ArrayListMultimap.create();
            for (DataPoint dataPoint : dataPoints) {
                Map<Component, VTLObject> map = structure.asMap(dataPoint);
                VTLObject group = map.get(this.component);
                buckets.put(group, new SignedDataPoint(dataPoint, Composition.UNION));
            }

            // TODO: Filter the nodes by the keys of the bucket (and check that it is faster)
            // For each group put the content in every successors. If the edge was a complement (-) then we invert
            // the sign of each datapoint (ie. a - (b - c + d) = a - b + c - d)
            for (VTLObject node : sorted) {
                for (VTLObject successor : graph.successors(node)) {
                    Composition sign = graph.edgeValue(node, successor);
                    for (SignedDataPoint point : buckets.get(node)) {
                        if (Composition.COMPLEMENT.equals(sign)) {
                            // Invert if complement.
                            buckets.put(successor, SignedDataPoint.invert(point));
                        } else {
                            buckets.put(successor, new SignedDataPoint(point, point.sign));
                        }
                    }
                }
            }

            // Put the new "mapped" component
            List<SignedDataPoint> result = Lists.newArrayList();
            for (Map.Entry<VTLObject, SignedDataPoint> entry : buckets.entries()) {
                VTLObject group = entry.getKey();
                SignedDataPoint point = entry.getValue();
                result.add(point);
                structure.asMap(point).put(this.component, group);
            }

            // Not needed since we are constructing the result by group.
            // Collections.sort(result, groupOrder);

            return result;

        }).flatMap(Collection::stream);


        return StreamUtils.aggregate(
                streamToAggregate,
                (dataPoint, dataPoint2) -> groupOrder.compare(dataPoint, dataPoint2) == 0
        ).map(dataPoints -> {

            DataPoint aggregate;
            // Optimization.
            if (dataPoints.size() > 1) {


                // TODO: extract merge function. Use static for now.
                Component value = structure.get("BELOP");

                // Won't fail since we check size.
                aggregate = DataPoint.create(dataPoints.get(0));
                Map<Component, VTLObject> result = structure.asMap(aggregate);
                result.put(value, null);


                Iterator<SignedDataPoint> iterator = dataPoints.iterator();
                while (iterator.hasNext()) {
                    SignedDataPoint signedDataPoint = iterator.next();

                    Map<Component, VTLObject> next = structure.asMap(signedDataPoint);
                    VTLObject objectValue = next.get(value);
                    if (Composition.COMPLEMENT.equals(signedDataPoint.getSign())) {
                        objectValue = new VTLObject() {
                            @Override
                            public Object get() {
                                Integer integer = (Integer) next.get(value).get();
                                return -1 * integer;
                            }
                        };
                    }
                    result.merge(value, objectValue, (vtlObject, vtlObject2) -> {
                        return VTLObject.of(Integer.sum((Integer) vtlObject.get(), (Integer) vtlObject2.get()));
                    });
                }
            } else {
                aggregate = dataPoints.get(0);
            }

            return aggregate;
        });
    }

    private Order computePredicate() {
        // Same as the groupOrder, but we exclude the hierarchy component.
        Order.Builder builder = Order.create(getDataStructure());
        for (Map.Entry<Component, Order.Direction> direction : computeOrder().entrySet()) {
            if (!direction.getKey().equals(this.component)) {
                builder.put(direction);
            }
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

    static class SignedDataPoint extends DataPoint {
        private final Composition sign;

        public SignedDataPoint(Collection<? extends VTLObject> c, Composition sign) {
            super(c);
            this.sign = checkNotNull(sign);
        }

        static SignedDataPoint invert(SignedDataPoint original) {
            switch (original.getSign()) {
                case COMPLEMENT:
                    return new SignedDataPoint(original, Composition.UNION);
                case UNION:
                    return new SignedDataPoint(original, Composition.COMPLEMENT);
                default:
                    throw new IllegalArgumentException("unknown sign");
            }
        }

        Composition getSign() {
            return sign;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(super.toString())
                    .addValue(sign)
                    .toString();
        }
    }
}
