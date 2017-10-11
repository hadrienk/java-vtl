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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FunctionExpression<T extends VTLObject> implements VTLExpression2 {

    private final VTLFunction<T> wrappedFunction;
    private final List<VTLExpression2> arguments;
    private final Map<String, VTLExpression2> namedArguments;

    public FunctionExpression(VTLFunction<T> wrappedFunction, List<VTLExpression2> arguments, Map<String, VTLExpression2> namedArguments) {
        this.wrappedFunction = wrappedFunction;
        this.arguments = arguments;
        this.namedArguments = namedArguments;
    }

    public FunctionExpression(VTLFunction<T> wrappedFunction, List<VTLExpression2> arguments) {
        this(wrappedFunction, arguments, Collections.emptyMap());
    }

    public FunctionExpression(VTLFunction<T> wrappedFunction, Map<String, VTLExpression2> namedArguments) {
        this(wrappedFunction, Collections.emptyList(), namedArguments);
    }

    @Override
    public VTLObject resolve(Bindings bindings) {
        // Resolve the parameters.
        List<VTLObject> resolvedParameters = Lists.newArrayList();
        for (VTLExpression2 expression2 : arguments) {
            resolvedParameters.add(expression2.resolve(bindings));
        }
        Map<String, VTLObject> resolvedNamedParameters = Maps.newLinkedHashMap();
        for (Map.Entry<String, VTLExpression2> entry : namedArguments.entrySet()) {
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
    public Class getType() {
        return wrappedFunction.getVTLType();
    }
}
