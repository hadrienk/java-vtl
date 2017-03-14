package no.ssb.vtl.script.operations.hierarchy;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.junit.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.*;
import static no.ssb.vtl.model.Order.Direction.*;

public class HierarchyOperationTest extends RandomizedTest {

    private VTLPrintStream printStream = new VTLPrintStream(System.out);

    //@Test
    public void testNetwork() throws Exception {

        MutableNetwork<String, String> build = NetworkBuilder.undirected().build();

        build.addNode("Austria");
        build.addNode("Italy");
        build.addNode("Holland");
        build.addNode("Belgium");
        build.addNode("Luxembourg");

        build.addNode("Benelux");
        build.addNode("European Union");

        build.addEdge("European Union", "Austria", "+");
        build.addEdge("Italy", "Austria", "+");
        build.addEdge("Holland", "Austria", "+");
        build.addEdge("Belgium", "Austria", "+");
        build.addEdge("Luxembourg", "Austria", "+");

        build.addEdge("Benelux", "Holland", "+");
        build.addEdge("Benelux", "Belgium", "+");
        build.addEdge("Benelux", "Luxembourg", "+");

    }

    @Test
    public void testGraph() throws Exception {

        MutableGraph<String> graph = GraphBuilder.directed().allowsSelfLoops(false).build();
        graph.putEdge("Austria", "European Union");
        graph.putEdge("Italy", "European Union");
        graph.putEdge("Holland", "European Union");
        graph.putEdge("Belgium", "European Union");
        graph.putEdge("Luxembourg", "European Union");
        graph.putEdge("Holland", "Benelux");
        graph.putEdge("Belgium", "Benelux");
        graph.putEdge("Luxembourg", "Benelux");

// TODO
//        MutableValueGraph<String, Boolean> buckets = ValueGraphBuilder.from(graph).build();
//        buckets.putEdgeValue("Austria", "European Union", true);
//        buckets.putEdgeValue("Italy", "European Union", true);
//        buckets.putEdgeValue("Holland", "European Union", true);
//        buckets.putEdgeValue("Belgium", "European Union", true);
//        buckets.putEdgeValue("Luxembourg", "European Union", true);
//        buckets.putEdgeValue("Holland", "Benelux", true);
//        buckets.putEdgeValue("Belgium", "Benelux", true);
//        buckets.putEdgeValue("Luxembourg", "Benelux", true);

        // Year, Country, Pop
        Dataset population = createPopulationDataset();

        printStream.println(population);

        // Country is the identifier we operate on.
        // Population is the value we operate on.

        final DataStructure structure = population.getDataStructure();
        final List<Component> ics = Lists.newArrayList(structure.get("Year"));
        final Component value = structure.get("Population");
        final Component group = structure.get("Country");

        Order ordering = Order.create(structure)
                .put("Year", ASC)
                .put("Country", ASC)
                .build();

        Order aggregateOn = Order.create(structure)
                .put("Year", ASC)
                .build();

        Stream<DataPoint> sortedData = population.getData(ordering).get();

        Stream<DataPoint> newStream = StreamUtils.aggregate(
                sortedData,
                (prev, current) -> aggregateOn.compare(prev, current) == 0
        ).peek(o -> printStream.println(o))
                .map(dataPoints -> {

                    // Map the values.
                    Map<String, Integer> buckets = Maps.newHashMap();
                    for (DataPoint dataPoint : dataPoints) {
                        VTLObject groupObject = structure.asMap(dataPoint).get(group);
                        String node = (String) groupObject.get();
                        if (graph.nodes().contains(node)) {
                            VTLObject valueObject = structure.asMap(dataPoint).get(value);
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
                        for (Component ic : ics) {
                            asMap.put(ic, original.get(ic));
                        }

                        asMap.put(group, VTLObject.of(entry.getKey()));
                        asMap.put(value, VTLObject.of(entry.getValue()));
                    }
                    return result;

                }).flatMap(Collection::stream);

        printStream.println(createDataset(structure, newStream));

    }

    private Dataset createDataset(final DataStructure structure, final Stream<DataPoint> newStream) {
        return new Dataset() {
            @Override
            public Stream<DataPoint> getData() {
                return newStream;
            }

            @Override
            public Optional<Map<String, Integer>> getDistinctValuesCount() {
                return Optional.empty();
            }

            @Override
            public Optional<Long> getSize() {
                return Optional.empty();
            }

            @Override
            public DataStructure getDataStructure() {
                return structure;
            }
        };
    }

    private Dataset createPopulationDataset() {
        DataStructure structure = DataStructure.builder()
                .put("Year", IDENTIFIER, Year.class)
                .put("Country", IDENTIFIER, String.class)
                .put("Population", MEASURE, Integer.class)
                .build();

        List<Year> years = Lists.newArrayList(
                Year.of(2000),
                Year.of(2001),
                Year.of(2002),
                Year.of(2003)
        );

        List<String> countries = Lists.newArrayList(
                "Austria",
                "Belgium",
                "Holland",
                "Italy",
                "Luxembourg"
        );

        ArrayList<DataPoint> data = Lists.newArrayList();
        for (Year year : years) {
            for (String country : countries) {
                DataPoint point = structure.wrap(ImmutableMap.of(
                        "Year", year,
                        "Country", country,
                        "Population", randomIntBetween(0, 20)
                ));
                data.add(point);
            }
        }
        Collections.shuffle(data, new Random(randomLong()));

        return new Dataset() {
            @Override
            public Stream<DataPoint> getData() {
                return data.stream();
            }

            @Override
            public Optional<Map<String, Integer>> getDistinctValuesCount() {
                return Optional.empty();
            }

            @Override
            public Optional<Long> getSize() {
                return Optional.of((long) data.size());
            }

            @Override
            public DataStructure getDataStructure() {
                return structure;
            }
        };
    }
}
