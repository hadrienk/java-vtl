package no.ssb.vtl.script.operations.hierarchy;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.google.common.graph.ImmutableGraph;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class HierarchyOperation extends AbstractUnaryDatasetOperation {

    private final ImmutableGraph<String> graph;
    private final Component id;
    private final Component value;

    // TODO: Error messages.
    public HierarchyOperation(Dataset dataset, Graph<String> hierarchy, Component id, Component value) {
        super(dataset);

        this.id = checkNotNull(id);
        checkArgument(id.isIdentifier());

        this.value = checkNotNull(value);
        checkArgument(value.isMeasure());

        // TODO: Hierarchy should be typed.
        checkNotNull(hierarchy);
        checkArgument(hierarchy.isDirected());
        checkArgument(!hierarchy.allowsSelfLoops());
        this.graph = ImmutableGraph.copyOf(hierarchy);
    }

    public HierarchyOperation(Dataset dataset, Dataset hierarchy, Component id, Component value) {
        this(dataset, convertToHierarchy(hierarchy), id, value);
    }

    private static Graph<String> convertToHierarchy(Dataset hierarchy) {
        throw new UnsupportedOperationException("not implemented");
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

            Map<String, Integer> buckets = Maps.newHashMap();

            // Map the values.
            for (DataPoint dataPoint : dataPoints) {
                VTLObject groupObject = structure.asMap(dataPoint).get(this.id);
                String node = (String) groupObject.get();
                if (graph.nodes().contains(node)) {
                    VTLObject valueObject = structure.asMap(dataPoint).get(this.value);
                    buckets.put(node, (Integer) valueObject.get());
                }
            }

            // Aggregate
            for (EndpointPair<String> pair : graph.edges()) {
                if (buckets.containsKey(pair.source())) {
                    buckets.merge(pair.target(), buckets.get(pair.source()), Integer::sum);
                }
            }

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
