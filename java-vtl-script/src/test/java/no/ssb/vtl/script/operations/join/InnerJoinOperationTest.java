package no.ssb.vtl.script.operations.join;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.time.Instant;
import java.time.Year;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InnerJoinOperationTest extends RandomizedTest {

    private ObjectMapper mapper = new ObjectMapper();

    private static Condition<DataPoint> dataPointWith(String name, Object value) {
        return new Condition<DataPoint>(new Predicate<DataPoint>() {
            @Override
            public boolean test(DataPoint dataPoint) {
                return name.equals(dataPoint.getName()) &&
                        value.equals(dataPoint.get());
            }
        }, "data point with name %s and value %s", name, value);
    }

    @Test
    public void testDefaultJoin() throws Exception {
        Dataset ds1 = mock(Dataset.class, "ds1");
        Dataset ds2 = mock(Dataset.class, "ds1");

        DataStructure structure1 = DataStructure.of(
                mapper::convertValue,
                "time", IDENTIFIER, Year.class,
                "ref_area", IDENTIFIER, String.class,
                "partner", IDENTIFIER, String.class,
                "obs_value", MEASURE, Integer.class,
                "obs_status", ATTRIBUTE, String.class
        );

        DataStructure structure2 = DataStructure.of(
                mapper::convertValue,
                "time", IDENTIFIER, Year.class,
                "ref_area", IDENTIFIER, String.class,
                "partner", IDENTIFIER, String.class,
                "obs_value", MEASURE, Integer.class,
                "obs_status", ATTRIBUTE, String.class
        );

        given(ds1.getDataStructure()).willReturn(structure1);
        given(ds2.getDataStructure()).willReturn(structure2);

        given(ds1.get()).willAnswer(o -> Stream.of(
                tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "CA"),
                        structure1.wrap("obs_value", "20"),
                        structure1.wrap("obs_status", "E")
                ), tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "BG"),
                        structure1.wrap("obs_value", "2"),
                        structure1.wrap("obs_status", "P")
                ), tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "RO"),
                        structure1.wrap("obs_value", "2"),
                        structure1.wrap("obs_status", "P")
                )
        ));

        given(ds2.get()).willAnswer(o -> Stream.of(
                tuple(
                        structure2.wrap("time", Year.of(2010)),
                        structure2.wrap("ref_area", "EU25"),
                        structure2.wrap("partner", "CA"),
                        structure2.wrap("obs_value", "10"),
                        structure2.wrap("obs_status", "P")
                )
        ));

        AbstractJoinOperation result = new InnerJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        new VTLPrintStream(System.out).println(result);

        assertThat(result.workDataset().getDataStructure())
                .containsOnly(
                        entry("time", structure1.get("time")),
                        entry("ref_area", structure1.get("ref_area")),
                        entry("partner", structure1.get("partner")),
                        entry("ds1_obs_value", structure1.get("obs_value")),
                        entry("ds1_obs_status", structure1.get("obs_status")),
                        entry("ds2_obs_value", structure2.get("obs_value")),
                        entry("ds2_obs_status", structure2.get("obs_status"))
                );

        assertThat(result.workDataset().get())
                .flatExtracting(tuple -> tuple)
                .flatExtracting(dataPoint -> Arrays.asList(dataPoint.getRole(), dataPoint.getComponent(), dataPoint.get()))

                .startsWith(
                        IDENTIFIER, structure1.get("time"), Year.of(2010),
                        IDENTIFIER, structure1.get("ref_area"), "EU25",
                        IDENTIFIER, structure1.get("partner"), "CA",
                        MEASURE, structure1.get("obs_value"), "20",
                        ATTRIBUTE, structure1.get("obs_status"), "E",
                        MEASURE, structure2.get("obs_value"), "10",
                        ATTRIBUTE, structure2.get("obs_status"), "P"
                );
    }

    @Test
    @Repeat(iterations = 10)
    public void testRandomDatasets() throws Exception {

        Integer datasetAmount = scaledRandomIntBetween(1, 50);
        Integer rowAmount = scaledRandomIntBetween(0, 500);
        Set<Component> allComponents = Sets.newHashSet();

        Map<String, Dataset> datasets = Maps.newLinkedHashMap();
        for (int i = 0; i < datasetAmount; i++) {

            DataStructure structure = DataStructure.of(
                    mapper::convertValue,
                    "id1", IDENTIFIER, Year.class,
                    "id2", IDENTIFIER, String.class,
                    "id3", IDENTIFIER, Instant.class,
                    "measure", MEASURE, Integer.class,
                    "attribute", ATTRIBUTE, String.class
            );
            Dataset dataset = mock(Dataset.class, "Mocked dataset" + i);
            when(dataset.getDataStructure()).thenReturn(structure);
            datasets.put("ds" + i, dataset);

            allComponents.addAll(structure.values());

            int j = i;
            when(dataset.get()).then(invocation ->
                    IntStream.rangeClosed(0, rowAmount)
                            .boxed()
                            .map(rowNum ->
                                    tuple(
                                            structure.wrap("id1", Year.of(2000)),
                                            structure.wrap("id2", "id"),
                                            structure.wrap("id3", Instant.ofEpochMilli(60 * 60 * 24 * 100)),
                                            structure.wrap("measure", "measure-" + j + "-" + rowNum),
                                            structure.wrap("attribute", "attribute-" + j + "-" + rowNum)
                                    )
                            )
            );
        }

        InnerJoinOperation result = new InnerJoinOperation(datasets);

        assertThat(result.getDataStructure())
                .describedAs("data structure of the inner join")
                .hasSize(datasetAmount * 2 + 3);

        assertThat(allComponents)
                .describedAs("all the components in the datasets")
                .hasSize(datasetAmount * 5);

        List<Object> data = datasets.values().stream()
                .flatMap(Supplier::get)
                .map(Dataset.Tuple::values)
                .flatMap(Collection::stream)
                .map(DataPoint::get)
                .collect(Collectors.toList());

        assertThat(result.get())
                .describedAs("the data")
                .flatExtracting(Dataset.Tuple::values)
                .extracting(DataPoint::get)
                .containsOnlyElementsOf(
                        data
                );


//        assertThat(allComponents)
//                .describedAs("all the components in the datasets")
//                .
//                .containsExactlyElementsOf(result.getDataStructure().values());

        //showDataset(result);

        //assertThat(result.get()).isEmpty();

    }

    @Test
    public void testInnerJoinWithCodeListDataset() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        Dataset dsCodeList2 = mock(Dataset.class);

        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "kommune_nr", Component.Role.IDENTIFIER, String.class,
                "periode", Component.Role.IDENTIFIER, Instant.class, //TODO String?
                "m1", Component.Role.MEASURE, Integer.class,
                "at1", Component.Role.ATTRIBUTE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(structure1);
        when(ds1.get()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "kommune_nr", "0101",
                        "periode", Instant.parse("2015-01-01T00:00:00.00Z"),
                        "m1", 100,
                        "at1", "attr1"
                ),
                ImmutableMap.of(
                        "kommune_nr", "0111",
                        "periode", Instant.parse("2014-01-01T00:00:00.00Z"),
                        "m1", 101,
                        "at1", "attr2"
                ),
                ImmutableMap.of(
                        "kommune_nr", "9000",
                        "periode", Instant.parse("2014-01-01T00:00:00.00Z"),
                        "m1", 102,
                        "at1", "attr3"
                )
        ).map(structure1::wrap));

        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "kommune_nr", Component.Role.IDENTIFIER, String.class, //code
                "name", Component.Role.MEASURE, String.class,
                "validFrom", Component.Role.IDENTIFIER, Instant.class,
                "validTo", Component.Role.IDENTIFIER, Instant.class
        );
        when(dsCodeList2.getDataStructure()).thenReturn(structure2);
        when(dsCodeList2.get()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "kommune_nr", "0101",
                        "name", "Halden",
                        "validFrom", Instant.parse("2013-01-01T00:00:00.00Z")
//                        "validTo", null
                ),
                ImmutableMap.of(
                        "kommune_nr", "0111",
                        "name", "Hvaler",
                        "validFrom", Instant.parse("2015-01-01T00:00:00.00Z")
//                        "validTo", null
                ),
                ImmutableMap.of(
                        "kommune_nr", "1001",
                        "name", "Kristiansand",
                        "validFrom", Instant.parse("2013-01-01T00:00:00.00Z"),
                        "validTo", Instant.parse("2015-01-01T00:00:00.00Z")
                )
        ).map(structure2::wrap));


        AbstractJoinOperation ds3 = new InnerJoinOperation(ImmutableMap.of("ds1", ds1, "dsCodeList2", dsCodeList2));

        new VTLPrintStream(System.out).println(ds3);

        assertThat(ds3.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("periode", Component.Role.IDENTIFIER),
                entry("ds1_m1", Component.Role.MEASURE),
                entry("dsCodeList2_name", Component.Role.MEASURE),
                entry("validFrom", Component.Role.IDENTIFIER),
                entry("validTo", Component.Role.IDENTIFIER)
        );

        assertThat(ds3.get()).flatExtracting(input -> input)
                .extracting(DataPoint::get)
                .containsExactly(
                        "0101", Instant.parse("2015-01-01T00:00:00.00Z"), 100, "Halden", Instant.parse("2013-01-01T00:00:00.00Z"), null,
                        "0111", Instant.parse("2014-01-01T00:00:00.00Z"), 101, "Hvaler", Instant.parse("2015-01-01T00:00:00.00Z"), null
                );
    }

    private Dataset.Tuple tuple(DataPoint... components) {
        return new Dataset.AbstractTuple() {
            @Override
            protected List<DataPoint> delegate() {
                return Arrays.asList(components);
            }
        };
    }
}
