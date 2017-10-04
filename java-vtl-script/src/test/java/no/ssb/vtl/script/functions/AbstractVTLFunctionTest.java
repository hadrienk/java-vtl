package no.ssb.vtl.script.functions;

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
                .hasMessageContaining("third")
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
                .hasMessageContaining("third")
                .hasMessageContaining("String")
                .hasMessageContaining("Boolean")
                .isExactlyInstanceOf(IllegalArgumentException.class);

    }
}
