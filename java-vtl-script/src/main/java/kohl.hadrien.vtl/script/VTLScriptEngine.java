package kohl.hadrien.vtl.script;

/*-
 * #%L
 * java-vtl-script
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import kohl.hadrien.VTLLexer;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.connector.Connector;
import kohl.hadrien.vtl.script.visitors.AssignmentVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.script.*;
import java.io.IOException;
import java.io.Reader;

/**
 * A VTL {@link ScriptEngine} implementation.
 */
public class VTLScriptEngine extends AbstractScriptEngine {

    private final ImmutableList<Connector> connectors;

    /**
     * Create a new engine instance.
     *
     * @param connectors connectors that will be used to retrieve data.
     */
    public VTLScriptEngine(Connector... connectors) {
        this.connectors = ImmutableList.copyOf(connectors);

    }

    /**
     * Create a new engine instance.
     *
     * @param n          the bindings to use.
     * @param connectors connectors that will be used to retrieve data.
     */
    public VTLScriptEngine(Bindings n, Connector... connectors) {
        super(n);
        this.connectors = ImmutableList.copyOf(connectors);
    }

    @Override
    public Object eval(String script, ScriptContext context) throws ScriptException {
        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(script));
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));

        ParseTree start = parser.start();
        // Run loop.
        AssignmentVisitor assignmentVisitor = new AssignmentVisitor(context, connectors);
        return assignmentVisitor.visit(start);
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        try {
            VTLLexer lexer = new VTLLexer(new ANTLRInputStream(reader));
            return null;
        } catch (IOException ioe) {
            throw new ScriptException(ioe);
        }
    }

    @Override
    public Bindings createBindings() {
        return new SimpleBindings(Maps.newConcurrentMap());
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return null;
    }
}
