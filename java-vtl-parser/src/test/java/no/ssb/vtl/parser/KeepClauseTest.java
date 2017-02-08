package no.ssb.vtl.parser;

import org.junit.Test;

public class KeepClauseTest extends GrammarTest {

    @Test
    public void testJoinKeep() throws Exception {
        String expression = "" +
                "keep varID1, varID2, varID3.varID4";
        parse(expression, "joinKeepExpression");
    }

}
