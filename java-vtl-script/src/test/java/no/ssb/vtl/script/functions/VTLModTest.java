package no.ssb.vtl.script.functions;

/*
 * -
 *  * ========================LICENSE_START=================================
 * * Java VTL
 *  *
 * %%
 * Copyright (C) 2017 Arild Johan Takvam-Borge
 *  *
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VTLModTest extends AbstractVTLNumberBinaryFunctionTest {
    @Before
    public void setUp() throws Exception {
        vtlBinaryFunction = VTLMod.getInstance();
    }

    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(1));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5.5),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(1.5));

        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-5),
                        VTLNumber.of(0)
                )
        ))
                .as("exception when passing zero where zero is not allowed")
                .hasMessage("Denominator cannot be null or zero")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Override
    public void testInvokeWithNegativeNumber() throws Exception {
        VTLObject<?> result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-5),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(1));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5.5),
                        VTLNumber.of(-2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(1.5));
    }

    @Override
    public void testInvokeWithNullAsSecondParameter() {
        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-5),
                        VTLNumber.of((Double) null)
                )
        ))
                .as("exception when passing zero where zero is not allowed")
                .hasMessage("Argument{name=denominator, type=VTLNumber} cannot be null or zero, was [NULL]")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
