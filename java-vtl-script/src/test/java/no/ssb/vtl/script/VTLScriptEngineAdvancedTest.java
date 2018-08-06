package no.ssb.vtl.script;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * Copyright (C) 2018 Pawel Buczek
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

import com.google.common.base.Stopwatch;
import no.ssb.vtl.connectors.Connector;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.StaticDataset;
import no.ssb.vtl.model.VTLObject;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VTLScriptEngineAdvancedTest {

    private Connector connector = mock(Connector.class);
    private ScriptEngine engine = new VTLScriptEngine(connector);
    private Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);

    @Test
    public void testManyOrsBools() throws Exception {
        runExpressionLoop("true","or");
    }

    @Test
    public void testManyAndBools() throws Exception {
        runExpressionLoop("true", "and");
    }

    @Test
    public void testManyXorBools() throws Exception {
        runExpressionLoop("true", "xor");
    }

    @Test
    public void testManyAdd() throws Exception {
        runExpressionLoop("1", "+");
    }

    @Test
    public void testManySubtract() throws Exception {
        runExpressionLoop("1", "-");
    }

    @Test
    public void testManyDivide() throws Exception {
        runExpressionLoop("2", "/");
    }

    @Test
    public void testManyMultiply() throws Exception {
        runExpressionLoop("2", "*");
    }

    @Test
    public void testManyConcat() throws Exception {
        runExpressionLoop("\"AA\"", "||");
    }

    private void runExpressionLoop(String literal, String operator) throws ScriptException {
        String expr = String.format("test := %s %s %s", literal, operator, literal);
        VTLScriptEngine engine = new VTLScriptEngine();
        for (int i = 0; i < 100; i++) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            engine.eval(expr);
            stopwatch.stop(); // optional
            System.out.printf("# of " + operator + "s: %d, time: %s\n", i + 1, stopwatch);
            assertThat(stopwatch.elapsed(TimeUnit.MILLISECONDS)).isLessThan(2000);
            expr = String.format(" %s %s %s ", expr, operator, literal);
        }
    }


    @Test
    public void testUnionThenSumAlong() throws Exception {
        Dataset ds1 = StaticDataset.create()
                .addComponent("id1", Component.Role.IDENTIFIER, String.class)
                .addComponent("id2", Component.Role.IDENTIFIER, String.class)
                .addComponent("id3", Component.Role.IDENTIFIER, String.class)
                .addComponent("m1", Component.Role.MEASURE, Long.class)

                .addPoints("2016", "A", "001", 1L)
                .addPoints("2016", "A", "002", 2L)
                .build();

        Dataset ds2 = StaticDataset.create()
                .addComponent("id1", Component.Role.IDENTIFIER, String.class)
                .addComponent("id2", Component.Role.IDENTIFIER, String.class)
                .addComponent("id3", Component.Role.IDENTIFIER, String.class)
                .addComponent("m1", Component.Role.MEASURE, Long.class)

                .addPoints("2016", "A", "003", 3L)
                .addPoints("2016", "B", "004", 4L)
                .build();


        bindings.put("ds1", ds1);
        bindings.put("ds2", ds2);

        engine.eval("" +
                "ds3 := union(ds1, ds2)" +
                "ds4 := sum(ds3) along id3"
        );


        assertThat(bindings).containsKey("ds4");
        assertThat(bindings.get("ds4")).isInstanceOf(Dataset.class);

        Dataset ds4 = (Dataset) bindings.get("ds4");
        assertThat(ds4.getDataStructure())
                .describedAs("data structure of d4")
                .containsOnlyKeys(
                        "id1",
                        "id2",
                        "m1"
                );

        assertThat(ds4.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "2016", "A", 6L,
                        "2016", "B", 4L
                );
    }

    @Test
    public void testSumAlongThenUnion() throws Exception {
        Dataset ds1 = StaticDataset.create()
                .addComponent("id1", Component.Role.IDENTIFIER, String.class)
                .addComponent("id2", Component.Role.IDENTIFIER, String.class)
                .addComponent("id3", Component.Role.IDENTIFIER, String.class)
                .addComponent("m1", Component.Role.MEASURE, Long.class)

                .addPoints("2016", "A", "002", 2L)
                .addPoints("2016", "A", "001", 1L)
                .build();

        Dataset ds2 = StaticDataset.create()
                .addComponent("id1", Component.Role.IDENTIFIER, String.class)
                .addComponent("id2", Component.Role.IDENTIFIER, String.class)
                .addComponent("m1", Component.Role.MEASURE, Long.class)

                .addPoints("2016", "B", 4L)
                .build();


        bindings.put("ds1", ds1);
        bindings.put("ds2", ds2);

        engine.eval("" +
                "ds3 := sum(ds1) along id3 " +
                "ds4 := union(ds3, ds2)"
        );


        assertThat(bindings).containsKey("ds4");
        assertThat(bindings.get("ds4")).isInstanceOf(Dataset.class);

        Dataset ds4 = (Dataset) bindings.get("ds4");
        assertThat(ds4.getDataStructure())
                .describedAs("data structure of d4")
                .containsOnlyKeys(
                        "id1",
                        "id2",
                        "m1"
                );

        assertThat(ds4.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "2016", "A", 3L,
                        "2016", "B", 4L
                );
    }

}
