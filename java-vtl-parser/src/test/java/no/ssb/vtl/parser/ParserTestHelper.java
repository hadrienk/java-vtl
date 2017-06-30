package no.ssb.vtl.parser;

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

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerInterpreter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.antlr.v4.tool.Rule;

public class ParserTestHelper {
    
    // TODO: Build a more robust way to test.
    public static String parse(String expression, String rule, Grammar grammar) {
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
    
    public static String filterWhiteSpaces(String string) {
        return string.replaceAll("\\s+", "");
    }
}
