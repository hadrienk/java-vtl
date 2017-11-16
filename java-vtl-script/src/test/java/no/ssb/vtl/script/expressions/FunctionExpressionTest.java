package no.ssb.vtl.script.expressions;

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

import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLString;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class FunctionExpressionTest {

    private VTLExpression expr = mock(VTLExpression.class);;
    private VTLFunction.Signature signature = VTLFunction.Signature.builder()
            .addArgument("one", VTLString.class)
            .addArgument("two", VTLString.class)
            .addArgument("three", VTLString.class, true)
            .addArgument("four", VTLString.class, false)
            .addArgument("five", VTLString.class, false)
            .addArgument("six", VTLString.class, false)
            .build();

    @Test
    public void testDuplicateArguments() throws Exception {
        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, asList(expr, expr, expr), of("one", expr))
        )
                .as("missing argument exception")
                .hasMessage("duplicate argument(s): one");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, asList(expr, expr, expr), of("two", expr))
        )
                .as("missing argument exception")
                .hasMessage("duplicate argument(s): two");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, asList(expr, expr, expr), of("one", expr, "three", expr))
        )
                .as("missing argument exception")
                .hasMessage("duplicate argument(s): one, three");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, asList(expr, expr, expr), of("three", expr, "one", expr))
        )
                .as("missing argument exception")
                .hasMessage("duplicate argument(s): three, one");
    }

    @Test
    public void testNoArgument() {
        VTLFunction.Signature signature = VTLFunction.Signature.builder().build();
        FunctionExpression.mergeArguments(signature, emptyList(), emptyMap());
    }

    @Test
    public void testUnknownArgumentsInMap() throws Exception {

        signature = VTLFunction.Signature.builder()
                .addArgument("one", VTLString.class)
                .addArgument("two", VTLString.class)
                .build();

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, asList(expr, expr), of("three", expr, "four", expr))
        )
                .as("missing argument exception")
                .hasMessage("unknown argument(s): three, four");
    }

    @Test
    public void testUnknownArguments() throws Exception {

        signature = VTLFunction.Signature.builder()
                .addArgument("one", VTLString.class)
                .addArgument("two", VTLString.class)
                .build();

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, asList(expr, expr, expr), emptyMap())
        )
                .as("missing argument exception")
                .hasMessage("too many arguments, expected 2 but got 3");
    }

    @Test
    public void testMissingArguments() throws Exception {
        List<List<VTLExpression>> success = asList(
                asList(expr, expr, expr, expr, expr, expr),
                asList(expr, expr, expr, expr, expr),
                asList(expr, expr, expr, expr),
                asList(expr, expr, expr)
        );

        for (List<VTLExpression> expressions : success)
            FunctionExpression.mergeArguments(signature, expressions, emptyMap());

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, asList(expr), emptyMap())
        )
                .as("missing argument exception")
                .hasMessage("missing argument(s): two, three");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, emptyList(), emptyMap())
        )
                .as("missing argument exception")
                .hasMessage("missing argument(s): one, two, three");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, emptyList(), of("three", expr))
        )
                .as("missing argument exception")
                .hasMessage("missing argument(s): one, two");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, emptyList(), of("three", expr, "four", expr))
        )
                .as("missing argument exception")
                .hasMessage("missing argument(s): one, two");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, emptyList(), of("three", expr, "four", expr))
        )
                .as("missing argument exception")
                .hasMessage("missing argument(s): one, two");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, emptyList(), of("one", expr, "three", expr, "four", expr))
        )
                .as("missing argument exception")
                .hasMessage("missing argument(s): two");

        assertThatThrownBy(
                () -> FunctionExpression.mergeArguments(signature, emptyList(), of("two", expr, "three", expr, "four", expr))
        )
                .as("missing argument exception")
                .hasMessage("missing argument(s): one");

    }
}
