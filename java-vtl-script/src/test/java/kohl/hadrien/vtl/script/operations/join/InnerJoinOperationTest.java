package kohl.hadrien.vtl.script.operations.join;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import kohl.hadrien.vtl.model.*;
import org.junit.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by hadrien on 16/11/2016.
 */
public class InnerJoinOperationTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testDefaultJoin() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        Dataset ds2 = mock(Dataset.class);
        Dataset ds3 = mock(Dataset.class);

        DataStructure structure = DataStructure.of(
                mapper::convertValue,
                "time", Identifier.class, Year.class,
                "ref_area", Identifier.class, String.class,
                "partner", Identifier.class, String.class,
                "obs_value", Measure.class, Integer.class,
                "obs_status", Attribute.class, String.class
        );

        given(ds1.getDataStructure()).willReturn(structure);
        given(ds2.getDataStructure()).willReturn(structure);

        given(ds1.get()).willReturn(Stream.of(
                tuple(
                        structure.wrap("time", Year.of(2010)),
                        structure.wrap("ref_area", "EU25"),
                        structure.wrap("partner", "CA"),
                        structure.wrap("obs_value", "20"),
                        structure.wrap("obs_status", "E")
                ), tuple(
                        structure.wrap("time", Year.of(2010)),
                        structure.wrap("ref_area", "EU25"),
                        structure.wrap("partner", "BG"),
                        structure.wrap("obs_value", "2"),
                        structure.wrap("obs_status", "P")
                ), tuple(
                        structure.wrap("time", Year.of(2010)),
                        structure.wrap("ref_area", "EU25"),
                        structure.wrap("partner", "RO"),
                        structure.wrap("obs_value", "2"),
                        structure.wrap("obs_status", "P")
                )
        ));

        given(ds2.get()).willReturn(Stream.of(
                tuple(
                        structure.wrap("time", Year.of(2010)),
                        structure.wrap("ref_area", "EU25"),
                        structure.wrap("partner", "CA"),
                        structure.wrap("obs_value", "10"),
                        structure.wrap("obs_status", "P")
                )
        ));

        AbstractJoinOperation result = new InnerJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        result.getClauses().add(new JoinClause() {
            @Override
            public DataStructure transformDataStructure(DataStructure structure) {
                return structure;
            }

            @Override
            public Dataset.Tuple transformTuple(Dataset.Tuple structure) {
                return structure;
            }
        });

        assertThat(result.get()).contains(
                tuple(
                        structure.wrap("time", Year.of(2010)),
                        structure.wrap("ref_area", "EU25"),
                        structure.wrap("partner", "CA"),
                        structure.wrap("obs_value", "20"),
                        structure.wrap("obs_status", "E"),
                        structure.wrap("obs_value", "10"),
                        structure.wrap("obs_status", "P")
                )
        );
    }

    private Dataset.Tuple tuple(Component... components) {
        return new Dataset.AbstractTuple() {
            @Override
            protected List<Component> delegate() {
                return Arrays.asList(components);
            }
        };
    }
}
