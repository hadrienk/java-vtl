package kohl.hadrien.vtl.script;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.Reader;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import kohl.hadrien.VTLLexer;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.connector.Connector;
import kohl.hadrien.vtl.script.visitors.AssignmentVisitor;

/**
 * A VTL {@link ScriptEngine} implementation.
 */
public class VTLScriptEngine extends AbstractScriptEngine {

  private final ImmutableList<Connector> connectors;

  /**
   * Create a new engine instance.
   * @param connectors connectors that will be used to retrieve data.
   */
  public VTLScriptEngine(Connector... connectors) {
    this.connectors = ImmutableList.copyOf(connectors);

  }

  /**
   * Create a new engine instance.
   * @param n the bindings to use.
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
    assignmentVisitor.visit(start);

    return null;
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
