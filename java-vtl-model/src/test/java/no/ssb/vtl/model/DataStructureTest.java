package no.ssb.vtl.model;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

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

        DataStructure structure = DataStructure.of(
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
