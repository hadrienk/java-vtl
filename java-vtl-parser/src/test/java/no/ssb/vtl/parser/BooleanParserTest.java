package no.ssb.vtl.parser;


import org.junit.Test;

public class BooleanParserTest extends GrammarTest {

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
    }
}
