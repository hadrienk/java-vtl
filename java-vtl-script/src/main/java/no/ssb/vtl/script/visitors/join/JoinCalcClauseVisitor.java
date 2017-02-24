package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.visitors.BooleanExpressionVisitor;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.String.*;

/**
 * Visitor for join calc clauses.
 */
public class JoinCalcClauseVisitor extends VTLBaseVisitor<Function<DataPoint, Object>> {

    private final ReferenceVisitor referenceVisitor;
    private final DataStructure dataStructure;
    
    public JoinCalcClauseVisitor(ReferenceVisitor referenceVisitor, DataStructure dataStructure) {
        this.referenceVisitor = referenceVisitor;
        this.dataStructure = dataStructure;
    }
    
    /**
     * Temporary hack for tests //TODO: Remove
     */
    JoinCalcClauseVisitor(ReferenceVisitor referenceVisitor) {
        this(referenceVisitor, null);
    }

    @Override
    public Function<DataPoint, Object> visitJoinCalcReference(VTLParser.JoinCalcReferenceContext ctx) {
        Component component = (Component) referenceVisitor.visit(ctx.componentRef());
        return dataPoint -> {
            Map<Component, VTLObject> map = dataStructure.asMap(dataPoint);
            Optional<VTLObject> vtlObject = Optional.ofNullable(map.get(component));
            return vtlObject.map(VTLObject::get).orElseThrow(() -> new RuntimeException(format("component %s not found in %s", component, dataPoint)));
        };
    }

    @Override
    public Function<DataPoint, Object> visitJoinCalcAtom(VTLParser.JoinCalcAtomContext ctx) {
        VTLParser.ConstantContext constantValue = ctx.constant();
        if (constantValue.FLOAT_CONSTANT() != null)
            return tuple -> Float.valueOf(constantValue.FLOAT_CONSTANT().getText());
        if (constantValue.INTEGER_CONSTANT() != null)
            return tuple -> Integer.valueOf(constantValue.INTEGER_CONSTANT().getText());

        throw new RuntimeException(
                format("unsuported constant type %s", constantValue)
        );
    }

    @Override
    public Function<DataPoint, Object> visitJoinCalcPrecedence(VTLParser.JoinCalcPrecedenceContext ctx) {
        return visit(ctx.joinCalcExpression());
    }

    @Override
    public Function<DataPoint, Object> visitJoinCalcSummation(VTLParser.JoinCalcSummationContext ctx) {
        Function<DataPoint, Object> leftResult = visit(ctx.leftOperand);
        Function<DataPoint, Object> rightResult = visit(ctx.rightOperand);

        // TODO: Check types?
        //checkArgument(Number.class.isAssignableFrom(leftResult.getType()));
        //checkArgument(Number.class.isAssignableFrom(rightResult.getType()));

        return dataPoint -> {

            Number leftNumber = (Number) leftResult.apply(dataPoint);
            Number rightNumber = (Number) rightResult.apply(dataPoint);

            // TODO: Write test
            if (leftNumber == null || rightNumber == null) {
                return null;
            }

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
        };
    }


    @Override
    public Function<DataPoint, Object> visitJoinCalcProduct(VTLParser.JoinCalcProductContext ctx) {
        Function<DataPoint, Object> leftResult = visit(ctx.leftOperand);
        Function<DataPoint, Object> rightResult = visit(ctx.rightOperand);

        // Check types?
        //checkArgument(Number.class.isAssignableFrom(leftResult.getType()));
        //checkArgument(Number.class.isAssignableFrom(rightResult.getType()));

        return dataPoint -> {
            Number leftNumber = (Number) leftResult.apply(dataPoint);
            Number rightNumber = (Number) rightResult.apply(dataPoint);

            if (leftNumber == null ^ rightNumber == null) {
                return null;
            }

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

        };
    }
    
    @Override
    public Function<DataPoint, Object> visitJoinCalcBoolean(VTLParser.JoinCalcBooleanContext ctx) {
        BooleanExpressionVisitor booleanVisitor = new BooleanExpressionVisitor(referenceVisitor, dataStructure);
        Predicate<DataPoint> predicate = booleanVisitor.visit(ctx.booleanExpression());
        return predicate::test;
    }
}
