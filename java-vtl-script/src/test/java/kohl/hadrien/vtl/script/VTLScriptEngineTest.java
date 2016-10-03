package kohl.hadrien.vtl.script;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;

import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import kohl.hadrien.Attribute;
import kohl.hadrien.Component;
import kohl.hadrien.DataStructure;
import kohl.hadrien.Dataset;
import kohl.hadrien.Identifier;
import kohl.hadrien.Measure;
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

    when(dataset.getDataStructure()).thenReturn(
        new DataStructure(ImmutableMap.<String, Class<? extends Component>>builder()
            .put("id1", Identifier.class)
            .put("me1", Measure.class)
            .put("at1", Attribute.class)
            .build())
    );

    bindings.put("ds1", dataset);
    engine.eval("ds2 := ds1[rename id1 as id3]"
        + "                [rename id3 as id1]"
        + "                [rename id1 as id1m role = MEASURE,"
        + "                        me1 as me1a role = ATTRIBUTE,"
        + "                        at1 as at1i role = IDENTIFIER]");

    assertThat(bindings).containsKey("ds2");
    Dataset result = (Dataset) bindings.get("ds2");

    assertThat(result.getDataStructure()).contains(
        entry("id1m", Measure.class),
        entry("me1a", Attribute.class),
        entry("at1i", Identifier.class)
    );

  }
}
