package no.ssb.vtl.script.visitors.functions;

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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.functions.VTLAbs;
import no.ssb.vtl.script.functions.VTLCeil;
import no.ssb.vtl.script.functions.VTLFloor;
import no.ssb.vtl.script.functions.VTLRound;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class NativeFunctionsVisitor extends VTLBaseVisitor<VTLObject> {

    static ImmutableMap<String, VTLFunction> functions;

    static {
        functions = ImmutableMap.<String, VTLFunction>builder()
                .put("abs", new VTLAbs())
                .put("round", new VTLRound())
                .put("ceil", new VTLCeil())
                .put("floor", new VTLFloor())
                .build();
    }

    private final VTLBaseVisitor<VTLObject> expressionVisitor;

    public NativeFunctionsVisitor(VTLBaseVisitor<VTLObject> expressionVisitor) {
        this.expressionVisitor = checkNotNull(expressionVisitor);
    }

    @Override
    public VTLObject visitNativeCall(VTLParser.NativeCallContext ctx) {
        if (functions.containsKey(ctx.functionName.getText())) {
            VTLFunction functionInstance = functions.get(ctx.functionName.getText());
            VTLParser.FunctionParametersContext parameters = ctx.functionParameters();
            return functionInstance.invoke(
                    evaluateParamerers(parameters.expression()),
                    evaluateNamedParameters(parameters.namedExpression())
            );
        } else {
            throw new UnsupportedOperationException("NOT IMPLEMENTED");
        }
    }

    private Map<String, VTLObject> evaluateNamedParameters(List<VTLParser.NamedExpressionContext> namedExpressionContexts) {
        ImmutableMap.Builder<String, VTLObject> builder = ImmutableMap.builder();
        for (VTLParser.NamedExpressionContext expressionContext : namedExpressionContexts) {
            String name = expressionContext.name.getText();
            VTLObject value = expressionVisitor.visit(expressionContext);
            builder.put(name, value);
        }
        return builder.build();
    }

    private List<VTLObject> evaluateParamerers(List<VTLParser.ExpressionContext> expression) {
        ImmutableList.Builder<VTLObject> builder = ImmutableList.builder();
        for (VTLParser.ExpressionContext expressionContext : expression) {
            VTLObject value = expressionVisitor.visit(expressionContext);
            builder.add(value);
        }
        return builder.build();
    }


}
