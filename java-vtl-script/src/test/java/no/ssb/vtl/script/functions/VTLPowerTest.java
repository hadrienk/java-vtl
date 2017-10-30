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
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VTLPowerTest extends AbstractVTLNumberBinaryFunctionTest {

    @Before
    public void setUp() throws Exception {
        vtlBinaryFunction = VTLPower.getInstance();
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5),
                        VTLNumber.of(2)
                        )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(25.0));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(3),
                        VTLNumber.of(3)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(27.0));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(3.4),
                        VTLNumber.of(1.2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(4.342848711597634));
    }

    @Test
    @Override
    public void testInvokeWithNegativeNumber() throws Exception {
        VTLObject<?> result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-5),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(25.0));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(3),
                        VTLNumber.of(-3)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(0.037037037037037035));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-3.4),
                        VTLNumber.of(-1.2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(Double.NaN));
    }

    @Test
    @Override
    public void testInvokeWithNullValue() {

        VTLObject<?> result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of((Double) null),
                        VTLNumber.of(4)
                )
        );

        assertThat(result).isEqualTo(VTLNumber.of((Number)null));

    }

    @Test
    @Override
    public void testInvokeWithNullAsSecondParameter() {
        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(4),
                        VTLNumber.of((Double) null)
                )
        ))
                .as("exception when passing null where not null is expected")
                .hasMessage("Argument{name=base, type=VTLNumber} must be a valid number, was [NULL]")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
