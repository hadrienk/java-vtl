package no.ssb.vtl.script.expressions.logic;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.expressions.LiteralExpression;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

public class NotExpressionTest {
    private VTLExpression truthy= new LiteralExpression(VTLObject.of(true));
    private VTLExpression falsy = new LiteralExpression(VTLObject.of(false));
    private VTLExpression nully = new LiteralExpression(VTLObject.of((Boolean) null));

    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void testTruth() throws Exception {
        ImmutableMap.Builder<VTLExpression, VTLExpression> builder = ImmutableMap.builder();
        builder.put(falsy, truthy);
        builder.put(truthy, falsy);
        builder.put(nully, nully);

        for (Map.Entry<VTLExpression, VTLExpression> test : builder.build().entrySet()) {
            VTLExpression operand = test.getKey();
            VTLObject expected = test.getValue().resolve(null);
            VTLExpression result = new NotExpression(operand);

            softly.assertThat(expected.get())
                    .as("result of %s -> %s", result, result.resolve(null).get())
                    .isEqualTo(result.resolve(null).get());

        }
    }
}
