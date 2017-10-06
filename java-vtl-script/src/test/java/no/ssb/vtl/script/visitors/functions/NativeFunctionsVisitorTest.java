package no.ssb.vtl.script.visitors.functions;

import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.visitors.LiteralVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeFunctionsVisitorTest {

    private NativeFunctionsVisitor visitor;

    @Before
    public void setUp() throws Exception {
        LiteralVisitor literalVisitor = new LiteralVisitor();
        visitor = new NativeFunctionsVisitor(literalVisitor);
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
        VTLObject result = visitor.visit(parse.expression());
        assertThat(result.get()).isEqualTo(1);
    }
}
