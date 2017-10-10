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

public class VTLLnTest extends AbstractVTLNumberFunctionTest {

    @Before
    public void setUp() {
        vtlFunction = new VTLLn();
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(148)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(4.997212273764115));

        result = vtlFunction.invoke(
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
        try {
            vtlFunction.invoke(
                    Lists.newArrayList(
                            VTLNumber.of(-99)
                    )
            );
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("The number must be greater than zero");
        }

    }
}