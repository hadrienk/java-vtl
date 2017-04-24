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
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.junit.Test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role;
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
                "m1", Role.MEASURE, Long.class,
                "m2", Role.MEASURE, Double.class,
                "at1", Role.MEASURE, String.class
        );
        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "id2", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Long.class,
                "m2", Role.MEASURE, Double.class,
                "at2", Role.MEASURE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(structure1);
        when(ds2.getDataStructure()).thenReturn(structure2);

        when(ds1.getData()).then(invocation -> Stream.of(
                structure1.wrap(ImmutableMap.of(
                        "id1", "1",
                        "id2", "1",
                        "m1", 10L,
                        "m2", 20,
                        "at1", "attr1-1"
                )),
                structure1.wrap(ImmutableMap.of(
                        "id1", "2",
                        "id2", "2",
                        "m1", 100L,
                        "m2", 200,
                        "at1", "attr1-2"
                ))
        ));
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());

        when(ds2.getData()).then(invocation -> Stream.of(
                structure2.wrap(ImmutableMap.of(
                        "id1", "1",
                        "id2", "1",
                        "m1", 30L,
                        "m2", 40,
                        "at2", "attr2-1"
                )),
                structure2.wrap(ImmutableMap.of(
                        "id1", "2",
                        "id2", "2",
                        "m1", 300L,
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
                "  ident := ds1.m1 + ds2.m2 - ds1.m2 - ds2.m1," +            // id1, id2, ds1.m1, ds1.m2, d2.m1, d2.m2, at1, at2, ident
                "  keep ident, ds1.m1, ds2.m1, ds2.m2," +                   // id1, id2, ds1.m1, ds2.m1, ds2.m2, ident
                "  boolTest := (ds1.m1 = 10)," +
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

        assertThat(ds3.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "1", "1", 10L, 40, 0L, true
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
                Arrays.asList("1", 101L, 102L, 103L),
                Arrays.asList("2", 201L, 202L, 203L),
                Arrays.asList("3", 301L, 302L, 303L)
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
                "  total := ds1.m1 + ds1.m2 + ds1.m3," +
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
                        "1", "m1", 101L,
                        "1", "m2", 102L,
                        "1", "m3", 103L,
                        "1", "total", 101L + 102L + 103L,

                        "2", "m1", 201L,
                        "2", "m2", 202L,
                        "2", "m3", 203L,
                        "2", "total", 201L + 202L + 203L,

                        "3", "m1", 301L,
                        "3", "m2", 302L,
                        "3", "m3", 303L,
                        "3", "total", 301L + 302L + 303L
                );
    }

    @Test
    public void testJoinUnfold() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        DataStructure ds = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "id2", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Long.class,
                "m2", Role.MEASURE, Double.class,
                "at1", Role.MEASURE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(ds);
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());
        when(ds1.getData()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "id1", "1",
                        "id2", "one",
                        "m1", 101L,
                        "m2", 1.1,
                        "at1", "attr1"
                ),
                ImmutableMap.of(
                        "id1", "1",
                        "id2", "two",
                        "m1", 102L,
                        "m2", 1.1,
                        "at1", "attr2"
                ),
                ImmutableMap.of(
                        "id1", "2",
                        "id2", "one",
                        "m1", 201L,
                        "m2", 1.1,
                        "at1", "attr2"
                ), ImmutableMap.of(
                        "id1", "2",
                        "id2", "two",
                        "m1", 202L,
                        "m2", 1.1,
                        "at1", "attr2"
                )
        ).map(ds::wrap));

        bindings.put("ds1", ds1);
        engine.eval("ds2 := [ds1] {" +
                "  unfold id2, ds1.m1 to \"one\", \"two\"," +
                "  onePlusTwo := one + two" +
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
                        "1", 101L, 102L, 101L + 102L,
                        "2", 201L, 202L, 201L + 202L
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
                + "                [rename id1 as id1m role MEASURE,"
                + "                        me1 as me1a role ATTRIBUTE,"
                + "                        at1 as at1i role IDENTIFIER]");

        assertThat(bindings).containsKey("ds2");
        Dataset result = (Dataset) bindings.get("ds2");

        assertThat(result.getDataStructure().getRoles()).contains(
                entry("id1m", Role.MEASURE),
                entry("me1a", Role.ATTRIBUTE),
                entry("at1i", Role.IDENTIFIER)
        );
    }

    @Test
    public void testCheckSingleRule() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        Dataset dsCodeList2 = mock(Dataset.class);
        Dataset dsCodeList3 = mock(Dataset.class);

        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "kommune_nr", Role.IDENTIFIER, String.class,
                "periode", Role.IDENTIFIER, String.class,
                "kostragruppe", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Long.class,
                "at1", Role.ATTRIBUTE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(structure1);
        when(ds1.getData()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "kommune_nr", "0101",
                        "periode", "2015",
                        "kostragruppe", "EKG14",
                        "m1", 100L,
                        "at1", "attr1"
                ),
                ImmutableMap.of(
                        "kommune_nr", "0101",
                        "periode", "2015",
                        "kostragruppe", "EKG15",
                        "m1", 110L,
                        "at1", "attr4"
                ),
                ImmutableMap.of(
                        "kommune_nr", "0111",
                        "periode", "2014",
                        "kostragruppe", "EKG14",
                        "m1", 101L,
                        "at1", "attr2"
                ),
                ImmutableMap.of(
                        "kommune_nr", "9000",
                        "periode", "2014",
                        "kostragruppe", "EKG14",
                        "m1", 102L,
                        "at1", "attr3"
                )
        ).map(structure1::wrap));
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());

        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "code", Role.IDENTIFIER, String.class,
                "name", Role.MEASURE, String.class,
                "period", Role.IDENTIFIER, String.class
        );
        when(dsCodeList2.getDataStructure()).thenReturn(structure2);

        when(dsCodeList2.getData()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "code", "0101",
                        "name", "Halden 2010-2013",
                        "period", "2010"
                ),
                ImmutableMap.of(
                        "code", "0101",
                        "name", "Halden 2010-2013",
                        "period", "2011"
                ),
                ImmutableMap.of(
                        "code", "0101",
                        "name", "Halden 2010-2013",
                        "period", "2012"
                ),
                ImmutableMap.of(
                        "code", "0101",
                        "name", "Halden",
                        "period", "2013"
                ),
                ImmutableMap.of(
                        "code", "0101",
                        "name", "Halden",
                        "period", "2014"
                ),
                ImmutableMap.of(
                        "code", "0101",
                        "name", "Halden",
                        "period", "2015"
                ),
                ImmutableMap.of(
                        "code", "0101",
                        "name", "Halden",
                        "period", "2016"
                ),
                ImmutableMap.of(
                        "code", "0101",
                        "name", "Halden",
                        "period", "2017"
                ),
                ImmutableMap.of(
                        "code", "0111",
                        "name", "Hvaler",
                        "period", "2015"
                ),
                ImmutableMap.of(
                        "code", "0111",
                        "name", "Hvaler",
                        "period", "2016"
                ),
                ImmutableMap.of(
                        "code", "0111",
                        "name", "Hvaler",
                        "period", "2017"
                ),
                ImmutableMap.of(
                        "code", "1001",
                        "name", "Kristiansand",
                        "period", "2013"
                ),
                ImmutableMap.of(
                        "code", "1001",
                        "name", "Kristiansand",
                        "period", "2014"
                ),
                ImmutableMap.of(
                        "code", "1001",
                        "name", "Kristiansand",
                        "period", "2015"
                )
        ).map(structure2::wrap));
        when(dsCodeList2.getData(any(Order.class))).thenReturn(Optional.empty());

        DataStructure structure3 = DataStructure.of(
                (o, aClass) -> o,
                "code", Role.IDENTIFIER, String.class,
                "name", Role.MEASURE, String.class,
                "period", Role.IDENTIFIER, String.class
        );
        when(dsCodeList3.getDataStructure()).thenReturn(structure3);

        when(dsCodeList3.getData()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "code", "EKG14",
                        "name", "Bergen, Trondheim og Stavanger",
                        "period", "2010"
                ),
                ImmutableMap.of(
                        "code", "EKG14",
                        "name", "Bergen, Trondheim og Stavanger",
                        "period", "2011"
                ),
                ImmutableMap.of(
                        "code", "EKG14",
                        "name", "Bergen, Trondheim og Stavanger",
                        "period", "2012"
                ),
                ImmutableMap.of(
                        "code", "EKG14",
                        "name", "Bergen, Trondheim og Stavanger",
                        "period", "2013"
                ),
                ImmutableMap.of(
                        "code", "EKG14",
                        "name", "Bergen, Trondheim og Stavanger",
                        "period", "2014"
                ),
                ImmutableMap.of(
                        "code", "EKG14",
                        "name", "Bergen, Trondheim og Stavanger",
                        "period", "2015"
                ),
                ImmutableMap.of(
                        "code", "EKG14",
                        "name", "Bergen, Trondheim og Stavanger",
                        "period", "2016"
                ),
                ImmutableMap.of(
                        "code", "EKG14",
                        "name", "Bergen, Trondheim og Stavanger",
                        "period", "2017"
                ),
                ImmutableMap.of(
                        "code", "EKG15",
                        "name", "Oslo kommune",
                        "period", "2016"
                ),
                ImmutableMap.of(
                        "code", "EKG15",
                        "name", "Oslo kommune",
                        "period", "2017"
                )
        ).map(structure2::wrap));
        when(dsCodeList2.getData(any(Order.class))).thenReturn(Optional.empty());

        bindings.put("ds1", ds1);
        bindings.put("ds2", dsCodeList2);
        bindings.put("ds3", dsCodeList3);

        VTLPrintStream out = new VTLPrintStream(System.out);
        engine.eval("" +
                "ds2r := ds2[rename code as kommune_nr, period as periode]" +
                "dsBoolean0 := [outer ds1, ds2r]{" +
                "   ds2_CONDITION := name is not null," +
                "   rename name to ds2_name," +
                "   kommune_nr_RESULTAT := ds2_CONDITION" +
                "}"+
                "ds3r := ds3[rename code as kostragruppe, period as periode]" +
                "dsBoolean1 := [outer ds1, ds3r]{" +
                "   ds3_CONDITION := name is not null," +
                "   rename name to ds3_name," +
                "   kostragruppe_RESULTAT := ds3_CONDITION" +
                "}"+
                "dsBoolean3 := [dsBoolean0, dsBoolean1]{" +
                "   filter true" +
                "}" +
                "ds4invalid := check(dsBoolean3, not_valid, measures, errorcode(\"TEST_ERROR_CODE\"))" +
                "ds4valid   := check(dsBoolean3, valid, measures)"
        );

