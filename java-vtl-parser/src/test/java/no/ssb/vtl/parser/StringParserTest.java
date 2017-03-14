package no.ssb.vtl.parser;


import org.junit.Test;

public class StringParserTest extends GrammarTest {

    @Test
    public void testDateFromStringQuotedFormat() throws Exception {
        parse("date_from_string(m1, \"YYYY\")", "dateFromStringExpression");
    }

    @Test(expected = Exception.class)
    public void testDateFromStringUnquotedFormat() throws Exception {
        parse("date_from_string(m1, YYYY)", "dateFromStringExpression");
    }

}
