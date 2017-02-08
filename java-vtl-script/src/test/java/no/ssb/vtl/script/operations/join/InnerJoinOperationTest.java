package no.ssb.vtl.script.operations.join;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class InnerJoinOperationTest {

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
        Dataset ds1 = mock(Dataset.class);
        Dataset ds2 = mock(Dataset.class);

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

        given(ds1.get()).willReturn(Stream.of(
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

        given(ds2.get()).willReturn(Stream.of(
                tuple(
                        structure2.wrap("time", Year.of(2010)),
                        structure2.wrap("ref_area", "EU25"),
                        structure2.wrap("partner", "CA"),
                        structure2.wrap("obs_value", "10"),
                        structure2.wrap("obs_status", "P")
                )
        ));

        AbstractJoinOperation result = new InnerJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

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

                .containsExactly(
                        IDENTIFIER, structure1.get("time"), Year.of(2010),
                        IDENTIFIER, structure1.get("ref_area"), "EU25",
                        IDENTIFIER, structure1.get("partner"), "CA",
                        MEASURE, structure1.get("obs_value"), "20",
                        ATTRIBUTE, structure1.get("obs_status"), "E",
                        MEASURE, structure2.get("obs_value"), "10",
                        ATTRIBUTE, structure2.get("obs_status"), "P"
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
