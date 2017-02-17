package no.ssb.vtl.parser;

import org.junit.Test;

/**
 * Created by hadrien on 10/02/2017.
 */
public class IdentifiersTest extends GrammarTest {

    @Test(expected = Exception.class)
    public void testIdentifierWithLineBreaks() throws Exception {
        parse("'ident\nifier'", "identifier");
    }

    @Test
    public void testIdentifierQuotes() throws Exception {
        parse("'ident''ifier'", "identifier");
    }

    @Test
    public void testIdentifier() throws Exception {
        parse("identifier", "identifier");
    }

    @Test(expected = Exception.class)
    public void testInvalidIdentifier() throws Exception {
        parse("123identifier", "identifier");
    }

    @Test
    public void testQuotedInvalidIdentifier() throws Exception {
        parse("'123identifier'", "identifier");
    }
}