//        out.println(bindings.get("dsBoolean0"));
//        out.println(bindings.get("dsBoolean1"));
//        out.println(bindings.get("dsBoolean3"));
        out.println(bindings.get("ds4invalid"));

        assertThat(bindings).containsKey("ds4invalid");
        assertThat(bindings).containsKey("ds4valid");

        Dataset ds3invalid = (Dataset) bindings.get("ds4invalid");
        Dataset ds3valid = (Dataset) bindings.get("ds4valid");

        //not checking for other measures and attributes since they are
        //not necessary for validation
        assertThat(ds3invalid.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("periode", Component.Role.IDENTIFIER),
                entry("kostragruppe", Component.Role.IDENTIFIER),
                entry("errorcode", Component.Role.ATTRIBUTE),
                entry("dsBoolean0_kommune_nr_RESULTAT", Component.Role.MEASURE),
                entry("dsBoolean1_kostragruppe_RESULTAT", Component.Role.MEASURE)
        );

        assertThat(ds3valid.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("periode", Component.Role.IDENTIFIER),
                entry("kostragruppe", Component.Role.IDENTIFIER),
                entry("errorcode", Component.Role.ATTRIBUTE),
                entry("dsBoolean0_kommune_nr_RESULTAT", Component.Role.MEASURE),
                entry("dsBoolean1_kostragruppe_RESULTAT", Component.Role.MEASURE)
        );

        // Should only contain the "non valid" rows.
        DataStructure ds3InvalidDataStruct = ds3invalid.getDataStructure();
        List<DataPoint> ds3InvalidDataPoints = ds3invalid.getData().collect(Collectors.toList());

        assertThat(ds3InvalidDataPoints).hasSize(3);

        //using for loop as data points come in random order
        Map<Component, VTLObject> map;
        for (DataPoint dp : ds3InvalidDataPoints) {
            map = ds3InvalidDataStruct.asMap(dp);

            if (map.get(ds3InvalidDataStruct.get("kommune_nr")).get().equals("0101")) {
                assertThat(map.get(ds3InvalidDataStruct.get("kommune_nr")).get()).isEqualTo("0101");
                assertThat(map.get(ds3InvalidDataStruct.get("periode")).get()).isEqualTo("2015");
                assertThat(map.get(ds3InvalidDataStruct.get("kostragruppe")).get()).isEqualTo("EKG15");
                assertThat(map.get(ds3InvalidDataStruct.get("errorcode")).get()).isEqualTo("TEST_ERROR_CODE");
                assertThat(map.get(ds3InvalidDataStruct.get("dsBoolean0_kommune_nr_RESULTAT")).get()).isEqualTo(true);
                assertThat(map.get(ds3InvalidDataStruct.get("dsBoolean1_kostragruppe_RESULTAT")).get()).isEqualTo(false);
            } else if (map.get(ds3InvalidDataStruct.get("kommune_nr")).get().equals("9000")) {
                assertThat(map.get(ds3InvalidDataStruct.get("kommune_nr")).get()).isEqualTo("9000");
                assertThat(map.get(ds3InvalidDataStruct.get("periode")).get()).isEqualTo("2014");
                assertThat(map.get(ds3InvalidDataStruct.get("kostragruppe")).get()).isEqualTo("EKG14");
                assertThat(map.get(ds3InvalidDataStruct.get("errorcode")).get()).isEqualTo("TEST_ERROR_CODE");
                assertThat(map.get(ds3InvalidDataStruct.get("dsBoolean0_kommune_nr_RESULTAT")).get()).isEqualTo(false);
                assertThat(map.get(ds3InvalidDataStruct.get("dsBoolean1_kostragruppe_RESULTAT")).get()).isEqualTo(true);
            } else if (map.get(ds3InvalidDataStruct.get("kommune_nr")).get().equals("0111")) {
                assertThat(map.get(ds3InvalidDataStruct.get("kommune_nr")).get()).isEqualTo("0111");
                assertThat(map.get(ds3InvalidDataStruct.get("periode")).get()).isEqualTo("2014");
                assertThat(map.get(ds3InvalidDataStruct.get("kostragruppe")).get()).isEqualTo("EKG14");
                assertThat(map.get(ds3InvalidDataStruct.get("errorcode")).get()).isEqualTo("TEST_ERROR_CODE");
                assertThat(map.get(ds3InvalidDataStruct.get("dsBoolean0_kommune_nr_RESULTAT")).get()).isEqualTo(false);
                assertThat(map.get(ds3InvalidDataStruct.get("dsBoolean1_kostragruppe_RESULTAT")).get()).isEqualTo(true);
            }
        }

        // Should only contain the "valid" rows.
        DataStructure ds3ValidDataStruct = ds3valid.getDataStructure();
        List<DataPoint> ds3ValidDataPoints = ds3valid.getData().collect(Collectors.toList());

        assertThat(ds3ValidDataPoints).hasSize(1);

        map = ds3ValidDataStruct.asMap(ds3ValidDataPoints.get(0));
        assertThat(map.get(ds3ValidDataStruct.get("kommune_nr")).get()).isEqualTo("0101");
        assertThat(map.get(ds3ValidDataStruct.get("periode")).get()).isEqualTo("2015");
        assertThat(map.get(ds3ValidDataStruct.get("kostragruppe")).get()).isEqualTo("EKG14");
        assertThat(map.get(ds3ValidDataStruct.get("errorcode")).get()).isNull();
        assertThat(map.get(ds3ValidDataStruct.get("dsBoolean0_kommune_nr_RESULTAT")).get()).isEqualTo(true);
        assertThat(map.get(ds3ValidDataStruct.get("dsBoolean1_kostragruppe_RESULTAT")).get()).isEqualTo(true);
    }

    @Test
    public void testNvlAsClause() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        DataStructure ds = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Long.class,
                "m2", Role.MEASURE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(ds);
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());
        when(ds1.getData()).then(invocation -> Stream.of(
                tuple(
                        ds.wrap("id1", "1"),
                        ds.wrap("m1", 1L),
                        ds.wrap("m2", null)
                ),
                tuple(
                        ds.wrap("id1", "2"),
                        ds.wrap("m1", null),
                        ds.wrap("m2", "str2")
                ),
                tuple(
                        ds.wrap("id1", "3"),
                        ds.wrap("m1", null),
                        ds.wrap("m2", null)
                )
        ));

        bindings.put("ds1", ds1);
        engine.eval("ds2 := [ds1] {" +
                "   m11 := nvl(m1 , 0), " +
                "   m22 := nvl(ds1.m2, \"constant\"), " +
                "   drop m1, m2 " +
                "}"
        );

        assertThat(bindings).containsKey("ds2");
        Dataset ds2 = (Dataset) bindings.get("ds2");

        assertThat(ds2.getDataStructure().getRoles()).containsOnly(
                entry("id1", Role.IDENTIFIER),
                entry("m11", Role.MEASURE),
                entry("m22", Role.MEASURE)
        );

        assertThat(ds2.getDataStructure().getTypes()).containsOnly(
                entry("id1", String.class),
                entry("m11", Long.class),
                entry("m22", String.class)
        );

        assertThat(ds2.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "1", 1L, "constant",
                        "2", 0L, "str2",
                        "3", 0L, "constant"
                );
    }

    @Test(expected = ScriptException.class)
    public void testNvlAsClauseNotEqualTypes() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        DataStructure ds = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Long.class
        );
        when(ds1.getDataStructure()).thenReturn(ds);
        when(ds1.getData()).then(invocation -> Stream.of(
                tuple(
                        ds.wrap("id1", "1"),
                        ds.wrap("m1", null)
                )
        ));

        bindings.put("ds1", ds1);
        engine.eval("ds2 := [ds1] {" +
                "   m11 := nvl(m1 , \"constant\") " +
                "}"
        );
    }

    @Test
    public void testDateFromStringAsClause() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        DataStructure ds = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(ds);
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());
        when(ds1.getData()).then(invocation -> Stream.of(
                tuple(
                        ds.wrap("id1", "1"),
                        ds.wrap("m1", "2017")
                ),
                tuple(
                        ds.wrap("id1", "2"),
                        ds.wrap("m1", null)
                )
        ));

        bindings.put("ds1", ds1);
        engine.eval("ds2 := [ds1] {" +
                "   m11 := date_from_string(m1, \"YYYY\"), " +
                "   drop m1 " +
                "}"
        );

        assertThat(bindings).containsKey("ds2");
        Dataset ds2 = (Dataset) bindings.get("ds2");

        assertThat(ds2.getDataStructure().getRoles()).containsOnly(
                entry("id1", Role.IDENTIFIER),
                entry("m11", Role.MEASURE)
        );

        assertThat(ds2.getDataStructure().getTypes()).containsOnly(
                entry("id1", String.class),
                entry("m11", Instant.class)
        );

        assertThat(ds2.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "1", ZonedDateTime.of(2017,1,1,0,0,0,0, ZoneId.systemDefault()).toInstant(),
                        "2", null
                );

    }

    @Test(expected = ScriptException.class)
    public void testDateFromStringAsClauseUnsupportedFormat() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        DataStructure ds = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(ds);

        bindings.put("ds1", ds1);
        engine.eval("ds2 := [ds1] {" +
                "   m11 := date_from_string(m1, \"YYYYSN\") " +
                "}"
        );

    }

    @Test(expected = ScriptException.class)
    public void testDateFromStringAsClauseInputNotStringType() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        DataStructure ds = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Long.class
        );
        when(ds1.getDataStructure()).thenReturn(ds);

        bindings.put("ds1", ds1);
        engine.eval("ds2 := [ds1] {" +
                "   m11 := date_from_string(m1, \"YYYY\") " +
                "}"
        );

    }
    
    @Test
    public void testAggregationSumGroupBy() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        DataStructure structure = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, Long.class,
                "id2", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Long.class,
                "m2", Role.MEASURE, Double.class,
                "at1", Role.ATTRIBUTE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(structure);
    
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());
        when(ds1.getData()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "id1", 1L,
                        "id2", "one",
                        "m1", 101L,
                        "m2", 1.1,
                        "at1", "attr1"
                ),
                ImmutableMap.of(
                        "id1", 1L,
                        "id2", "two",
                        "m1", 102L,
                        "m2", 1.1,
                        "at1", "attr2"
                ),
                ImmutableMap.of(
                        "id1", 2L,
                        "id2", "one",
                        "m1", 201L,
                        "m2", 1.1,
                        "at1", "attr2"
                ), ImmutableMap.of(
                        "id1", 2L,
                        "id2", "two",
                        "m1", 202L,
                        "m2", 1.1,
                        "at1", "attr2"
                )
        ).map(structure::wrap));
    
        bindings.put("ds1", ds1);
        engine.eval("ds2 := sum(ds1.m1) group by id1");
    
        assertThat(bindings).containsKey("ds2");
        Dataset ds2 = (Dataset) bindings.get("ds2");
    
        assertThat(ds2.getDataStructure().getRoles()).containsOnly(
                entry("id1", Role.IDENTIFIER),
                entry("m1", Role.MEASURE)
        );
    
        assertThat(ds2.getDataStructure().getTypes()).containsOnly(
                entry("id1", Long.class),
                entry("m1", Long.class)
        );
    
        assertThat(ds2.getData()).flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        1L, 101L + 102L,
                        2L, 201L + 202L
                );
    }
    
    @Test
    public void testAggregationSumAlong() throws Exception {
        Dataset ds1 = mock(Dataset.class);
        DataStructure structure = DataStructure.of(
                (o, aClass) -> o,
                "id1", Role.IDENTIFIER, Long.class,
                "id2", Role.IDENTIFIER, String.class,
                "m1", Role.MEASURE, Long.class,
                "at1", Role.ATTRIBUTE, String.class
        );
        when(ds1.getDataStructure()).thenReturn(structure);
        
        when(ds1.getData(any(Order.class))).thenReturn(Optional.empty());
        when(ds1.getData()).then(invocation -> Stream.of(
                (Map) ImmutableMap.of(
                        "id1", 1L,
                        "id2", "one",
                        "m1", 101L,
                        "at1", "attr1"
                ),
                ImmutableMap.of(
                        "id1", 1L,
                        "id2", "two",
                        "m1", 102L,
                        "at1", "attr2"
                ),
                ImmutableMap.of(
                        "id1", 2L,
                        "id2", "one",
                        "m1", 201L,
                        "at1", "attr2"
                ), ImmutableMap.of(
                        "id1", 2L,
                        "id2", "two",
                        "m1", 202L,
                        "at1", "attr2"
                )
        ).map(structure::wrap));
        
        bindings.put("ds1", ds1);
        engine.eval("ds2 := sum(ds1) along id2");
        
        assertThat(bindings).containsKey("ds2");
        Dataset ds2 = (Dataset) bindings.get("ds2");
        
        assertThat(ds2.getDataStructure().getRoles()).containsOnly(
                entry("id1", Role.IDENTIFIER),
                entry("m1", Role.MEASURE)
        );
        
        assertThat(ds2.getDataStructure().getTypes()).containsOnly(
                entry("id1", Long.class),
                entry("m1", Long.class)
        );
        
        assertThat(ds2.getData()).flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        1L, 101L + 102L,
                        2L, 201L + 202L
                );
    }
    
    private DataPoint tuple(VTLObject... components) {
        return DataPoint.create(Arrays.asList(components));
    }
}
