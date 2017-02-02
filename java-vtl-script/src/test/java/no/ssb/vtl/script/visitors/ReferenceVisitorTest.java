package no.ssb.vtl.script.visitors;


import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.Before;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.SimpleBindings;

import static no.ssb.vtl.model.Component.Role.IDENTIFIER;
import static org.mockito.Mockito.*;

public class ReferenceVisitorTest {

    private SimpleBindings bindings;
    private Component component;
    private Dataset dataset;

    private static VTLParser parse(String expression) {
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(expression));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        return parser;
    }

    @Before
    public void setUp() throws Exception {
        DataStructure structure = DataStructure.of((o, aClass) -> o,
                "component", IDENTIFIER, String.class
        );
        component = structure.get("component");
        dataset = mock(Dataset.class);
        bindings = new SimpleBindings(ImmutableMap.of(
                "component", component,
                "dataset", dataset
        ));

        when(dataset.getDataStructure()).thenReturn(structure);
    }

    @Test
    public void testEmpty() throws Exception {
        Bindings emptyBindings = mock(Bindings.class);
        when(emptyBindings.isEmpty()).thenReturn(true);

        ReferenceVisitor referenceVisitor = new ReferenceVisitor(emptyBindings);

        VTLParser parser;
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            parser = parse("component");
            softly.assertThat(referenceVisitor.visit(parser.variableRef()))
                    .describedAs("resolved %s with empty scope",((Supplier) parser::variableRef))
                    .isNull();

            parser = parse("component");
            softly.assertThat(referenceVisitor.visit(parser.componentRef()))
                    .describedAs("resolved %s with empty scope", ((Supplier) parser::componentRef))
                    .isNull();

            parser = parse("component");
            softly.assertThat(referenceVisitor.visit(parser.datasetRef()))
                    .describedAs("resolved %s with empty scope",((Supplier) parser::datasetRef))
                    .isNull();
        }

        verify(emptyBindings, times(3)).isEmpty();
        verifyNoMoreInteractions(emptyBindings);
    }

    @Test
    public void testComponents() throws Exception {
        String[] components = new String[]{
                "component",
                "'component'",
                "dataset.'component'",
                "'dataset'.component"
        };

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            ReferenceVisitor referenceVisitor = new ReferenceVisitor(bindings);
            for (String componentExpression : components) {
                VTLParser parser = parse(componentExpression);
                softly.assertThat(referenceVisitor.visit(parser.variableRef()))
                        .describedAs("resolved variableRef for [%s]", componentExpression)
                        .isSameAs(component);
            }
        }
    }

    @Test
    public void testDatasets() throws Exception {
        String[] datasets = new String[]{
                "dataset",
                "'dataset'",
        };

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            ReferenceVisitor referenceVisitor = new ReferenceVisitor(bindings);
            for (String datasetExpression : datasets) {
                VTLParser parser = parse(datasetExpression);
                softly.assertThat(referenceVisitor.visit(parser.variableRef()))
                        .describedAs("resolved variableRef for [%s]", datasetExpression)
                        .isNotNull()
                        .isSameAs(dataset);
            }
        }
    }
}
