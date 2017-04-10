package no.ssb.vtl.script.visitors;

import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ParamVisitorTest {

    private ParamVisitor visitor;

    private static VTLParser parse(String expression) {
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(expression));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        return parser;
    }

    @Before
    public void setUp() throws Exception {
        visitor = new ParamVisitor(null);
    }

    @Test
    public void visitDoubleConstant() throws Exception {
        VTLParser parser = parse("1.0");

        Object param = visitor.visit(parser.constant());

        assertThat(param instanceof Double);
        assertThat(param).isEqualTo(1.0d);
    }

    @Test
    public void visitDoubleInScientificNotationConstant() throws Exception {
        VTLParser parser = parse("1.234e2");

        Object param = visitor.visit(parser.constant());

        assertThat(param instanceof Double);
        assertThat(param).isEqualTo(1.234e2);
    }
}