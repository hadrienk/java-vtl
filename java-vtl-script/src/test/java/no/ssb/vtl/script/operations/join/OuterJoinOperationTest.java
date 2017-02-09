package no.ssb.vtl.script.operations.join;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OuterJoinOperationTest extends RandomizedTest {

    @Test
    @Repeat(iterations = 10)
    public void testRandomDatasets() throws Exception {

        // Build random test data.

        Integer datasetAmount = randomIntBetween(1, 2);
        Integer rowAmount = randomIntBetween(0, 100);
        Integer identifierAmount = randomIntBetween(0, 5);
        Integer componentAmount = randomIntBetween(1, 5);

        Map<String, Dataset> datasets = Maps.newLinkedHashMap();
        Map<String, List<Dataset.Tuple>> data = Maps.newLinkedHashMap();
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

            List<Dataset.Tuple> currentData = Lists.newArrayList();
            for (int j = 0; j < rowAmount; j++) {
                List<DataPoint> points = Lists.newArrayList();
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
                "value", MEASURE, Integer.class
        );

        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "id3", IDENTIFIER, String.class,
                "value", MEASURE, Integer.class
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
                        structure2.wrap("id1", "1"),
                        structure2.wrap("id2", "b"),
                        structure2.wrap("id3", "id"),
                        structure2.wrap("value", "left 1b")
                ), tuple(
                        structure2.wrap("id1", "2"),
                        structure2.wrap("id2", "c"),
                        structure2.wrap("id3", "id"),
                        structure2.wrap("value", "left 2c")
                ), tuple(
                        structure2.wrap("id1", "3"),
                        structure2.wrap("id2", "d"),
                        structure2.wrap("id3", "id"),
                        structure2.wrap("value", "left 3d")
                )
        ));

        AbstractJoinOperation result = new OuterJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        VTLPrintStream vtlPrintStream = new VTLPrintStream(System.out);

        vtlPrintStream.println(result.getDataStructure());

        vtlPrintStream.println(ds1);
        vtlPrintStream.println(ds2);

        vtlPrintStream.println(result.getDataStructure());
        vtlPrintStream.println(result);
    }

    private Dataset.Tuple tuple(DataPoint... components) {
        return new Dataset.AbstractTuple() {
            @Override
            protected List<DataPoint> delegate() {
                return Arrays.asList(components);
            }
        };
    }

    private Dataset.Tuple tuple(List<DataPoint> components) {
        return new Dataset.AbstractTuple() {
            @Override
            protected List<DataPoint> delegate() {
                return components;
            }
        };
    }
}
