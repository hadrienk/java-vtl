package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
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

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AbstractVTLFunctionTest {

    @Test
    public void testInvokeWithWrongType() throws Exception {
        AbstractVTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testInvokeWithWrongType",
                VTLString.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class)
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        assertThatThrownBy(() -> {
            function.invoke(Arrays.asList(VTLObject.of(false)));
        })
                .as("exception when passing wrong type argument")
                .hasMessageContaining("first")
                .hasMessageContaining("String")
                .hasMessageContaining("Boolean")
                .isExactlyInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void testInvokeOptionalWithWrongType() throws Exception {
        AbstractVTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testInvokeWithWrongType",
                VTLString.class,
                new AbstractVTLFunction.OptionalArgument<>("first", VTLString.class, VTLObject.of("optional"))
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        assertThatThrownBy(() -> {
            function.invoke(Arrays.asList(VTLObject.of(false)));
        })
                .as("exception when passing wrong type optional argument")
                .hasMessageContaining("first")
                .hasMessageContaining("String")
                .hasMessageContaining("Boolean")
                .isExactlyInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void testInvokeWithWrongTypes() throws Exception {
        AbstractVTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testInvokeWithWrongType",
                VTLString.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.Argument<>("third", VTLString.class)
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        assertThatThrownBy(() -> {
            function.invoke(Arrays.asList(VTLObject.of("string"), VTLObject.of(false), VTLObject.of(false)));
        }).as("exception when passing several wrong type arguments")
                .hasMessageContaining("second")
                // TODO: .hasMessageContaining("third")
                .hasMessageContaining("String")
                .hasMessageContaining("Boolean")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testInvokeOptionalWithWrongTypes() throws Exception {
        AbstractVTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testInvokeWithWrongType",
                VTLString.class,
                new AbstractVTLFunction.OptionalArgument<>("first", VTLString.class, VTLObject.of("optional")),
                new AbstractVTLFunction.OptionalArgument<>("second", VTLString.class, VTLObject.of("optional")),
                new AbstractVTLFunction.OptionalArgument<>("third", VTLString.class, VTLObject.of("optional"))
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        assertThatThrownBy(() -> {
            function.invoke(Arrays.asList(VTLObject.of("string"), VTLObject.of(false), VTLObject.of(false)));
        }).as("exception when passing several wrong type optional arguments")
                .hasMessageContaining("second")
                // TODO: .hasMessageContaining("third")
                .hasMessageContaining("String")
                .hasMessageContaining("Boolean")
                .isExactlyInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void testMissingArgument() throws Exception {
        VTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testMissingArgument",
                VTLString.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.Argument<>("third", VTLString.class)
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        assertThatThrownBy(() -> {
            function.invoke(ImmutableMap.of("first", VTLString.of("ok"), "second", VTLString.of("ok")));
        }).as("exception when missing argument (named invocation)")
                .hasMessageContaining("missing")
                .hasMessageContaining("third")
                .isExactlyInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            function.invoke(Collections.singletonList(VTLString.of("ok")), ImmutableMap.of("second", VTLString.of("ok")));
        }).as("exception when missing argument (mixed invocation)")
                .hasMessageContaining("missing")
                .hasMessageContaining("third")
                .isExactlyInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            function.invoke(Arrays.asList(VTLString.of("ok"), VTLString.of("ok")));
        }).as("exception when missing argument (unnamed invocation)")
                .hasMessageContaining("missing")
                .hasMessageContaining("third")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testUnknownNamedArgument() throws Exception {
        VTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testUnknownNamedArgument",
                VTLString.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.Argument<>("third", VTLString.class)
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        assertThatThrownBy(() -> {
            function.invoke(ImmutableMap.of("first", VTLString.of("ok"), "second", VTLString.of("ok"), "notfound", VTLString.of("ok")));
        }).as("exception when unknown argument is found")
                .hasMessageContaining("notfound")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testInvalidReturnType() throws Exception {
        // TODO
    }

    @Test
    public void testNoArguments() throws Exception {
        VTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testUnknownNamedArgument",
                VTLString.class
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                return VTLObject.of("returnedValue");
            }
        };

        VTLString result = function.invoke(Collections.emptyList());
        assertThat(result).isEqualTo(VTLObject.of("returnedValue"));

    }

    @Test
    public void testOptionalArgument() throws Exception {
        VTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testUnknownNamedArgument",
                VTLString.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.OptionalArgument<>("third", VTLString.class, VTLObject.of("defaultThird"))
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                return VTLObject.of("returnedValue");
            }
        };

        VTLString resultUnamedInvocation = function.invoke(
            Arrays.asList(VTLObject.of("passedFirst"),VTLObject.of("passedSecond"))
        );

        VTLString resultNamedInvocation = function.invoke(
                ImmutableMap.of(
                        "first", VTLObject.of("passedFirst"),
                        "second", VTLObject.of("passedSecond")
                )
        );

        VTLObject<String> resultMixedInvocation = function.invoke(
                Arrays.asList(VTLObject.of("passedFirst")),
                ImmutableMap.of(
                        "second", VTLObject.of("passedSecond")
                )
        );

        assertThat(resultUnamedInvocation).isEqualTo(VTLObject.of("returnedValue"));
        assertThat(resultNamedInvocation).isEqualTo(VTLObject.of("returnedValue"));
        assertThat(resultMixedInvocation).isEqualTo(VTLObject.of("returnedValue"));

    }

    @Test
    public void testType() throws Exception {
        VTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "testUnknownNamedArgument",
                VTLString.class
        ) {

            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                return null;
            }
        };

        assertThat(function.getVTLType()).isSameAs(VTLString.class);
    }

    @Test
    public void testToString() throws Exception {
        VTLFunction<VTLString> function = new AbstractVTLFunction<VTLString>(
                "functionName",
                VTLString.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.OptionalArgument<>("third", VTLString.class, VTLObject.of("defaultThird"))
        ) {
            @Override
            protected VTLString safeInvoke(TypeSafeArguments arguments) {
                return VTLObject.of("returnedValue");
            }
        };

        assertThat(function.toString()).isNotEmpty();
    }
}
