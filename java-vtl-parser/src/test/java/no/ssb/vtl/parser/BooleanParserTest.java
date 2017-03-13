package no.ssb.vtl.parser;


import org.junit.Test;

public class BooleanParserTest extends GrammarTest {

    @Test
    public void testAlgebra() throws Exception {
        parse("true and false or false and true", "booleanExpression");
        parse("true and (false or false) and true", "booleanExpression");
        parse("not true and not (true or false) and not true", "booleanExpression");
        parse("not ( ( true xor not (true or false) and not true ) )", "booleanExpression");
    }

    @Test
    public void testEquality() throws Exception {
        parse("dataset.component = component", "booleanExpression");
        parse("dataset.component <> component", "booleanExpression");
        parse("dataset.component <= component", "booleanExpression");
        parse("dataset.component >= component", "booleanExpression");
        parse("dataset.component < component", "booleanExpression");
        parse("dataset.component > component", "booleanExpression");
    }

    @Test
    public void testNot() throws Exception {
        parse("not(true)", "booleanExpression");
        parse("not(false)", "booleanExpression");
        parse("not(true and false)", "booleanExpression");
        parse("not(a <> b or a = b and false)", "booleanExpression");
    }

    @Test
    public void testIsNull() throws Exception {
        parse("isnull(a)", "booleanExpression");
        parse("isnull(null)", "booleanExpression");
        parse("component is not null", "booleanExpression");
        parse("dataset.component is not null", "booleanExpression");
        parse("component is null", "booleanExpression");
        parse("dataset.component is null", "booleanExpression");
    }
}
