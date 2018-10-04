package no.ssb.vtl.model;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VtlFilteringTest {

    private List<DataPoint> data;
    private DataStructure structure;

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

        List<VTLObject> vtlValues = Arrays.asList(values).stream().map(VTLObject::of).collect(Collectors.toList());

        return new FilteringSpecification.Literal() {
            @Override
            public String getColumn() {
                return column;
            }

            @Override
            public FilteringSpecification.Operator getOperator() {
                return op;
            }

            @Override
            public Collection<VTLObject> getValues() {
                return vtlValues;
            }

            @Override
            public Boolean isNegated() {
                return negated;
            }
        };
    }

    @Before
    public void setUp() throws Exception {
        structure = DataStructure.of(
                "1", Component.Role.IDENTIFIER, String.class,
                "2", Component.Role.IDENTIFIER, String.class
        );

        ArrayList<String> dims = Lists.newArrayList("a", "b", "c", "d", "e");
        data = Lists.cartesianProduct(dims, dims).stream().map(strings -> {
            return DataPoint.create(strings.toArray(new String[0]));
        }).collect(Collectors.toList());
    }

    @Test
    public void testEqual() {

        FilteringSpecification spec = spec(
                clause(equalTo("1", "a")),
                clause(equalTo("2", "a"))
        );

        List<DataPoint> result = data.stream().filter(new VtlFiltering(spec, structure)).collect(Collectors.toList());
        printResult(result);
    }

    @Test
    public void testNotEqual() {

        // (1 = a) and (not 2 = a)
        FilteringSpecification spec = spec(
                clause(equalTo("1", "a")),
                clause(notEqualTo("2", "a"))
        );

        List<DataPoint> result = data.stream().filter(new VtlFiltering(spec, structure)).collect(Collectors.toList());
        printResult(result);
    }

    @Test
    public void testGreaterThan() {

        // (1 = a) and (not 2 = a)
        FilteringSpecification spec = spec(
                clause(equalTo("1", "a")),
                clause(greaterThan("2", "c"))
        );

        List<DataPoint> result = data.stream().filter(new VtlFiltering(spec, structure)).collect(Collectors.toList());
        printResult(result);
    }

    @Test
    public void testGreaterOrEqual() {

        // (1 = a) and (not 2 = a)
        FilteringSpecification spec = spec(
                clause(equalTo("1", "a")),
                clause(greaterThanOrEqualTo("2", "c"))
        );

        List<DataPoint> result = data.stream().filter(new VtlFiltering(spec, structure)).collect(Collectors.toList());
        printResult(result);
    }

    @Test
    public void testLessThan() {

        // (1 = a) and (not 2 = a)
        FilteringSpecification spec = spec(
                clause(equalTo("1", "a")),
                clause(lessThan("2", "c"))
        );

        List<DataPoint> result = data.stream().filter(new VtlFiltering(spec, structure)).collect(Collectors.toList());
        printResult(result);
    }

    @Test
    public void testLessOrEqual() {

        // (1 = a) and (not 2 = a)
        FilteringSpecification spec = spec(
                clause(eq("1", "a")),
                clause(lessThanOrEqualTo("2", "c"))
        );

        List<DataPoint> result = data.stream().filter(new VtlFiltering(spec, structure)).collect(Collectors.toList());
        printResult(result);
    }


    public void printResult(List<DataPoint> result) {
        for (DataPoint datum : data) {
            System.out.print(datum + " -> ");
            for (DataPoint res : result) {
                if (datum.equals(res)) {
                    System.out.print(res);
                }
            }
            System.out.println();
        }
    }
}