package no.ssb.vtl.script;

import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;

import static org.assertj.core.api.Assertions.*;


public class VTLScriptContextTest {

    private VTLScriptContext vtlScriptContext = new VTLScriptContext();;

    @Test
    public void testEngineScopeOrderOfAttributes() throws Exception {
        vtlScriptContext.setAttribute("b", "bValue", ScriptContext.ENGINE_SCOPE);
        vtlScriptContext.setAttribute("a", "aValue", ScriptContext.ENGINE_SCOPE);

        Bindings bindingsEngineScope = vtlScriptContext.getBindings(ScriptContext.ENGINE_SCOPE);
        assertThat(bindingsEngineScope).containsExactly(
                entry("b", "bValue"),
                entry("a", "aValue")
        );
    }

    @Test
    public void testGlobalScopeOrderOfAttributes() throws Exception {
        vtlScriptContext.setAttribute("b", "bValue", ScriptContext.GLOBAL_SCOPE);
        vtlScriptContext.setAttribute("a", "aValue", ScriptContext.GLOBAL_SCOPE);

        Bindings bindingsEngineScope = vtlScriptContext.getBindings(ScriptContext.GLOBAL_SCOPE);
        assertThat(bindingsEngineScope).containsExactly(
                entry("b", "bValue"),
                entry("a", "aValue")
        );
    }

}