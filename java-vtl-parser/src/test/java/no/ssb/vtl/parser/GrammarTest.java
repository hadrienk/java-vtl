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

import no.ssb.vtl.test.junit.GrammarRule;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Rule;

/**
 * Base class for grammar tests
 */
public abstract class GrammarTest {

    @Rule
    public GrammarRule grammarRule = new GrammarRule();

    protected ParserRuleContext parse(String expr, String rule) throws Exception {
        return grammarRule.parse(expr, grammarRule.withRule(rule));
    }
}
