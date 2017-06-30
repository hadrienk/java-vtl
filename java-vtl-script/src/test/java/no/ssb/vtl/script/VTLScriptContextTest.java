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
