package no.ssb.vtl.script.operations.hierarchy;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.junit.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.*;

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

        // Year, Country, Pop
        Dataset population = createPopulationDataset();

        printStream.println(population);

        // Country is the identifier we operate on.
        // Population is the value we operate on.
        DataStructure structure = population.getDataStructure();
        Component value = structure.get("Population");
        Component identifier = structure.get("Country");

        HierarchyOperation result = new HierarchyOperation(population, graph, identifier, value);
        printStream.println(result);

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
