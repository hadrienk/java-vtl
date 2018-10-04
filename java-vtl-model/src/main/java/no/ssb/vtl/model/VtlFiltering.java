package no.ssb.vtl.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VtlFiltering implements Filtering {

    private final ImmutableList<String> columns;
    private final FilteringSpecification spec;

    public VtlFiltering(DataStructure structure, FilteringSpecification spec) {
        this.columns = ImmutableList.copyOf(structure.keySet());
        this.spec = spec;
    }

    public VtlFiltering(DataStructure structure, List<List<Literal>> spec) {
        this.columns = ImmutableList.copyOf(structure.keySet());
        List<Clause> clauses = Lists.newArrayList();
        for (List<Literal> literals : spec) {
            clauses.add(() -> literals);
        }
        this.spec = () -> clauses;
    }

    public VtlFiltering(DataStructure structure, FilteringSpecification.Clause... clauses) {
        this.columns = ImmutableList.copyOf(structure.keySet());
        this.spec = () -> Arrays.asList(clauses);
    }

    public static FilteringSpecification spec(FilteringSpecification.Clause... clauses) {
        return () -> Arrays.asList(clauses);
    }

    public static FilteringSpecification.Clause clause(FilteringSpecification.Literal... literals) {
        return () -> Arrays.asList(literals);
    }

    public static FilteringSpecification.Literal equalTo(String column, Object value) {
        return eq(column, value);
    }

    public static FilteringSpecification.Literal eq(String column, Object value) {
        return literal(column, FilteringSpecification.Operator.EQ, false, value);
    }

    public static FilteringSpecification.Literal notEqualTo(String column, Object value) {
        return neq(column, value);
    }

    public static FilteringSpecification.Literal neq(String column, Object value) {
        return literal(column, FilteringSpecification.Operator.EQ, true, value);
    }

    public static FilteringSpecification.Literal greaterThan(String column, Object value) {
        return gt(column, value);
    }

    public static FilteringSpecification.Literal gt(String column, Object value) {
        return literal(column, FilteringSpecification.Operator.GT, false, value);
    }

    public static FilteringSpecification.Literal ge(String column, Object value) {
        return literal(column, FilteringSpecification.Operator.GE, false, value);
    }

    public static FilteringSpecification.Literal lt(String column, Object value) {
        return literal(column, FilteringSpecification.Operator.GE, true, value);
    }

    public static FilteringSpecification.Literal le(String column, Object value) {
        return literal(column, FilteringSpecification.Operator.GT, true, value);
    }

    public static FilteringSpecification.Literal greaterThanOrEqualTo(String column, Object value) {
        return ge(column, value);
    }

    public static FilteringSpecification.Literal lessThanOrEqualTo(String column, Object value) {
        return le(column, value);
    }

    public static FilteringSpecification.Literal lessThan(String column, Object value) {
        return lt(column, value);
    }

    public static FilteringSpecification.Literal literal(String column, FilteringSpecification.Operator op, boolean negated, Object... values) {

        List<VTLObject> vtlValues = Arrays.stream(values).map(VTLObject::of).collect(Collectors.toList());

        return new VtlLiteral(column, op, vtlValues, negated);
    }

    @Override
    public boolean test(DataPoint dataPoint) {
        Boolean result = true;
        for (Clause clause : getClauses()) {

            result = false;
            for (Literal literal : clause.getLiterals()) {
                VTLObject variable = findValue(dataPoint, literal);
                Collection<VTLObject> values = literal.getValues();

                for (VTLObject value : values) {
                    switch (literal.getOperator()) {
                        case IN:
                        case EQ:
                            result = variable.compareTo(value) == 0;
                            break;
                        case GT:
                            result = variable.compareTo(value) > 0;
                            break;
                        case GE:
                            result = variable.compareTo(value) >= 0;
                            break;
                    }
                    result = literal.isNegated() ^ result;
                }
                if (result) {
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        String s = getClauses().stream().map(c -> c.getLiterals().stream().map(Object::toString).collect(
                Collectors.joining("||", "(", ")"))).collect(Collectors.joining("&&"));
        stringHelper.addValue(s);
        return stringHelper.toString();
    }

    private VTLObject findValue(DataPoint dataPoint, Literal literal) {
        int index = columns.indexOf(literal.getColumn());
        return dataPoint.get(index);
    }

    @Override
    public Collection<Clause> getClauses() {
        return spec.getClauses();
    }

    private static class VtlLiteral implements Literal {

        private final String column;
        private final Operator op;
        private final ImmutableSet<VTLObject> values;
        private final boolean negated;

        public VtlLiteral(String column, Operator op, List<VTLObject> vtlValues, boolean negated) {
            this.column = column;
            this.op = op;
            this.values = ImmutableSet.copyOf(vtlValues);
            this.negated = negated;
        }

        @Override
        public String toString() {
            String values = this.values.stream().map(VTLObject::toString).collect(Collectors.joining(","));
            switch (op) {
                case EQ:
                    return String.format("%s %s %s", column, isNegated() ? "!=" : "=", values);
                case GT:
                    return String.format("%s %s %s", column, isNegated() ? "<=" : ">", values);
                case GE:
                    return String.format("%s %s %s", column, isNegated() ? "<" : ">=", values);
                case IN:
                    return String.format("%s %s(%s)", column, isNegated() ? "not in" : "in", values);
                default:
                    return "unknown";
            }
        }

        @Override
        public String getColumn() {
            return this.column;
        }

        @Override
        public Operator getOperator() {
            return this.op;
        }

        @Override
        public Collection<VTLObject> getValues() {
            return this.values;
        }

        @Override
        public Boolean isNegated() {
            return this.negated;
        }
    }
}
