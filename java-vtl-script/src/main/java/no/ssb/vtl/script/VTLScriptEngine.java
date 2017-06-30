package no.ssb.vtl.script;

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
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.error.SyntaxException;
import no.ssb.vtl.script.error.WrappedException;
import no.ssb.vtl.script.visitors.AssignmentVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import javax.script.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.TimeZone;

/**
 * A VTL {@link ScriptEngine} implementation.
 */
public class VTLScriptEngine extends AbstractScriptEngine {

    private TimeZone timeZone = TimeZone.getDefault();
    private final ImmutableList<Connector> connectors;

    /**
     * Create a new engine instance.
     *
     * @param connectors connectors that will be used to retrieve data.
     */
    public VTLScriptEngine(Connector... connectors) {
        this.connectors = ImmutableList.copyOf(connectors);
        context = new VTLScriptContext();
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
        context = new VTLScriptContext();
    }

    /**
     * Returns the default time zone of the JVM.
     * //TODO use non-static method to be able to set up time zone per VTLScriptEngine instance. Need to also expose in Visitors
     *
     * @return the default time zone of the JVM.
     */
    public static TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    /**
     * Sets a different global timezone.
     *
     * The engine will use a different global time zone in situations no implicit time
     * zone is defined in date conversion operations. For example:
     * <pre>
     *     osloTz = TimeZone.getTimeZone("Europe/Oslo");
     *     vtlEngine.setTimeZone(osloTz);
     *
     *     // date will be 1999-12-31T23:00:00.000Z
     *     vtlEngine.eval("date := date_from_string(\"2000\", \"YYYY\")");
     *
     * </pre>
     * @param tz the time zone
     */
    public void setTimeZone(TimeZone tz) {
        timeZone = tz;
    }
    @Override
    public Object eval(String script, ScriptContext context) throws ScriptException {
        return eval(new StringReader(script), context);
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        /*
            Until compilation is done, this is the main method that allows
            VTL execution.
            Exceptions' hierarchy is as follow:
                - ScriptException
                    - CompilationException
                        - SyntaxException
                        - TypeException
                        - ConstraintException
                    - ValidationException
                    - VTLRuntimeException
            The WrappedScriptException is used to report errors that are not originating
            from the VTL Parser.
         */
        try {
            VTLLexer lexer = new VTLLexer(new ANTLRInputStream(reader));
            VTLParser parser = new VTLParser(new CommonTokenStream(lexer));

            lexer.removeErrorListeners();
            parser.removeErrorListeners();

            BaseErrorListener errorListener = new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int column, String msg, RecognitionException e) {
                    WrappedException wrappedException;
                    if (e instanceof WrappedException) {
                        wrappedException = (WrappedException) e;
                    } else {
                         wrappedException = new WrappedException(
                                 msg,
                                 recognizer,
                                 e != null ? e.getInputStream() : null,
                                 e != null ? (ParserRuleContext) e.getCtx() : null,
                                 new SyntaxException(
                                         msg, null, line, column, "VTL-0199"
                                 )
                        );
                    }
                    wrappedException.setLine(line);
                    wrappedException.setColumn(column);
                    throw new ParseCancellationException(msg, wrappedException);
                }
            };
            lexer.addErrorListener(errorListener);
            parser.addErrorListener(errorListener);

            VTLParser.StartContext start = parser.start();

            // Run loop.
            AssignmentVisitor assignmentVisitor = new AssignmentVisitor(context, connectors);
            Dataset last = null;
            for (VTLParser.AssignmentContext assignmentContext : start.assignment()) {
                last = assignmentVisitor.visit(assignmentContext);
            }
            return last;

        } catch (ParseCancellationException pce) {
            if (pce.getCause() instanceof WrappedException) {
                WrappedException cause = (WrappedException) pce.getCause();

                if (cause.getCause() instanceof ScriptException) {
                    throw ((ScriptException) cause.getCause());
                } else {
                    throw new ScriptException(
                            pce.getMessage(),
                            null,
                            cause.getLineNumber(),
                            cause.getColumnNumber()
                    );
                }

            } else {
                if (pce.getCause() != null) {
                    throw new ScriptException(pce.getCause().getMessage());
                } else {
                    throw new ScriptException(pce.getMessage());
                }
            }
        } catch (IOException | RuntimeException ioe) {
            throw new ScriptException(ioe);
        }
    }

    @Override
    public Bindings createBindings() {
        return new SimpleBindings(Maps.newLinkedHashMap());
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return null;
    }
}
