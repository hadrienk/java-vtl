package kohl.hadrien.vtl.script;

import com.google.common.collect.ImmutableList;

import java.io.Reader;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;

/**
 * A VTL {@link ScriptEngine} implementation.
 */
public class VTLScriptEngine extends AbstractScriptEngine {

  private final ImmutableList<DatasetConnector> connectors;

  public VTLScriptEngine(DatasetConnector... connectors) {
    this.connectors = ImmutableList.copyOf(connectors);
  }

  public VTLScriptEngine(Bindings n, DatasetConnector... connectors) {
    super(n);
    this.connectors = ImmutableList.copyOf(connectors);
  }

  @Override
  public Object eval(String script, ScriptContext context) throws ScriptException {
    return null;
  }

  @Override
  public Object eval(Reader reader, ScriptContext context) throws ScriptException {
    return null;
  }

  @Override
  public Bindings createBindings() {
    return null;
  }

  @Override
  public ScriptEngineFactory getFactory() {
    return null;
  }
}
