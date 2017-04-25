package no.ssb.vtl.parser;

import org.junit.Test;

public class ConstantsTest extends GrammarTest{
    
    @Test
    public void testEscapedQuote() throws Exception {
        parse("\"ident\"\"ifier\"", "constant");
    }
}
