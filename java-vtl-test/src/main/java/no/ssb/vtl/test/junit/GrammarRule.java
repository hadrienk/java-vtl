package no.ssb.vtl.test.junit;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.Resources;
import org.antlr.runtime.RecognitionException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.antlr.v4.tool.Rule;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A grammar rule that can be used to simplify grammar testing.
 * <p>
 * Parsing errors are reported using the {@link ErrorCollector}.
 */
public class GrammarRule implements TestRule {

    private String startRule;
    private URL grammarURL;
    private Grammar grammar;

    public GrammarRule() {
    }

    public GrammarRule(URL grammarURL, String startRule) {
        this.grammarURL = checkNotNull(grammarURL);
        this.startRule = checkNotNull(startRule);
    }

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
            if (grammarURL == null) {
                try {
                    Class<?> vtlParserClass = ClassLoader.getSystemClassLoader()
                            .loadClass("no.ssb.vtl.parser.VTLParser");
                    grammarURL = Resources.getResource(vtlParserClass, "VTL.g4");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(
                            "could not find no.ssb.vtl.parser.VTLParser.class, did you compile antlr project?"
                    );
                }
            }
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
     * Parse an expression starting from the default rule.
     *
     * @param expression the expression to parse.
     * @return the resulting parse tree.
     * @throws Exception if the expression failed to parse.
     */
    public ParserRuleContext parse(String expression) throws Exception {
        return parse(expression, withRule(checkNotNull(startRule)));
    }

    /**
     * Parse an expression starting from the given <b>ANTLR rule</b>
     * <p>
     * In order to get the Rule, use the {@link #withRule(String)} method.
     *
     * @param expression the expression to parse.
     * @param rule       the rule to start from.
     *                   @param diagnostic {@link DiagnosticErrorListener} will be used if true.
     * @return the resulting parse tree.
     * @throws Exception if the expression failed to parse.
     */
    public ParserRuleContext parse(String expression, Rule rule, boolean diagnostic) throws Exception {
        Multimap<Integer, String> messages = LinkedListMultimap.create();

        LexerInterpreter lexerInterpreter = grammar.createLexerInterpreter(
                new ANTLRInputStream(expression)
        );
        GrammarParserInterpreter parserInterpreter = grammar.createGrammarParserInterpreter(
                new CommonTokenStream(lexerInterpreter)
        );

        BaseErrorListener errorListener;
        if (diagnostic) {
            errorListener = new DiagnosticErrorListener();
        } else {
            errorListener = new ConsoleErrorListener();
        }

        BaseErrorListener ruleErrorReporter = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {
                int startLine = line, stopLine = line;
                int startColumn = charPositionInLine, stopColumn = charPositionInLine;
                if (offendingSymbol instanceof Token) {
                    Token symbol = (Token) offendingSymbol;
                    int start = symbol.getStartIndex();
                    int stop = symbol.getStopIndex();
                    if (start >= 0 && stop >= 0) {
                        stopColumn = startColumn + (stop - start) + 1;
                    }
                }

                messages.put(stopLine,
                        String.format("at [%4s:%6s]:\t%s (%s)\n",
                                String.format("%d,%d", startLine, stopLine),
                                String.format("%d,%d", startColumn, stopColumn),
                                msg, Optional.ofNullable(e).map(ex -> ex.getClass().getSimpleName()).orElse("null"))
                );
            }
        };

        parserInterpreter.setErrorHandler(new GrammarParserInterpreter.BailButConsumeErrorStrategy());
        lexerInterpreter.removeErrorListeners();
        parserInterpreter.removeErrorListeners();

        lexerInterpreter.addErrorListener(errorListener);
        parserInterpreter.addErrorListener(errorListener);
        lexerInterpreter.addErrorListener(ruleErrorReporter);
        parserInterpreter.addErrorListener(ruleErrorReporter);

        ParserRuleContext parse = parserInterpreter.parse(rule.index);

        if (!messages.isEmpty()) {

            StringBuilder expressionWithErrors = new StringBuilder();
            LineNumberReader expressionReader = new LineNumberReader(new StringReader(expression));
            String line;
            while ((line = expressionReader.readLine()) != null) {
                int lineNumber = expressionReader.getLineNumber();
                expressionWithErrors.append(
                        String.format("\t%d:%s%n", lineNumber, line)
                );
                if (messages.containsKey(lineNumber)) {
                    expressionWithErrors.append(String.format("%n"));
                    for (String message : messages.get(lineNumber)) {
                        expressionWithErrors.append(message);
                    }
                }
            }
            throw new Exception(String.format("errors parsing expression:%n%n%s%n", expressionWithErrors.toString()));
        }

        return parse;
    }

    /**
     * Parse an expression starting from the given <b>ANTLR rule</b>
     * <p>
     * In order to get the Rule, use the {@link #withRule(String)} method.
     *
     * @param expression the expression to parse.
     * @param rule       the rule to start from.
     * @return the resulting parse tree.
     * @throws Exception if the expression failed to parse.
     */
    public ParserRuleContext parse(String expression, Rule rule) throws Exception {
        return parse(expression, rule, false);
    }

}
