package kohl.hadrien.vtl.script.operations.join;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.model.Measure;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class JoinOperationTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSimple() throws Exception {

        Dataset ds1 = mock(Dataset.class);

        DataStructure ds1Struct = DataStructure.of(
                mapper::convertValue,
                "m",
                Measure.class,
                Integer.class
        );

        given(ds1.getDataStructure()).willReturn(ds1Struct);

        JoinOperation result = new JoinOperation(ImmutableMap.of("ds1", ds1)) {

            @Override
            Dataset join() {
                return ds1;
            }
        };

        result.getClauses().add(dataset -> {
            return dataset;
        });

        assertThat(result.get()).isSameAs(ds1);

    }
}
