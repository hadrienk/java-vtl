package no.ssb.vtl.script.operations.hierarchy;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Streams;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.google.common.io.Resources;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.brotli.dec.BrotliInputStream;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static java.util.stream.Collectors.*;
import static no.ssb.vtl.model.Component.Role.*;
import static no.ssb.vtl.script.operations.hierarchy.HierarchyOperation.*;
import static org.assertj.core.api.Assertions.*;

public class HierarchyOperationTest extends RandomizedTest {

    private VTLPrintStream printStream = new VTLPrintStream(System.out);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testAlgo() throws Exception {

        MutableValueGraph<String, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();

        graph.putEdgeValue("A", "B", Composition.UNION);
        graph.putEdgeValue("A", "C", Composition.UNION);

        graph.putEdgeValue("B", "C", Composition.COMPLEMENT);

        graph.putEdgeValue("D", "B", Composition.UNION);
        graph.putEdgeValue("E", "C", Composition.UNION);

        graph.putEdgeValue("F", "B", Composition.UNION);
        graph.putEdgeValue("F", "C", Composition.UNION);

        graph.putEdgeValue("G", "C", Composition.COMPLEMENT);
        graph.putEdgeValue("G", "E", Composition.UNION);

        graph.putEdgeValue("H", "E", Composition.UNION);

        graph.putEdgeValue("I", "C", Composition.UNION);
        graph.putEdgeValue("I", "F", Composition.UNION);

        graph.putEdgeValue("J", "F", Composition.COMPLEMENT);

        assertThat(Graphs.hasCycle(graph)).isFalse();

        ImmutableMap<String, Integer> allNodeValues = ImmutableMap.<String, Integer>builder()
                .put("A", 1).put("B", 2).put("C", 4)
                .put("D", 8).put("E", 16).put("F", 32)
                .put("G", 64).put("H", 128).put("I", 256)
                .put("J", 512).build();

        ImmutableMap<String, Integer> leavesValues = ImmutableMap.<String, Integer>builder()
                .put("A", 1).put("D", 8).put("G", 64)
                .put("H", 128).put("I", 256).put("J", 512)
                .build();


        LinkedList<String> sorted = sortTopologically(graph);

        // Go through the list, duplicating elements if union.
        Multimap<String, Integer> merged = LinkedHashMultimap.create();
        for (Map.Entry<String, Integer> entry : leavesValues.entrySet()) {
            merged.put(entry.getKey(), entry.getValue());
        }
        for (String node : sorted) {
            for (String successor : graph.successors(node)) {
                if (graph.edgeValue(node, successor) != Composition.UNION)
                    continue;

                merged.putAll(successor, merged.get(node));
            }
        }

        System.out.println(merged);
    }

    @Test
    public void testCheckNoPath() throws Exception {

        MutableGraph<String> graph = GraphBuilder.directed().allowsSelfLoops(false).build();

        graph.putEdge("a", "b1");
        graph.putEdge("a", "b2");
        graph.putEdge("a", "b3");
        graph.putEdge("b1", "c1");
        graph.putEdge("b1", "c2");
        graph.putEdge("b1", "c3");

        graph.putEdge("b2", "d1");
        graph.putEdge("b2", "d2");
        graph.putEdge("b2", "d3");

        graph.putEdge("b3", "e1");
        graph.putEdge("b3", "e2");
        graph.putEdge("b3", "e3");


        //graph.putEdge(graph, "a", "e1");

        assertThat(findPaths(graph, "e1", "a")).isEmpty();
        long sum = 0;
        final int loops = 100000;
        for (int i = 0; i < loops; i++) {
            long then = System.nanoTime();
            findPaths(graph, "e1", "a");
            sum += System.nanoTime() - then;
        }
        System.out.println("findPaths(): " + sum / loops);


        assertThat(findPaths(graph, "a", "e1")).isNotEmpty();

        graph.putEdge("a", "e2");
        assertThat(Graphs.hasCycle(graph)).isFalse();
        graph.putEdge("e1", "a");
        assertThat(Graphs.hasCycle(graph)).isTrue();

        sum = 0;
        for (int i = 0; i < loops; i++) {
            long then = System.nanoTime();
            Graphs.hasCycle(graph);
            sum += System.nanoTime() - then;
        }
        System.out.println("Graphs.hasCycle(): " + sum / loops);

    }

