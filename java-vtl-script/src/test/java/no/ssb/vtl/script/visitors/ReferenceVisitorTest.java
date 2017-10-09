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
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.util.Maps;
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
        DataStructure structure = DataStructure.of(
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
    public void testNotFound() throws Exception {
        Bindings emptyBindings = new SimpleBindings(
                Maps.newHashMap("dataset", dataset));
        ReferenceVisitor referenceVisitor = new ReferenceVisitor(emptyBindings);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThatThrownBy(() -> referenceVisitor.visit(parse("componentNotFound").componentRef()))
                    .describedAs("exception when component not found")
                    .hasMessageContaining("variable")
                    .hasMessageContaining("componentNotFound")
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> referenceVisitor.visit(parse("dataset.componentNotFound").componentRef()))
                    .describedAs("exception when component not found")
                    .hasMessageContaining("variable")
                    .hasMessageContaining("componentNotFound")
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> referenceVisitor.visit(parse("datasetNotFound").datasetRef()))
                    .describedAs("exception when component not found")
                    .hasMessageContaining("variable")
                    .hasMessageContaining("datasetNotFound")
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> referenceVisitor.visit(parse("variableNotFound").variable()))
                    .describedAs("exception when component not found")
                    .hasMessageContaining("variable")
                    .hasMessageContaining("variableNotFound")
                    .hasMessageContaining("not found");

        }
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
                softly.assertThat(referenceVisitor.visit(parser.componentRef()))
                        .describedAs("resolved componentRef for [%s]", componentExpression)
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
                softly.assertThat(referenceVisitor.visit(parser.datasetRef()))
                        .describedAs("resolved datasetRef for [%s]", datasetExpression)
                        .isNotNull()
                        .isSameAs(dataset);
            }
        }
    }
}
