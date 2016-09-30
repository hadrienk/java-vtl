package kohl.hadrien.vtl.script;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import kohl.hadrien.Dataset;
import kohl.hadrien.vtl.script.connector.Connector;

public class VTLScriptEngineTest {

  private Dataset dataset = mock(Dataset.class);
  private Connector connector = mock(Connector.class);
  private ScriptEngine engine = new VTLScriptEngine(connector);
  private Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);

  @Test
  public void testAssignment() throws Exception {

    bindings.put("ds1", dataset);
    engine.eval("ds2 := ds1");

    assertThat(bindings).contains(entry("ds2", dataset));

  }

  @Test
  public void testGet() throws Exception {

    when(connector.canHandle(anyString())).thenReturn(true);
    when(connector.getDataset(anyString())).thenReturn(dataset);

    bindings.put("ds1", dataset);
    engine.eval("ds2 := get(todo)");

    assertThat(bindings).contains(entry("ds2", dataset));

  }

  @Test
  public void testPut() throws Exception {

    when(connector.canHandle(anyString())).thenReturn(true);
    when(connector.putDataset(anyString(), any())).thenReturn(dataset);
    engine.eval("ds1 := put(todo)");

    assertThat(bindings).contains(entry("ds1", dataset));

  }

  @Test
  public void testRename() throws Exception {

    bindings.put("ds1", dataset);
    engine.eval("ds2 := ds1[rename var1 as var2]"
        + "                [rename var2 as var1]"
        + "                [rename var1 as var2 role = IDENTIFIER,"
        + "                        var1 as var2 role = MEASURE,"
        + "                        var1 as var2 role = ATTRIBUTE]");

    assertThat(bindings).contains(entry("ds2", dataset));

  }
}
