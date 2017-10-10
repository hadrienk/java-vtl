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

public class VTLNrootTest extends AbstractVTLNumberBinaryFunctionTest{

    @Before
    public void setUp() throws Exception {
        vtlBinaryFunction = new VTLNroot();
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {

        VTLObject<?> result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(25),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(5.0));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(8),
                        VTLNumber.of(5)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(1.5157165665103982));

        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(8),
                        VTLNumber.of(0)
                )
        ))
                .as("exception when zero is passed where zero is not allowed")
                .hasMessage("Index cannot be zero")
                .isExactlyInstanceOf(IllegalArgumentException.class);

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(0),
                        VTLNumber.of(5)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(0.0));

        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5.7),
                        VTLNumber.of(1.99)
                )
        ))
                .as("exception when passing a double where an int is expected")
                .hasMessage("Index must be of type Integer")
                .isExactlyInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @Override
    public void testInvokeWithNegativeNumber() throws Exception {
        VTLObject<?> result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-8),
                        VTLNumber.of(3)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(-2.0));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(8),
                        VTLNumber.of(-3)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(0.5));

        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-25),
                        VTLNumber.of(2)
                )
        ))
                .as("exception when passing a negative value where a positive value is expected")
                .hasMessage("The number must be greater than zero when index is even")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Override
    public void testInvokeWithNullAsSecondParameter() {
        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(99),
                        VTLNumber.of((Number) null)
                )
        ))
                .as("exception when passing null where null is not allowed")
                .hasMessage("The index cannot be null")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
