package no.ssb.vtl.script.functions;

/*
 * -
 *  * ========================LICENSE_START=================================
 * * Java VTL
 *  *
 * %%
 * Copyright (C) 2016 - 2017 Arild Johan Takvam-Borge
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
 *
 *
 *
 */

import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VTLTruncTest extends AbstractVTLNumberFunctionTest {

    @Before
    public void setUp() {
        vtlFunction = new VTLTrunc();
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5.12345),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(5.12));

        result = vtlFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5.88888),
                        VTLNumber.of(3)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(5.888));

        result = vtlFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5.432),
                        VTLNumber.of(0)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(5));
    }

    @Test
    @Override
    public void testInvokeWithNegativeNumber() throws Exception {
        VTLObject<?> result = vtlFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-5.6667),
                        VTLNumber.of(3)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(-5.666));

        result = vtlFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-5),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(-5.00));
    }

    @Test
    @Override
    public void testInvokeWithString() throws Exception {
        try {
            vtlFunction.invoke(
                    Lists.newArrayList(
                            VTLNumber.of("3.444445"),
                            VTLNumber.of(4)
                    )
            );
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("invalid type class no.ssb.vtl.model.VTLString$1 for argument ds, expected class no.ssb.vtl.model.VTLNumber");
        }
    }

    @Test
    @Override
    public void testInvokeWithTooManyArguments() {
        try {
            vtlFunction.invoke(
                    Lists.newArrayList(
                            VTLNumber.of(3.444445),
                            VTLNumber.of(4),
                            VTLNumber.of(11)
                    )
            );
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("expected 2 argument(s) but got 3");
        }
    }

    @Test
    public void testInvokeWithTooFewArguments() {
        try {
            vtlFunction.invoke(
                    Lists.newArrayList(
                            VTLNumber.of(3.444445)
                    )
            );
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("missing arguments [decimals]");
        }
    }

    @Test
    @Override
    public void testInvokeWithNullValue() {

        VTLObject<?> result = vtlFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of((Number)null),
                        VTLNumber.of(4)
                )
        );

        assertThat(result).isEqualTo(VTLNumber.of((Number)null));
    }

    @Test
    public void testInvokeWithNullDecimal() {
        VTLObject<?> result = vtlFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(1.52222),
                        VTLNumber.of((Number)null)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(1));
    }

    @Test
    public void testInvokeWithNegativeDecimalNumber() throws Exception {
        try {
            vtlFunction.invoke(
                    Lists.newArrayList(
                            VTLNumber.of(3.444445),
                            VTLNumber.of(-5)
                    )
            );
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Number of decimals must be equal to or greater than zero");
        }
    }
}
