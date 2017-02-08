package no.ssb.vtl.test.junit;

import com.google.common.io.Resources;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.runtime.RecognitionException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.antlr.v4.tool.Rule;
import org.junit.rules.ErrorCollector;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A grammar rule that can be used to simplify grammar testing.
 * <p>
 * Parsing errors are reported using the {@link ErrorCollector}.
 */
public class GrammarRule extends ErrorCollector {

    private final URL grammarURL;
    private Grammar grammar;

    public GrammarRule() {
        grammarURL = Resources.getResource(VTLParser.class, "VTL.g4");
    }

    public GrammarRule(URL grammarFile) {
        grammarURL = grammarFile;
    }

    /**
     * Modifies the method-running {@link Statement} to implement this
     * test-running rule.
     *
     * @param base        The {@link Statement} to be modified
     * @param description A {@link Description} of the test implemented in {@code base}
     * @return a new statement, which may be the same as {@code base},
     * a wrapper around {@code base}, or a completely new Statement.
     */
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                before();
                base.evaluate();
            }
        };
    }

    private void before() {
        try {
            String grammarString = Resources.toString(grammarURL, Charset.defaultCharset());
            grammar = new Grammar(grammarString);
        } catch (IOException | RecognitionException e) {
            throw new IllegalArgumentException("could not create grammar", e);
        }
    }

    public Rule withRule(String ruleName) {
        return checkNotNull(
                grammar.getRule(ruleName),
                "could not find rule %s in grammar %s",
                ruleName, grammarURL
        );
    }

    /**
     * Parse an expression starting from the given <b>ANTLR rule</b>
     * <p>
     * In order to get the Rule, use the {@link #withRule(String)} method.
     *
     * @param expression the expression to parse.
     * @param rule       the rule to start from.
     * @return the resulting parse tree.
     */
    public ParserRuleContext parse(String expression, Rule rule) {
        LexerInterpreter lexerInterpreter = grammar.createLexerInterpreter(
                new ANTLRInputStream(expression)
        );
        GrammarParserInterpreter parserInterpreter = grammar.createGrammarParserInterpreter(
                new CommonTokenStream(lexerInterpreter)
        );

        DiagnosticErrorListener diagnosticErrorListener = new DiagnosticErrorListener();
        BaseErrorListener ruleErrorReporter = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {
                addError(e);
            }
        };

        parserInterpreter.setErrorHandler(new GrammarParserInterpreter.BailButConsumeErrorStrategy());

        lexerInterpreter.addErrorListener(diagnosticErrorListener);
        parserInterpreter.addErrorListener(diagnosticErrorListener);
        lexerInterpreter.addErrorListener(ruleErrorReporter);
        parserInterpreter.addErrorListener(ruleErrorReporter);

        return parserInterpreter.parse(rule.index);

    }

}
