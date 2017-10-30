package no.ssb.vtl.script.functions;

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

import no.ssb.vtl.model.VTLObject;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VTLConcatenationTest {

    private VTLConcatenation vtlConcatenation;

    @Before
    public void setUp() throws Exception {
        vtlConcatenation = VTLConcatenation.getInstance();
    }

    @Test
    public void testInvoke() throws Exception {
        VTLObject<?> result = vtlConcatenation.invoke(
                Lists.newArrayList(
                        VTLObject.of("leftParameter"),
                        VTLObject.of("rightParameter")
                )
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLObject.of("leftParameter".concat("rightParameter")));
    }

    @Test
    public void testInvokeLeftNull() throws Exception {
        VTLObject<?> result = vtlConcatenation.invoke(
                Lists.newArrayList(
                        VTLObject.of((String) null),
                        VTLObject.of("rightParameter")
                )
        );
        assertThat(result).isEqualTo(VTLObject.NULL);
    }

    @Test
    public void testInvokeRightNull() throws Exception {
        VTLObject<?> result = vtlConcatenation.invoke(
                Lists.newArrayList(
                        VTLObject.of("leftParameter"),
                        VTLObject.of((String) null)
                )
        );
        assertThat(result).isEqualTo(VTLObject.NULL);
    }
}
