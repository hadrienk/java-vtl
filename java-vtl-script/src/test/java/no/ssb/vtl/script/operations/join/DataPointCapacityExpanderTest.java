package no.ssb.vtl.script.operations.join;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2018 Hadrien Kohl
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

import no.ssb.vtl.model.DataPoint;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DataPointCapacityExpanderTest {

    private DataPoint datapoint;

    @Before
    public void setUp() throws Exception {
        datapoint = DataPoint.create("1", "2", "3", "4");
    }

    @Test
    public void testDoesNothingIfCapacityIsUnder() {
        DataPointCapacityExpander expander = new DataPointCapacityExpander(datapoint.size() - 1);

        Integer sizeBefore = datapoint.size();
        expander.accept(datapoint);
        assertThat(datapoint.size()).isEqualTo(sizeBefore);
    }

    @Test
    public void testDoesNothingIfCapacityIsEqual() {
        DataPointCapacityExpander expander = new DataPointCapacityExpander(datapoint.size());

        Integer sizeBefore = datapoint.size();
        expander.accept(datapoint);
        assertThat(datapoint.size()).isEqualTo(sizeBefore);
    }

    @Test
    public void testCapacityIsUnder() {
        DataPointCapacityExpander expander = new DataPointCapacityExpander(datapoint.size() * 2);

        Integer sizeBefore = datapoint.size();
        expander.accept(datapoint);
        assertThat(datapoint.size()).isEqualTo(sizeBefore * 2);

    }
}
