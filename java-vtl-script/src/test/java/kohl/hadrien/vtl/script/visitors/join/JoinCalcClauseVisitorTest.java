package kohl.hadrien.vtl.script.visitors.join;

import com.google.common.collect.Maps;
import kohl.hadrien.vtl.model.AbstractComponent;
import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.Measure;
import kohl.hadrien.vtl.parser.VTLLexer;
import kohl.hadrien.vtl.parser.VTLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JoinCalcClauseVisitorTest {

    @Test
    public void testNumericalLiteralsSum() throws Exception {

        String test = "1 + 2 + 3 + 4 + 5 - 6 - 7 - 8 - 9";
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(test));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());

        JoinCalcClauseVisitor visitor = new JoinCalcClauseVisitor(Collections.emptyMap());

        Component result = visitor.visit(parser.joinCalcExpression());

        assertThat(result.get()).isEqualTo(1 + 2 + 3 + 4 + 5 - 6 - 7 - 8 - 9);

    }

    @Test
    public void testNumericalLiteralsSumWithParenthesis() throws Exception {

        String test = "1 + 2 + ( 3 + 4 + 5 - 6 - 7 ) - 8 - 9";
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(test));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());

        JoinCalcClauseVisitor visitor = new JoinCalcClauseVisitor(Collections.emptyMap());

        Component result = visitor.visit(parser.joinCalcExpression());

        assertThat(result.get()).isEqualTo(1 + 2 + (3 + 4 + 5 - 6 - 7) - 8 - 9);

    }

    @Test
    public void testNumericalLiteralsProduct() throws Exception {

        String test = "1 * 2 * 3 * 4 * 5 / 6 / 7 / 8 / 9";
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(test));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());

        JoinCalcClauseVisitor visitor = new JoinCalcClauseVisitor(Collections.emptyMap());

        Component result = visitor.visit(parser.joinCalcExpression());

        assertThat(result.get()).isEqualTo(1 * 2 * 3 * 4 * 5 / 6 / 7 / 8 / 9);

    }

    @Test
    public void testNumericalVariableReference() {
        String test = "1 * 2 + a * (b - c) / d - 10";

        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(test));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());

        Map<String, Object> variables = Maps.newHashMap();
        variables.put("a", createNumericalComponent(20));
        variables.put("b", createNumericalComponent(15));
        variables.put("c", createNumericalComponent(10));
        variables.put("d", createNumericalComponent(5));


        JoinCalcClauseVisitor visitor = new JoinCalcClauseVisitor(variables);

        Component result = visitor.visit(parser.joinCalcExpression());

        assertThat(result.get()).isEqualTo(1 * 2 + 20 * (15 - 10) / 5 - 10);

    }

    @Test
    public void testNumericalLiteralsProductWithParenthesis() throws Exception {

        String test = "1 * 2 * ( 3 * 4 * 5 / 6 / 7 ) / 8 / 9";
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(test));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());

        // Setup fake map.

        JoinCalcClauseVisitor visitor = new JoinCalcClauseVisitor(Collections.emptyMap());

        Component result = visitor.visit(parser.joinCalcExpression());

        assertThat(result.get()).isEqualTo(1 * 2 * (3 * 4 * 5 / 6 / 7) / 8 / 9);

    }

    @Test
    public void testNumericalReferenceNotFound() throws Exception {

        String test = "notFoundVariable + 1";
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(test));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        JoinCalcClauseVisitor visitor = new JoinCalcClauseVisitor(Collections.emptyMap());


        Throwable ex = null;
        try {
            // TODO: This should happen during execution (when data is computed).
            Component result = visitor.visit(parser.joinCalcExpression());
            result.get();
        } catch (Throwable t) {
            ex = t;
        }
        assertThat(ex).hasMessageContaining("variable")
                .hasMessageContaining("notFoundVariable");

    }

    private Component createNumericalComponent(Integer value) {
        return new AbstractComponent() {
            @Override
            public String name() {
                return "test component";
            }

            @Override
            public Class<?> type() {
                return Integer.class;
            }

            @Override
            public Class<? extends Component> role() {
                return Measure.class;
            }

            @Override
            public Object get() {
                return value;
            }
        };
    }
}
