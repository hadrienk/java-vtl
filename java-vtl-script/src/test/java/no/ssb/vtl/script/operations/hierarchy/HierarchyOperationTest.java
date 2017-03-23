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
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static java.util.stream.Collectors.*;
import static no.ssb.vtl.model.Component.Role.*;
import static no.ssb.vtl.script.operations.hierarchy.HierarchyOperation.*;
import static org.assertj.core.api.Assertions.*;

public class HierarchyOperationTest extends RandomizedTest {

    private static VTLPrintStream PS = new VTLPrintStream(System.out);
    private static ObjectMapper MAPPER = new ObjectMapper();

    private static List<Object> createAggregatedPopulation() {
        return Lists.newArrayList(
                Year.of(2000), "Austria", 2000, -2000L,
                Year.of(2000), "Belgium", 2000, -2000L,
                Year.of(2000), "European Union", 10000, -10000L,
                Year.of(2000), "Luxembourg", 2000, -2000L,
                Year.of(2000), "Benelux", 6000, -6000L,
                Year.of(2000), "Italy", 2000, -2000L,
                Year.of(2000), "Holland", 2000, -2000L,
                Year.of(2001), "Austria", 2001, -2001L,
                Year.of(2001), "Belgium", 2001, -2001L,
                Year.of(2001), "European Union", 10005, -10005L,
                Year.of(2001), "Luxembourg", 2001, -2001L,
                Year.of(2001), "Benelux", 6003, -6003L,
                Year.of(2001), "Italy", 2001, -2001L,
                Year.of(2001), "Holland", 2001, -2001L,
                Year.of(2002), "Austria", 2002, -2002L,
                Year.of(2002), "Belgium", 2002, -2002L,
                Year.of(2002), "European Union", 10010, -10010L,
                Year.of(2002), "Luxembourg", 2002, -2002L,
                Year.of(2002), "Benelux", 6006, -6006L,
                Year.of(2002), "Italy", 2002, -2002L,
                Year.of(2002), "Holland", 2002, -2002L,
                Year.of(2003), "Austria", 2003, -2003L,
                Year.of(2003), "Belgium", 2003, -2003L,
                Year.of(2003), "European Union", 10015, -10015L,
                Year.of(2003), "Luxembourg", 2003, -2003L,
                Year.of(2003), "Benelux", 6009, -6009L,
                Year.of(2003), "Italy", 2003, -2003L,
                Year.of(2003), "Holland", 2003, -2003L
        );
    }

