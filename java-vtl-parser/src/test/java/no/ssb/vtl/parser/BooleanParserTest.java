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


import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

public class BooleanParserTest extends GrammarTest {

    protected ParserRuleContext parse(String expr) throws Exception {
        return super.parse(expr, "expression");
    }

    @Test
    public void testAlgebra() throws Exception {
        parse("true and false or false and true");
        parse("true and (false or false) and true");
        parse("not true and not (true or false) and not true");
        parse("not ( ( true xor not (true or false) and not true ) )");
    }

    @Test
    public void testEquality() throws Exception {
        parse("dataset.component = component");
        parse("dataset.component <> component");
        parse("dataset.component <= component");
        parse("dataset.component >= component");
        parse("dataset.component < component");
        parse("dataset.component > component");
    }

    @Test
    public void testNot() throws Exception {
        parse("not(true)");
        parse("not(false)");
        parse("not(true and false)");
        parse("not(a <> b or a = b and false)");
    }

    @Test
    public void testIsNull() throws Exception {
        parse("isnull(a)");
        parse("isnull(null)");
        parse("component is not null");
        parse("dataset.component is not null");
        parse("component is null");
        parse("dataset.component is null");
    }
}
