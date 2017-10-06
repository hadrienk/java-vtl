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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FunctionCallParserTest extends GrammarTest {

    @Parameter(0)
    public String expression;

    @Parameter(1)
    public String rule;


    @Parameters(name = "{index}: parse \"{0}\" with rule {1}")
    public static Collection<Object[]> functionCalls() {
        return Arrays.asList(new Object[][]{
                {"func()", "functionCall"},
                {"func(param1)", "functionCall"},
                {"func(param1, param2, param3)", "functionCall"},
                {"func(namedParam1: expr)", "functionCall"},
                {"func(namedParam1: expr,namedParam2: expr, namedParam3: expr)", "functionCall"},
                {"func(param1, namedParam1: expr)", "functionCall"},
                {"func(param1, param2, param3, namedParam1: expr,namedParam2: expr, namedParam3: expr)", "functionCall"},

                {"somefunction()", "functionCall"},
                {"one(two(three(four(123, \"string\", 132.0), five(), false), null))", "functionCall"},
                {"variable := aFunction123()", "start"}
        });
    }

    @Test
    public void testFunction() throws Exception {
        parse(expression, rule);
    }
}
