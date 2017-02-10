package no.ssb.vtl.parser;

import org.junit.Test;

public class DropClauseTest extends GrammarTest {

    @Test
    public void testJoinDrop() throws Exception {
        parse("drop varID1, varID2, varID3.varID4", "joinDropExpression");
    }

}
