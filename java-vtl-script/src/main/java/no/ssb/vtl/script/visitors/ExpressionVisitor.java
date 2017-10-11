package no.ssb.vtl.script.visitors;

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

import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLTyped;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.error.VTLRuntimeException;
import no.ssb.vtl.script.functions.FunctionExpression;
import no.ssb.vtl.script.functions.VTLAddition;
import no.ssb.vtl.script.functions.VTLConcatenation;
import no.ssb.vtl.script.functions.VTLDivision;
import no.ssb.vtl.script.functions.VTLMultiplication;
import no.ssb.vtl.script.functions.VTLSubtraction;
import no.ssb.vtl.script.visitors.functions.NativeFunctionsVisitor;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 *
 */
public class ExpressionVisitor extends VTLBaseVisitor<VTLExpression2> {

    private final LiteralVisitor literalVisitor = LiteralVisitor.getInstance();
    private final NativeFunctionsVisitor nativeFunctionsVisitor = new NativeFunctionsVisitor(this);
    private final Bindings scope;

    public ExpressionVisitor(Bindings scope) {
        this.scope = checkNotNull(scope);
    }

    @Override
    public VTLExpression2 visitLiteral(VTLParser.LiteralContext ctx) {
        VTLObject literal = literalVisitor.visit(ctx);

        // Literal are always resolved.
        // TODO: Litteral extends Expression2?
        return new VTLExpression2() {
            @Override
            public Class<?> getType() {
                return literal.getClass();
            }

            @Override
            public VTLObject resolve(Bindings dataPoint) {
                return literal;
            }
        };

    }

    @Override
    public VTLExpression2 visitPrecedenceExpr(VTLParser.PrecedenceExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public VTLExpression2 visitBinaryExpr(VTLParser.BinaryExprContext ctx) {
        VTLExpression2 leftExpression = visit(ctx.left);
        VTLExpression2 rightExpression = visit(ctx.right);
        switch (ctx.op.getType()) {
            case VTLParser.CONCAT:
                // TODO: Singletons.
                return new FunctionExpression<>(new VTLConcatenation(), leftExpression, rightExpression);
            case VTLParser.MUL:
                return new FunctionExpression<>(VTLMultiplication.getInstance(), leftExpression, rightExpression);
            case VTLParser.DIV:
                return new FunctionExpression<>(VTLDivision.getInstance(), leftExpression, rightExpression);
            case VTLParser.PLUS:
                return new FunctionExpression<>(VTLAddition.getInstance(), leftExpression, rightExpression);
            case VTLParser.MINUS:
                return new FunctionExpression<>(VTLSubtraction.getInstance(), leftExpression, rightExpression);
            default:
                throw new UnsupportedOperationException("unknown operator " + ctx.op.getText());
        }
    }

    @Override
    public VTLExpression2 visitNativeCall(VTLParser.NativeCallContext ctx) {
        return nativeFunctionsVisitor.visit(ctx);
    }

    @Override
    public VTLExpression2 visitVariable(VTLParser.VariableContext ctx) {
        String identifier = ctx.getText();

        // Unescape.
        if (identifier.startsWith("\'") && identifier.endsWith("\'")) {
            identifier = identifier.substring(1, identifier.length() - 1);
        }

        if (scope.containsKey(identifier)) {
            Object object = scope.get(identifier);
            if (object instanceof VTLTyped) {

                // Save the type and identifier.
                // TODO: VariableReference extends VTLExpression2 ?
                VTLTyped typed = (VTLTyped) object;
                String finalIdentifier = identifier;
                return new VTLExpression2() {

                    @Override
                    public Class<?> getType() {
                        return typed.getVTLType();
                    }

                    @Override
                    public VTLObject resolve(Bindings bindings) {
                        return (VTLObject) bindings.get(finalIdentifier);
                    }
                };

            } else {
                throw new VTLRuntimeException(
                        format("unknown object [%s]", object), "VTL-101", ctx
                );
            }
        } else {
            throw new VTLRuntimeException(
                    format("undefined variable [%s] (scope [%s]", identifier, scope),
                    "VTL-101", ctx
            );
        }
    }
}
