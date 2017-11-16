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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract VTLFunction that takes care of arguments validation.
 * TODO: Equals
 */
public abstract class AbstractVTLFunction<T extends VTLObject> implements VTLFunction<T> {

    private static final String INVALID_ARGUMENT_COUNT = "expected %s argument(s) but got %s";
    private static final String UNKNOWN_ARGUMENTS = "unknown arguments %s";
    private static final String MISSING_ARGUMENTS = "missing arguments %s";

    private final String id;
    private final Class<T> type;
    private final ImmutableMap<String, AbstractVTLFunction.Argument<?>> signature;

    protected AbstractVTLFunction(String id, Class<T> returnType, Argument... arguments) {
        this(id, returnType, Arrays.asList(arguments));
    }

    protected AbstractVTLFunction(String id, Class<T> returnType) {
        this(id, returnType, Collections.emptyList());
    }

    private AbstractVTLFunction(String id, Class<T> returnType, List<Argument> arguments) {
        this.id = checkNotNull(id);
        this.type = checkNotNull(returnType);
        checkArgument(!id.isEmpty());

        ImmutableMap.Builder<String, Argument<?>> signatureBuilder = ImmutableMap.builder();
        for (Argument<?> argument : arguments)
            signatureBuilder.put(argument.getName(), argument);
        this.signature = signatureBuilder.build();
    }

    /**
     * Combine the two Maps representing the invocation's arguments.
     *
     * @param arguments      the arguments (as a map)
     * @param namedArguments the named arguments
     */
    private static Map<String, VTLObject> mergeArguments(Map<String, VTLObject> arguments, Map<String, VTLObject> namedArguments) {
        ImmutableMap.Builder<String, VTLObject> builder = ImmutableMap.builder();
        for (Map.Entry<String, VTLObject> entry : arguments.entrySet()) {
            builder.put(entry);
        }
        for (Map.Entry<String, VTLObject> entry : namedArguments.entrySet()) {
            builder.put(entry);
        }
        return builder.build();
    }

    /**
     * Finds the names of a list of arguments.
     *
     * @param arguments the list of arguments
     * @return a map where the keys are the names of the arguments.
     */
    private Map<String, VTLObject> findNames(List<VTLObject> arguments) {

        // Early check since we are using iterator.
        checkArgument(arguments.size() <= signature.size(),
                INVALID_ARGUMENT_COUNT,
                signature.size(), arguments.size()
        );

        ImmutableMap.Builder<String, VTLObject> namedArguments = ImmutableMap.builder();
        Iterator<String> names = signature.keySet().iterator();
        for (VTLObject argument : arguments) {
            String name = names.next();
            namedArguments.put(name, argument);
        }
        return namedArguments.build();
    }

    private TypeSafeArguments createTypeSafeArguments(Map<String, VTLObject> namedArguments) {
        Map<String, VTLObject> arguments = addOptionalValues(namedArguments);
        validateArguments(arguments);
        return new TypeSafeArguments(arguments, signature);
    }

    private Map<String, VTLObject> addOptionalValues(Map<String, VTLObject> arguments) {
        ImmutableMap.Builder<String, VTLObject> builder = ImmutableMap.builder();
        for (String name : signature.keySet()) {
            VTLObject value;
            if (arguments.containsKey(name)) {
                value = arguments.get(name);
            } else {
                Argument argument = signature.get(name);
                if (argument instanceof AbstractVTLFunction.OptionalArgument) {
                    value = ((OptionalArgument) argument).getDefaultValue();
                } else {
                    // TODO: refactor to avoid this since it is not testable.
                    throw new IllegalArgumentException("Required argument not present");
                }
            }
            builder.put(name, value);
        }
        return builder.build();
    }

    /**
     * Checks if the named arguments are of the correct types.
     */
    private void validateArguments(Map<String, VTLObject> arguments) {
        if (arguments.isEmpty())
            return; // No need to check if empty.

        // Named arguments that are not in the signature.
        Sets.SetView<String> unknown = Sets.difference(arguments.keySet(), signature.keySet());
        checkArgument(unknown.isEmpty(), UNKNOWN_ARGUMENTS, unknown);

        // Filter the optional arguments.
        Set<String> requiredArgumentNames = signature.values().stream()
                .filter(obj -> !OptionalArgument.class.isInstance(obj))
                .map(Argument::getName)
                .collect(Collectors.toSet());

        // Non-optional named arguments missing.
        Sets.SetView<String> missing = Sets.difference(requiredArgumentNames, arguments.keySet());
        checkArgument(missing.isEmpty(), MISSING_ARGUMENTS, missing);

    }

    @Override
    public Signature getSignature() {
        // Not optimal, but restricts the refactoring ATM.
        return Signature.builder().addArguments(signature).build();
    }

    @Override
    public Class<T> getVTLType() {
        return type;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", this.id)
                .add("signature", signature)
                .addValue(getVTLType())
                .toString();
    }

    @Override
    public T invoke(List<VTLObject> arguments) {
        return invoke(findNames(arguments));
    }

    @Override
    public T invoke(List<VTLObject> arguments, Map<String, VTLObject> namedArguments) {
        return invoke(mergeArguments(findNames(arguments), namedArguments));
    }

    @Override
    public T invoke(Map<String, VTLObject> namedArguments) {
        validateArguments(namedArguments);
        Map<String, VTLObject> arguments = addOptionalValues(namedArguments);
        return safeInvoke(createTypeSafeArguments(arguments));
    }

    protected abstract T safeInvoke(TypeSafeArguments arguments);

    // TODO: Rename
    public static class Argument<A extends VTLObject> extends VTLFunction.Argument<A> {
        private final String name;

        public Argument(String name, Class<A> type) {
            this(name, type, true);
        }

        protected Argument(String name, Class<A> type, boolean required) {
            super(type, required);
            this.name = checkNotNull(name);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("name", name)
                    .add("type", getVTLType().getSimpleName())
                    .toString();
        }
    }

    protected static class OptionalArgument<A extends VTLObject> extends Argument<A> {

        private final A defaultValue;

        protected OptionalArgument(String name, Class<A> type, A defaultValue) {
            super(name, type, false);
            this.defaultValue = checkNotNull(defaultValue);
        }

        public VTLObject<A> getDefaultValue() {
            return defaultValue;
        }
    }
}
