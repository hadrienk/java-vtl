package no.ssb.vtl.script.visitors.join;

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

import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLScalarExpressionVisitor;

/**
 * Visitor for join calc clauses.
 */
@Deprecated
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

//    @Override
//    public VTLExpression visitJoinCalcReference(VTLParser.JoinCalcReferenceContext ctx) {
//        Component component = (Component) referenceVisitor.visit(ctx.componentRef());
//        return new VTLExpression.Builder(component.getType(), dataPoint -> {
//            Map<Component, VTLObject> map = dataStructure.asMap(dataPoint);
//            Optional<VTLObject> vtlObject = Optional.ofNullable(map.get(component));
//            return vtlObject.orElseThrow(
//                    () -> new RuntimeException(format("component %s not found in %s", component, dataPoint)));
//        }).description(component.toString()).build();
//    }
//
//    @Override
//    public VTLExpression visitJoinCalcAtom(VTLParser.JoinCalcAtomContext ctx) {
//        VTLParser.ConstantContext constantValue = ctx.constant();
//        if (constantValue.FLOAT_CONSTANT() != null){
//            Double aDouble = Double.valueOf(constantValue.FLOAT_CONSTANT().getText());
//            return new VTLExpression.Builder(Double.class, dataPoint -> VTLObject.of(aDouble))
//                    .description(aDouble + "d").build();
//        }
//        if (constantValue.INTEGER_CONSTANT() != null) {
//            Long aLong = Long.valueOf(constantValue.INTEGER_CONSTANT().getText());
//            return new VTLExpression.Builder(Long.class, dataPoint -> VTLObject.of(aLong))
//                    .description(aLong.toString()).build();
//        }
//        if (constantValue.STRING_CONSTANT() != null) {
//            String aString = VisitorUtil.stripQuotes(constantValue.STRING_CONSTANT());
//            return new VTLExpression.Builder(String.class, dataPoint -> VTLObject.of(aString))
//                    .description(aString).build();
//        }
//        if (constantValue.BOOLEAN_CONSTANT() != null) {
//            Boolean aBoolean = Boolean.valueOf(constantValue.BOOLEAN_CONSTANT().getText());
//            return new VTLExpression.Builder(Boolean.class, dataPoint -> VTLObject.of(aBoolean))
//                    .description(aBoolean.toString()).build();
//        }
//        //TODO: NULL_CONSTANT ?
//
//        throw new RuntimeException(
//                format("unsupported constant type %s", constantValue)
//        );
//        //TODO: Merge this with ParamVisitor.visitConstant()
//    }
//
//    @Override
//    public VTLExpression visitJoinCalcPrecedence(VTLParser.JoinCalcPrecedenceContext ctx) {
//        return visit(ctx.joinCalcExpression());
//    }
//
//    @Override
//    public VTLExpression visitJoinCalcSummation(VTLParser.JoinCalcSummationContext ctx) {
//        VTLExpression leftResult = visit(ctx.leftOperand);
//        VTLExpression rightResult = visit(ctx.rightOperand);
//
//        // TODO: Check types?
//        //checkArgument(Number.class.isAssignableFrom(leftResult.getType()));
//        //checkArgument(Number.class.isAssignableFrom(rightResult.getType()));
//
//        return new VTLExpression.Builder(getResultingType(leftResult, rightResult, ctx.sign), dataPoint -> {
//
//            Number leftNumber = (Number) leftResult.apply(dataPoint).get();
//            Number rightNumber = (Number) rightResult.apply(dataPoint).get();
//
//            // TODO: Write test
//            if (leftNumber == null || rightNumber == null) {
//                return null;
//            }
//
//            VTLNumber left = VTLNumber.of(leftNumber);
//            if (ctx.sign.getText().equals("+")) {
//                return left.add(rightNumber);
//            } else {
//                return left.subtract(rightNumber);
//            }
//
//
//        }).description(format("%s %s %s", leftResult, ctx.sign.getText(), rightResult)).build();
//    }
//
//    @Override
//    public VTLExpression visitJoinCalcProduct(VTLParser.JoinCalcProductContext ctx) {
//        VTLExpression leftResult = visit(ctx.leftOperand);
//        VTLExpression rightResult = visit(ctx.rightOperand);
//
//        // Check types?
//        //checkArgument(Number.class.isAssignableFrom(leftResult.getType()));
//        //checkArgument(Number.class.isAssignableFrom(rightResult.getType()));
//
//        return new VTLExpression.Builder(getResultingType(leftResult, rightResult, ctx.sign), dataPoint -> {
//            Number leftNumber = (Number) leftResult.apply(dataPoint).get();
//            Number rightNumber = (Number) rightResult.apply(dataPoint).get();
//
//            if (leftNumber == null ^ rightNumber == null) {
//                return null;
//            }
//
//            VTLNumber left = VTLNumber.of(leftNumber);
//            if (ctx.sign.getText().equals("*")) {
//                return left.multiply(rightNumber);
//            } else {
//                return left.divide(rightNumber);
//            }
//
//        }).description(format("%s %s %s", leftResult, ctx.sign.getText(), rightResult)).build();
//    }
//
//    @Override
//    public VTLExpression visitJoinCalcBoolean(VTLParser.JoinCalcBooleanContext ctx) {
//        BooleanExpressionVisitor booleanVisitor = new BooleanExpressionVisitor(referenceVisitor, dataStructure);
//        VTLPredicate predicate = booleanVisitor.visit(ctx.booleanExpression());
//        return new VTLExpression.Builder(Boolean.class,
//                dataPoint -> VTLBoolean.of(predicate.apply(dataPoint)))
//                .description("boolean").build();
//    }
//
//    @Override
//    public VTLExpression visitJoinCalcCondition(VTLParser.JoinCalcConditionContext ctx) {
//        ConditionalExpressionVisitor conditionalExpressionVisitor = new ConditionalExpressionVisitor(
//                referenceVisitor, dataStructure);
//        return conditionalExpressionVisitor.visit(ctx.conditionalExpression());
//    }
//
//    @Override
//    public VTLExpression visitJoinCalcDate(VTLParser.JoinCalcDateContext ctx) {
//        DateFunctionVisitor dateFunctionVisitor = new DateFunctionVisitor(
//                referenceVisitor, dataStructure);
//        return dateFunctionVisitor.visit(ctx.dateFunction());
//    }
//
//    private Class getResultingType(VTLExpression leftResult, VTLExpression rightResult, Token sign) {
//        if (sign.getText().equals("/")) {
//            return Double.class;
//        } else if (leftResult.getType().equals(Double.class) || rightResult.getType().equals(Double.class)) {
//            return Double.class;
//        } else {
//            return Long.class;
//        }
//    }

}
