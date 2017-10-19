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

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLTyped;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.VTLDataset;
import no.ssb.vtl.script.error.VTLRuntimeException;
import no.ssb.vtl.script.functions.FunctionExpression;
import no.ssb.vtl.script.functions.VTLAddition;
import no.ssb.vtl.script.functions.VTLAnd;
import no.ssb.vtl.script.functions.VTLConcatenation;
import no.ssb.vtl.script.functions.VTLDivision;
import no.ssb.vtl.script.functions.VTLMultiplication;
import no.ssb.vtl.script.functions.VTLNot;
import no.ssb.vtl.script.functions.VTLOr;
import no.ssb.vtl.script.functions.VTLSubtraction;
import no.ssb.vtl.script.functions.VTLXor;
import no.ssb.vtl.script.visitors.functions.NativeFunctionsVisitor;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import javax.script.Bindings;
import java.util.function.BiPredicate;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 *
 */
// TODO: extend abstract variable visitor.
public class ExpressionVisitor extends VTLBaseVisitor<VTLExpression2> {

    private final LiteralVisitor literalVisitor = LiteralVisitor.getInstance();
    private final NativeFunctionsVisitor nativeFunctionsVisitor = new NativeFunctionsVisitor(this);

    public Bindings getBindings() {
        return scope;
    }

    private final Bindings scope;

    public ExpressionVisitor(Bindings scope) {
        this.scope = checkNotNull(scope);
    }

