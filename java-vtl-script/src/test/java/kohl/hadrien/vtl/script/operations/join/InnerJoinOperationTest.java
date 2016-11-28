package kohl.hadrien.vtl.script.operations.join;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import kohl.hadrien.vtl.model.DataPoint;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import org.junit.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static kohl.hadrien.vtl.model.Component.Role;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class InnerJoinOperationTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testDefaultJoin() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        Dataset ds2 = mock(Dataset.class);

        DataStructure structure1 = DataStructure.of(
                mapper::convertValue,
                "time", Role.IDENTIFIER, Year.class,
                "ref_area", Role.IDENTIFIER, String.class,
                "partner", Role.IDENTIFIER, String.class,
                "obs_value", Role.MEASURE, Integer.class,
                "obs_status", Role.ATTRIBUTE, String.class
        );

        DataStructure structure2 = DataStructure.of(
                mapper::convertValue,
                "time", Role.IDENTIFIER, Year.class,
                "ref_area", Role.IDENTIFIER, String.class,
                "partner", Role.IDENTIFIER, String.class,
                "obs_value", Role.MEASURE, Integer.class,
                "obs_status", Role.ATTRIBUTE, String.class
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

        /*result.getClauses().add(new JoinClause() {
            @Override
            public DataStructure transformDataStructure(DataStructure structure1) {
                return structure1;
            }

            @Override
            public Dataset.Tuple transformTuple(Dataset.Tuple structure1) {
                return structure1;
            }
        });*/

        assertThat(result.get()).contains(
                tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "CA"),
                        structure1.wrap("obs_value", "20"),
                        structure1.wrap("obs_status", "E"),
                        structure1.wrap("obs_value", "10"),
                        structure1.wrap("obs_status", "P")
                )
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
