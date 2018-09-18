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
import no.ssb.vtl.model.VTLObject;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class TypeSafeArguments {
    private final Map<AbstractVTLFunction.Argument, VTLObject<?>> arguments;

    private static final String WRONG_ARGUMENT_TYPE = "invalid type %s for argument %s, expected %s";

    public TypeSafeArguments(Map<String, VTLObject> arguments, Map<String, AbstractVTLFunction.Argument<?>> signature) {
        checkArgument(arguments.size() == signature.size());
        ImmutableMap.Builder<AbstractVTLFunction.Argument, VTLObject<?>> builder = ImmutableMap.builder();
        for (String name : signature.keySet()) {
            VTLObject value = arguments.get(name);
            AbstractVTLFunction.Argument argument = signature.get(name);
            builder.put(argument, checkType(value, argument));
        }
        this.arguments = builder.build();
    }

    public <T extends VTLObject> T get(AbstractVTLFunction.Argument<T> argumentReference) {
        checkArgument(arguments.containsKey(argumentReference));
        return (T) arguments.get(argumentReference);
    }

    public <T extends VTLObject> T getNullable(AbstractVTLFunction.Argument<T> argumentReference, T valueIfNull) {
        checkArgument(arguments.containsKey(argumentReference));
        T value = (T) arguments.get(argumentReference);
        return value.get() == null ? valueIfNull : value;
    }

    private static VTLObject checkType(VTLObject value, AbstractVTLFunction.Argument argument) {
        // TODO: exception type.
        checkArgument(value.get() == null || argument.getVTLType().isAssignableFrom(value.getClass()),
                WRONG_ARGUMENT_TYPE,
                value.getClass(), argument.getName(), argument.getVTLType()
        );
        return value;
    }

}
