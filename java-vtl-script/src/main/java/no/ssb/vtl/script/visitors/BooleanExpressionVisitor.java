package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.function.Predicate;

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
        Component component = (Component) referenceVisitor.visit(ctx.componentRef());
        Object scalar = getScalar(ctx.constant());
    
        Predicate<DataPoint> predicate;
        
        switch (ctx.op.getType()) {
            case VTLParser.EQ:
                predicate = dataPoint -> dataPoint.get().equals(scalar);
                break;
            default:
                throw new ParseCancellationException("Unsupported boolean equality operator");
        }
        
        return tuple -> tuple.stream()
                .filter(dataPoint -> component.equals(dataPoint.getComponent()))
                .anyMatch(predicate);
    }
    
    private Object getScalar(VTLParser.ConstantContext ctx) {
        String constant = ctx.getText();
        if (ctx.BOOLEAN_CONSTANT() != null) {
            return Boolean.valueOf(constant);
        } else if (ctx.FLOAT_CONSTANT() != null) {
            return Float.valueOf(constant);
        } else if (ctx.INTEGER_CONSTANT() != null) {
            return Integer.valueOf(constant);
        } else if (ctx.NULL_CONSTANT() != null) {
            return null;
        } else { //String
            return constant.replace("\"", "");
        }
    }
    
}
