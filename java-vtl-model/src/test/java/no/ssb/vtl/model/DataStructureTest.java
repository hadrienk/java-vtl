package no.ssb.vtl.model;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


public class DataStructureTest {

    @Test
    public void testWrapWithMap() throws Exception {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("region", "0101");
        map.put("number", "1.0");
        map.put("period", "2015");

        DataStructure structure = DataStructure.of((s, o) -> null,
                "number", Component.Role.MEASURE, Double.class,
                "period", Component.Role.IDENTIFIER, String.class,
                "region", Component.Role.IDENTIFIER, String.class
        );

        DataPoint wrapped = structure.wrap(map);

        assertThat(wrapped).hasSize(3);
        assertThat(wrapped)
                .extracting(VTLObject::get)
                .containsExactly(
                        "1.0", "2015", "0101"
                );
    }

}