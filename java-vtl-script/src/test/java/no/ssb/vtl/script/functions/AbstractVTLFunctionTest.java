package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import org.junit.Test;

import java.util.Arrays;

public class AbstractVTLFunctionTest {

    @Test
    public void testInvokeWithWrongType() throws Exception {
        AbstractVTLFunction function = new AbstractVTLFunction(
                "testInvokeWithWrongType",
                new AbstractVTLFunction.Argument<>("first", VTLString.class)
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        function.invoke(Arrays.asList(VTLObject.of(false)));
    }

    @Test
    public void testInvokeOptionalWithWrongType() throws Exception {
        AbstractVTLFunction function = new AbstractVTLFunction(
                "testInvokeWithWrongType",
                new AbstractVTLFunction.OptionalArgument<>("first", VTLString.class, VTLObject.of("optional"))
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        function.invoke(Arrays.asList(VTLObject.of(false)));
    }

    @Test
    public void testInvokeWithWrongTypes() throws Exception {
        AbstractVTLFunction function = new AbstractVTLFunction(
                "testInvokeWithWrongType",
                new AbstractVTLFunction.Argument<>("first", VTLString.class),
                new AbstractVTLFunction.Argument<>("second", VTLString.class),
                new AbstractVTLFunction.Argument<>("third", VTLString.class)
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        function.invoke(Arrays.asList(VTLObject.of("string"), VTLObject.of(false), VTLObject.of(false)));
    }

    @Test
    public void testInvokeOptionalWithWrongTypes() throws Exception {
        AbstractVTLFunction function = new AbstractVTLFunction(
                "testInvokeWithWrongType",
                new AbstractVTLFunction.OptionalArgument<>("first", VTLString.class, VTLObject.of("optional")),
                new AbstractVTLFunction.OptionalArgument<>("second", VTLString.class, VTLObject.of("optional")),
                new AbstractVTLFunction.OptionalArgument<>("third", VTLString.class, VTLObject.of("optional"))
        ) {
            @Override
            VTLObject safeInvoke(TypeSafeArguments arguments) {
                throw new RuntimeException("should not execute");
            }
        };

        function.invoke(Arrays.asList(VTLObject.of("string"), VTLObject.of(false), VTLObject.of(false)));
    }
}
