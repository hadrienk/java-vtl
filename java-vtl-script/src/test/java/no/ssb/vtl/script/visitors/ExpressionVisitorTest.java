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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLDate;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
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
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

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
        softly.assertThat(result.resolve(null).get()).isEqualTo(15.1);
    }

    @Test
    public void testConcat() throws Exception {
        VTLParser parse = parse("\"string\" || \"string\"");
        VTLExpression2 result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLString.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo("stringstring");
    }

    @Test
    public void testDivision() throws Exception {
        VTLParser parse = parse("-5 / 0.05");
        VTLExpression2 result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLNumber.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(-100.0);
    }

    @Test
    public void testMultiplication() throws Exception {
        VTLParser parse = parse("-1.5 * -10");
        VTLExpression2 result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLNumber.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(15.0);
    }

    @Test
    public void testAddition() throws Exception {
        VTLParser parse = parse("-10 + 15");
        VTLExpression2 result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLNumber.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(5L);
    }

    @Test
    public void testSubtraction() throws Exception {
        VTLParser parse = parse("-10 - 15");
        VTLExpression2 result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLNumber.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(-25L);
    }

    @Test
    public void testPrecedence() throws Exception {
        VTLParser parse = parse("(-10 - 15) * 2");
        VTLExpression2 result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLNumber.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(-50L);

        parse = parse("-10 - 15 * 2");
        result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLNumber.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(-40L);
    }

    @Test
    public void testBooleanOperators() {
        VTLBoolean vtlTrue = VTLBoolean.of(true);
        VTLBoolean vtlFalse = VTLBoolean.of(false);
        VTLBoolean vtlNull = VTLBoolean.of((Boolean) null);

        ImmutableTable.Builder<String, String, VTLBoolean> builder = ImmutableTable.builder();
        builder.put("true %s true", "and", vtlTrue);
        builder.put("true %s false", "and", vtlFalse);
        builder.put("true %s null", "and",  vtlNull);
        builder.put("false %s true", "and", vtlFalse);
        builder.put("false %s false", "and", vtlFalse);
        builder.put("false %s null", "and", vtlFalse);
        builder.put("null %s true", "and", vtlNull);
        builder.put("null %s false", "and", vtlFalse);
        builder.put("null %s null", "and", vtlNull);

        builder.put("true %s true", "or", vtlTrue);
        builder.put("true %s false", "or", vtlTrue);
        builder.put("true %s null", "or",  vtlTrue);
        builder.put("false %s true", "or", vtlTrue);
        builder.put("false %s false", "or", vtlFalse);
        builder.put("false %s null", "or", vtlNull);
        builder.put("null %s true", "or", vtlTrue);
        builder.put("null %s false", "or", vtlNull);
        builder.put("null %s null", "or", vtlNull);

        builder.put("true %s true", "xor", vtlFalse);
        builder.put("true %s false", "xor", vtlTrue);
        builder.put("true %s null", "xor",  vtlNull);
        builder.put("false %s true", "xor", vtlTrue);
        builder.put("false %s false", "xor", vtlFalse);
        builder.put("false %s null", "xor", vtlNull);
        builder.put("null %s true", "xor", vtlNull);
        builder.put("null %s false", "xor", vtlNull);
        builder.put("null %s null", "xor", vtlNull);

        builder.put("%s true", "not", vtlFalse);
        builder.put("%s false", "not", vtlTrue);
        builder.put("%s null", "not", vtlNull);

        for (Table.Cell<String, String, VTLBoolean> test : builder.build().cellSet()) {
            String exprTpl = checkNotNull(test.getRowKey());
            String op = test.getColumnKey();
            String expr = format(exprTpl, op);

            VTLParser parse = parse(expr);
            VTLExpression2 result = expressionVisitor.visit(parse.expression());
            softly.assertThat(result.resolve(null))
                    .as("result of the expression [%s]", expr)
                    .isEqualTo(test.getValue());
        }
    }

    @Test
    public void testEqualityOperators() throws Exception {
        ImmutableMap.Builder<String, Boolean> tests = ImmutableMap.<String, Boolean>builder()
                .put("1 > 0", true)
                .put("0 > 1", false)
                .put("-1 > -2", true)
                .put("-2 > -1", false)
                .put("1 >= 1", true)
                .put("1 >= 0", true)
                .put("0 >= 1", false)
                .put("-1 >= -2", true)
                .put("-2 >= -1", false)

                .put("1 < 0", false)
                .put("0 < 1", true)
                .put("-1 < -2", false)
                .put("-2 < -1", true)
                .put("1 <= 1", true)
                .put("1 <= 0", false)
                .put("0 <= 1", true)
                .put("-1 <= -2", false)
                .put("-2 <= -1", true)

                .put("\"b\" > \"a\"", true)
                .put("\"a\" > \"b\"", false)

                .put("1.5 > 0.5", true)
                .put("0.5 > 1.5", false)
                .put("-0.5 > -1.5", true)
                .put("-1.5 > -0.5", false);

                // TODO: Dates

        for (Map.Entry<String, Boolean> test : tests.build().entrySet()) {
            VTLParser parse = parse(test.getKey());
            VTLExpression2 result = expressionVisitor.visit(parse.expression());
            softly.assertThat(result.resolve(null).get())
                    .as("result of the expression [%s]", test.getKey())
                    .isEqualTo(test.getValue());
        }
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
        softly.assertThat(result.getVTLType())
                .as("type of variable [variable]")
                .isEqualTo(expected.getVTLType());

        parse = parse("'sum'");
        result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.resolve(bindings))
                .as("object in variable ['sum']")
                .isSameAs(expected);
        softly.assertThat(result.getVTLType())
                .as("type of variable ['sum']")
                .isEqualTo(expected.getVTLType());
    }
}
