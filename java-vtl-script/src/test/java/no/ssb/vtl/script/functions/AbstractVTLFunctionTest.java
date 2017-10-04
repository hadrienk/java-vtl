package no.ssb.vtl.script.functions;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AbstractVTLFunctionTest {

    @Test
    public void testInvokeWithWrongType() throws Exception {
        AbstractVTLFunction<String> function = new AbstractVTLFunction<String>(
                "testInvokeWithWrongType",
                String.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class)
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
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
        AbstractVTLFunction<String> function = new AbstractVTLFunction<String>(
                "testInvokeWithWrongType",
                String.class,
                new AbstractVTLFunction.OptionalArgument<>("first", VTLString.class, VTLObject.of("optional"))
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
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
        AbstractVTLFunction<String> function = new AbstractVTLFunction<String>(
                "testInvokeWithWrongType",
                String.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.Argument<>("third", VTLString.class)
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
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
        AbstractVTLFunction<String> function = new AbstractVTLFunction<String>(
                "testInvokeWithWrongType",
                String.class,
                new AbstractVTLFunction.OptionalArgument<>("first", VTLString.class, VTLObject.of("optional")),
                new AbstractVTLFunction.OptionalArgument<>("second", VTLString.class, VTLObject.of("optional")),
                new AbstractVTLFunction.OptionalArgument<>("third", VTLString.class, VTLObject.of("optional"))
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
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
        VTLFunction<String> function = new AbstractVTLFunction<String>(
                "testMissingArgument",
                String.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.Argument<>("third", VTLString.class)
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        assertThatThrownBy(() -> {
            function.invoke(ImmutableMap.of("first", VTLString.of("ok"), "second", VTLString.of("ok")));
        }).as("exception when missing argument")
                .hasMessageContaining("missing")
                .hasMessageContaining("third")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testUnknownNamedArgument() throws Exception {
        VTLFunction<String> function = new AbstractVTLFunction<String>(
                "testUnknownNamedArgument",
                String.class,
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.Argument<>("third", VTLString.class)
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        assertThatThrownBy(() -> {
            function.invoke(ImmutableMap.of("first", VTLString.of("ok"), "second", VTLString.of("ok"), "notfound", VTLString.of("ok")));
        }).as("exception when unknown argument is found")
                .hasMessageContaining("notfound")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
