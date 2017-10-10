package no.ssb.vtl.script.operations.join;

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

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.StaticDataset;
import no.ssb.vtl.model.VTLObject;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DataPointBindingsTest {

    @Test
    public void test() throws Exception {
        StaticDataset ds = StaticDataset.create()
                .addComponent("id", Component.Role.IDENTIFIER, String.class)
                .addComponent("measure", Component.Role.MEASURE, String.class)
                .addPoints("id", "measure")
                .build();

        DataPointBindings dataPointBindings = new DataPointBindings(ds.getDataStructure());

        List<DataPoint> pointList = ds.getData().map(dataPointBindings::setDataPoint)
                .peek(bindings -> {
                    bindings.put("id", VTLObject.of("newId"));
                    bindings.put("measure", VTLObject.of("newMeasure"));
                })
                .map(DataPointBindings::getDataPoint)
                .collect(Collectors.toList());

        assertThat(pointList)
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "newId", "newMeasure"
                );
    }
}
