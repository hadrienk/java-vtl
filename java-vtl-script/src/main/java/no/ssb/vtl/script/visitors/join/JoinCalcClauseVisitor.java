package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLPredicate;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.visitors.BooleanExpressionVisitor;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLScalarExpressionVisitor;

import java.util.Map;
import java.util.Optional;

import static java.lang.String.*;

/**
 * Visitor for join calc clauses.
 */
public class JoinCalcClauseVisitor extends VTLScalarExpressionVisitor<VTLExpression> {

    private final ReferenceVisitor referenceVisitor;
    private final DataStructure dataStructure;
    
    public JoinCalcClauseVisitor(ReferenceVisitor referenceVisitor, DataStructure dataStructure) {
        this.referenceVisitor = referenceVisitor;
        this.dataStructure = dataStructure;
    }
    
    JoinCalcClauseVisitor() {
        this(null, null);
    }

    @Override
    public VTLExpression visitJoinCalcReference(VTLParser.JoinCalcReferenceContext ctx) {
        Component component = (Component) referenceVisitor.visit(ctx.componentRef());
        return new VTLExpression.Builder(component.getType(), dataPoint -> {
            Map<Component, VTLObject> map = dataStructure.asMap(dataPoint);
            Optional<VTLObject> vtlObject = Optional.ofNullable(map.get(component));
            return vtlObject.orElseThrow(
                    () -> new RuntimeException(format("component %s not found in %s", component, dataPoint)));
        }).description(component.toString()).build();
    }

    @Override
    public VTLExpression visitJoinCalcAtom(VTLParser.JoinCalcAtomContext ctx) {
        VTLParser.ConstantContext constantValue = ctx.constant();
        if (constantValue.FLOAT_CONSTANT() != null){
            Float aFloat = Float.valueOf(constantValue.FLOAT_CONSTANT().getText());
            return new VTLExpression.Builder(Float.class, dataPoint -> VTLObject.of(aFloat))
                    .description(aFloat+"f").build();
        }
        if (constantValue.INTEGER_CONSTANT() != null) {
            Integer integer = Integer.valueOf(constantValue.INTEGER_CONSTANT().getText());
            return new VTLExpression.Builder(Integer.class, dataPoint -> VTLObject.of(integer))
                    .description(integer.toString()).build();
        }
        throw new RuntimeException(
                format("unsuported constant type %s", constantValue)
        );
    }

    @Override
    public VTLExpression visitJoinCalcPrecedence(VTLParser.JoinCalcPrecedenceContext ctx) {
        return visit(ctx.joinCalcExpression());
    }

    @Override
    public VTLExpression visitJoinCalcSummation(VTLParser.JoinCalcSummationContext ctx) {
        VTLExpression leftResult = visit(ctx.leftOperand);
        VTLExpression rightResult = visit(ctx.rightOperand);

        // TODO: Check types?
        //checkArgument(Number.class.isAssignableFrom(leftResult.getType()));
        //checkArgument(Number.class.isAssignableFrom(rightResult.getType()));
    
        return new VTLExpression.Builder(Number.class, dataPoint -> {
        
            Number leftNumber = (Number) leftResult.apply(dataPoint).get();
            Number rightNumber = (Number) rightResult.apply(dataPoint).get();
        
            // TODO: Write test
            if (leftNumber == null || rightNumber == null) {
                return null;
            }
        
            VTLNumber left = VTLNumber.of(leftNumber);
            if (ctx.sign.getText().equals("+")) {
                return left.add(rightNumber);
            } else {
                return left.subtract(rightNumber);
            }
        
        
        }).description(format("%s %s %s", leftResult, ctx.sign.getText(), rightResult)).build();
    }


    @Override
    public VTLExpression visitJoinCalcProduct(VTLParser.JoinCalcProductContext ctx) {
        VTLExpression leftResult = visit(ctx.leftOperand);
        VTLExpression rightResult = visit(ctx.rightOperand);

        // Check types?
        //checkArgument(Number.class.isAssignableFrom(leftResult.getType()));
        //checkArgument(Number.class.isAssignableFrom(rightResult.getType()));
    
        return new VTLExpression.Builder(Number.class, dataPoint -> {
            Number leftNumber = (Number) leftResult.apply(dataPoint).get();
            Number rightNumber = (Number) rightResult.apply(dataPoint).get();
        
            if (leftNumber == null ^ rightNumber == null) {
                return null;
            }
        
            VTLNumber left = VTLNumber.of(leftNumber);
            if (ctx.sign.getText().equals("*")) {
                return left.multiply(rightNumber);
            } else {
                return left.divide(rightNumber);
            }
            
        }).description(format("%s %s %s", leftResult, ctx.sign.getText(), rightResult)).build();
    }
    
    @Override
    public VTLExpression visitJoinCalcBoolean(VTLParser.JoinCalcBooleanContext ctx) {
        BooleanExpressionVisitor booleanVisitor = new BooleanExpressionVisitor(referenceVisitor, dataStructure);
        VTLPredicate predicate = booleanVisitor.visit(ctx.booleanExpression());
        return new VTLExpression.Builder(Boolean.class,
                dataPoint -> VTLBoolean.of(predicate.apply(dataPoint)))
                .description("boolean").build();
    }
}