    @Override
    public VTLExpression2 visitLiteral(VTLParser.LiteralContext ctx) {
        VTLObject literal = literalVisitor.visit(ctx);

        // Literal are always resolved.
        // TODO: Literal extends Expression2?
        return new VTLExpression2() {

            @Override
            public String toString() {
                return literal.toString();
            }

            @Override
            public Class<?> getVTLType() {
                if (literal instanceof VTLTyped)
                    return ((VTLTyped) literal).getVTLType();
                return VTLObject.class;
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
    public VTLExpression2 visitUnaryExpr(VTLParser.UnaryExprContext ctx) {
        VTLExpression2 operand = visit(ctx.expression());
        switch (ctx.op.getType()) {
            case VTLParser.NOT:
                return new FunctionExpression<>(VTLNot.getInstance(), operand);
            default:
                throw new ParseCancellationException("unknown operator " + ctx.op.getText());
        }
    }

    @Override
    public VTLExpression2 visitArithmeticExpr(VTLParser.ArithmeticExprContext ctx) {
        VTLExpression2 leftExpression = visit(ctx.left);
        VTLExpression2 rightExpression = visit(ctx.right);

        // Arithmetic expression types are function of the type of the operands.
        switch (ctx.op.getType()) {
            case VTLParser.MUL:
                return new FunctionExpression<VTLNumber>(VTLMultiplication.getInstance(), leftExpression, rightExpression) {
                    @Override
                    public Class getVTLType() {
                        if (leftExpression.getVTLType() == VTLFloat.class || rightExpression.getVTLType() == VTLFloat.class)
                            return VTLFloat.class;
                        return VTLInteger.class;
                    }
                };
            case VTLParser.DIV:
                return new FunctionExpression<>(VTLDivision.getInstance(), leftExpression, rightExpression);
            case VTLParser.PLUS:
                return new FunctionExpression<VTLNumber>(VTLAddition.getInstance(), leftExpression, rightExpression) {
                    @Override
                    public Class getVTLType() {
                        if (leftExpression.getVTLType() == VTLFloat.class || rightExpression.getVTLType() == VTLFloat.class)
                            return VTLFloat.class;
                        return VTLInteger.class;
                    }
                };
            case VTLParser.MINUS:
                return new FunctionExpression<VTLNumber>(VTLSubtraction.getInstance(), leftExpression, rightExpression) {
                    @Override
                    public Class getVTLType() {
                        if (leftExpression.getVTLType() == VTLFloat.class || rightExpression.getVTLType() == VTLFloat.class)
                            return VTLFloat.class;
                        return VTLInteger.class;
                    }
                };

            default:
                throw new ParseCancellationException("unknown operator " + ctx.op.getText());
        }
    }

    @Override
    public VTLExpression2 visitBinaryExpr(VTLParser.BinaryExprContext ctx) {
        VTLExpression2 leftExpression = visit(ctx.left);
        VTLExpression2 rightExpression = visit(ctx.right);
        switch (ctx.op.getType()) {

            case VTLParser.CONCAT:
                return new FunctionExpression<>(VTLConcatenation.getInstance(), leftExpression, rightExpression);

            case VTLParser.EQ:
                return getBooleanExpression((left, right) -> left.compareTo(right) == 0, leftExpression, rightExpression);
            case VTLParser.NE:
                return getBooleanExpression((left, right) -> left.compareTo(right) != 0, leftExpression, rightExpression);
            case VTLParser.LE:
                return getBooleanExpression((l, r) ->  l.compareTo(r) <= 0, leftExpression, rightExpression);
            case VTLParser.LT:
                return getBooleanExpression((l, r) -> l.compareTo(r) < 0, leftExpression, rightExpression);
            case VTLParser.GE:
                return getBooleanExpression((l, r) -> l.compareTo(r) >= 0, leftExpression, rightExpression);
            case VTLParser.GT:
                return getBooleanExpression((l, r) -> l.compareTo(r) > 0, leftExpression, rightExpression);

            case VTLParser.AND:
                return new FunctionExpression<>(VTLAnd.getInstance(), leftExpression, rightExpression);
            case VTLParser.OR:
                return  new FunctionExpression<>(VTLOr.getInstance(), leftExpression, rightExpression);
            case VTLParser.XOR:
                return new FunctionExpression<>(VTLXor.getInstance(), leftExpression, rightExpression);
            default:
                throw new ParseCancellationException("unknown operator " + ctx.op.getText());
        }
    }

    private VTLExpression2 getBooleanExpression(BiPredicate<VTLObject, VTLObject> predicate, VTLExpression2 leftExpression, VTLExpression2 rightExpression) {
        return new VTLExpression2() {
            @Override
            public VTLObject resolve(Bindings bindings) {
                VTLObject left = (VTLObject) leftExpression.resolve(bindings);
                VTLObject right = (VTLObject) rightExpression.resolve(bindings);
                return VTLBoolean.of(predicate.test(left, right));
            }

            @Override
            public Class getVTLType() {
                return VTLBoolean.class;
            }

        };
    }

    @Override
    public VTLExpression2 visitFunctionCall(VTLParser.FunctionCallContext ctx) {
        return nativeFunctionsVisitor.visit(ctx);
    }

    @Override
    public VTLExpression2 visitMembershipExpression(VTLParser.MembershipExpressionContext ctx) {
        String leftIdentifier = checkVariableExist(scope, ctx.left);
        Object object = scope.get(leftIdentifier);
        if (object instanceof Bindings) {
            Bindings bindings = (Bindings) object;
            String rightIdentifier = checkVariableExist(bindings, ctx.right);
            VTLTyped typed = (VTLTyped) bindings.get(rightIdentifier);
            return new VTLExpression2() {

                @Override
                public VTLObject resolve(Bindings bindings) {
                    return (VTLObject) ((Bindings) bindings.get(leftIdentifier)).get(rightIdentifier);
                }

                @Override
                public Class getVTLType() {
                    return typed.getVTLType();
                }

            };
        } else {
            throw new UnsupportedOperationException("[" + leftIdentifier + "] was not a dataset");
        }
    }

    private static String checkVariableExist(Bindings bindings, VTLParser.VariableContext ctx) {
        String identifier = ctx.getText();
        // TODO: Remove escape logic.
        identifier = unEscape(identifier);

        if (bindings.containsKey(identifier))
            return identifier;

        throw new VTLRuntimeException(
                format("undefined variable [%s] (scope [%s])", identifier, bindings),
                "VTL-101", ctx
        );
    }

    @Override
    public VTLExpression2 visitVariable(VTLParser.VariableContext ctx) {
        String identifier = checkVariableExist(scope, ctx);
        Object object = scope.get(identifier);
        if (object instanceof VTLTyped) {

            // Save the type and identifier.
            // TODO: VariableReference extends VTLExpression2 ?
            VTLTyped typed = (VTLTyped) object;
            return new VTLExpression2() {

                @Override
                public Class<?> getVTLType() {
                    return typed.getVTLType();
                }

                @Override
                public VTLObject resolve(Bindings bindings) {
                    return (VTLObject) bindings.get(identifier);
                }
            };

        }
        if (object instanceof Dataset) {
            return new VTLExpression2() {
                @Override
                public VTLObject resolve(Bindings bindings) {
                    return VTLDataset.of((Dataset) bindings.get(identifier));
                }

                @Override
                public Class getVTLType() {
                    return VTLDataset.class;
                }
            };
        }
        throw new VTLRuntimeException(
                format("unknown object [%s]", object), "VTL-101", ctx
        );
    }

    private static String unEscape(String identifier) {
        // Unescape.
        if (identifier.startsWith("\'") && identifier.endsWith("\'")) {
            identifier = identifier.substring(1, identifier.length() - 1);
        }
        return identifier;
    }
}
