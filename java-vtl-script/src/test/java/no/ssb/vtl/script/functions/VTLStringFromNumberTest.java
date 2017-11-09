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

import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class VTLStringFromNumberTest {

    private VTLStringFromNumber vtlStringFromNumber;
    private VTLObject<?> result;

    @Before
    public void setUp() throws Exception {
        vtlStringFromNumber = VTLStringFromNumber.getInstance();
    }

    @Test
    public void testInvokeWithInteger() throws Exception {
        result = vtlStringFromNumber.invoke(
                Lists.newArrayList(
                        VTLInteger.of(123)
                )
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLString.of("123"));
    }

    @Test
    public void testInvokeWithDouble() throws Exception {
        result = vtlStringFromNumber.invoke(
                Lists.newArrayList(
                        VTLFloat.of(123.456d)
                )
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLString.of("123.456"));
    }

    @Test
    public void testInvokeWithDoubleScientific() throws Exception {
        result = vtlStringFromNumber.invoke(
                Lists.newArrayList(
                        VTLFloat.of(1.123e-10)
                )
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLString.of("1.123E-10"));
    }

    @Test
    public void testInvokeNonNumber() throws Exception {
        assertThatThrownBy(() -> vtlStringFromNumber.invoke(
                Lists.newArrayList(
                        VTLString.of("123")
                )
        ))
                .as("exception when passing number as input")
                .hasMessageContaining("expected class no.ssb.vtl.model.VTLNumber")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testInvokeNull() throws Exception {
        result = vtlStringFromNumber.invoke(
                Lists.newArrayList(
                        VTLString.of((String) null)
                )
        );
        assertThat(result).isEqualTo(VTLString.of((String) null));
    }

}
