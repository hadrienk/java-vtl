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

public class VTLLogTest extends AbstractVTLNumberBinaryFunctionTest {

    @Before
    public void setUp() {
        vtlBinaryFunction = VTLLog.getInstance();
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(1024),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(10.0));

        result = vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(1024),
                        VTLNumber.of(10)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(3.0102999566398116));
    }

    @Test
    @Override
    public void testInvokeWithNegativeNumber() throws Exception {

        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-1024),
                        VTLNumber.of(2)
                )
        ))
                .as("exception when passing negative number where positive is expected")
                .hasMessage("Argument{name=ds, type=VTLNumber} must be greater than zero, was -1024")
                .isExactlyInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> vtlBinaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(1024),
                        VTLNumber.of(-10)
                )
        ))
                .as("exception when passing negative number where positive is expected")
                .hasMessage("Argument{name=base, type=VTLNumber} must be greater than zero, was -10")
                .isExactlyInstanceOf(IllegalArgumentException.class);
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
                .as("exception when passing <=0 where >0 is expected")
                .hasMessage("Argument{name=base, type=VTLNumber} must be greater than zero, was [NULL]")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
