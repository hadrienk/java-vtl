package no.ssb.vtl.script.visitors.join;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.VTLScriptEngine;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JoinFilterClauseVisitorTest {
    
    private Dataset dataset = mock(Dataset.class);
    private Connector connector = mock(Connector.class);
    private ScriptEngine engine = new VTLScriptEngine(connector);
    private Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
    
    @Test
    public void testSimpleBooleanFilter() throws Exception {
    
        Dataset ds1 = mock(Dataset.class);
        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "id1", Component.Role.IDENTIFIER, String.class,
                "m1", Component.Role.MEASURE, Integer.class
        );
        when(ds1.getDataStructure()).thenReturn(structure1);
    
        when(ds1.get()).then(invocation -> Stream.of(
                structure1.wrap(ImmutableMap.of(
                        "id1", "1",
                        "m1", 10
                )),
                structure1.wrap(ImmutableMap.of(
                        "id1", "2",
                        "m1", 100
                ))
        ));
    
        bindings.put("ds1", ds1);
        
        
        engine.eval("" +
                "ds3 := [ds1]{" +
                "  filter id1 = \"1\" and m1 > 9" +
                "}" +
                "");
    
    
        assertThat(bindings).containsKey("ds3");
        assertThat(bindings.get("ds3")).isInstanceOf(Dataset.class);
        Dataset ds3 = (Dataset) bindings.get("ds3");
        
        assertThat(ds3.get())
                .flatExtracting(input -> input)
                .extracting(DataPoint::get)
                .containsExactly(
                        "1", 10
                );
    }
}