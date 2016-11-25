package kohl.hadrien.vtl.script.visitors.join;

import kohl.hadrien.vtl.model.AbstractComponent;
import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.Measure;
import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 * Visitor for join calc clauses.
 */
public class JoinCalcClauseVisitor extends VTLBaseVisitor<Component> {

    private final Map<String, Object> variables;

    /**
     * Instantiate a visitor that transforms calc operations into values.
     */
    public JoinCalcClauseVisitor(Map<String, Object> variables) {
        this.variables = checkNotNull(variables);
    }

    @Override
    public Component visitJoinCalcReference(VTLParser.JoinCalcReferenceContext ctx) {
        //
        String variableName = ctx.getText();
        if (!variables.containsKey(variableName)) {
            throw new RuntimeException(format("variable %s not found", variableName));
        }

        Object object = variables.get(variableName);
        if (!(object instanceof Component)) {
            throw new RuntimeException(
                    format("unsupported type, %s was %s, expected %s",
                            variableName, object.getClass(), Component.class)
            );
        }

        // TODO: What is supported here? Just number?
        return (Component) object;
    }

    @Override
    public Component visitJoinCalcRef(VTLParser.JoinCalcRefContext ctx) {
        return super.visitJoinCalcRef(ctx);
    }


    @Override
    public Component visitJoinCalcAtom(VTLParser.JoinCalcAtomContext ctx) {
        VTLParser.ConstantContext constantValue = ctx.constant();
        if (constantValue.FLOAT_CONSTANT() != null)
            return new AbstractComponent() {
                @Override
                public String name() {
                    return "constant";
                }

                @Override
                public Class<?> type() {
                    return Float.class;
                }

                @Override
                public Class<? extends Component> role() {
                    return Measure.class;
                }

                @Override
                public Object get() {
                    return Float.valueOf(constantValue.FLOAT_CONSTANT().getText());
                }
            };

        if (constantValue.INTEGER_CONSTANT() != null)
            return new AbstractComponent() {
                @Override
                public String name() {
                    return "constant";
                }

                @Override
                public Class<?> type() {
                    return Integer.class;
                }

                @Override
                public Class<? extends Component> role() {
                    return Measure.class;
                }

                @Override
                public Object get() {
                    return Integer.valueOf(constantValue.INTEGER_CONSTANT().getText());
                }
            };

        throw new RuntimeException(
                format("unsuported constant type %s", constantValue)
        );
    }

    @Override
    public Component visitJoinCalcPrecedence(VTLParser.JoinCalcPrecedenceContext ctx) {
        return visit(ctx.joinCalcExpression());
    }

    @Override
    public Component visitJoinCalcSummation(VTLParser.JoinCalcSummationContext ctx) {
        Component leftResult = visit(ctx.leftOperand);
        Component rightResult = visit(ctx.rightOperand);

        // Check types?
        checkArgument(Number.class.isAssignableFrom(leftResult.type()));
        checkArgument(Number.class.isAssignableFrom(rightResult.type()));

        return new AbstractComponent() {
            @Override
            public String name() {
                return "NA";
            }

            @Override
            public Class<?> type() {
                return Number.class;
            }

            @Override
            public Class<? extends Component> role() {
                return Measure.class;
            }

            @Override
            public Object get() {
                Number leftNumber = (Number) leftResult.get();
                Number rightNumber = (Number) rightResult.get();

                // TODO: document boxing and overflow
                if (leftNumber instanceof Float || rightNumber instanceof Float) {
                    if (ctx.sign.getText().equals("+")) {
                        return leftNumber.floatValue() + rightNumber.floatValue();
                    } else {
                        return leftNumber.floatValue() - rightNumber.floatValue();
                    }
                }
                if (leftNumber instanceof Double || rightNumber instanceof Double) {
                    if (ctx.sign.getText().equals("+")) {
                        return leftNumber.doubleValue() + rightNumber.doubleValue();
                    } else {
                        return leftNumber.doubleValue() - rightNumber.doubleValue();
                    }
                }
                if (leftNumber instanceof Integer || rightNumber instanceof Integer) {
                    if (ctx.sign.getText().equals("+")) {
                        return leftNumber.intValue() + rightNumber.intValue();
                    } else {
                        return leftNumber.intValue() - rightNumber.intValue();
                    }
                }
                if (leftNumber instanceof Long || rightNumber instanceof Long) {
                    if (ctx.sign.getText().equals("+")) {
                        return leftNumber.longValue() + rightNumber.longValue();
                    } else {
                        return leftNumber.longValue() - rightNumber.longValue();
                    }
                }

                throw new RuntimeException(
                        format("unsupported number types %s, %s", leftNumber.getClass(), rightNumber.getClass())
                );
            }
        };
    }


    @Override
    public Component visitJoinCalcProduct(VTLParser.JoinCalcProductContext ctx) {
        Component leftResult = visit(ctx.leftOperand);
        Component rightResult = visit(ctx.rightOperand);

        // Check types?
        checkArgument(Number.class.isAssignableFrom(leftResult.type()));
        checkArgument(Number.class.isAssignableFrom(rightResult.type()));

        return new AbstractComponent() {
            @Override
            public String name() {
                return "NA";
            }

            @Override
            public Class<?> type() {
                return Number.class;
            }

            @Override
            public Class<? extends Component> role() {
                return Measure.class;
            }

            @Override
            public Object get() {
                Number leftNumber = (Number) leftResult.get();
                Number rightNumber = (Number) rightResult.get();

                // TODO: document boxing and overflow
                if (leftNumber instanceof Float || rightNumber instanceof Float) {
                    if (ctx.sign.getText().equals("*")) {
                        return leftNumber.floatValue() * rightNumber.floatValue();
                    } else {
                        return leftNumber.floatValue() / rightNumber.floatValue();
                    }
                }
                if (leftNumber instanceof Double || rightNumber instanceof Double) {
                    if (ctx.sign.getText().equals("*")) {
                        return leftNumber.doubleValue() * rightNumber.doubleValue();
                    } else {
                        return leftNumber.doubleValue() / rightNumber.doubleValue();
                    }
                }
                if (leftNumber instanceof Integer || rightNumber instanceof Integer) {
                    if (ctx.sign.getText().equals("*")) {
                        return leftNumber.intValue() * rightNumber.intValue();
                    } else {
                        return leftNumber.intValue() / rightNumber.intValue();
                    }
                }
                if (leftNumber instanceof Long || rightNumber instanceof Long) {
                    if (ctx.sign.getText().equals("*")) {
                        return leftNumber.longValue() * rightNumber.longValue();
                    } else {
                        return leftNumber.longValue() / rightNumber.longValue();
                    }
                }

                throw new RuntimeException(
                        format("unsupported number types %s, %s", leftNumber.getClass(), rightNumber.getClass())
                );
            }
        };
    }

}
