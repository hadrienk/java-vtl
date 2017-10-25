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
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VTLAbsTest extends AbstractVTLNumberUnaryFunctionTest {


    @Before
    public void setUp() {
        vtlUnaryFunction = VTLAbs.getInstance();
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(9L)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(9));

        result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(9.12345)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(9.12345));
    }

    @Test
    @Override
    public void testInvokeWithNegativeNumber() throws Exception {
        VTLObject<?> result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-9L)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(9));

        result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-9.12345)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(9.12345));
    }
}
