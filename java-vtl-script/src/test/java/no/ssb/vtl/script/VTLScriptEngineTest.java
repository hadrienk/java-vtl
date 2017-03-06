package no.ssb.vtl.script;

/*-
 * #%L
 * java-vtl-script
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import java.time.Instant;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role;
import static no.ssb.vtl.test.ComponentConditions.componentWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        engine.eval("ds2 := get(\"todo\")");

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
    public void testJoin() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        Dataset ds2 = mock(Dataset.class);

        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "id2", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Integer.class,
                "m2", Role.MEASURE, Double.class,
                "at1", Role.MEASURE, String.class
        );
        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "id2", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Integer.class,
                "m2", Role.MEASURE, Double.class,
                "at2", Role.MEASURE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(structure1);
        when(ds2.getDataStructure()).thenReturn(structure2);

        when(ds1.getData()).then(invocation -> Stream.of(
                structure1.wrap(ImmutableMap.of(
                        "id1", "1",
                        "id2", "1",
                        "m1", 10,
                        "m2", 20,
                        "at1", "attr1-1"
                )),
                structure1.wrap(ImmutableMap.of(
                        "id1", "2",
                        "id2", "2",
                        "m1", 100,
                        "m2", 200,
                        "at1", "attr1-2"
                ))
        ));
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());

        when(ds2.getData()).then(invocation -> Stream.of(
                structure2.wrap(ImmutableMap.of(
                        "id1", "1",
                        "id2", "1",
                        "m1", 30,
                        "m2", 40,
                        "at2", "attr2-1"
                )),
                structure2.wrap(ImmutableMap.of(
                        "id1", "2",
                        "id2", "2",
                        "m1", 300,
                        "m2", 400,
                        "at2", "attr2-2"
                ))
        ));
        when(ds2.getData(any(Order.class))).thenReturn(Optional.empty());

        bindings.put("ds1", ds1);
        bindings.put("ds2", ds2);

        engine.eval("" +
                "ds3 := [ds1, ds2]{" +                                      // id1, id2, ds1.m1, ds1.m2, d2.m1, d2.m2, at1, at2
                "  filter id1 = \"1\" and m1 = 30 or m1 = 10," +            //TODO: precedence
                "  ident = ds1.m1 + ds2.m2 - ds1.m2 - ds2.m1," +            // id1, id2, ds1.m1, ds1.m2, d2.m1, d2.m2, at1, at2, ident
                "  keep ident, ds1.m1, ds2.m1, ds2.m2," +                   // id1, id2, ds1.m1, ds2.m1, ds2.m2, ident
                "  boolTest = (ds1.m1 = 10)," +
                "  drop ds2.m1," +                                          // id1, id2, ds1.m1, ds2.m2, ident
                "  rename id1 to renamedId1, ds1.m1 to m1, ds2.m2 to m2" +  // renamedId1, id2, m1, m2, ident
                "}" +
                "");

        assertThat(bindings).containsKey("ds3");
        assertThat(bindings.get("ds3")).isInstanceOf(Dataset.class);

        Dataset ds3 = (Dataset) bindings.get("ds3");
        assertThat(ds3.getDataStructure())
                .describedAs("data structure of d3")
                .containsOnlyKeys(
                "renamedId1",
                "id2",
                "m2",
                "m1",
                "ident",
                "boolTest"
        );

        assertThat(ds3.getDataStructure().values())
                .haveAtLeastOne(componentWith("renamedId1", Role.IDENTIFIER))
                .haveAtLeastOne(componentWith("id2", Role.IDENTIFIER))
                .haveAtLeastOne(componentWith("m2", Role.MEASURE))
                .haveAtLeastOne(componentWith("m1", Role.MEASURE))
                .haveAtLeastOne(componentWith("ident", Role.MEASURE))
                .haveAtLeastOne(componentWith("boolTest", Role.MEASURE));


        assertThat(ds3.getData())
                .flatExtracting(input -> input)
                .extracting((vtlObject) -> vtlObject.getComponent().getName())
                .containsExactly(
                        "renamedId1", "id2", "m1", "m2", "ident", "boolTest"
                );

        assertThat(ds3.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "1", "1", 10, 40, 0, true
                );
    }

    @Test
    public void testJoinFold() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        DataStructure ds = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Number.class,
                "m2", Role.MEASURE, Number.class,
                "m3", Role.MEASURE, Number.class
        );
        when(ds1.getDataStructure()).thenReturn(ds);
        when(ds1.getData()).then(invocation -> Stream.of(
                Arrays.asList("1", 101, 102, 103),
                Arrays.asList("2", 201, 202, 203),
                Arrays.asList("3", 301, 302, 303)
        ).map(list -> {
            Iterator<?> it = list.iterator();
            List<VTLObject> points = Lists.newArrayList();
            for (String name : ds.keySet()) {
                Object value = it.hasNext() ? it.next() : null;
                points.add(ds.wrap(name, value));
            }
            return DataPoint.create(points);
        }));
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());

        bindings.put("ds1", ds1);
        engine.eval("ds2 := [ds1] {" +
                "  total = ds1.m1 + ds1.m2 + ds1.m3," +
                "  fold ds1.m1, ds1.m2, ds1.m3, total to type, value" +
                "}"
        );

        assertThat(bindings).containsKey("ds2");
        Dataset ds2 = (Dataset) bindings.get("ds2");

        assertThat(ds2.getDataStructure().getRoles()).containsOnly(
                entry("id1", Role.IDENTIFIER),
                entry("type", Role.IDENTIFIER),
                entry("value", Role.MEASURE)
        );

        assertThat(ds2.getData()).flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "1", "m1", 101,
                        "1", "m2", 102,
                        "1", "m3", 103,
                        "1", "total", 101 + 102 + 103,

                        "2", "m1", 201,
                        "2", "m2", 202,
                        "2", "m3", 203,
                        "2", "total", 201 + 202 + 203,

                        "3", "m1", 301,
                        "3", "m2", 302,
                        "3", "m3", 303,
                        "3", "total", 301 + 302 + 303
                );
    }

    @Test
    public void testJoinUnfold() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        DataStructure ds = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "id2", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Integer.class,
                "m2", Role.MEASURE, Double.class,
                "at1", Role.MEASURE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(ds);
        when(ds1.getData()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "id1", "1",
                        "id2", "one",
                        "m1", 101,
                        "m2", 1.1,
                        "at1", "attr1"
                ),
                ImmutableMap.of(
                        "id1", "1",
                        "id2", "two",
                        "m1", 102,
                        "m2", 1.1,
                        "at1", "attr2"
                ),
                ImmutableMap.of(
                        "id1", "2",
                        "id2", "one",
                        "m1", 201,
                        "m2", 1.1,
                        "at1", "attr2"
                ), ImmutableMap.of(
                        "id1", "2",
                        "id2", "two",
                        "m1", 202,
                        "m2", 1.1,
                        "at1", "attr2"
                )
        ).map(ds::wrap));

        bindings.put("ds1", ds1);
        engine.eval("ds2 := [ds1] {" +
                "  unfold id2, ds1.m1 to \"one\", \"two\"," +
                "  onePlusTwo = one + two" +
                "}"
        );

        assertThat(bindings).containsKey("ds2");
        Dataset ds2 = (Dataset) bindings.get("ds2");

        assertThat(ds2.getDataStructure().getRoles()).containsOnly(
                entry("id1", Role.IDENTIFIER),
                entry("one", Role.MEASURE),
                entry("two", Role.MEASURE),
                entry("onePlusTwo", Role.MEASURE)
        );

        assertThat(ds2.getData()).flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "1", 101, 102, 101 + 102,
                        "2", 201, 202, 201 + 202
                );
    }

    @Test
    public void testRename() throws Exception {

        when(dataset.getDataStructure()).thenReturn(
                DataStructure.of((s, o) -> null,
                        "id1", Role.IDENTIFIER, String.class,
                        "me1", Role.MEASURE, String.class,
                        "at1", Role.ATTRIBUTE, String.class
                )
        );

        bindings.put("ds1", dataset);
        engine.eval("ds2 := ds1[rename id1 as id3]"
                + "            [rename id3 as id1]"
                + "                [rename id1 as id1m role = MEASURE,"
                + "                        me1 as me1a role = ATTRIBUTE,"
                + "                        at1 as at1i role = IDENTIFIER]");

        assertThat(bindings).containsKey("ds2");
        Dataset result = (Dataset) bindings.get("ds2");

        assertThat(result.getDataStructure().getRoles()).contains(
                entry("id1m", Role.MEASURE),
                entry("me1a", Role.ATTRIBUTE),
                entry("at1i", Role.IDENTIFIER)
        );
    }

    //TODO temporary
//    @Test
    public void testCheckSingleRule() throws Exception {

        when(dataset.getDataStructure()).thenReturn(
                DataStructure.of((s, o) -> null,
                        "kommune_nr", Role.IDENTIFIER, String.class,
                        "code", Role.IDENTIFIER, String.class, //from KLASS
                        "CONDITION", Role.MEASURE, Boolean.class
                )
        );

        bindings.put("ds1", dataset);
        engine.eval("ds2 := check (ds1)");

        assertThat(bindings).containsKey("ds2");
        Dataset result = (Dataset) bindings.get("ds2");

        assertThat(result.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("code", Component.Role.IDENTIFIER),
                entry("CONDITION", Component.Role.MEASURE),
                entry("errorcode", Component.Role.ATTRIBUTE),
                entry("errorlevel", Component.Role.ATTRIBUTE)
        );
    }

    //TODO temporary
//    @Test
    public void testCheckSingleRuleWithJoin() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        Dataset dsCodeList2 = mock(Dataset.class);

        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "kommune_nr", Role.IDENTIFIER, String.class,
                "periode", Role.IDENTIFIER, Instant.class, //TODO String?
                "m1", Role.MEASURE, Integer.class,
                "at1", Role.ATTRIBUTE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(structure1);
        when(ds1.getData()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "kommune_nr", "0101",
                        "periode", Instant.parse("2015-01-01T00:00:00.00Z"),
                        "m1", 100,
                        "at1", "attr1"
                ),
                ImmutableMap.of(
                        "kommune_nr", "0111",
                        "periode", Instant.parse("2014-01-01T00:00:00.00Z"),
                        "m1", 101,
                        "at1", "attr2"
                ),
                ImmutableMap.of(
                        "kommune_nr", "9000",
                        "periode", Instant.parse("2014-01-01T00:00:00.00Z"),
                        "m1", 102,
                        "at1", "attr3"
                )
        ).map(structure1::wrap));

        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "code", Role.IDENTIFIER, String.class,
                "name", Role.MEASURE, String.class,
                "validFrom", Role.IDENTIFIER, Instant.class,
                "validTo", Role.IDENTIFIER, Instant.class
        );
        when(dsCodeList2.getDataStructure()).thenReturn(structure2);
        when(dsCodeList2.getData()).then(invocation -> Stream.of(
                tuple(
                        structure2.wrap("code", "0101"),
                        structure2.wrap("name", "Halden"),
                        structure2.wrap("validFrom",  Instant.parse("2013-01-01T00:00:00.00Z")),
                        structure2.wrap("validTo", null)
                ),
                tuple(
                        structure2.wrap("code", "0111"),
                        structure2.wrap("name", "Hvaler"),
                        structure2.wrap("validFrom", Instant.parse("2015-01-01T00:00:00.00Z")),
                        structure2.wrap("validTo", null)
                ),
                tuple(
                        structure2.wrap("code", "1001"),
                        structure2.wrap("name", "Kristiansand"),
                        structure2.wrap("validFrom", Instant.parse("2013-01-01T00:00:00.00Z")),
                        structure2.wrap("validTo", Instant.parse("2015-01-01T00:00:00.00Z"))
                )
        ));

        bindings.put("ds1", ds1);
        bindings.put("ds2", dsCodeList2);

        VTLPrintStream out = new VTLPrintStream(System.out);
        engine.eval("" +
                        "ds2r := ds2[rename code as kommune_nr]" +
//                        "'inner' := [ds1, ds2r] {" +
//                        "   type = 1" +
//                        "}" +
                        "dsBoolean := [outer ds1, ds2r]{" +
                        "   filter periode <> null," +
                        "   CONDITION = (validFrom <> null " +
                        "       and validFrom <= periode " +
                        "       and validTo > periode)" +
                        "}"+
                        "ds3 := check(dsBoolean, not_valid, measures)"
        );
//        out.println(bindings.get("ds2r"));
//        out.println(bindings.get("'inner'"));

//        engine.eval("" +
//                "ds2r := ds2[rename code as kommune_nr]" +
//                "dsBoolean := [outer ds1, ds2r]{" +           // kommune_nr, periode, ds1_m1, ds2r_name, validFrom, validTo
//                "    filter periode <> null, " +              // same structure, filter out codes that exist only in code list, but not in ds1
//                "    CONDITION = (ds2r_name <> null " +       // kommune_nr, periode, ds1_m1, ds2r_name, validFrom, validTo, CONDITION
//                "       and validFrom <= periode " +
//                "       and validTo > periode)" +
//                "       and validFrom <= date_from_string(periode, \"YYYY\") " +
//                "       and validTo > date_from_string(periode, \"YYYY\"))" +
//                "}" +
//                "ds3 := check(dsBoolean, not_valid, measures)");


        assertThat(bindings).containsKey("ds3");
        out.println(bindings.get("dsBoolean"));
        out.println(bindings.get("ds3"));
        Dataset ds3 = (Dataset) bindings.get("ds3");

        assertThat(ds3.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("periode", Component.Role.IDENTIFIER),
                entry("ds1_m1", Component.Role.MEASURE),
                entry("ds1_at1", Component.Role.ATTRIBUTE),
                entry("ds2r_name", Component.Role.MEASURE),
                entry("validFrom", Component.Role.IDENTIFIER),
                entry("validTo", Component.Role.IDENTIFIER),
                entry("CONDITION", Component.Role.MEASURE),
                entry("errorcode", Component.Role.ATTRIBUTE)
        );

        assertThat(ds3.getData()).flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "0101", "2015", 100, "attr1", "Halden", Instant.parse("2013-01-01T00:00:00.00Z"), null, true,
                        "0111", "2014", 101, "attr2", "Hvaler", Instant.parse("2015-01-01T00:00:00.00Z"), null, false,
                        "9000", "2014", 102, "attr3", null,     null                                    , null, false
                );
    }

    private DataPoint tuple(VTLObject... components) {
        return DataPoint.create(Arrays.asList(components));
    }
}
