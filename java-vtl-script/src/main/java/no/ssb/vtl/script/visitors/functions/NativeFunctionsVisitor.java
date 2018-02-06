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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLTyped;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.error.ContextualRuntimeException;
import no.ssb.vtl.script.expressions.FunctionExpression;
import no.ssb.vtl.script.functions.VTLAbs;
import no.ssb.vtl.script.functions.VTLCeil;
import no.ssb.vtl.script.functions.VTLDateFromString;
import no.ssb.vtl.script.functions.VTLExp;
import no.ssb.vtl.script.functions.VTLFloatFromString;
import no.ssb.vtl.script.functions.VTLFloor;
import no.ssb.vtl.script.functions.VTLIntegerFromString;
import no.ssb.vtl.script.functions.VTLLn;
import no.ssb.vtl.script.functions.VTLLog;
import no.ssb.vtl.script.functions.VTLMod;
import no.ssb.vtl.script.functions.VTLNroot;
import no.ssb.vtl.script.functions.VTLNvl;
import no.ssb.vtl.script.functions.VTLPower;
import no.ssb.vtl.script.functions.VTLRound;
import no.ssb.vtl.script.functions.VTLSqrt;
import no.ssb.vtl.script.functions.VTLStringFromNumber;
import no.ssb.vtl.script.functions.VTLSubstr;
import no.ssb.vtl.script.functions.VTLTrunc;
import no.ssb.vtl.script.functions.string.VTLLeftTrim;
import no.ssb.vtl.script.functions.string.VTLLower;
import no.ssb.vtl.script.functions.string.VTLRightTrim;
import no.ssb.vtl.script.functions.string.VTLTrim;
import no.ssb.vtl.script.functions.string.VTLUpper;

import javax.script.Bindings;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

public class NativeFunctionsVisitor extends VTLBaseVisitor<VTLExpression> {

    private static ImmutableMap<String, VTLFunction> functions;

    static {
        // TODO(hadrien): I'd like to use VTLParser.FUNC_* here. If someone has an idea?
        functions = ImmutableMap.<String, VTLFunction>builder()
                .put("abs", VTLAbs.getInstance())
                .put("round", VTLRound.getInstance())
                .put("ceil", VTLCeil.getInstance())
                .put("floor", VTLFloor.getInstance())
                .put("exp", VTLExp.getInstance())
                .put("ln", VTLLn.getInstance())
                .put("log", VTLLog.getInstance())
                .put("mod", VTLMod.getInstance())
                .put("nroot", VTLNroot.getInstance())
                .put("power", VTLPower.getInstance())
                .put("sqrt", VTLSqrt.getInstance())
                .put("trunc", VTLTrunc.getInstance())
                .put("ltrim", VTLLeftTrim.getInstance())
                .put("rtrim", VTLRightTrim.getInstance())
                .put("trim", VTLTrim.getInstance())
                .put("upper", VTLUpper.getInstance())
                .put("lower", VTLLower.getInstance())
                .put("date_from_string", VTLDateFromString.getInstance())
                .put("integer_from_string", VTLIntegerFromString.getInstance())
                .put("float_from_string", VTLFloatFromString.getInstance())
                .put("string_from_number", VTLStringFromNumber.getInstance())
                .put("substr", VTLSubstr.getInstance())
                .build();
    }

    private final VTLBaseVisitor<VTLExpression> expressionVisitor;

    public NativeFunctionsVisitor(VTLBaseVisitor<VTLExpression> expressionVisitor) {
        this.expressionVisitor = checkNotNull(expressionVisitor);
    }

    @VisibleForTesting
    static VTLExpression coerceNullLiteralType(VTLExpression expression, VTLTyped replacement) {
        if (expression.getVTLType().equals(VTLObject.class)) {
            return new VTLExpression() {
                @Override
                public VTLObject resolve(Bindings bindings) {
                    return expression.resolve(bindings);
                }

                @Override
                public Class getVTLType() {
                    return replacement.getVTLType();
                }
            };
        } else {
            return expression;
        }
    }

    @Override
    public VTLExpression visitNvlFunction(VTLParser.NvlFunctionContext ctx) {
        VTLParser.ExpressionContext nullableCtx = ctx.expression(0);
        VTLParser.ExpressionContext replacementCtx = ctx.expression(1);
        VTLExpression nullable = expressionVisitor.visit(nullableCtx);
        VTLExpression replacement = expressionVisitor.visit(replacementCtx);

        // If it is a null literal (type unknown) make it the type of replacement.
        VTLExpression finalNullable = coerceNullLiteralType(nullable, replacement);
        if (!finalNullable.getVTLType().equals(replacement.getVTLType())) {
            throw new ContextualRuntimeException(
                    format("%s and %s must be of the same type",
                            nullableCtx.getText(), replacementCtx.getText()
                    ), ctx
            );
        }

        return new FunctionExpression<VTLObject>(new VTLNvl(), finalNullable, replacement) {
            @Override
            public Class getVTLType() {
                return finalNullable.getVTLType();
            }
        };
    }

    @Override
    public VTLExpression visitNativeFunctionCall(VTLParser.NativeFunctionCallContext ctx) {
        try {
            if (functions.containsKey(ctx.functionName.getText())) {
                VTLFunction<VTLObject> functionInstance = functions.get(ctx.functionName.getText());

                // Evaluate parameter expressions.
                List<VTLExpression> parametersExp = Collections.emptyList();
                Map<String, VTLExpression> namedParametersExp = Collections.emptyMap();

                VTLParser.FunctionParametersContext parameters = ctx.functionParameters();
                if (parameters != null) {
                    parametersExp = evaluateParameters(parameters.expression());
                    namedParametersExp = evaluateNamedParameters(parameters.namedExpression());
                }

                // Wrap function as an expression.
                return new FunctionExpression<>(functionInstance, parametersExp, namedParametersExp);
            } else {
                throw new ContextualRuntimeException("user defined function not implemented", ctx);
            }
        } catch (IllegalArgumentException iae) {
            throw new ContextualRuntimeException(iae, ctx);
        }
    }

    private Map<String, VTLExpression> evaluateNamedParameters(List<VTLParser.NamedExpressionContext> namedExpressionContexts) {
        ImmutableMap.Builder<String, VTLExpression> builder = ImmutableMap.builder();
        for (VTLParser.NamedExpressionContext expressionContext : namedExpressionContexts) {
            String name = expressionContext.name.getText();
            VTLExpression value = expressionVisitor.visit(expressionContext);
            builder.put(name, value);
        }
        return builder.build();
    }

    private List<VTLExpression> evaluateParameters(List<VTLParser.ExpressionContext> expression) {
        ImmutableList.Builder<VTLExpression> builder = ImmutableList.builder();
        for (VTLParser.ExpressionContext expressionContext : expression) {
            VTLExpression value = expressionVisitor.visit(expressionContext);
            builder.add(value);
        }
        return builder.build();
    }


}
