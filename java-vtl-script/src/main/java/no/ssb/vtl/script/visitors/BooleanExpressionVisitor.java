package no.ssb.vtl.script.visitors;

import com.google.common.collect.MoreCollectors;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.lang.String;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static java.lang.String.*;

public class BooleanExpressionVisitor extends VTLBaseVisitor<Predicate<Dataset.Tuple>> {
    
    private final ReferenceVisitor referenceVisitor;
    
    public BooleanExpressionVisitor(ReferenceVisitor referenceVisitor) {
        this.referenceVisitor = referenceVisitor;
    }
    
    @Override
    public Predicate<Dataset.Tuple> visitBooleanExpression(VTLParser.BooleanExpressionContext ctx) {
        if (ctx.BOOLEAN_CONSTANT() != null) {
            Boolean booleanConstant = Boolean.valueOf(ctx.BOOLEAN_CONSTANT().getText());
            return dataPoints -> booleanConstant;
        }else if (ctx.op != null) {
            Predicate<Dataset.Tuple> left = visit(ctx.booleanExpression(0));
            Predicate<Dataset.Tuple> right = visit(ctx.booleanExpression(1));
            switch (ctx.op.getType()) {
                case VTLParser.AND:
                    return left.and(right);
                case VTLParser.OR:
                    return left.or(right);
                case VTLParser.XOR:
                    return left.or(right).and(left.and(right).negate());
                default:
                    throw new ParseCancellationException("Unsupported boolean operation: " + ctx.op.getText());
            }
        } else if (ctx.booleanEquality() != null) {
            return visit(ctx.booleanEquality());
        } else {
            return super.visit(ctx);
        }
    }
    
    @Override
    public Predicate<Dataset.Tuple> visitBooleanEquality(VTLParser.BooleanEqualityContext ctx) {
        ParamVisitor paramVisitor = new ParamVisitor(referenceVisitor);
        Object left = paramVisitor.visit(ctx.left);
        Object right = paramVisitor.visit(ctx.right);
    
        BiPredicate<Object, Object> booleanOperation = getBooleanOperation(ctx.op);
        
        if (isComp(left) && !isComp(right)) {
            return tuple -> {
                Object leftValue = getOnlyElement(left, tuple);
                return booleanOperation.test(leftValue, right);
            };
        } else if (!isComp(left) && isComp(right)){
            return tuple -> {
                Object rightValue = getOnlyElement(right, tuple);
                return booleanOperation.test(left, rightValue);
            };
        } else if (isComp(left) && isComp(right)) {
            return tuple -> {
                Object rightValue = getOnlyElement(right, tuple);
                Object leftValue = getOnlyElement(left, tuple);
                return booleanOperation.test(leftValue, rightValue);
            };
        } else {
            return tuple -> booleanOperation.test(left, right);
        }
    }
    
    private BiPredicate<Object, Object> getBooleanOperation(Token op) {
        BiPredicate<Object, Object> neitherIsNull = (l, r) -> !(l == null || r == null);
        BiPredicate<Object, Object> bothIsNull = (l, r) -> (l == null && r == null);
        BiPredicate<Object, Object> leftNotNull = (l, r) -> l != null;
        BiPredicate<Object, Object> equals = (leftNotNull.and(Object::equals)).or(bothIsNull);
        switch (op.getType()) {
            case VTLParser.EQ:
                return equals;
            case VTLParser.NE:
                return equals.negate();
            case VTLParser.LE:
                return neitherIsNull.and((l, r) -> compare(l, r) <= 0);
            case VTLParser.LT:
                return neitherIsNull.and((l, r) -> compare(l, r) < 0);
            case VTLParser.GE:
                return neitherIsNull.and((l, r) -> compare(l, r) >= 0);
            case VTLParser.GT:
                return neitherIsNull.and((l, r) -> compare(l, r) > 0);
            default:
                throw new ParseCancellationException("Unsupported boolean equality operator " + op);
        }
    }
    
    private Object getOnlyElement(Object component, Dataset.Tuple tuple) {
        Optional<DataPoint> element = tuple.stream()
                .filter(dataPoint -> component.equals(dataPoint.getComponent()))
                .collect(MoreCollectors.toOptional());
        return element.map(DataPoint::get).orElse(null);
    }
    
    private boolean isComp(Object o) {
        return o instanceof Component;
    }
    
    private int compare(Object value, Object scalar) {
        if (value instanceof Integer && scalar instanceof  Integer) {
            return ((Integer) value).compareTo((Integer) scalar);
        } else if (value instanceof Float && scalar instanceof Float) {
            return ((Float) value).compareTo((Float) scalar);
        } else if (value instanceof Boolean && scalar instanceof Boolean) {
            return ((Boolean) value).compareTo((Boolean) scalar);
        } else if (value instanceof String && scalar instanceof String) {
            return ((String) value).compareTo((String) scalar);
        }
        throw new ParseCancellationException(
                format("Cannot compare %s of type %s with %s of type %s",
                        value, value.getClass(), scalar, scalar.getClass())
        );
    }
    
}
