package no.ssb.vtl.parser;


import org.junit.Test;

public class ConditionalParserTest extends GrammarTest {

    @Test
    public void testNvlWithNumber() throws Exception {
        parse("nvl(m1 , 0)", "conditionalExpression");
    }

    @Test
    public void testNvlComponentRefWithString() throws Exception {
        parse("nvl(ds1.m2, \"constant\")", "conditionalExpression");
    }

}
