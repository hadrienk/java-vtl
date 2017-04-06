package no.ssb.vtl.script.visitors;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Before;
import org.junit.Test;

import javax.script.SimpleBindings;

import static org.mockito.Mockito.*;

public class ConditionalExpressionVisitorTest {

    private ConditionalExpressionVisitor visitor;
    private ReferenceVisitor referenceVisitor;
    private DataStructure dataStructure;
    private SimpleBindings bindings;
    private Dataset dataset;

    private static VTLParser parse(String expression) {
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(expression));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        return parser;
    }

    @Before
    public void setUp() throws Exception {
        dataStructure = DataStructure.of(
                (o, aClass) -> o,
                "id1", Component.Role.IDENTIFIER, String.class,
                "m2Double", Component.Role.MEASURE, Double.class,
                "m2Long", Component.Role.MEASURE, Long.class);

        dataset = mock(Dataset.class);
        bindings = new SimpleBindings(ImmutableMap.of(
                "m2Double", dataStructure.get("m2Double"),
                "m2Long", dataStructure.get("m2Long"),
                "dataset", dataset
        ));

        when(dataset.getDataStructure()).thenReturn(dataStructure);

        referenceVisitor = new ReferenceVisitor(bindings);
        visitor = new ConditionalExpressionVisitor(referenceVisitor, dataStructure);
    }

    @Test
    public void visitNvlExpressionDoubleToReplaceDoubleNoException() throws Exception {
        VTLParser parser = parse("nvl(m2Double, 0.0)");
        visitor.visitNvlExpression(parser.nvlExpression());
    }

    @Test(expected = ParseCancellationException.class)
    public void visitNvlExpressionLongToReplaceDouble() throws Exception {
        VTLParser parser = parse("nvl(m2Double, 0)");
        visitor.visitNvlExpression(parser.nvlExpression());
    }

    @Test
    public void visitNvlExpressionLongToReplaceLongNoException() throws Exception {
        VTLParser parser = parse("nvl(m2Long, 0)");
        visitor.visitNvlExpression(parser.nvlExpression());
    }

    @Test(expected = ParseCancellationException.class)
    public void visitNvlExpressionDoubleToReplaceLong() throws Exception {
        VTLParser parser = parse("nvl(m2Long, 0.0)");
        visitor.visitNvlExpression(parser.nvlExpression());
    }

}