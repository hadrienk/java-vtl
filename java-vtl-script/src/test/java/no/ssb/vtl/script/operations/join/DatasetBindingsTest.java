package no.ssb.vtl.script.operations.join;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLDate;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLString;
import no.ssb.vtl.model.VTLTyped;
import org.junit.Test;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DatasetBindingsTest {

    @Test
    public void testDatasetBindings() throws Exception {

        DataStructure dataStructure = DataStructure.builder()
                .put("c1", Component.Role.IDENTIFIER, String.class)
                .put("c2", Component.Role.IDENTIFIER, Long.class)
                .put("c3", Component.Role.IDENTIFIER, Double.class)
                .put("c4", Component.Role.IDENTIFIER, Instant.class)
                .put("c5", Component.Role.IDENTIFIER, Boolean.class)
                .build();

        Dataset dataset = getDataset(dataStructure);
        DatasetBindings bindings = new DatasetBindings(dataset);


        assertThat(((VTLTyped<?>) bindings.get("c1")).getVTLType()).isEqualTo(VTLString.class);
        assertThat(((VTLTyped<?>) bindings.get("c2")).getVTLType()).isEqualTo(VTLNumber.class);
        assertThat(((VTLTyped<?>) bindings.get("c3")).getVTLType()).isEqualTo(VTLNumber.class);
        assertThat(((VTLTyped<?>) bindings.get("c4")).getVTLType()).isEqualTo(VTLDate.class);
        assertThat(((VTLTyped<?>) bindings.get("c5")).getVTLType()).isEqualTo(VTLBoolean.class);

    }

    private Dataset getDataset(DataStructure dataStructure) {
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
                    return dataStructure;
                }
            };
    }
}
