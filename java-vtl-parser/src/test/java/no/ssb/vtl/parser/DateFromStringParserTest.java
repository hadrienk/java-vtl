package no.ssb.vtl.parser;


import org.junit.Test;

public class DateFromStringParserTest extends GrammarTest {

    @Test
    public void testDateFromStringQuotedFormat() throws Exception {
        parse("date_from_string(m1, \"YYYY\")", "dateFromStringFunction");
    }

    @Test(expected = Exception.class)
    public void testDateFromStringUnquotedFormat() throws Exception {
        parse("date_from_string(m1, YYYY)", "dateFromStringFunction");
    }

}
