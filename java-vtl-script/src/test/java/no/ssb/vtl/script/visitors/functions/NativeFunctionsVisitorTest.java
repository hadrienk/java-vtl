package no.ssb.vtl.script.visitors.functions;

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

import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.visitors.ExpressionVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import javax.script.SimpleBindings;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeFunctionsVisitorTest {

    private NativeFunctionsVisitor visitor;

    @Before
    public void setUp() throws Exception {
        visitor = new NativeFunctionsVisitor(new ExpressionVisitor(new SimpleBindings()));
    }

    private static VTLParser parse(String expression) {
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(expression));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        return parser;
    }

    @Test
    public void testAbs() throws Exception {
        VTLParser parse = parse("abs(-1)");
        VTLExpression2 result = visitor.visit(parse.expression());
        assertThat(result.resolve(null).get()).isEqualTo(1);
    }

    @Test
    public void testRound() throws Exception {
        VTLParser parse = parse("round(1.75,1)");
        VTLExpression2 result = visitor.visit(parse.expression());
        assertThat(result.resolve(null).get()).isEqualTo(1.8);
    }

    @Test
    public void testCeil() throws Exception {
        VTLParser parse = parse("ceil(1.5)");
        VTLExpression2 result = visitor.visit(parse.expression());
        assertThat(result.resolve(null).get()).isEqualTo(2);
    }

    @Test
    public void testFloor() throws Exception {
        VTLParser parse = parse("floor(1.5)");
        VTLExpression2 result = visitor.visit(parse.expression());
        assertThat(result.resolve(null).get()).isEqualTo(1);
    }
}
