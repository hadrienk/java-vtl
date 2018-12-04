package no.ssb.vtl.script.expressions;

import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VtlFiltering;
import no.ssb.vtl.script.expressions.equality.AbstractEqualityExpression;
import no.ssb.vtl.script.expressions.equality.EqualExpression;
import no.ssb.vtl.script.expressions.equality.GraterThanExpression;
import no.ssb.vtl.script.expressions.equality.GreaterOrEqualExpression;
import no.ssb.vtl.script.expressions.equality.IsNotNullExpression;
import no.ssb.vtl.script.expressions.equality.IsNullExpression;
import no.ssb.vtl.script.expressions.equality.LesserOrEqualExpression;
import no.ssb.vtl.script.expressions.equality.LesserThanExpression;
import no.ssb.vtl.script.expressions.equality.NotEqualExpression;
import no.ssb.vtl.script.expressions.logic.AndExpression;
import no.ssb.vtl.script.expressions.logic.OrExpression;
import no.ssb.vtl.script.expressions.logic.XorExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class VtlFilteringConverter {

    public static VtlFiltering convert(AbstractEqualityExpression equalityExpression) {
        VTLExpression leftOperand = equalityExpression.getLeftOperand();
        VTLExpression rightOperand = equalityExpression.getRightOperand();
        VariableExpression variableExpression = null;
        LiteralExpression literalExpression = null;
        for (VTLExpression operand : Arrays.asList(leftOperand, rightOperand)) {
            if (operand instanceof VariableExpression) {
                variableExpression = (VariableExpression) operand;
            }
            if (operand instanceof LiteralExpression) {
                literalExpression = (LiteralExpression) operand;
            }
        }
        if (literalExpression == null || variableExpression == null) {
            return VtlFiltering.literal(false, FilteringSpecification.Operator.TRUE, null, null);
        } else {

            // Use the internal identifier with dataset prefix if it is a membership expression.
            // This should be refactored at some point.
            String column = "";
            if (variableExpression instanceof MembershipExpression) {
                column = ((MembershipExpression) variableExpression).getDatasetIdentifier() + "_";
            }
            column = column + variableExpression.getIdentifier();

            VTLObject value = literalExpression.resolve(null);
            if (equalityExpression instanceof EqualExpression) {
                return VtlFiltering.eq(column, value.get());
            } else if (equalityExpression instanceof NotEqualExpression) {
                return VtlFiltering.neq(column, value.get());
            } else if (equalityExpression instanceof GreaterOrEqualExpression) {
                return VtlFiltering.ge(column, value.get());
            } else if (equalityExpression instanceof GraterThanExpression) {
                return VtlFiltering.gt(column, value.get());
            } else if (equalityExpression instanceof LesserOrEqualExpression) {
                return VtlFiltering.le(column, value.get());
            } else if (equalityExpression instanceof LesserThanExpression) {
                return VtlFiltering.lt(column, value.get());
            } else if (equalityExpression instanceof IsNullExpression) {
                if (equalityExpression instanceof IsNotNullExpression) {
                    return VtlFiltering.neq(column, value.get());
                } else {
                    return VtlFiltering.eq(column, value.get());
                }
            }
            return null;
        }
    }

    public static VtlFiltering convert(VTLExpression predicate) {
        if (!predicate.getVTLType().equals(VTLBoolean.class)) {
            throw new IllegalArgumentException(format("predicate %s was not a boolean", predicate));
        }

        if (predicate instanceof OrExpression) {
            return convert((OrExpression) predicate);
        } else if (predicate instanceof AndExpression) {
            return convert((AndExpression) predicate);
        } else if (predicate instanceof XorExpression) {
            throw new UnsupportedOperationException();
        } else if (predicate instanceof AbstractEqualityExpression) {
            return convert((AbstractEqualityExpression) predicate);
        } else if (predicate instanceof LiteralExpression) {
            VTLBoolean value = (VTLBoolean) predicate.resolve(null);
            return VtlFiltering.literal(!value.get(), FilteringSpecification.Operator.TRUE, null, value);
        }
        return null;
    }

    public static VtlFiltering convert(OrExpression orExpression) {
        List<VtlFiltering> ops = new ArrayList<>();
        if (orExpression.getLeftOperand() instanceof OrExpression) {
            ops.addAll(convert((OrExpression) orExpression.getLeftOperand()).getOperands());
        } else {
            ops.add(convert(orExpression.getLeftOperand()));
        }
        VtlFiltering sub = convert(orExpression.getRightOperand());
        if (sub != null && sub.getOperator() != FilteringSpecification.Operator.TRUE) {
            ops.add(sub);
        }

        return VtlFiltering.or(ops.toArray(new VtlFiltering[0]));
    }

    public static VtlFiltering convert(AndExpression andExpression) {
        List<VtlFiltering> ops = new ArrayList<>();
        if (andExpression.getLeftOperand() instanceof AndExpression) {
            ops.addAll(convert((AndExpression) andExpression.getLeftOperand()).getOperands());
        } else {
            ops.add(convert(andExpression.getLeftOperand()));
        }
        ops.add(convert(andExpression.getRightOperand()));
        return VtlFiltering.and(ops.toArray(new VtlFiltering[0]));
    }
}
