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

public class IfThenElseParserTest extends GrammarTest {

    protected ParserRuleContext parse(String expr) throws Exception {
        return super.parse(expr, "expression");
    }

    @Test
    public void testIfThenElseWithNull() throws Exception {
        parse("if true then null elseif true then 2 else 3");
        parse("if true then 1 elseif true then null else 3");
        parse("if true then 1 elseif true then 2 else null");
    }

    @Test
    public void testIfThenElse() throws Exception {
        parse("if true then 1 elseif true then 2 else 3");
    }

    @Test(expected = Exception.class)
    public void testMissingThen() throws Exception {
        parse("if true elseif true then 2 else 3");
    }

    @Test(expected = Exception.class)
    public void testMissingElse() throws Exception {
        parse("if true then 1 elseif true then 2");
    }
}
