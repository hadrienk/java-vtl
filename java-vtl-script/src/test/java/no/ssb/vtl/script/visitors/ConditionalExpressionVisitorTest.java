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
    private Component component;

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
                "m1", Component.Role.MEASURE, Number.class,
                "m2", Component.Role.MEASURE, Double.class);

        component = dataStructure.get("m1");
        dataset = mock(Dataset.class);
        bindings = new SimpleBindings(ImmutableMap.of(
                "m1", component,
                "dataset", dataset
        ));

        when(dataset.getDataStructure()).thenReturn(dataStructure);

        referenceVisitor = new ReferenceVisitor(bindings);
        visitor = new ConditionalExpressionVisitor(referenceVisitor, dataStructure);
    }

    @Test
    public void visitNvlExpressionWithDouble() throws Exception {
        VTLParser parser = parse("nvl(m1, 0.0)");

        //no exception
        visitor.visitNvlExpression(parser.nvlExpression());
    }

    @Test
    public void visitNvlExpressionWithInt() throws Exception {
        VTLParser parser = parse("nvl(m1, 0)");

        //no exception
        visitor.visitNvlExpression(parser.nvlExpression());
    }
}