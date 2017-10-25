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
        vtlBinaryFunction = VTLNroot.getInstance();
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
                .hasMessage("Argument{name=index, type=VTLNumber} cannot be null or zero, was 0")
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
                .hasMessage("Argument{name=ds, type=VTLNumber} must be an integer, was 1.99")
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
                .hasMessage("Argument{name=ds, type=VTLNumber} must be greater than zero" +
                        " when Argument{name=index, type=VTLNumber} is even, was -25.0")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Override
    public void testInvokeWithNullAsSecondParameter() {
        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(99),
                        VTLNumber.of((Double) null)
                )
        ))
                .as("exception when passing null where null is not allowed")
                .hasMessage("Argument{name=index, type=VTLNumber} cannot be null or zero, was [NULL]")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
