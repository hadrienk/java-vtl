package no.ssb.vtl.script.operations.check;

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

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static no.ssb.vtl.script.operations.check.CheckSingleRuleOperation.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CheckSingleRuleOperationTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testArgumentDataset() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expect(hasProperty("message", containsString("dataset was null")));
        new CheckSingleRuleOperation.Builder(null).build();
    }

    /**
     * NOTE: This is not in the spec for check with single rule, but exists for check with datapoint rulesets.
     * <p>
     * VTL 1.1, line 4799.
     */
    @Test
    public void testArgumentAllAndMeasuresToReturn() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expect(hasProperty("message", containsString("cannot use 'all' with 'measures'")));
        new CheckSingleRuleOperation.Builder(mock(Dataset.class))
                .rowsToReturn(CheckSingleRuleOperation.RowsToReturn.ALL)
                .componentsToReturn(CheckSingleRuleOperation.ComponentsToReturn.MEASURES)
                .build();
    }

    /**
     * The input Dataset must have all Boolean Measure Components.
     * NOTE: The spec is not consistent with regard to parameters and constraints sections (line 4925-4926 and 4949).
     * We decided to allow more than one Boolean Measure Component.
     * <p>
     * VTL 1.1 line 4949.
     */
    @Test
    public void testArgumentDatasetComponentsTooManyBooleans() throws Exception {
        Dataset dataset = mock(Dataset.class);
        when(dataset.getDataStructure()).thenReturn(
                DataStructure.of((s, o) -> null,
                        "id1", Component.Role.IDENTIFIER, String.class,
                        "me1", Component.Role.MEASURE, Boolean.class,
                        "me2", Component.Role.MEASURE, Boolean.class,
                        "at1", Component.Role.ATTRIBUTE, String.class
                )
        );

        new CheckSingleRuleOperation.Builder(dataset)
                .rowsToReturn(CheckSingleRuleOperation.RowsToReturn.VALID)
                .componentsToReturn(CheckSingleRuleOperation.ComponentsToReturn.MEASURES)
                .build();
    }

    @Test
    public void testCheckReturnMeasuresNotValidRows() throws Exception {
        //This data structure is a result of a boolean operation, so it will either have one CONDITION component
        //or more with "_CONDITION" suffix for each component.
        DataStructure dataStructure = DataStructure.of((s, o) -> s,
                "kommune_nr", Component.Role.IDENTIFIER, String.class,
                "code", Component.Role.IDENTIFIER, String.class, //from KLASS
                "measure", Component.Role.MEASURE, String.class, // Some measure.
                "CONDITION", Component.Role.MEASURE, Boolean.class
        );

        Dataset ds = mock(Dataset.class);
        when(ds.getDataStructure()).thenReturn(dataStructure);

        when(ds.getData()).thenReturn(Stream.of(
                tuple(
                        dataStructure.wrap("kommune_nr", "0101"),
                        dataStructure.wrap("code", "0101"),
                        dataStructure.wrap("measure", "measure 0101"),
                        dataStructure.wrap("CONDITION", true)
                ), tuple(
                        dataStructure.wrap("kommune_nr", "9990"),
                        dataStructure.wrap("code", null), //not in the code list, so a null value
                        dataStructure.wrap("measure", "measure 9990"),
                        dataStructure.wrap("CONDITION", false)
                ), tuple(
                        dataStructure.wrap("kommune_nr", "0104"),
                        dataStructure.wrap("code", "0104"),
                        dataStructure.wrap("measure", "measure 0104"),
                        dataStructure.wrap("CONDITION", true)
                )
        ));

        CheckSingleRuleOperation checkOperation = new CheckSingleRuleOperation.Builder(ds)
                .rowsToReturn(CheckSingleRuleOperation.RowsToReturn.NOT_VALID)
                .componentsToReturn(CheckSingleRuleOperation.ComponentsToReturn.MEASURES)
                .build();

        assertThat(checkOperation.getDataStructure().getRoles()).containsExactly(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("code", Component.Role.IDENTIFIER),
                entry("measure", Component.Role.MEASURE),
                entry(ERROR_CODE_LABEL, Component.Role.ATTRIBUTE)
        );

        Stream<DataPoint> stream = checkOperation.getData();
        assertThat(stream).isNotNull();

        List<DataPoint> collect = stream.collect(toList());

        assertThat(collect).hasSize(1);

        DataStructure dStructure = checkOperation.getDataStructure();

        //row 1
        Map<Component, VTLObject> map = dStructure.asMap(collect.get(0));
        assertThat(map.get(dStructure.get("kommune_nr")).get()).isEqualTo("9990");
        assertThat(map.get(dStructure.get("code")).get()).isEqualTo(null);
        assertThat(map.get(dStructure.get("measure")).get()).isEqualTo("measure 9990");
        assertThat(map.get(dStructure.get(ERROR_CODE_LABEL)).get()).isEqualTo(null);

    }

    @Test
    public void testCheckReturnMeasuresValidRows() throws Exception {
        //This data structure is a result of a boolean operation, so it will either has one CONDITION component
        //or more with "_CONDITION" suffix for each component.
        DataStructure dataStructure = DataStructure.of((s, o) -> s,
                "kommune_nr", Component.Role.IDENTIFIER, String.class,
                "code", Component.Role.IDENTIFIER, String.class, //from KLASS
                "measure", Component.Role.MEASURE, String.class,
                "CONDITION", Component.Role.MEASURE, Boolean.class
        );

        Dataset ds = mock(Dataset.class);
        when(ds.getDataStructure()).thenReturn(dataStructure);

        when(ds.getData()).thenReturn(Stream.of(
                tuple(
                        dataStructure.wrap("kommune_nr", "0101"),
                        dataStructure.wrap("code", "0101"),
                        dataStructure.wrap("measure", "measure 0101"),
                        dataStructure.wrap("CONDITION", true)
                ), tuple(
                        dataStructure.wrap("kommune_nr", "9990"),
                        dataStructure.wrap("code", null), //not in the code list, so a null value
                        dataStructure.wrap("measure", "measure 9990"),
                        dataStructure.wrap("CONDITION", false)
                ), tuple(
                        dataStructure.wrap("kommune_nr", "0104"),
                        dataStructure.wrap("code", "0104"),
                        dataStructure.wrap("measure", "measure 0104"),
                        dataStructure.wrap("CONDITION", true)
                )
        ));

        CheckSingleRuleOperation checkOperation = new CheckSingleRuleOperation.Builder(ds)
                .rowsToReturn(CheckSingleRuleOperation.RowsToReturn.VALID)
                .componentsToReturn(CheckSingleRuleOperation.ComponentsToReturn.MEASURES)
                .build();

        assertThat(checkOperation.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("code", Component.Role.IDENTIFIER),
                entry("measure", Component.Role.MEASURE),
                entry(ERROR_CODE_LABEL, Component.Role.ATTRIBUTE)
        );

        Stream<DataPoint> stream = checkOperation.getData();
        assertThat(stream).isNotNull();

        List<DataPoint> collect = stream.collect(toList());

        assertThat(collect).hasSize(2);

        DataStructure dStructure = checkOperation.getDataStructure();

        //row 1
        Map<Component, VTLObject> map = dStructure.asMap(collect.get(0));
        assertThat(map.get(dStructure.get("kommune_nr")).get()).isEqualTo("0101");
        assertThat(map.get(dStructure.get("code")).get()).isEqualTo("0101");
        assertThat(map.get(dStructure.get("measure")).get()).isEqualTo("measure 0101");
        assertThat(map.get(dStructure.get(ERROR_CODE_LABEL)).get()).isEqualTo(null);

        //row 2
        map = dStructure.asMap(collect.get(1));
        assertThat(map.get(dStructure.get("kommune_nr")).get()).isEqualTo("0104");
        assertThat(map.get(dStructure.get("code")).get()).isEqualTo("0104");
        assertThat(map.get(dStructure.get("measure")).get()).isEqualTo("measure 0104");
        assertThat(map.get(dStructure.get(ERROR_CODE_LABEL)).get()).isEqualTo(null);
    }

    @Test
    public void testCheckReturnConditionNotValidRows() throws Exception {
        //This data structure is a result of a boolean operation, so it will either has one CONDITION component
        //or more with "_CONDITION" suffix for each component.
        //No attribute components as the VTL 1.1 does not specify that.
        DataStructure dataStructure = DataStructure.of((s, o) -> s,
                "kommune_nr", Component.Role.IDENTIFIER, String.class,
                "code", Component.Role.IDENTIFIER, String.class, //from KLASS
                "CONDITION_CONDITION", Component.Role.MEASURE, Boolean.class,
                "booleanMeasure_CONDITION", Component.Role.MEASURE, Boolean.class,
                "stringMeasure", Component.Role.MEASURE, String.class
        );

        Dataset ds = mock(Dataset.class);
        when(ds.getDataStructure()).thenReturn(dataStructure);

        when(ds.getData()).thenReturn(Stream.of(
                tuple(
                        dataStructure.wrap("kommune_nr", "0101"),
                        dataStructure.wrap("code", "0101"),
                        dataStructure.wrap("CONDITION_CONDITION", true),
                        dataStructure.wrap("booleanMeasure_CONDITION", true),
                        dataStructure.wrap("stringMeasure", "t1")
                ), tuple(
                        dataStructure.wrap("kommune_nr", "9990"),
                        dataStructure.wrap("code", null),
                        dataStructure.wrap("CONDITION_CONDITION", true),
                        dataStructure.wrap("booleanMeasure_CONDITION", false),
                        dataStructure.wrap("stringMeasure", "t2")
                ), tuple(
                        dataStructure.wrap("kommune_nr", "0104"),
                        dataStructure.wrap("code", null), //not in the code list, so a null value
                        dataStructure.wrap("CONDITION_CONDITION", false),
                        dataStructure.wrap("booleanMeasure_CONDITION", false),
                        dataStructure.wrap("stringMeasure", "t3")
                )
        ));

        CheckSingleRuleOperation checkOperation = new CheckSingleRuleOperation.Builder(ds)
                .rowsToReturn(CheckSingleRuleOperation.RowsToReturn.NOT_VALID)
                .componentsToReturn(CheckSingleRuleOperation.ComponentsToReturn.CONDITION)
                .build();

        assertThat(checkOperation.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("code", Component.Role.IDENTIFIER),
                entry(CONDITION_LABEL, Component.Role.MEASURE),   //new component, result of CONDITION_CONDITION && booleanMeasure_CONDITION
                entry(ERROR_CODE_LABEL, Component.Role.ATTRIBUTE)  //new component
        );

        Stream<DataPoint> stream = checkOperation.getData();
        assertThat(stream).isNotNull();

        List<DataPoint> collect = stream.collect(toList());

        assertThat(collect).hasSize(2);

        DataStructure dStructure = checkOperation.getDataStructure();

        //row 1
        Map<Component, VTLObject> map = dStructure.asMap(collect.get(0));
        assertThat(map.get(dStructure.get("kommune_nr")).get()).isEqualTo("9990");
        assertThat(map.get(dStructure.get("code")).get()).isEqualTo(null);
        assertThat(map.get(dStructure.get(CONDITION_LABEL)).get()).isEqualTo(false);
        assertThat(map.get(dStructure.get(ERROR_CODE_LABEL)).get()).isEqualTo(null);

        //row 2
        map = dStructure.asMap(collect.get(1));
        assertThat(map.get(dStructure.get("kommune_nr")).get()).isEqualTo("0104");
        assertThat(map.get(dStructure.get("code")).get()).isEqualTo(null);
        assertThat(map.get(dStructure.get(CONDITION_LABEL)).get()).isEqualTo(false);
        assertThat(map.get(dStructure.get(ERROR_CODE_LABEL)).get()).isEqualTo(null);

    }

    @Test
    public void testCheckReturnConditionValidRows() throws Exception {
        //This data structure is a result of a boolean operation, so it will either has one CONDITION component
        //or more with "_CONDITION" suffix for each component.
        //No attribute components as the VTL 1.1 does not specify that.
        DataStructure dataStructure = DataStructure.of((s, o) -> s,
                "kommune_nr", Component.Role.IDENTIFIER, String.class,
                "code", Component.Role.IDENTIFIER, String.class, //from KLASS
                "CONDITION_CONDITION", Component.Role.MEASURE, Boolean.class,
                "booleanMeasure_CONDITION", Component.Role.MEASURE, Boolean.class,
                "stringMeasure", Component.Role.MEASURE, String.class
        );

        Dataset ds = mock(Dataset.class);
        when(ds.getDataStructure()).thenReturn(dataStructure);

        when(ds.getData()).thenReturn(Stream.of(
                tuple(
                        dataStructure.wrap("kommune_nr", "0101"),
                        dataStructure.wrap("code", "0101"),
                        dataStructure.wrap("CONDITION_CONDITION", true),
                        dataStructure.wrap("booleanMeasure_CONDITION", true),
                        dataStructure.wrap("stringMeasure", "t1")
                ), tuple(
                        dataStructure.wrap("kommune_nr", "9990"),
                        dataStructure.wrap("code", null),
                        dataStructure.wrap("CONDITION_CONDITION", true),
                        dataStructure.wrap("booleanMeasure_CONDITION", false),
                        dataStructure.wrap("stringMeasure", "t2")
                ), tuple(
                        dataStructure.wrap("kommune_nr", "0104"),
                        dataStructure.wrap("code", null), //not in the code list, so a null value
                        dataStructure.wrap("CONDITION_CONDITION", false),
                        dataStructure.wrap("booleanMeasure_CONDITION", false),
                        dataStructure.wrap("stringMeasure", "t3")
                )
        ));

        CheckSingleRuleOperation checkOperation = new CheckSingleRuleOperation.Builder(ds)
                .rowsToReturn(CheckSingleRuleOperation.RowsToReturn.VALID)
                .componentsToReturn(CheckSingleRuleOperation.ComponentsToReturn.CONDITION)
                .errorCode("error001")
                .errorLevel(10L)
                .build();

        assertThat(checkOperation.getDataStructure().getRoles()).containsExactly(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("code", Component.Role.IDENTIFIER),
                entry(CONDITION_LABEL, Component.Role.MEASURE),    //new component, result of CONDITION_CONDITION && booleanMeasure_CONDITION
                entry(ERROR_CODE_LABEL, Component.Role.ATTRIBUTE), //new component
                entry(ERROR_LEVEL_LABEL, Component.Role.ATTRIBUTE) //new component
        );

        Stream<DataPoint> stream = checkOperation.getData();
        assertThat(stream).isNotNull();

        List<DataPoint> collect = stream.collect(toList());

        assertThat(collect).hasSize(1);

        DataStructure dStructure = checkOperation.getDataStructure();

        //row 1
        Map<Component, VTLObject> map = dStructure.asMap(collect.get(0));
        assertThat(map.get(dStructure.get("kommune_nr")).get()).isEqualTo("0101");
        assertThat(map.get(dStructure.get("code")).get()).isEqualTo("0101");
        assertThat(map.get(dStructure.get(CONDITION_LABEL)).get()).isEqualTo(true);
        assertThat(map.get(dStructure.get(ERROR_CODE_LABEL)).get()).isEqualTo("error001");
        assertThat(map.get(dStructure.get(ERROR_LEVEL_LABEL)).get()).isEqualTo(10L);

    }

    private DataPoint tuple(VTLObject... components) {
        return DataPoint.create(Arrays.asList(components));
    }

}
