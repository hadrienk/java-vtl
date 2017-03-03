package no.ssb.vtl.script.operations.join;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.carrotsearch.randomizedtesting.annotations.Seed;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static no.ssb.vtl.model.Component.Role.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OuterJoinOperationTest extends RandomizedTest {

    @Test
    @Seed("8144D952C87F27F0")
    public void testDebug() throws Exception {
        // Regression
        testRandomDatasets();
    }

    @Test
    @Repeat(iterations = 10)
    public void testRandomDatasets() throws Exception {

        // Build random test data.

        Integer datasetAmount = randomIntBetween(1, 10);
        Integer rowAmount = randomIntBetween(0, 20);
        Integer identifierAmount = randomIntBetween(0, 5);
        Integer componentAmount = randomIntBetween(1, 5);

        Map<String, Dataset> datasets = Maps.newLinkedHashMap();
        Map<String, List<DataPoint>> data = Maps.newLinkedHashMap();
        Map<String, DataStructure> dataStructures = Maps.newLinkedHashMap();

        // Creates random values.
        ImmutableMap<Class<?>, Function<Integer, Object>> types = ImmutableMap.of(
                String.class, rowId -> randomAsciiOfLengthBetween(5, 10) + "-" + rowId,
                Integer.class, integer -> integer,
                Float.class, Integer::floatValue,
                Double.class, Integer::doubleValue,
                Long.class, Integer::longValue
        );

        for (int i = 0; i < datasetAmount; i++) {
            String datasetName = "ds" + i;

            List<Class<?>> typeList = types.keySet().asList();
            DataStructure.Builder dataStructureBuilder = DataStructure.builder();
            dataStructureBuilder.put("rowNum", IDENTIFIER, String.class);
            for (int j = 0; j < identifierAmount + componentAmount; j++) {
                if (j < identifierAmount) {
                    dataStructureBuilder.put("i-" + j, IDENTIFIER, randomFrom(typeList));
                } else if (rarely()) {
                    dataStructureBuilder.put(datasetName + "-a-" + j, ATTRIBUTE, randomFrom(typeList));
                } else {
                    dataStructureBuilder.put(datasetName + "-m-" + j, MEASURE, randomFrom(typeList));
                }
            }
            DataStructure currentStructure = dataStructureBuilder.build();

            List<DataPoint> currentData = Lists.newArrayList();
            for (int j = 0; j < rowAmount; j++) {
                List<VTLObject> points = Lists.newArrayList();
                for (Component component : currentStructure.values()) {
                    Object value;
                    if (component.getName().equals("rowNum")) {
                        value = datasetName + "-row-" + j;
                    } else {
                        value = types.get(component.getType()).apply(j);
                    }
                    points.add(currentStructure.wrap(component.getName(), value));
                }
                currentData.add(tuple(points));
            }

            Dataset dataset = mock(Dataset.class, datasetName);
            datasets.put(datasetName, dataset);
            dataStructures.put(datasetName, currentStructure);
            when(dataset.getDataStructure()).thenReturn(currentStructure);
            data.put(datasetName, currentData);
            when(dataset.get()).thenAnswer(o -> currentData.stream());
        }

        OuterJoinOperation result = new OuterJoinOperation(datasets);
        VTLPrintStream out = new VTLPrintStream(System.out);
        out.println(result.getDataStructure());
        out.println(result);
    }

    @Test
    public void testDefault() throws Exception {


        Dataset ds1 = mock(Dataset.class, "ds1");
        Dataset ds2 = mock(Dataset.class, "ds1");

        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "id3", IDENTIFIER, String.class,
                "value", MEASURE, String.class
        );

        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "id3", IDENTIFIER, String.class,
                "value", MEASURE, String.class
        );

        given(ds1.getDataStructure()).willReturn(structure1);
        given(ds2.getDataStructure()).willReturn(structure2);

        given(ds1.get()).willAnswer(o -> Stream.of(
                tuple(
                        structure1.wrap("id1", "1"),
                        structure1.wrap("id2", "a"),
                        structure1.wrap("id3", "id"),
                        structure1.wrap("value", "left 1a")
                ), tuple(
                        structure1.wrap("id1", "2"),
                        structure1.wrap("id2", "b"),
                        structure1.wrap("id3", "id"),
                        structure1.wrap("value", "left 2b")
                ), tuple(
                        structure1.wrap("id1", "3"),
                        structure1.wrap("id2", "c"),
                        structure1.wrap("id3", "id"),
                        structure1.wrap("value", "left 3c")
                )
        ));

        given(ds2.get()).willAnswer(o -> Stream.of(
                tuple(
                        structure2.wrap("id1", "2"),
                        structure2.wrap("id2", "b"),
                        structure2.wrap("id3", "id"),
                        structure2.wrap("value", "right 2b")
                ), tuple(
                        structure2.wrap("id1", "3"),
                        structure2.wrap("id2", "c"),
                        structure2.wrap("id3", "id"),
                        structure2.wrap("value", "right 3c")
                ), tuple(
                        structure2.wrap("id1", "4"),
                        structure2.wrap("id2", "d"),
                        structure2.wrap("id3", "id"),
                        structure2.wrap("value", "right 4d")
                )
        ));

        AbstractJoinOperation result = new OuterJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        VTLPrintStream vtlPrintStream = new VTLPrintStream(System.out);
        //vtlPrintStream.println(result.getDataStructure());
        vtlPrintStream.println(ds1);
        vtlPrintStream.println(ds2);
        //vtlPrintStream.println(result.getDataStructure());
        vtlPrintStream.println(result);
        vtlPrintStream.println(result);

        // Check that the structure is correct. We expect:
        // common identifiers () + left and right.
        assertThat(result.getDataStructure())
                .containsOnly(
                        entry("id1", structure1.get("id1")),
                        entry("id2", structure1.get("id2")),
                        entry("id3", structure1.get("id3")),
                        entry("ds1_value", structure1.get("value")),
                        entry("ds2_value", structure2.get("value"))
                );

        assertThat(result.getData())
                .extracting(input -> input.stream().map(VTLObject::get).collect(Collectors.toList()))
                .containsExactly(
                        asList("1", "a", "id", "left 1a", null),
                        asList("2", "b", "id", "left 2b", "right 2b"),
                        asList("3", "c", "id", "left 3c", "right 3c"),
                        asList("4", "d", "id", null, "right 4d")
                );
    }

    @Test
    public void testOuterJoinWithUnequalIds() throws Exception {


        Dataset ds1 = mock(Dataset.class, "ds1");
        Dataset ds2 = mock(Dataset.class, "ds1");

        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "value", MEASURE, String.class
        );

        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "value", MEASURE, String.class,
                "id2", IDENTIFIER, String.class
        );

        given(ds1.getDataStructure()).willReturn(structure1);
        given(ds2.getDataStructure()).willReturn(structure2);

        given(ds1.get()).willAnswer(o -> Stream.of(
                tuple(
                        structure1.wrap("id1", "1"),
                        structure1.wrap("value", "left 1")
                ), tuple(
                        structure1.wrap("id1", "2"),
                        structure1.wrap("value", "left 2")
                ), tuple(
                        structure1.wrap("id1", "3"),
                        structure1.wrap("value", "left 3")
                )
        ));

        given(ds2.get()).willAnswer(o -> Stream.of(
                tuple(
                        structure2.wrap("id1", "2"),
                        structure2.wrap("value", "right 2"),
                        structure2.wrap("id2", "b")
                ), tuple(
                        structure2.wrap("id1", "3"),
                        structure2.wrap("value", "right 3"),
                        structure2.wrap("id2", "c")
                ), tuple(
                        structure2.wrap("id1", "4"),
                        structure2.wrap("value", "right 4"),
                        structure2.wrap("id2", "d")
                )
        ));

        AbstractJoinOperation result = new OuterJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        VTLPrintStream vtlPrintStream = new VTLPrintStream(System.out);
        //vtlPrintStream.println(result.getDataStructure());
        vtlPrintStream.println(ds1);
        vtlPrintStream.println(ds2);
        //vtlPrintStream.println(result.getDataStructure());
        vtlPrintStream.println(result);

        // Check that the structure is correct. We expect:
        // common identifiers () + left and right.
        assertThat(result.getDataStructure())
                .containsOnly(
                        entry("id1", structure1.get("id1")),
                        entry("ds1_value", structure1.get("value")),
                        entry("ds2_value", structure2.get("value")),
                        entry("id2", structure2.get("id2"))
                );

        assertThat(result.get())
                .extracting(input -> input.stream().map(VTLObject::get).collect(Collectors.toList()))
                .containsExactly(
                        asList("1", "left 1", null, null),
                        asList("2", "left 2", "right 2", "b"),
                        asList("3", "left 3", "right 3", "c"),
                        asList("4", null, "right 4", "d")
                );
    }

    private DataPoint tuple(VTLObject... components) {
        return DataPoint.create(asList(components));
    }

    private DataPoint tuple(List<VTLObject> components) {
        return DataPoint.create(components);
    }
}
