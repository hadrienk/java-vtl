package kohl.hadrien.vtl.script;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import kohl.hadrien.Dataset;

public class VTLScriptEngineTest {

  ScriptEngine engine = new VTLScriptEngine();

  @Test
  public void testAssignment() throws Exception {

    Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
    Dataset dataset = mock(Dataset.class);

    bindings.put("ds2", dataset);
    engine.eval("ds1 := ds2");

    assertThat(bindings).contains(entry("ds1", dataset));

  }

  @Test
  public void testRename() throws Exception {

    Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
    Dataset dataset = mock(Dataset.class);

    bindings.put("ds2", dataset);
    engine.eval("ds1 := ds2[rename id1 as renamedId1, id2 as renamed");

    Object ds1 = bindings.get("ds1");

    Dataset dataset1 = (Dataset) assertThat(ds1).isInstanceOf(Dataset.class);



  }
}