    @Test
    public void testIntegration() throws IOException {
        // curl commongui:commonguisecret@al-kostra-app-utv:7200/authserver/oauth/token -d grant_type=password -d username=admin -d password=admin
        //http://localhost:7080/api/v2/data/KOSTRA0A:425215?access_token=
        // columns=PERIODE,AARGANG,BYDEL,REGION,KONTOKLASSE,FUNKSJON_KAPITTEL,ART_SEKTOR,BELOP
        // sort=PERIODE&sort=PERIODE&AARGANG,BYDEL,REGION,KONTOKLASSE,FUNKSJON_KAPITTEL,ART_SEKTOR

        Dataset testDataset = create0ADataset();
        Dataset hierarchy = getAccountHierarchyDataset();

        printStream.println(testDataset.getDataStructure());
        printStream.println(hierarchy.getDataStructure());

        Component id = testDataset.getDataStructure().get("ART");
        Component value = testDataset.getDataStructure().get("BELOP");
        HierarchyOperation result = new HierarchyOperation(testDataset, hierarchy, id, value);

        AtomicLong count = new AtomicLong(0);
        result.getData().forEach(dataPoint -> {
            count.incrementAndGet();
            if (dataPoint.get(8).get().toString().length() > 4) {
                System.out.println(dataPoint);
            }
        });

        PrintStream file = new PrintStream(new FileOutputStream("/Users/hadrien/Projects/java-vtl/testResult"));
        result.getData().forEach(file::println);

        System.out.println(count);
    }

    private Dataset getAccountHierarchyDataset() throws IOException {
        DataStructure hierarchyStructure = createAccountHierarchyStructure();
        List<DataPoint> hierarchyDataset = createAccountHierarchy(hierarchyStructure);
        return new Dataset() {

            @Override
            public Stream<DataPoint> getData() {
                return hierarchyDataset.stream();
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
                return hierarchyStructure;
            }
        };
    }

    private DataStructure createAccountHierarchyStructure() {
        return DataStructure.builder()
                .put("to", IDENTIFIER, String.class)
                .put("from", IDENTIFIER, String.class)
                .put("sign", MEASURE, String.class)
                .build();
    }

    private List<DataPoint> createAccountHierarchy(DataStructure hierarchyStructure) throws IOException {
        InputStream compressedStream = Resources.getResource(this.getClass(), "account_hierarchy.json.bro").openStream();
        BrotliInputStream stream = new BrotliInputStream(compressedStream);

        JsonFactory factory = objectMapper.getFactory();
        JsonParser parser = factory.createParser(stream);

        parser.nextValue();
        parser.nextValue();
        MappingIterator<Map<String, Object>> rows = objectMapper.readValues(parser, new TypeReference<Map<String, Object>>() {
        });


        return Streams.stream(rows)
                .filter(map -> !map.get("from").toString().isEmpty())
                .filter(map -> !map.get("to").toString().isEmpty())
                .map((map) -> {
                    return hierarchyStructure.wrap(Maps.filterKeys(map, hierarchyStructure::containsKey));
                }).collect(toList());
    }

