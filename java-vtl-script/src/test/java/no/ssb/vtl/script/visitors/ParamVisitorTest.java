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

import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ParamVisitorTest {

    private ParamVisitor visitor;

    private static VTLParser parse(String expression) {
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(expression));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        return parser;
    }

    @Before
    public void setUp() throws Exception {
        visitor = new ParamVisitor(null);
    }

    @Test
    public void visitDoubleConstant() throws Exception {
        VTLParser parser = parse("1.0");

        Object param = visitor.visit(parser.constant());

        assertThat(param instanceof Double);
        assertThat(param).isEqualTo(1.0d);
    }

    @Test
    public void visitDoubleInScientificNotationConstant() throws Exception {
        VTLParser parser = parse("1.234e2");

        Object param = visitor.visit(parser.constant());

        assertThat(param instanceof Double);
        assertThat(param).isEqualTo(1.234e2);
    }
}
