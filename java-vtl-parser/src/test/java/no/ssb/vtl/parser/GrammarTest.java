package no.ssb.vtl.parser;

import no.ssb.vtl.test.junit.GrammarRule;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Rule;

/**
 * Base class for grammar tests
 */
public class GrammarTest {

    @Rule
    public GrammarRule grammarRule = new GrammarRule();

    protected ParserRuleContext parse(String expr, String rule) throws Exception {
        return grammarRule.parse(expr, grammarRule.withRule(rule));
    }
}
