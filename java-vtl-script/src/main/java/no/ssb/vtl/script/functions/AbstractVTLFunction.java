package no.ssb.vtl.script.functions;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLObject;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract vtl function that takes care of arguments validation.
 * TODO: Extend a contract.
 * TODO: Add toString
 * TODO: Equals
 */
public abstract class AbstractVTLFunction {

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
        checkArguments(arguments, namedArguments.values());
        return null;
    }

    private void checkArguments(Collection<VTLObject>... arguments) {
        // TODO
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

        public T getDefaultValue() {
            return defaultValue;
        }
    }
}
