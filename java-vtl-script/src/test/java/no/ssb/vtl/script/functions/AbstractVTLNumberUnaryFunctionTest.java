package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Arild Johan Takvam-Borge
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
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class AbstractVTLNumberUnaryFunctionTest
        implements VTLNumberFunctionTest {

    AbstractVTLFunction<? extends VTLNumber> vtlUnaryFunction;

    @Test
    @Override
    public void testInvokeWithNullValue() throws Exception {
        VTLObject<?> result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of((Float) null)
                )
        );

        assertThat(result).isEqualTo(VTLNumber.of((Number)null));
    }

    @Test
    @Override
    public void testInvokeWithString() throws Exception {
        assertThatThrownBy(() -> vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of("3.444445")
                )
        ))
                .as("Exception when passing String argument where Number is expected")
                .hasMessage("invalid type class no.ssb.vtl.model.VTLString$1 for argument ds, expected class no.ssb.vtl.model.VTLNumber")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @Override
    public void testInvokeWithEmptyArgumentsList() throws Exception {
        assertThatThrownBy(() -> vtlUnaryFunction.invoke(
                Lists.emptyList()
        ))
                .as("exception when passing no arguments")
                .hasMessage("Required argument not present")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @Override
    public void testInvokeWithTooManyArguments() throws Exception {
        assertThatThrownBy(() -> vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(3.444445),
                        VTLNumber.of(4)
                )
        ))
                .as("exception when passing too many arguments")
                .hasMessage("expected 1 argument(s) but got 2")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
