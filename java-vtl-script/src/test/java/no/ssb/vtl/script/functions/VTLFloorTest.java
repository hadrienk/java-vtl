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

import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VTLFloorTest extends AbstractVTLNumberUnaryFunctionTest {

    @Before
    public void setUp() {
        vtlUnaryFunction = VTLFloor.getInstance();
    }
    @Test
    public void testInvokeWithNullNumber() {
        List<VTLObject> tests = Lists.newArrayList(
                VTLObject.NULL,
                VTLObject.of((Double) null),
                VTLObject.of((Float) null),
                VTLFloat.of((Double) null),
                VTLFloat.of((Float) null),
                VTLInteger.of((Double) null),
                VTLInteger.of((Float) null)
        );

        for (VTLObject test : tests) {
            VTLNumber<?> result = vtlUnaryFunction.invoke(
                    Lists.newArrayList(
                            test
                    )
            );
            assertThat(result).isNotNull();
            assertThat(result).isInstanceOf(vtlUnaryFunction.getVTLType());
            assertThat(result).isEqualTo(VTLInteger.of((Double) null));
            assertThat(result).isSameAs(VTLInteger.NULL);
        }
    }

    @Test
    public void testWithNotANumber() {
        List<VTLObject> tests = Lists.newArrayList(
                VTLObject.of(Double.NaN),
                VTLObject.of(Float.NaN),
                VTLFloat.of(Float.NaN),
                VTLFloat.of(Double.NaN),
                VTLInteger.of(Float.NaN),
                VTLInteger.of(Double.NaN)
        );

        for (VTLObject test : tests) {
            VTLNumber<?> result = vtlUnaryFunction.invoke(
                    Lists.newArrayList(
                            test
                    )
            );
            assertThat(result).isNotNull();
            assertThat(result).isInstanceOf(vtlUnaryFunction.getVTLType());
            assertThat(result).isEqualTo(VTLFloat.of(0));
        }
    }

    @Test
    public void testWithNegativeInfinity() {
        List<VTLObject> tests = Lists.newArrayList(
                VTLObject.of(Double.NEGATIVE_INFINITY),
                VTLObject.of(Float.NEGATIVE_INFINITY),
                VTLFloat.of(Float.NEGATIVE_INFINITY),
                VTLFloat.of(Double.NEGATIVE_INFINITY)
        );

        for (VTLObject test : tests) {
            VTLNumber<?> result = vtlUnaryFunction.invoke(
                    Lists.newArrayList(
                            test
                    )
            );
            assertThat(result).isNotNull();
            assertThat(result).isInstanceOf(vtlUnaryFunction.getVTLType());
            assertThat(result).isEqualTo(VTLInteger.of(Long.MIN_VALUE));
        }
    }

    @Test
    public void testWithPositiveInfinity() {
        List<VTLObject> tests = Lists.newArrayList(
                VTLObject.of(Double.POSITIVE_INFINITY),
                VTLObject.of(Float.POSITIVE_INFINITY),
                VTLFloat.of(Float.POSITIVE_INFINITY),
                VTLFloat.of(Double.POSITIVE_INFINITY)
        );

        for (VTLObject test : tests) {
            VTLNumber<?> result = vtlUnaryFunction.invoke(
                    Lists.newArrayList(
                            test
                    )
            );
            assertThat(result).isNotNull();
            assertThat(result).isInstanceOf(vtlUnaryFunction.getVTLType());
            assertThat(result).isEqualTo(VTLInteger.of(Long.MAX_VALUE));
        }
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5.99)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(5));

        result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(5.22)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(5));

    }

    @Test
    @Override
    public void testInvokeWithNegativeNumber() {
        VTLObject<?> result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-4.99)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(-5));

        result = vtlUnaryFunction.invoke(
                Lists.newArrayList(
                        VTLNumber.of(-4.33)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLNumber.of(-5));
    }
}