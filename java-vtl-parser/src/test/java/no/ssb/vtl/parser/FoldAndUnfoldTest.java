package no.ssb.vtl.parser;

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

import org.junit.Test;

public class FoldAndUnfoldTest extends GrammarTest {

    @Test
    public void testJoinFold() throws Exception {
        // Test different writing forms of component
        parse("fold dataset.component, dataset.component to newIdentifier, newComponent", "joinFoldExpression");
        parse("fold dataset.'component', dataset.'component' to newIdentifier, newComponent", "joinFoldExpression");
        parse("fold 'dataset'.'component', 'dataset'.'component' to newIdentifier, newComponent", "joinFoldExpression");
        parse("fold 'dataset'.component, 'dataset'.component to newIdentifier, newComponent", "joinFoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingElementsFold() throws Exception {
        parse("fold to dataset.component, component", "joinFoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingMeasureFold() throws Exception {
        parse("fold \"varID1\" to component", "joinFoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingDimensionFold() throws Exception {
        parse("fold \"varID1\" to ,component", "joinFoldExpression");
    }

    @Test
    public void testJoinUnfold() throws Exception {

            parse("unfold dataset.component, component to \"varID1\", \"varID2\"", "joinUnfoldExpression");
            parse("unfold component, dataset.component to \"varID1\"", "joinUnfoldExpression");
            parse("unfold dataset.component, dataset.component to \"varID1\"", "joinUnfoldExpression");
            parse("unfold component, component to \"varID1\"", "joinUnfoldExpression");
    }

    @Test(expected = Exception.class)
    public void testInvalidMissingElementsJoinUnfold() throws Exception {
        parse("unfold dataset.component, component to ", "joinUnfoldExpression");
    }

    @Test(expected = Exception.class)
        public void testTooManyComponentsJoinUnfold() throws Exception {
        parse("unfold dataset.component, component, component to \"varID1\"", "joinUnfoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingMeasureJoinUnfold() throws Exception {
        parse("unfold component to \"varID1\"", "joinUnfoldExpression");
    }

    @Test(expected = Exception.class)
    public void testMissingIdentifierInvalidJoinUnfold() throws Exception {
        parse("unfold ,component to \"varID1\"", "joinUnfoldExpression");
    }
}
