package no.ssb.vtl.parser;

import org.junit.Test;

public class JoinParserTest extends GrammarTest {

    @Test
    public void testJoin() throws Exception {
        String expression = "" +
                "[varID1, varID2]{\n" +
                "  varID2 = varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 = varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

    @Test
    public void testJoinWithOn() throws Exception {
        String expression = "" +
                "[outer varID1, varID2 on dimensionExpr1, dimensionExpr2]{\n" +
                "  varID2 = varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 = varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }
}
