package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2017 Arild Johan Takvam-Borge
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

import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VTLLnTest extends AbstractVTLNumberUnaryFunctionTest {

    @Before
    public void setUp() {
        vtlUnaryFunction = VTLLn.getInstance();
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(148)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(4.997212273764115));

        result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(1)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(0.0));
    }

    @Test
    @Override
    public void testInvokeWithNegativeNumber() throws Exception {
        assertThatThrownBy(() -> vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-99)
                )
        ))
                .as("exception when passing negative number where positive is expected")
                .hasMessage("Argument{name=ds, type=VTLNumber} must be greater than zero, was -99")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
