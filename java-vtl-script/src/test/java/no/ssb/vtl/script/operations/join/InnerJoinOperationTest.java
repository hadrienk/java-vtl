package no.ssb.vtl.script.operations.join;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.carrotsearch.randomizedtesting.annotations.Seed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.time.Instant;
import java.time.Year;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InnerJoinOperationTest extends RandomizedTest {

    private ObjectMapper mapper = new ObjectMapper();

    private static Condition<VTLObject> dataPointWith(String name, Object value) {
        return new Condition<VTLObject>(new Predicate<VTLObject>() {
            @Override
            public boolean test(VTLObject value) {
                return name.equals(value.getComponent().getName()) &&
                        value.equals(value.get());
            }
        }, "data point with name %s and value %s", name, value);
    }

    @Test
    public void testDefaultJoin() throws Exception {
        Dataset ds1 = mock(Dataset.class, "ds1");
        Dataset ds2 = mock(Dataset.class, "ds2");

        DataStructure structure1 = DataStructure.of(
                mapper::convertValue,
                "time", IDENTIFIER, Year.class,
                "ref_area", IDENTIFIER, String.class,
                "partner", IDENTIFIER, String.class,
                "obs_value", MEASURE, Long.class,
                "obs_status", ATTRIBUTE, String.class
        );

        DataStructure structure2 = DataStructure.of(
                mapper::convertValue,
                "time", IDENTIFIER, Year.class,
                "ref_area", IDENTIFIER, String.class,
                "partner", IDENTIFIER, String.class,
                "obs_value", MEASURE, Long.class,
                "obs_status", ATTRIBUTE, String.class
        );

        given(ds1.getDataStructure()).willReturn(structure1);
        given(ds2.getDataStructure()).willReturn(structure2);

        given(ds1.getData()).willAnswer(o -> Stream.of(
                tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "CA"),
                        structure1.wrap("obs_value", 20L),
                        structure1.wrap("obs_status", "E")
                ), tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "BG"),
                        structure1.wrap("obs_value", 2L),
                        structure1.wrap("obs_status", "P")
                ), tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "RO"),
                        structure1.wrap("obs_value", 2L),
                        structure1.wrap("obs_status", "P")
                )
        ));
        given(ds1.getData(any(Order.class))).willReturn(Optional.empty());

        given(ds2.getData()).willAnswer(o -> Stream.of(
                tuple(
                        structure2.wrap("time", Year.of(2010)),
                        structure2.wrap("ref_area", "EU25"),
                        structure2.wrap("partner", "CA"),
                        structure2.wrap("obs_value", 10L),
                        structure2.wrap("obs_status", "P")
                )
        ));
        given(ds2.getData(any(Order.class))).willReturn(Optional.empty());

        AbstractJoinOperation result = new InnerJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        new VTLPrintStream(System.out).println(result);

        assertThat(result.getDataStructure())
                .containsOnly(
                        entry("time", structure1.get("time")),
                        entry("ref_area", structure1.get("ref_area")),
                        entry("partner", structure1.get("partner")),
                        entry("ds1_obs_value", structure1.get("obs_value")),
                        entry("ds1_obs_status", structure1.get("obs_status")),
                        entry("ds2_obs_value", structure2.get("obs_value")),
                        entry("ds2_obs_status", structure2.get("obs_status"))
                );

        assertThat(result.getData())
                .flatExtracting(tuple -> tuple)
                .flatExtracting(dataPoint -> {
                    Component component = dataPoint.getComponent();
                    return Arrays.asList(component.getRole(), component, dataPoint.get());
                })

                .startsWith(
                        IDENTIFIER, structure1.get("time"), Year.of(2010),
                        IDENTIFIER, structure1.get("ref_area"), "EU25",
                        IDENTIFIER, structure1.get("partner"), "CA",
                        MEASURE, structure1.get("obs_value"), 20L,
                        ATTRIBUTE, structure1.get("obs_status"), "E",
                        MEASURE, structure2.get("obs_value"), 10L,
                        ATTRIBUTE, structure2.get("obs_status"), "P"
                );
    }

    @Test
    @Seed("9DC9B02FF9A216E4")
    public void testRegression() throws Exception {
        //D0E9B354FC19C5A:9DC9B02FF9A216E4
        testRandomDatasets();
    }

    @Test
    @Repeat(iterations = 10)
    public void testRandomDatasets() throws Exception {

        Integer datasetAmount = scaledRandomIntBetween(1, 10);
        Integer rowAmount = scaledRandomIntBetween(0, 100);
        Set<Component> allComponents = Sets.newHashSet();

        Map<String, Dataset> datasets = Maps.newLinkedHashMap();
        for (int i = 0; i < datasetAmount; i++) {

            DataStructure structure = DataStructure.of(
                    mapper::convertValue,
                    "id1", IDENTIFIER, Year.class,
                    "id2", IDENTIFIER, String.class,
                    "id3", IDENTIFIER, Instant.class,
                    "measure", MEASURE, Long.class,
                    "attribute", ATTRIBUTE, String.class
            );
            Dataset dataset = mock(Dataset.class, "Mocked dataset" + i);
            when(dataset.getDataStructure()).thenReturn(structure);
            datasets.put("ds" + i, dataset);

            allComponents.addAll(structure.values());

            int j = i;
            when(dataset.getData()).then(invocation ->
                    IntStream.rangeClosed(0, rowAmount)
                            .boxed()
                            .map(rowNum ->
                                    tuple(
                                            structure.wrap("id1", Year.of(2000)),
                                            structure.wrap("id2", "id"),
                                            structure.wrap("id3", Instant.ofEpochMilli(60 * 60 * 24 * 100)),
                                            structure.wrap("measure", (long) (j + rowNum)),
                                            structure.wrap("attribute", "attribute-" + j + "-" + rowNum)
                                    )
                            )
            );
            when(dataset.getData(any(Order.class))).thenReturn(Optional.empty());
        }

        InnerJoinOperation result = new InnerJoinOperation(datasets);

        assertThat(result.getDataStructure())
                .describedAs("data structure of the inner join")
                .hasSize(datasetAmount * 2 + 3);

        assertThat(allComponents)
                .describedAs("all the components in the datasets")
                .hasSize(datasetAmount * 5);

        List<Object> data = datasets.values().stream()
                .flatMap(Dataset::getData)
                .flatMap(Collection::stream)
                .map(VTLObject::get)
                .collect(Collectors.toList());
    
        assertThat(result.getData().flatMap(Collection::stream))
                .describedAs("the data")
                .extracting(VTLObject::get)
                .containsOnlyElementsOf(
                        data
                );

    }

    @Test
    public void testInnerJoinWithCodeListDataset() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        Dataset dsCodeList2 = mock(Dataset.class);

        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "kommune_nr", Component.Role.IDENTIFIER, String.class,
                "periode", Component.Role.IDENTIFIER, Instant.class, //TODO String?
                "m1", Component.Role.MEASURE, Long.class,
                "at1", Component.Role.ATTRIBUTE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(structure1);
        when(ds1.getData()).then(invocation -> Stream.of(
                 (Map) ImmutableMap.of(
                        "kommune_nr", "0101",
                        "periode", Instant.parse("2015-01-01T00:00:00.00Z"),
                        "m1", 100L,
                        "at1", "attr1"
                ),
                ImmutableMap.of(
                        "kommune_nr", "0111",
                        "periode", Instant.parse("2014-01-01T00:00:00.00Z"),
                        "m1", 101L,
                        "at1", "attr2"
                ),
                ImmutableMap.of(
                        "kommune_nr", "9000",
                        "periode", Instant.parse("2014-01-01T00:00:00.00Z"),
                        "m1", 102L,
                        "at1", "attr3"
                )
        ).map(structure1::wrap));
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());

        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "kommune_nr", Component.Role.IDENTIFIER, String.class, //code
                "name", Component.Role.MEASURE, String.class,
                "validFrom", Component.Role.IDENTIFIER, Instant.class,
                "validTo", Component.Role.IDENTIFIER, Instant.class
        );

        when(dsCodeList2.getDataStructure()).thenReturn(structure2);
        when(dsCodeList2.getData()).then(invocation -> Stream.of(
                tuple(
                        structure2.wrap("kommune_nr", "0101"),
                        structure2.wrap("name", "Halden"),
                        structure2.wrap("validFrom",  Instant.parse("2013-01-01T00:00:00.00Z")),
                        structure2.wrap("validTo", null)
                ),
                tuple(
                        structure2.wrap("kommune_nr", "0111"),
                        structure2.wrap("name", "Hvaler"),
                        structure2.wrap("validFrom", Instant.parse("2015-01-01T00:00:00.00Z")),
                        structure2.wrap("validTo", null)
                ),
                tuple(
                        structure2.wrap("kommune_nr", "1001"),
                        structure2.wrap("name", "Kristiansand"),
                        structure2.wrap("validFrom", Instant.parse("2013-01-01T00:00:00.00Z")),
                        structure2.wrap("validTo", Instant.parse("2015-01-01T00:00:00.00Z"))
                )
        ));
        when(dsCodeList2.getData(any(Order.class))).thenReturn(Optional.empty());


        AbstractJoinOperation ds3 = new InnerJoinOperation(ImmutableMap.of("ds1", ds1, "dsCodeList2", dsCodeList2));

        new VTLPrintStream(System.out).println(ds1);
        new VTLPrintStream(System.out).println(dsCodeList2);
        new VTLPrintStream(System.out).println(ds3);

        assertThat(ds3.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("periode", Component.Role.IDENTIFIER),
                entry("ds1_m1", Component.Role.MEASURE),
                entry("ds1_at1", Component.Role.ATTRIBUTE),
                entry("dsCodeList2_name", Component.Role.MEASURE),
                entry("validFrom", Component.Role.IDENTIFIER),
                entry("validTo", Component.Role.IDENTIFIER)
        );

        assertThat(ds3.getData()).flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "0101", Instant.parse("2015-01-01T00:00:00.00Z"), 100L, "attr1", "Halden", Instant.parse("2013-01-01T00:00:00.00Z"), null,
                        "0111", Instant.parse("2014-01-01T00:00:00.00Z"), 101L, "attr2", "Hvaler", Instant.parse("2015-01-01T00:00:00.00Z"), null
                );
    }

    private DataPoint tuple(VTLObject... components) {
        return DataPoint.create(Arrays.asList(components));
    }
}
