package no.ssb.vtl.parser;

import org.junit.Test;

public class FoldAndUnfoldTest extends GrammarTest {

    @Test
    public void testJoinFold() throws Exception {
        parse("fold \"varID1\", \"varID2\" to dataset.component, component", "joinFoldExpression");
        parse("fold \"varID1\" to component, dataset.component", "joinFoldExpression");
        parse("fold \"varID1\" to component, component", "joinFoldExpression");
        parse("fold \"varID1\" to dataset.component, dataset.component", "joinFoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingElementsFold() throws Exception {
        parse("fold to dataset.component, component", "joinFoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingMeasureFold() throws Exception {
        parse("fold \"varID1\" to component", "joinFoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingDimensionFold() throws Exception {
        parse("fold \"varID1\" to ,component", "joinFoldExpression");
    }

    @Test
    public void testJoinUnfold() throws Exception {
            parse("unfold dataset.component, component to \"varID1\", \"varID2\"", "joinUnfoldExpression");
            parse("unfold component, dataset.component to \"varID1\"", "joinUnfoldExpression");
            parse("unfold dataset.component, dataset.component to \"varID1\"", "joinUnfoldExpression");
            parse("unfold component, component to \"varID1\"", "joinUnfoldExpression");
    }

    @Test(expected = Exception.class)
    public void testInvalidJoinUnfold() throws Exception {
        parse("unfold dataset.component, component to ", "joinUnfoldExpression");
        parse("unfold dataset.component, component, component to \"varID1\"", "joinUnfoldExpression");
        parse("unfold component to \"varID1\"", "joinUnfoldExpression");
        parse("unfold ,component to \"varID1\"", "joinUnfoldExpression");
    }
}
