package kohl.hadrien.vtl.parser;

import com.google.common.io.Resources;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerInterpreter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.antlr.v4.tool.Rule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.net.URL;
import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.*;
import static com.google.common.io.Resources.*;
import static org.assertj.core.api.Assertions.*;

public class FilterClauseTest {
    
    private static Grammar grammar;
    @ClassRule
    public static ExternalResource grammarResource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            URL grammarURL = getResource(this.getClass(), "/imports/Relational.g4");
            String grammarString = Resources.toString(grammarURL, Charset.defaultCharset());
            grammar = new Grammar(checkNotNull(grammarString));
        }
    };
    
    @Test
    public void testJoinWithFilter() throws Exception {
        String expression = "" +
               // "[varID1, varID2]{\n" +
                "  filter true";
               // "}";
        
        String parseTree = parse(expression, "joinFilterExpression");
        System.out.println(parseTree);
    
        assertThat(parseTree).isEqualTo("(joinFilterExpression:1 filter (booleanExpression:1 true))");
    }
    
    
    // TODO: Build a more robust way to test.
    private String parse(String expression, String rule) {
        LexerInterpreter lexerInterpreter = grammar.createLexerInterpreter(
                new ANTLRInputStream(expression)
        );
        GrammarParserInterpreter parserInterpreter = grammar.createGrammarParserInterpreter(
                new CommonTokenStream(lexerInterpreter)
        );
        
        //JoinParserTest.FailingErrorListener listener = new JoinParserTest.FailingErrorListener();
        //parserInterpreter.addErrorListener(listener);
        //lexerInterpreter.addErrorListener(listener);
        
        parserInterpreter.setErrorHandler(new BailErrorStrategy());
        
        Rule clause = grammar.getRule(rule);
        ParserRuleContext parse = parserInterpreter.parse(clause.index);
        return parse.toStringTree(parserInterpreter);
    }
    
}
