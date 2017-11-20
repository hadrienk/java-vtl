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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Helper class that transforms a VTLFunction to a VTLExpression.
 *
 * It takes care of resolving all the function parameters in
 * its {@link #resolve(Bindings)} method.
 */
public class FunctionExpression<T extends VTLObject> implements VTLExpression {

    private static final String INVALID_TYPE = "invalid argument type for %s, expected %s but got %s";
    private static final String TOO_MANY_ARGUMENTS = "too many arguments, expected %s but got %s";
    private static final String UNKNOWN_ARGUMENTS = "unknown argument(s): %s";
    private static final String DUPLICATE_ARGUMENTS = "duplicate argument(s): %s";
    private static final String MISSING_ARGUMENTS = "missing argument(s): %s";

    private final VTLFunction<T> wrappedFunction;
    private final List<VTLExpression> arguments;
    private final Map<String, VTLExpression> namedArguments;

    public FunctionExpression(VTLFunction<T> wrappedFunction, List<VTLExpression> arguments, Map<String, VTLExpression> namedArguments) {
        this.wrappedFunction = wrappedFunction;
        this.arguments = arguments;
        this.namedArguments = namedArguments;
        checkTypes(wrappedFunction, mergeArguments(wrappedFunction.getSignature(), arguments, namedArguments));
    }

    public FunctionExpression(VTLFunction<T> wrappedFunction, List<VTLExpression> arguments) {
        this(wrappedFunction, arguments, Collections.emptyMap());
    }

    public FunctionExpression(VTLFunction<T> wrappedFunction, Map<String, VTLExpression> namedArguments) {
        this(wrappedFunction, Collections.emptyList(), namedArguments);
    }

    public FunctionExpression(VTLFunction<T> wrappedFunction, VTLExpression... arguments) {
        this(wrappedFunction, Arrays.asList(arguments));
    }

    // TODO: Move to VTLFunction or AbstractVTLFunction.
    private void checkTypes(VTLFunction<?> function, Map<String, VTLExpression> arguments) {
        VTLFunction.Signature signature = function.getSignature();
        for (String argumentName : arguments.keySet()) {
            Class<?> expectedType = signature.get(argumentName).getVTLType();
            Class<?> argumentType  = arguments.get(argumentName).getVTLType();

            // Null values are always the correct type.
            if (argumentType.equals(VTLObject.class))
                continue;

            checkArgument(expectedType.isAssignableFrom(argumentType), INVALID_TYPE, argumentName, expectedType, argumentType);
        }
    }

    // TODO: Move to VTLFunction or AbstractVTLFunction.
    @VisibleForTesting
    static Map<String, VTLExpression> mergeArguments(VTLFunction.Signature signature, List<VTLExpression> arguments, Map<String, VTLExpression> namedArguments) {

        // Check unnamed arguments count.
        checkArgument(arguments.size() <= signature.size(), TOO_MANY_ARGUMENTS, signature.size(), arguments.size());

        ImmutableMap.Builder<String, VTLExpression> builder = ImmutableMap.builder();

        // Match the list with the signature names.
        Iterator<String> namesIterator = signature.keySet().iterator();
        for (VTLExpression argument : arguments) {
            builder.put(namesIterator.next(), argument);
        }


        // Check for duplicates
        Set<String> duplicates = Sets.intersection(namedArguments.keySet(), builder.build().keySet());
        checkArgument(duplicates.isEmpty(), DUPLICATE_ARGUMENTS, String.join(", ", duplicates));

        ImmutableMap<String, VTLExpression> computedArguments = builder.putAll(namedArguments).build();

        // Check for unknown arguments.
        Set<String> unknown = Sets.difference(computedArguments.keySet(), signature.keySet());
        checkArgument(unknown.isEmpty(), UNKNOWN_ARGUMENTS, String.join(", ", unknown));

        // Check for missing arguments
        Set<String> required = Maps.filterValues(signature, VTLFunction.Argument::isRequired).keySet();
        Set<String> missing = Sets.difference(required, computedArguments.keySet());
        checkArgument(missing.isEmpty(), MISSING_ARGUMENTS, String.join(", ", missing));

        return computedArguments;
    }

    @Override
    public VTLObject resolve(Bindings bindings) {
        // Resolve the parameters.
        List<VTLObject> resolvedParameters = Lists.newArrayList();
        for (VTLExpression expression : arguments) {
            resolvedParameters.add(expression.resolve(bindings));
        }
        Map<String, VTLObject> resolvedNamedParameters = Maps.newLinkedHashMap();
        for (Map.Entry<String, VTLExpression> entry : namedArguments.entrySet()) {
            resolvedNamedParameters.put(
                    entry.getKey(),
                    entry.getValue().resolve(bindings)
            );
        }

        return wrappedFunction.invoke(
                resolvedParameters,
                resolvedNamedParameters
        );
    }

    @Override
    public Class getVTLType() {
        return wrappedFunction.getVTLType();
    }
}
