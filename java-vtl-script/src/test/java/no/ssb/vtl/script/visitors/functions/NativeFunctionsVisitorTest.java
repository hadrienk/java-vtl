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

import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import no.ssb.vtl.model.VTLTyped;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.visitors.ExpressionVisitor;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.SimpleBindings;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeFunctionsVisitorTest {

    private NativeFunctionsVisitor visitor;

    @Before
    public void setUp() throws Exception {
        visitor = new NativeFunctionsVisitor(new ExpressionVisitor(new SimpleBindings()));
    }

    private static VTLParser parse(String expression) {
        VTLLexer lexer = new VTLLexer(CharStreams.fromString(expression));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        return parser;
    }

    @Test
    public void testAbs() throws Exception {
        VTLParser parse = parse("abs(-1)");
        VTLExpression result = visitor.visit(parse.expression());
        assertThat(result.resolve(null).get()).isEqualTo(1L);
    }

    @Test
    public void testRound() throws Exception {
        VTLParser parse = parse("round(1.75,1)");
        VTLExpression result = visitor.visit(parse.expression());
        assertThat(result.resolve(null).get()).isEqualTo(1.8);
    }

    @Test
    public void testCeil() throws Exception {
        VTLParser parse = parse("ceil(1.5)");
        VTLExpression result = visitor.visit(parse.expression());
        assertThat(result.getVTLType()).isEqualTo(VTLInteger.class);
        assertThat(result.resolve(null).get()).isEqualTo(2L);
    }

    @Test
    public void testFloor() throws Exception {
        VTLParser parse = parse("floor(1.5)");
        VTLExpression result = visitor.visit(parse.expression());
        assertThat(result.getVTLType()).isEqualTo(VTLInteger.class);
        assertThat(result.resolve(null).get()).isEqualTo(1L);
    }

    @Test
    public void testExp() throws Exception {
        VTLParser parser = parse("exp(5)");
        VTLExpression<?> result = visitor.visit(parser.expression());
        assertThat(result.resolve(null).get()).isEqualTo(148.4131591025766);
    }

    @Test
    public void testLn() throws Exception {
        VTLParser parser = parse("ln(148)");
        VTLExpression<?> result = visitor.visit(parser.expression());
        assertThat(result.resolve(null).get()).isEqualTo(4.997212273764115);
    }

    @Test
    public void testLog() throws Exception {
        VTLParser parser = parse("log(1024, 2)");
        VTLExpression<?> result = visitor.visit(parser.expression());
        assertThat(result.resolve(null).get()).isEqualTo(10d);
    }

    @Test
    public void testMod() throws Exception {
        VTLParser parser = parse("mod(10, 3)");
        VTLExpression<?> result = visitor.visit(parser.expression());
        assertThat(result.resolve(null).get()).isEqualTo(1d);
    }

    @Test
    public void testNroot() throws Exception {
        VTLParser parser = parse("nroot(25, 2)");
        VTLExpression<?> result = visitor.visit(parser.expression());
        assertThat(result.resolve(null).get()).isEqualTo(5d);
    }

    @Test
    public void testPower() throws Exception {
        VTLParser parser = parse("power(5, 2)");
        VTLExpression<?> result = visitor.visit(parser.expression());
        assertThat(result.resolve(null).get()).isEqualTo(25d);
    }

    @Test
    public void testSqrt() throws Exception {
        VTLParser parser = parse("sqrt(9)");
        VTLExpression<?> result = visitor.visit(parser.expression());
        assertThat(result.resolve(null).get()).isEqualTo(3d);
    }

    @Test
    public void testTrunc() throws Exception {
        VTLParser parser = parse("trunc(1.566, 1)");
        VTLExpression<?> result = visitor.visit(parser.expression());
        assertThat(result.resolve(null).get()).isEqualTo(1.5);
    }

    @Test
    public void testNvlWithNull() throws Exception {

        VTLExpression expression = new VTLExpression<VTLObject>() {

            @Override
            public Class getVTLType() {
                return VTLObject.class;
            }

            @Override
            public VTLObject resolve(Bindings bindings) {
                return VTLObject.NULL;
            }
        };
        VTLTyped<VTLString> replacement = new VTLTyped<VTLString>() {
            @Override
            public Class<VTLString> getVTLType() {
                return VTLString.class;
            }
        };

        VTLExpression result = NativeFunctionsVisitor.coerceNullLiteralType(expression, replacement);

        assertThat(result.getVTLType()).isEqualTo(VTLString.class);
    }
}
