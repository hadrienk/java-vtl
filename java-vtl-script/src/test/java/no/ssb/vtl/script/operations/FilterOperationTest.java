package no.ssb.vtl.script.operations;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLBoolean;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class FilterOperationTest {

    private Dataset dataset;

    @Before
    public void setUp() throws Exception {
        DataStructure structure = DataStructure.builder()
                .put("id", Component.Role.IDENTIFIER, String.class)
                .build();

        DataPoint dataPoint = structure.wrap(ImmutableMap.of("id", "idValue"));
        List<DataPoint> dataPoints = Collections.singletonList(dataPoint);

        dataset = new TestableDataset(structure, dataPoints);
    }

    @Test
    public void testPredicateReturnsNull() throws Exception {

        FilterOperation resultBooleanNull = new FilterOperation(dataset, dp -> VTLBoolean.of(null));
        assertThat(resultBooleanNull.getData()).isEmpty();

        FilterOperation resultNull = new FilterOperation(dataset, dp -> null);
        assertThat(resultNull.getData()).isEmpty();
    }

    @Test
    public void testPredicateReturnsFalse() throws Exception {

        FilterOperation result = new FilterOperation(dataset, dp -> VTLBoolean.of(Boolean.FALSE));
        assertThat(result.getData()).isEmpty();
    }


    @Test
    public void testPredicateReturnsTrue() throws Exception {

        FilterOperation result = new FilterOperation(dataset, dp -> VTLBoolean.of(Boolean.TRUE));
        assertThat(result.getData()).isNotEmpty();
    }

    private static class TestableDataset implements Dataset {

        private final List<DataPoint> dataPoints;
        private final DataStructure structure;

        private TestableDataset(DataStructure structure, List<DataPoint> dataPoints) {
            this.dataPoints = dataPoints;
            this.structure = structure;
        }

        @Override
        public Stream<DataPoint> getData() {
            return dataPoints.stream();
        }

        @Override
        public Optional<Map<String, Integer>> getDistinctValuesCount() {
            return Optional.empty();
        }

        @Override
        public Optional<Long> getSize() {
            return Optional.of((long) dataPoints.size());
        }

        @Override
        public DataStructure getDataStructure() {
            return structure;
        }
    }
}

