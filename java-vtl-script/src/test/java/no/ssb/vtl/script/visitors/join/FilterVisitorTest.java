package no.ssb.vtl.script.visitors.join;

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

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.connectors.Connector;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.VTLScriptEngine;
import org.junit.Before;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterVisitorTest {
    
    private Dataset dataset = mock(Dataset.class);
    private Connector connector = mock(Connector.class);
    private ScriptEngine engine = new VTLScriptEngine(connector);
    private Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
    private Dataset ds1;
    private Dataset ds2;
    
    @Before
    public void setUp() throws Exception {
        ds1 = mock(Dataset.class);
        DataStructure structure1 = DataStructure.of(
                "id1", Component.Role.IDENTIFIER, String.class,
                "m1", Component.Role.MEASURE, Long.class
        );
        when(ds1.getDataStructure()).thenReturn(structure1);

        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());
        when(ds1.getData()).then(invocation -> Stream.of(
                structure1.wrap(ImmutableMap.of(
                        "id1", "1",
                        "m1", 10L
                )),
                structure1.wrap(ImmutableMap.of(
                        "id1", "2",
                        "m1", 100L
                ))
        ));
        
        
        ds2 = mock(Dataset.class);
        DataStructure structure2 = DataStructure.of(
                "id1", Component.Role.IDENTIFIER, String.class,
                "m1", Component.Role.MEASURE, Long.class,
                "m2", Component.Role.MEASURE, Long.class,
                "a1", Component.Role.ATTRIBUTE, String.class
        );
        when(ds2.getDataStructure()).thenReturn(structure2);

        when(ds2.getData(any(Order.class))).thenReturn(Optional.empty());
        when(ds2.getData()).then(invocation -> Stream.of(
                structure2.wrap(ImmutableMap.of(
                        "id1", "1",
                        "m1", 10L,
                        "m2", 10L,
                        "a1", "test"
                )),
                structure2.wrap(ImmutableMap.of(
                        "id1", "2",
                        "m1", 100L,
                        "m2", 10L,
                        "a1", "2"
                ))
        ));
    }
    
    @Test
    public void testSimpleBooleanFilter() throws Exception {
        bindings.put("ds1", ds1);
        
        
        engine.eval("" +
                "ds3 := [ds1]{" +
                "  filter id1 = \"1\" and m1 > 9" +
                "}" +
                "");
    
    
        assertThat(bindings).containsKey("ds3");
        assertThat(bindings.get("ds3")).isInstanceOf(Dataset.class);
        Dataset ds3 = (Dataset) bindings.get("ds3");
        
        assertThat(ds3.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "1", 10L
                );
    }
    
    
    @Test
    public void testBooleanComponents() throws Exception {
        bindings.put("ds2", ds2);
    
    
        engine.eval("" +
                "ds3 := [ds2]{" +
                "  filter id1 = a1 or m1 > m2" +
                "}" +
                "");
    
    
        assertThat(bindings).containsKey("ds3");
        assertThat(bindings.get("ds3")).isInstanceOf(Dataset.class);
        Dataset ds3 = (Dataset) bindings.get("ds3");
    
        assertThat(ds3.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "2", 100L, 10L, "2"
                );
        
    }
}
