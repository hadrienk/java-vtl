package no.ssb.vtl.parser;


import org.junit.Test;

public class HierarchyParserTest extends GrammarTest {

    @Test
    public void testHierarchy() throws Exception {
        parse("hierarchy ( dataset, component, dataset, true )", "hierarchyExpression");
        parse("hierarchy ( dataset, component, dataset, false )", "hierarchyExpression");
        parse("hierarchy ( dataset, component, dataset, true, sum)", "hierarchyExpression");
        parse("hierarchy ( dataset, component, dataset, false, prod )", "hierarchyExpression");
    }
}
