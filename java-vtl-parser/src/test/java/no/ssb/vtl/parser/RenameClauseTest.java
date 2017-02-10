package no.ssb.vtl.parser;

import org.junit.Test;

public class RenameClauseTest extends GrammarTest {

    @Test
    public void testRename() throws Exception {
        String expression = "rename varID1 to varID2, varID3.varID4 to varID5";
        parse(expression, "joinRenameExpression");
    }

}
