package no.ssb.vtl.parser;

import org.junit.Test;

public class FilterClauseTest extends GrammarTest {

    @Test
    public void testJoinWithFilter() throws Exception {
        parse("filter true", "joinFilterExpression");
    }
    
}
