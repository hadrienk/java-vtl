package no.ssb.vtl.script.functions;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLObject;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class TypeSafeArguments {
    private final Map<AbstractVTLFunction.Argument, VTLObject<?>> arguments;

    private static final String WRONG_ARGUMENT_TYPE = "invalid type %s for argument %s, expected %s";

    public TypeSafeArguments(Map<String, VTLObject> arguments, ImmutableMap<String, AbstractVTLFunction.Argument> signature) {
        checkArgument(arguments.size() == signature.size());
        ImmutableMap.Builder<AbstractVTLFunction.Argument, VTLObject<?>> builder = ImmutableMap.builder();
        for (String name : signature.keySet()) {
            VTLObject value = arguments.get(name);
            AbstractVTLFunction.Argument argument = signature.get(name);
            builder.put(argument, checkType(value, argument));
        }
        this.arguments = builder.build();
    }

    public <T> T get(AbstractVTLFunction.Argument<T> argumentReference) {
        checkArgument(arguments.containsKey(argumentReference));
        return (T) arguments.get(argumentReference);
    }

    private static VTLObject checkType(VTLObject value, AbstractVTLFunction.Argument argument) {
        // TODO: exception type.
        checkArgument(argument.getType().isAssignableFrom(value.getClass()),
                WRONG_ARGUMENT_TYPE,
                value.getClass(), argument.getName(), argument.getType()
        );
        return value;
    }

}
