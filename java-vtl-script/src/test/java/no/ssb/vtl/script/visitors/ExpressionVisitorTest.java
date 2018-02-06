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
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.error.ContextualRuntimeException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import java.time.Instant;
import java.util.Map;

import static com.google.common.base.Preconditions.*;
import static java.lang.String.*;
import static org.assertj.core.api.Assertions.*;

public class ExpressionVisitorTest {

    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    private Bindings bindings;
    private ExpressionVisitor expressionVisitor;

    private static VTLParser parse(String expression) {
        VTLLexer lexer = new VTLLexer(CharStreams.fromString(expression));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));

        //remove ConsoleErrorListener to avoid repeated error messages
        parser.removeErrorListeners();
        //report "error alternatives" as RuntimeExceptions
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new RuntimeException("Syntax error at line " + line + ":" + charPositionInLine + " " + msg, e);
            }
        });

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
        VTLExpression result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.resolve(null).get()).isEqualTo(15.1);
    }

    @Test
    public void testConcat() throws Exception {
        VTLParser parse = parse("\"string\" || \"string\"");
        VTLExpression result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLString.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo("stringstring");
    }

    @Test
    public void testDivision() throws Exception {

        VTLParser parse1 = parse("-5 / 2");
        VTLExpression result1 = expressionVisitor.visit(parse1.expression());
        softly.assertThat(result1.getVTLType()).isEqualTo(VTLFloat.class);
        softly.assertThat(result1.resolve(null).get()).isEqualTo(-2.5);

        VTLParser parse2 = parse("-5 / 0.05");
        VTLExpression result2 = expressionVisitor.visit(parse2.expression());
        softly.assertThat(result2.getVTLType()).isEqualTo(VTLFloat.class);
        softly.assertThat(result2.resolve(null).get()).isEqualTo(-100.0);
    }

    @Test
    public void testDivisionTypeError() throws Exception {
        VTLParser parse1 = parse("\"not a number\" / 0.05");
        softly.assertThatThrownBy(() -> expressionVisitor.visit(parse1.expression()))
                .isInstanceOf(ContextualRuntimeException.class)
                .hasMessage("invalid type VTLString, expected VTLNumber");

        VTLParser parse2 = parse("-5 / \"not a number\"");
        softly.assertThatThrownBy(() -> expressionVisitor.visit(parse2.expression()))
                .isInstanceOf(ContextualRuntimeException.class)
                .hasMessage("invalid type VTLString, expected VTLNumber");
    }

    @Test
    public void testMultiplication() throws Exception {
        VTLParser parse = parse("-1.5 * -10");
        VTLExpression result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLFloat.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(15.0);

        parse = parse("5 * 10");
        result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLInteger.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(50L);
    }

    @Test
    public void testMultiplicationTypeError() throws Exception {
        VTLParser parse1 = parse("\"not a number\" * 1");
        softly.assertThatThrownBy(() -> expressionVisitor.visit(parse1.expression()))
                .isInstanceOf(ContextualRuntimeException.class)
                .hasMessage("invalid type VTLString, expected VTLNumber");

        VTLParser parse2 = parse("1 * \"not a number\"");
        softly.assertThatThrownBy(() -> expressionVisitor.visit(parse2.expression()))
                .isInstanceOf(ContextualRuntimeException.class)
                .hasMessage("invalid type VTLString, expected VTLNumber");
    }

    @Test
    public void testAddition() throws Exception {
        VTLParser parse = parse("-10 + 15");
        VTLExpression result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLInteger.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(5L);
    }

    @Test
    public void testAdditionTypeError() throws Exception {
        VTLParser parse1 = parse("\"not a number\" + 1");
        softly.assertThatThrownBy(() -> expressionVisitor.visit(parse1.expression()))
                .isInstanceOf(ContextualRuntimeException.class)
                .hasMessage("invalid type VTLString, expected VTLNumber");

        VTLParser parse2 = parse("1 + \"not a number\"");
        softly.assertThatThrownBy(() -> expressionVisitor.visit(parse2.expression()))
                .isInstanceOf(ContextualRuntimeException.class)
                .hasMessage("invalid type VTLString, expected VTLNumber");
    }

    @Test
    public void testSubtraction() throws Exception {
        VTLParser parse = parse("-10 - 15");
        VTLExpression result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLInteger.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(-25L);
    }

    @Test
    public void testSubtractionTypeError() throws Exception {
        VTLParser parse1 = parse("\"not a number\" - 1");
        softly.assertThatThrownBy(() -> expressionVisitor.visit(parse1.expression()))
                .isInstanceOf(ContextualRuntimeException.class)
                .hasMessage("invalid type VTLString, expected VTLNumber");

        VTLParser parse2 = parse("1 - \"not a number\"");
        softly.assertThatThrownBy(() -> expressionVisitor.visit(parse2.expression()))
                .isInstanceOf(ContextualRuntimeException.class)
                .hasMessage("invalid type VTLString, expected VTLNumber");
    }

    @Test
    public void testPrecedence() throws Exception {
        VTLParser parse = parse("(-10 - 15) * 2");
        VTLExpression result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLInteger.class);
        softly.assertThat(result.resolve(null).get()).isEqualTo(-50L);

        parse = parse("-10 - 15 * 2");
        result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLInteger.class);
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
            VTLExpression result = expressionVisitor.visit(parse.expression());
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
            VTLExpression result = expressionVisitor.visit(parse.expression());
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

        VTLExpression<?> result;
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

    @Test
    public void testIfThenElse() throws Exception {
        VTLParser parse = parse("if false then \"false\" elseif true then \"true\" else \"else\"");
        VTLExpression result = expressionVisitor.visit(parse.expression());
        softly.assertThat(result.getVTLType()).isEqualTo(VTLString.class);
        softly.assertThat(result.resolve(new SimpleBindings()).get()).isEqualTo("true");
    }

    @Test
    public void testEmbeddingIfFails() throws Exception {
        assertThatThrownBy(() -> {
            VTLParser parse = parse("if true then if true then 1 else 2 else 3");
            expressionVisitor.visit(parse.expression());
        })
                .as("exception when embedding if in an if")
                .hasMessageContaining("value cannot be another if-then-else expression")
                .isExactlyInstanceOf(RuntimeException.class);
    }
}
