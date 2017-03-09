package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLPredicate;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Map;
import java.util.function.BiPredicate;

public class BooleanExpressionVisitor extends VTLBaseVisitor<VTLPredicate> {
    
    private final ReferenceVisitor referenceVisitor;
    private final DataStructure dataStructure;
    
    public BooleanExpressionVisitor(ReferenceVisitor referenceVisitor, DataStructure dataStructure) {
        this.referenceVisitor = referenceVisitor;
        this.dataStructure = dataStructure;
    }
    
    @Override
    public VTLPredicate visitBooleanExpression(VTLParser.BooleanExpressionContext ctx) {
        if (ctx.BOOLEAN_CONSTANT() != null) {
            Boolean booleanConstant = Boolean.valueOf(ctx.BOOLEAN_CONSTANT().getText());
            return dataPoints -> VTLBoolean.of(booleanConstant);
        }else if (ctx.op != null) {
            VTLPredicate left = visit(ctx.booleanExpression(0));
            VTLPredicate right = visit(ctx.booleanExpression(1));
            switch (ctx.op.getType()) {
                case VTLParser.AND:
                    return left.and(right);
                case VTLParser.OR:
                    return left.or(right);
                case VTLParser.XOR:
                    return left.xor(right);
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
    public VTLPredicate visitBooleanEquality(VTLParser.BooleanEqualityContext ctx) {
        ParamVisitor paramVisitor = new ParamVisitor(referenceVisitor);
        Object left = paramVisitor.visit(ctx.left);
        Object right = paramVisitor.visit(ctx.right);
        
        BiPredicate<VTLObject, VTLObject> booleanOperation = getBooleanOperation(ctx.op.getType());
    
        return getVtlPredicate(left, right, booleanOperation);
    }
    
    VTLPredicate getVtlPredicate(Object left, Object right, BiPredicate<VTLObject, VTLObject> booleanOperation) {
        if (right == null || left == null) {
            return dataPoint -> null;
        }
        
        if (isComp(left) && !isComp(right)) {
            return dataPoint -> {
                VTLObject leftValue = getValue((Component) left, dataPoint);
                return nullableResultOf(booleanOperation, leftValue, VTLObject.of(right));
            };
        } else if (!isComp(left) && isComp(right)){
            return dataPoint -> {
                VTLObject rightValue = getValue((Component) right, dataPoint);
                return nullableResultOf(booleanOperation,VTLObject.of(left), rightValue);
            };
        } else if (isComp(left) && isComp(right)) {
            return dataPoint -> {
                VTLObject rightValue = getValue((Component) right, dataPoint);
                VTLObject leftValue = getValue((Component) left, dataPoint);
                return nullableResultOf(booleanOperation, leftValue, rightValue);
            };
        } else {
            return dataPoint -> nullableResultOf(booleanOperation, VTLObject.of(left), VTLObject.of(right));
        }
    }

    public VTLPredicate getIsNullPredicate(Object value) {
        if (isComp(value)) {
            return dataPoint -> {
                VTLObject resolvedValue = getValue((Component) value, dataPoint);
                return VTLBoolean.of(
                        resolvedValue == null ||
                        resolvedValue == VTLObject.NULL ||
                        resolvedValue.get() == null
                );
            };
        } else {
            return dataPoint -> VTLBoolean.of(value == null);
        }
    }

    private VTLBoolean nullableResultOf(BiPredicate<VTLObject, VTLObject> operation, VTLObject left, VTLObject right) {
        if (left == null || left.get() == null || right == null || right.get() == null) {
            return null;
        } else {
            return VTLBoolean.of(operation.test(left, right));
        }
    }
    
    BiPredicate<VTLObject, VTLObject> getBooleanOperation(int op) {
        BiPredicate<VTLObject, VTLObject> equals = ((l, r) -> l.compareTo(r) == 0);
        switch (op) {
            case VTLParser.EQ:
                return equals;
            case VTLParser.NE:
                return equals.negate();
            case VTLParser.LE:
                return (l, r) ->  l.compareTo(r) <= 0;
            case VTLParser.LT:
                return (l, r) -> l.compareTo(r) < 0;
            case VTLParser.GE:
                return (l, r) -> l.compareTo(r) >= 0;
            case VTLParser.GT:
                return (l, r) -> l.compareTo(r) > 0;
            default:
                throw new ParseCancellationException("Unsupported boolean equality operator " + op);
        }
    }


    @Override
    public VTLPredicate visitBooleanIsNull(VTLParser.BooleanIsNullContext ctx) {
        ParamVisitor paramVisitor = new ParamVisitor(referenceVisitor);
        Object ref = paramVisitor.visit(ctx.booleanParam());

        return getIsNullPredicate(ref);
    }

    private VTLObject getValue(Component component, DataPoint dataPoint) {
        Map<Component, VTLObject> componentVTLObjectMap = dataStructure.asMap(dataPoint);
        return componentVTLObjectMap.get(component);
    }
    
    private boolean isComp(Object o) {
        return o instanceof Component;
    }
    
    
}
