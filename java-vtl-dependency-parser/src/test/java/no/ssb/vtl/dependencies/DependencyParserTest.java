package no.ssb.vtl.dependencies;

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

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class DependencyParserTest {
    
    private DependencyParser parser = new DependencyParser();
    
    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();
    
    @Test
    public void joinCalc() throws Exception {
        String vtlExpression = "r := [t1, t2]{ var := t1.var1 / t2.var2, otherVar := t1.var3 * 1000}";
        Map<String, Assignment> dependencies = parser.parse(vtlExpression);
    
        System.out.println(dependencies);
        softly.assertThat(dependencies.get("var").getComponentRefs())
                .extracting("datasetId", "variableId")
                .containsExactlyInAnyOrder(tuple("t1", "var1"), tuple("t2", "var2"));
        softly.assertThat(dependencies.get("otherVar").getComponentRefs())
                .extracting("datasetId", "variableId")
                .containsExactlyInAnyOrder(tuple("t1", "var3"));
    }
    
    @Test
    public void joinRename() throws Exception {
        String vtlExpression = "r := [t1, t2]{ rename t1.var1 to var }";
        Map<String, Assignment> dependencies = parser.parse(vtlExpression);
        
        System.out.println(dependencies);
        softly.assertThat(dependencies.get("var").getComponentRefs())
                .extracting("datasetId", "variableId")
                .containsExactlyInAnyOrder(tuple("t1", "var1"));
    }
    
    @Test
    public void joinUnfold() throws Exception {
        String vtlExpression = "r := [t1, t2]{ unfold t1.id1, t1.m1 to \"val1\", \"val2\" }";
        Map<String, Assignment> dependencies = parser.parse(vtlExpression);
        
        System.out.println(dependencies);
        softly.assertThat(dependencies.get("\"val1\"").getComponentRefs())
                .extracting("datasetId", "variableId").as("dependencies of val1")
                .containsExactlyInAnyOrder(tuple("t1", "m1"));
    
        softly.assertThat(dependencies.get("\"val2\"").getComponentRefs())
                .extracting("datasetId", "variableId").as("dependencies of val2")
                .containsExactlyInAnyOrder(tuple("t1", "m1"));
    }
    
    @Test
    public void joinFold() throws Exception {
        String vtlExpression = "r := [t1, t2]{ fold t1.val1, t1.val2 to id1, m1 }";
        Map<String, Assignment> dependencies = parser.parse(vtlExpression);
        
        System.out.println(dependencies);
        softly.assertThat(dependencies.get("m1").getComponentRefs())
                .extracting("datasetId", "variableId").as("dependencies of m1")
                .containsExactlyInAnyOrder(tuple("t1", "val1"), tuple("t1", "val2"));
        
    }
    
}