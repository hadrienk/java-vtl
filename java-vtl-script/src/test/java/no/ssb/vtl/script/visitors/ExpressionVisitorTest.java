package no.ssb.vtl.script.visitors;

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

import no.ssb.vtl.model.VTLDate;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import java.time.Instant;

public class ExpressionVisitorTest {

    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    private Bindings bindings;
    private ExpressionVisitor expressionVisitor;

    private static VTLParser parse(String expression) {
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(expression));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        return parser;
    }

    @Before
    public void setUp() throws Exception {
        bindings = new SimpleBindings();
        expressionVisitor = new ExpressionVisitor(bindings);
    }

    @Test
    public void testFunctions() throws Exception {
        VTLParser parse = parse("abs(round(-15.12,1))");
        VTLExpression2 result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.resolve(null)).isNotNull();
    }

    @Test
    public void testVariable() throws Exception {
        VTLDate expected = VTLObject.of(Instant.now());
        bindings.put("variable", expected);
        bindings.put("sum", expected);

        VTLExpression2<?> result;
        VTLParser parse;

        parse = parse("variable");
        result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.resolve(bindings))
                .as("object in variable [variable]")
                .isSameAs(expected);
        softly.assertThat(result.getJavaClass())
                .as("type of variable [variable]")
                .isEqualTo(VTLDate.class);

        parse = parse("'sum'");
        result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.resolve(bindings))
                .as("object in variable ['sum']")
                .isSameAs(expected);
        softly.assertThat(result.getJavaClass())
                .as("type of variable ['sum']")
                .isEqualTo(VTLDate.class);
    }
}
