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

import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class DependencyParser {
    
    public Map<String, Set<ComponentRef>> parse(String vtlExpression) {
        try {
            VTLLexer lexer = new VTLLexer(new ANTLRInputStream(new StringReader(vtlExpression)));
            VTLParser parser = new VTLParser(new CommonTokenStream(lexer));
    
            ParseTreeWalker treeWalker = new ParseTreeWalker();
            AssignmentListener listener = new AssignmentListener();
            treeWalker.walk(listener, parser.start());
            return listener.getVariableDependency();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    
    }
    
}
