package no.ssb.vtl.parser;

import org.junit.Test;

public class FoldAndUnfoldTest extends GrammarTest {

    @Test
    public void testJoinFold() throws Exception {
        // Test different writing forms of component
        parse("fold dataset.component, dataset.component to newIdentifier, newComponent", "joinFoldExpression");
        parse("fold dataset.'component', dataset.'component' to newIdentifier, newComponent", "joinFoldExpression");
        parse("fold 'dataset'.'component', 'dataset'.'component' to newIdentifier, newComponent", "joinFoldExpression");
        parse("fold 'dataset'.component, 'dataset'.component to newIdentifier, newComponent", "joinFoldExpression");
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
    public void testInvalidMissingElementsJoinUnfold() throws Exception {
        parse("unfold dataset.component, component to ", "joinUnfoldExpression");
    }

    @Test(expected = Exception.class)
        public void testTooManyComponentsJoinUnfold() throws Exception {
        parse("unfold dataset.component, component, component to \"varID1\"", "joinUnfoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingMeasureJoinUnfold() throws Exception {
        parse("unfold component to \"varID1\"", "joinUnfoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingIdentifierInvalidJoinUnfold() throws Exception {
        parse("unfold ,component to \"varID1\"", "joinUnfoldExpression");
    }
}
