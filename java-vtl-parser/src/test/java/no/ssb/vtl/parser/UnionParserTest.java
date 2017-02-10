package no.ssb.vtl.parser;


import org.junit.Test;

public class UnionParserTest extends GrammarTest {

    @Test
    public void testUnion() throws Exception {
        String expression = "union( datasetExpr1, datasetExpr2, datasetExpr3 )";
        parse(expression, "unionExpression");
    }

    @Test
    public void testUnionWithOnlyOneDataset() throws Exception {
        parse("union( datasetExpr1 )", "unionExpression");
    }
}
