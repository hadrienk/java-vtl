package no.ssb.vtl.script.functions;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.VTLObject;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract vtl function that takes care of arguments validation.
 * TODO: Extend a contract.
 * TODO: Add toString
 * TODO: Equals
 */
public abstract class AbstractVTLFunction {

    public static final String ARGUMENT_LARGER_THAN_DEFINITION = "passed argument larger than definition";
    public static final String UNKNOWN_ARGUMENTS = "unknown arguments %s";
    public static final String MISSING_ARGUMENTS = "missing arguments %s";
    public static final String WRONG_ARGUMENT_TYPE = "invalid type %s for argument %s, expected %s";
    private final String id;
    private final ImmutableMap<String, Argument> signature;

    protected AbstractVTLFunction(String id, Argument... arguments) {
        this.id = checkNotNull(id);
        checkArgument(!id.isEmpty());

        ImmutableMap.Builder<String, Argument> signature = ImmutableMap.builder();
        for (Argument argument : arguments)
            signature.put(argument.name, argument);

        this.signature = signature.build();
    }

    protected AbstractVTLFunction(String id) {
        this(id, ImmutableMap.of());
    }

    private AbstractVTLFunction(String id, ImmutableMap<String, Argument> signature) {
        this.id = checkNotNull(id);
        checkArgument(!id.isEmpty());
        this.signature = checkNotNull(signature);
    }

    // TODO: Interface
    public VTLObject invoke(List<VTLObject> arguments) {
        return invoke(arguments, Collections.emptyMap());
    }

    // TODO: Interface
    public VTLObject invoke(List<VTLObject> arguments, Map<String, VTLObject> namedArguments) {
        TypeSafeArguments typeSafeArguments = createTypeSafeArguments(arguments, namedArguments);
        return safeInvoke(typeSafeArguments);
    }

    private TypeSafeArguments createTypeSafeArguments(List<VTLObject> arguments, Map<String, VTLObject> namedArguments) {
        checkArgumentsTypes(arguments);
        checkNamedArgumentTypes(namedArguments);
        return null;
    }

    /**
     * Checks if the named arguments are of the correct types.
     */
    private void checkNamedArgumentTypes(Map<String, VTLObject> namedArguments) {
        // TODO: exception type.
        checkArgument(namedArguments.size() > signature.size(),
                ARGUMENT_LARGER_THAN_DEFINITION
        );

        Sets.SetView<String> unknown = Sets.difference(namedArguments.keySet(), signature.keySet());
        checkArgument(unknown.isEmpty(), UNKNOWN_ARGUMENTS, unknown);

        Map<String, Argument> requiredSignature = signature.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof OptionalArgument)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Sets.SetView<String> missing = Sets.difference(requiredSignature.keySet(), namedArguments.keySet());
        checkArgument(missing.isEmpty(), MISSING_ARGUMENTS, missing);

    }

    private void checkType(VTLObject value, Argument argument) {
        // TODO: exception type.
        checkArgument(argument.getType().isAssignableFrom(value.getClass()),
                WRONG_ARGUMENT_TYPE,
                value.getClass(), argument.getName(), argument.getType()
        );
    }

    private void checkArgumentsTypes(Collection<VTLObject> arguments) {
        // TODO: exception type.
        checkArgument(arguments.size() <= signature.size(),
                ARGUMENT_LARGER_THAN_DEFINITION
        );

        Iterator<VTLObject> argumentsIt = arguments.iterator();
        Iterator<Argument> signatureIt = signature.values().iterator();
        while (argumentsIt.hasNext() && signatureIt.hasNext()) {
            VTLObject argument = argumentsIt.next();
            Argument signature = signatureIt.next();
            checkType(argument, signature);
        }
    }

    abstract VTLObject safeInvoke(TypeSafeArguments arguments);

    // TODO:
    public static class TypeSafeArguments {

        public <T extends VTLObject> T get(Argument<T> left) {
            return null;
        }

    }

    public static class Argument<T extends VTLObject> {
        private final String name;
        private final Class<T> type;

        public Argument(String name, Class<T> type) {
            this.name = checkNotNull(name);
            this.type = checkNotNull(type);
        }

        public String getName() {
            return name;
        }

        public Class<T> getType() {
            return type;
        }

    }

    public static class OptionalArgument<T extends VTLObject> extends Argument<T> {

        private final T defaultValue;

        public OptionalArgument(String name, Class<T> type, T defaultValue) {
            super(name, type);
            this.defaultValue = checkNotNull(defaultValue);
        }

        public VTLObject<T> getDefaultValue() {
            return defaultValue;
        }
    }
}