    private static Dataset create0ADataset(String file) {
        DataStructure structure = create0AStructure();
        return new Dataset() {
            @Override
            public Stream<DataPoint> getData() {
                return create0AData(structure, file);
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

    private static DataStructure create0AStructure() {
        return DataStructure.builder()
                .put("PERIODE", IDENTIFIER, String.class)
                .put("ART_SEKTOR", IDENTIFIER, String.class)
                .put("BYDEL", IDENTIFIER, String.class)
                .put("FUNKSJON_KAPITTEL", IDENTIFIER, String.class)
                .put("KONTOKLASSE", IDENTIFIER, String.class)
                .put("REGION", IDENTIFIER, String.class)
                .put("BELOP", MEASURE, Integer.class)
                .build();
    }

    private static Stream<DataPoint> create0AData(DataStructure structure, String file) {

        try {
            InputStream stream = openBroFile(file);

            JsonFactory factory = MAPPER.getFactory();
            JsonParser parser = factory.createParser(stream);

            parser.nextValue();
            parser.nextValue();
            MappingIterator<List<Object>> rows = MAPPER.readValues(parser, new TypeReference<List<Object>>() {
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
                    VTLObject vtlObject = VTLObject.of(MAPPER.convertValue(list.get(i), components.next().getType()));
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

    private static InputStream openBroFile(String file) throws IOException {
        InputStream stream = Resources.getResource(HierarchyOperation.class, file).openStream();
        if (file.endsWith(".bro"))
            stream = new BrotliInputStream(stream);
        return stream;
    }

    private static Dataset createPopulationDataset() {
        DataStructure structure = DataStructure.builder()
                .put("Year", IDENTIFIER, Year.class)
                .put("Country", IDENTIFIER, String.class)
                .put("Population", MEASURE, Integer.class)
                .put("OtherPopulation", MEASURE, Long.class)
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
                        "Population", year.getValue(), //randomIntBetween(0, 20)
                        "OtherPopulation", (long) year.getValue() * -1
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

    private static Dataset getAccountHierarchyDataset(String file) throws IOException {
        DataStructure hierarchyStructure = createAccountHierarchyStructure();
        List<DataPoint> hierarchyDataset = createAccountHierarchy(hierarchyStructure, file);
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

    private static DataStructure createAccountHierarchyStructure() {
        return DataStructure.builder()
                .put("to", IDENTIFIER, String.class)
                .put("from", IDENTIFIER, String.class)
                .put("sign", MEASURE, String.class)
                .build();
    }

    private static List<DataPoint> createAccountHierarchy(DataStructure hierarchyStructure, String file) throws IOException {
        InputStream stream = openBroFile(file);

        JsonFactory factory = MAPPER.getFactory();
        JsonParser parser = factory.createParser(stream);

        parser.nextValue();
        parser.nextValue();
        MappingIterator<Map<String, Object>> rows = MAPPER.readValues(parser, new TypeReference<Map<String, Object>>() {
        });


        return Streams.stream(rows)
                .filter(map -> !map.get("from").toString().isEmpty())
                .filter(map -> !map.get("to").toString().isEmpty())
                .map((map) -> {
                    return hierarchyStructure.wrap(Maps.filterKeys(map, hierarchyStructure::containsKey));
                }).collect(toList());
    }

    private static Dataset createEmptyDataset(final DataStructure structure) {
        return new Dataset() {
            @Override
            public Stream<DataPoint> getData() {
                return Stream.empty();
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

    @Test
    public void testConstraintComponentIsInDataset() throws Exception {
        // Component must part of the structure.
        MutableValueGraph<VTLObject, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        DataStructure structure = DataStructure.builder()
                .put("id1", IDENTIFIER, String.class)
                .put("id2", IDENTIFIER, String.class)
                .put("m1", MEASURE, Integer.class)
                .build();

        DataStructure otherStructure = DataStructure.builder()
                .put("id1", IDENTIFIER, String.class)
                .put("id2", IDENTIFIER, String.class)
                .put("m1", MEASURE, Integer.class)
                .build();

        Dataset dataset = createEmptyDataset(structure);

        assertThatThrownBy(() -> {
            new HierarchyOperation(dataset, graph, otherStructure.get("m1"));
        }).isNotNull().hasMessageContaining(otherStructure.get("m1").toString());
    }

    @Test
    public void testConstraintComponentIsNumeric() throws Exception {
        // Component must be an identifier.
        MutableValueGraph<VTLObject, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        DataStructure structure = DataStructure.builder()
                .put("id1", IDENTIFIER, String.class)
                .put("id2", IDENTIFIER, String.class)
                .put("m1", MEASURE, Integer.class)
                .build();

        Dataset dataset = createEmptyDataset(structure);

        assertThatThrownBy(() -> {
            new HierarchyOperation(dataset, graph, structure.get("m1"));
        }).isNotNull().hasMessageContaining("m1");
    }

    @Test
    public void testConstraintAllNumeric() {
        // All measures must be numeric
        MutableValueGraph<VTLObject, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        DataStructure structure = DataStructure.builder()
                .put("id1", IDENTIFIER, String.class)
                .put("id2", IDENTIFIER, String.class)
                .put("m1", MEASURE, Integer.class)
                .put("m2", MEASURE, Double.class)
                .put("m3", MEASURE, Float.class)
                .put("m4", MEASURE, String.class)
                .build();

        Dataset dataset = createEmptyDataset(structure);

        assertThatThrownBy(() -> {
            new HierarchyOperation(dataset, graph, structure.get("id2"));
        }).isNotNull().hasMessageContaining("m4");
    }

    @Test
    public void testConstaintGraphType() throws Exception {
        // The type of the graph should be the same as the component.
        fail("TODO");
    }

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

    //@Test
    public void testIntegration2016() throws IOException {
        // See generate-data.sh

        Dataset testDataset = create0ADataset("kostra0a_grunnskole_driftregnskap_2016.json.bro");

        Dataset hierarchy = getAccountHierarchyDataset("account_hierarchy.json.bro");

        DataStructure dataStructure = testDataset.getDataStructure();
        PS.println(dataStructure);
        PS.println(hierarchy.getDataStructure());

        Component value = dataStructure.get("ART_SEKTOR");

        HierarchyOperation result = new HierarchyOperation(testDataset, hierarchy, value);

        PrintStream file = new PrintStream(new FileOutputStream("/Users/hadrien/Projects/java-vtl/result_account_2016"));
        result.getData().forEach(file::println);

    }

    //@Test
    public void testIntegration2015() throws IOException {
        // See generate-data.sh

        Dataset testDataset = create0ADataset("kostra0a_grunnskole_driftregnskap_2015.json.bro");
        Dataset hierarchy = getAccountHierarchyDataset("account_hierarchy.json.bro");

        DataStructure dataStructure = testDataset.getDataStructure();
        PS.println(dataStructure);
        PS.println(hierarchy.getDataStructure());

        Component value = dataStructure.get("ART_SEKTOR");
        HierarchyOperation result = new HierarchyOperation(testDataset, hierarchy, value);

        //PS.println(result);

        PrintStream file = new PrintStream(new FileOutputStream("/Users/hadrien/Projects/java-vtl/result_account_2015"));
        result.getData().forEach(file::println);

    }

    @Test
    public void testWithComplement() throws Exception {

        // Here the hierarchy uses complement
        //
        // Austria         -(+)-> European Union
        // ...             -(+)-> European Union
        //
        // European Union  -(+)-> Luxembourg
        //
        // Luxembourg      -(-)-> Benelux
        // Austria         -(-)-> Benelux
        // Italy         -(-)-> Benelux

        MutableValueGraph<VTLObject, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        graph.putEdgeValue(VTLObject.of("Austria"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Italy"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Holland"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Belgium"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Luxembourg"), VTLObject.of("European Union"), Composition.UNION);

        graph.putEdgeValue(VTLObject.of("European Union"), VTLObject.of("Benelux"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Austria"), VTLObject.of("Benelux"), Composition.COMPLEMENT);
        graph.putEdgeValue(VTLObject.of("Italy"), VTLObject.of("Benelux"), Composition.COMPLEMENT);

        // Year, Country, Pop
        Dataset population = createPopulationDataset();

        PS.println(population);

        // Country is the identifier we operate on.
        DataStructure structure = population.getDataStructure();
        Component value = structure.get("Country");
        HierarchyOperation result = new HierarchyOperation(population, graph, value);

        PS.println(result);

        List<Object> aggregatedPopulation = createAggregatedPopulation();

        assertThat(result.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsOnlyElementsOf(aggregatedPopulation);

    }

    @Test
    public void testWithComposition() throws Exception {

        // Here the hierarchy contains two levels;
        //
        // Luxembourg -> Benelux
        // ... -> Benelux
        //
        // Benelux -> European Union
        // Austria -> European Union
        // ... -> European Union

        MutableValueGraph<VTLObject, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        graph.putEdgeValue(VTLObject.of("Austria"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Italy"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Benelux"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Holland"), VTLObject.of("Benelux"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Belgium"), VTLObject.of("Benelux"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Luxembourg"), VTLObject.of("Benelux"), Composition.UNION);

        // Year, Country, Pop
        Dataset population = createPopulationDataset();

        PS.println(population);

        // Country is the identifier we operate on.
        // Population is the value we operate on.
        DataStructure structure = population.getDataStructure();
        Component value = structure.get("Country");
        HierarchyOperation result = new HierarchyOperation(population, graph, value);

        PS.println(result);

        List<Object> aggregatedPopulation = createAggregatedPopulation();

        assertThat(result.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsOnlyElementsOf(aggregatedPopulation);

    }

    @Test
    public void testWithoutComposition() throws Exception {

        // Here the hierarchy contains node that are connected to two others nodes;
        // Luxembourg -> European Union
        // Benelux -> European Union

        MutableValueGraph<VTLObject, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        graph.putEdgeValue(VTLObject.of("Austria"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Italy"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Holland"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Belgium"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Luxembourg"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Holland"), VTLObject.of("Benelux"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Belgium"), VTLObject.of("Benelux"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Luxembourg"), VTLObject.of("Benelux"), Composition.UNION);

        // Year, Country, Pop
        Dataset population = createPopulationDataset();

        PS.println(population);

        // Country is the identifier we operate on.
        // Population is the value we operate on.
        DataStructure structure = population.getDataStructure();
        Component value = structure.get("Country");
        HierarchyOperation result = new HierarchyOperation(population, graph, value);

        PS.println(result);

        List<Object> aggregatedPopulation = createAggregatedPopulation();

        assertThat(result.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsOnlyElementsOf(aggregatedPopulation);

    }
}