    private Dataset create0ADataset() {
        DataStructure structure = create0AStructure();
        return new Dataset() {
            @Override
            public Stream<DataPoint> getData() {
                return create0AData(structure);
            }

            @Override
            public Optional<Stream<DataPoint>> getData(Order orders, Filtering filtering, Set<String> components) {
                return Optional.of(getData());
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

    private DataStructure create0AStructure() {
        return DataStructure.builder()
                .put("AARGANG", IDENTIFIER, String.class)
                .put("ART_SEKTOR", IDENTIFIER, String.class)
                .put("BYDEL", IDENTIFIER, String.class)
                .put("FUNKSJON_KAPITTEL", IDENTIFIER, String.class)
                .put("KONTOKLASSE", IDENTIFIER, String.class)
                .put("PERIODE", IDENTIFIER, String.class)
                .put("REGION", IDENTIFIER, String.class)
                .put("BELOP", MEASURE, Integer.class)
                .put("ART", IDENTIFIER, String.class)
                .build();
    }

    private Stream<DataPoint> create0AData(DataStructure structure) {

        try {
            InputStream compressedStream = Resources.getResource(this.getClass(), "hierarchy-data.bro").openStream();
            BrotliInputStream stream = new BrotliInputStream(compressedStream);
            JsonFactory factory = objectMapper.getFactory();
            JsonParser parser = factory.createParser(stream);
            MappingIterator<List<Object>> rows = objectMapper.readValues(parser, new TypeReference<List<Object>>() {
            });

            // Needed so that we can filter null ids.
            List<Component> ids = structure.values().stream()
                    .filter(Component::isIdentifier)
                    .collect(toList());

            return Streams.stream(rows).map(list -> {

                checkArgument(list.size() == structure.size());
                DataPoint dataPoint = structure.wrap();

                Iterator<Component> components = structure.values().iterator();
                for (int i = 0; i < list.size(); i++) {
                    VTLObject vtlObject = VTLObject.of(objectMapper.convertValue(list.get(i), components.next().getType()));
                    dataPoint.set(i, vtlObject);
                }
                return dataPoint;

            }).filter(dataPoint -> {
                Map<Component, VTLObject> asMap = structure.asMap(dataPoint);
                for (Component id : ids) {
                    if (asMap.get(id).get() == null) {
                        return false;
                    }
                }
                return true;
            });

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    public void testGraph() throws Exception {


        MutableValueGraph<String, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        graph.putEdgeValue("Austria", "European Union", Composition.UNION);
        graph.putEdgeValue("Italy", "European Union", Composition.UNION);
        graph.putEdgeValue("Holland", "European Union", Composition.UNION);
        graph.putEdgeValue("Belgium", "European Union", Composition.UNION);
        graph.putEdgeValue("Luxembourg", "European Union", Composition.UNION);
        graph.putEdgeValue("Holland", "Benelux", Composition.UNION);
        graph.putEdgeValue("Belgium", "Benelux", Composition.UNION);
        graph.putEdgeValue("Luxembourg", "Benelux", Composition.UNION);

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

        assertThat(result.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactlyInAnyOrder(
                        Year.of(2000), "Austria", 2000,
                        Year.of(2000), "Belgium", 2000,
                        Year.of(2000), "European Union", 10000,
                        Year.of(2000), "Luxembourg", 2000,
                        Year.of(2000), "Benelux", 6000,
                        Year.of(2000), "Italy", 2000,
                        Year.of(2000), "Holland", 2000,
                        Year.of(2001), "Austria", 2001,
                        Year.of(2001), "Belgium", 2001,
                        Year.of(2001), "European Union", 10005,
                        Year.of(2001), "Luxembourg", 2001,
                        Year.of(2001), "Benelux", 6003,
                        Year.of(2001), "Italy", 2001,
                        Year.of(2001), "Holland", 2001,
                        Year.of(2002), "Austria", 2002,
                        Year.of(2002), "Belgium", 2002,
                        Year.of(2002), "European Union", 10010,
                        Year.of(2002), "Luxembourg", 2002,
                        Year.of(2002), "Benelux", 6006,
                        Year.of(2002), "Italy", 2002,
                        Year.of(2002), "Holland", 2002,
                        Year.of(2003), "Austria", 2003,
                        Year.of(2003), "Belgium", 2003,
                        Year.of(2003), "European Union", 10015,
                        Year.of(2003), "Luxembourg", 2003,
                        Year.of(2003), "Benelux", 6009,
                        Year.of(2003), "Italy", 2003,
                        Year.of(2003), "Holland", 2003
                );

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
                        "Population", year.getValue()//randomIntBetween(0, 20)
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
