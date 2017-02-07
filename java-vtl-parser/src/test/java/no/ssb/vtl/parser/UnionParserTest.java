package no.ssb.vtl.parser;


import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.antlr.v4.runtime.*;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.antlr.v4.tool.Rule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.Resources.getResource;
import static no.ssb.vtl.parser.ParserTestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UnionParserTest {

    private static Grammar grammar;
    @ClassRule
    public static ExternalResource grammarResource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            URL grammarURL = getResource(this.getClass(), "VTL.g4");
            String grammarString = Resources.toString(grammarURL, Charset.defaultCharset());
            grammar = new Grammar(checkNotNull(grammarString));
        }
    };

    @Test
    public void testUnion() throws Exception {
        String expression = "union( datasetExpr1, datasetExpr2, datasetExpr3 )";

        String parseResult = parse(expression);
        assertThat(filterWhiteSpaces(parseResult)).isEqualTo(filterWhiteSpaces(
                "(unionExpression:1union((datasetExpression:5(exprAtom:1(variableRef:1(identifier:2datasetExpr1)))),(datasetExpression:5(exprAtom:1(variableRef:1(identifier:2datasetExpr2)))),(datasetExpression:5(exprAtom:1(variableRef:1(identifier:2datasetExpr3))))))"
        ));
    }

    @Test
    public void testUnionWithOnlyOneDataset() throws Exception {
        String expression = "union( datasetExpr1 )";

        String parseResult = parse(expression);
        // @formatter:off
        assertThat(filterWhiteSpaces(parseResult)).isEqualTo(filterWhiteSpaces(
                "(unionExpression:1union((datasetExpression:5(exprAtom:1(variableRef:1(identifier:2datasetExpr1))))))"
        ));
        // @formatter:on
    }

    // TODO: Build a more robust way to test.
    private String parse(String expression) {
        LexerInterpreter lexerInterpreter = grammar.createLexerInterpreter(
                new ANTLRInputStream(expression)
        );
        GrammarParserInterpreter parserInterpreter = grammar.createGrammarParserInterpreter(
                new CommonTokenStream(lexerInterpreter)
        );

        List<RecognitionException> exceptions = Lists.newArrayList();
        parserInterpreter.setErrorHandler(new DefaultErrorStrategy() {
            @Override
            public void reportError(Parser recognizer, RecognitionException e) {
                exceptions.add(e);
            }
        });
        lexerInterpreter.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                exceptions.add(e);
            }
        });
        Rule clause = grammar.getRule("unionExpression");
        ParserRuleContext parse = parserInterpreter.parse(clause.index);
        String parseTree = parse.toStringTree(parserInterpreter);
        if (exceptions.isEmpty())
            return parseTree;
        throw exceptions.get(0);
    }
}
