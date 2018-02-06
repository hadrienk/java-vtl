package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Pawel Buczek
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

import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class VTLIntegerFromStringTest {

    private VTLIntegerFromString vtlIntegerFromString;
    private VTLObject<?> result;

    @Before
    public void setUp() throws Exception {
        vtlIntegerFromString = VTLIntegerFromString.getInstance();
    }

    @Test
    public void testInvoke() throws Exception {
        result = vtlIntegerFromString.invoke(
                Lists.newArrayList(
                        VTLObject.of("1000")
                )
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLInteger.of(1000));
    }

    @Test
    public void testInvokeNonString() throws Exception {
        assertThatThrownBy(() -> vtlIntegerFromString.invoke(
                Lists.newArrayList(
                        VTLObject.of(1000)
                )
        ))
                .as("exception when passing number as input")
                .hasMessageContaining("expected class no.ssb.vtl.model.VTLString")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testInvokeNull() throws Exception {
        result = vtlIntegerFromString.invoke(
                Lists.newArrayList(
                        VTLObject.of((String) null)
                )
        );
        assertThat(result).isEqualTo(VTLString.of((String) null));
    }

}
