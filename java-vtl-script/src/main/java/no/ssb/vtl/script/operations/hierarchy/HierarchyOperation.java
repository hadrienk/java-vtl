package no.ssb.vtl.script.operations.hierarchy;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.graph.Graph;
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
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class HierarchyOperation extends AbstractUnaryDatasetOperation {

    public static final String FROM_COLUMN_NAME = "from";
    public static final String TO_COLUMN_NAME = "to";
    public static final String SIGN_COLUMN_NAME = "sign";
    public static final String COLUMN_NOT_FOUND = "could not find the column %s";
    private static final Map<String, Composition> COMPOSITION_MAP = ImmutableMap.of(
            "", Composition.UNION,
            "+", Composition.UNION,
            "plus", Composition.UNION,
            "-", Composition.COMPLEMENT,
            "minus", Composition.COMPLEMENT
    );
    public static final String UNKNOWN_SIGN_VALUE = "unknown sign value %s";
    public static final String CIRCULAR_DEPENDENCY = "the edge %s -(%s)-> %s introduced a loop (%s)";
    private final ImmutableValueGraph<String, Composition> graph;
    private final Component id;
    private final Component value;

    // TODO: Error messages.
    public HierarchyOperation(Dataset dataset, ValueGraph<String, Composition> hierarchy, Component id, Component value) {
        super(dataset);

        this.id = checkNotNull(id);
        checkArgument(id.isIdentifier());

        this.value = checkNotNull(value);
        checkArgument(value.isMeasure());

        // TODO: Hierarchy should be typed.
        checkNotNull(hierarchy);
        checkArgument(hierarchy.isDirected());
        checkArgument(!hierarchy.allowsSelfLoops());
        this.graph = ImmutableValueGraph.copyOf(hierarchy);
    }

    public HierarchyOperation(Dataset dataset, Dataset hierarchy, Component id, Component value) {
        this(dataset, convertToHierarchy(hierarchy), id, value);
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
    private static ValueGraph<String, Composition> convertToHierarchy(final Dataset hierarchy) {

        // Checks.
        final DataStructure structure = checkNotNull(hierarchy).getDataStructure();
        Component fromComponent = checkNotNull(structure.get(FROM_COLUMN_NAME), COLUMN_NOT_FOUND, FROM_COLUMN_NAME);
        Component toComponent = checkNotNull(structure.get(TO_COLUMN_NAME), COLUMN_NOT_FOUND, TO_COLUMN_NAME);
        Component signComponent = checkNotNull(structure.get(SIGN_COLUMN_NAME), COLUMN_NOT_FOUND, SIGN_COLUMN_NAME);

        // The graph.
        MutableValueGraph<String, Composition> graph = ValueGraphBuilder.directed()
                .allowsSelfLoops(false)
                .build();

        // Add all the points.
        for (DataPoint point : (Iterable<? extends DataPoint>) hierarchy.getData()::iterator) {

            Map<Component, VTLObject> asMap = structure.asMap(point);

            String from = asMap.get(fromComponent).get().toString();
            String to = asMap.get(toComponent).get().toString();
            String sign = asMap.get(signComponent).get().toString();

            Composition composition = checkNotNull(COMPOSITION_MAP.get(sign), UNKNOWN_SIGN_VALUE, sign);

            List<List<String>> paths = checkNoPath(graph, to, from);
            checkArgument(paths.isEmpty(), CIRCULAR_DEPENDENCY, from, composition, to, paths);

            graph.putEdgeValue(from, to, composition);
        }
        return graph;
    }

    static List<List<String>> checkNoPath(Graph<String> graph, String from, String to) {
        List<List<String>> paths = Lists.newArrayList();
        if (graph.nodes().contains(from) && graph.nodes().contains(to)) {
            // DAG means no loop.
            // Each time we add nodes, check if there is a path
            // already. We don't check if we never saw one of them.

            //LinkedHashSet<String> path = Sets.newLinkedHashSet();
            Deque<String> stack = Lists.newLinkedList();
            stack.push(from);

            while (!stack.isEmpty()) {
                String current = stack.pop();

                if (current.equals(to)) {
                    // return false;
                    ArrayList<String> path = Lists.newArrayList();
                    path.add(from);
                    path.addAll(stack);
                    path.add(to);
                    paths.add(path);
                    continue;
                }
                for (String t : graph.successors(current)) {
                    if (!stack.contains(t)) {
                        stack.push(t);
                    }
                }
            }
        }
        return paths;
    }

    private static Order computeOrder(DataStructure structure, Component id) {
        Order.Builder builder = Order.create(structure);
        for (Component component : structure.values()) {
            if (!component.equals(id)) {
                builder.put(component, Order.Direction.ASC);
            }
        }
        return builder.build();
    }

    @Override
    protected DataStructure computeDataStructure() {
        return getChild().getDataStructure();
    }

    @Override
    public Stream<DataPoint> getData() {

        final DataStructure structure = getDataStructure();

        // Order by all ids, except the one we apply the hierarchy on.
        final Order requiredOrder = computeOrder(structure, id);

        // The identifiers we want to copy.
        final List<Component> identifiers = structure.values().stream()
                .filter(Component::isIdentifier)
                .filter(Predicate.isEqual(id).negate())
                .collect(Collectors.toList());

        // Get the data sorted.
        Stream<DataPoint> sortedData = getChild().getData(requiredOrder)
                .orElse(getChild().getData().sorted(requiredOrder));

        return StreamUtils.aggregate(
                sortedData,
                (prev, current) -> requiredOrder.compare(prev, current) == 0
        ).map(dataPoints -> {

            // Optimization
            if (dataPoints.size() == 1) {
                return dataPoints;
            }

            Map<String, Integer> buckets = Maps.newConcurrentMap();

            // Map the values.
            for (DataPoint dataPoint : dataPoints) {
                VTLObject groupObject = structure.asMap(dataPoint).get(this.id);
                String node = (String) groupObject.get();
                if (graph.nodes().contains(node) && !graph.successors(node).isEmpty()) {
                    VTLObject valueObject = structure.asMap(dataPoint).get(this.value);
                    buckets.put(node, (Integer) valueObject.get());
                }
            }

            // Aggregate
            for (String source : buckets.keySet()) {
                for (String destination : graph.successors(source)) {
                    buckets.merge(destination, buckets.get(source), Integer::sum);
                }
            }
            // TODO: Use graph.predecessors()
//            for (EndpointPair<String> pair : graph.edges()) {
//                if (buckets.containsKey(pair.source())) {
//                    buckets.merge(pair.target(), buckets.get(pair.source()), Integer::sum);
//                }
//            }

            Map<Component, VTLObject> original = structure.asMap(dataPoints.get(0));
            List<DataPoint> result = Lists.newArrayList();
            for (Map.Entry<String, Integer> entry : buckets.entrySet()) {

                DataPoint point = structure.wrap();
                result.add(point);

                Map<Component, VTLObject> asMap = structure.asMap(point);
                for (Component ic : identifiers) {
                    asMap.put(ic, original.get(ic));
                }

                asMap.put(this.id, VTLObject.of(entry.getKey()));
                asMap.put(this.value, VTLObject.of(entry.getValue()));
            }
            return result;

        }).flatMap(Collection::stream);
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
