package no.ssb.vtl.script.operations;

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

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import org.assertj.core.api.JUnitSoftAssertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role;

@Deprecated
public class CalcOperationTest {

    private VTLExpression expression;
    private Dataset dataset;

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Before
    public void setUp() throws Exception {
        expression = new VTLExpression() {
            @Override
            public Class<?> getType() {
                return String.class;
            }

            @Override
            public VTLObject apply(DataPoint vtlObjects) {
                return VTLObject.of("changed");
            }
        };

        DataStructure structure = DataStructure.builder()
                .put("id", Role.IDENTIFIER, String.class)
                .put("measure", Role.MEASURE, String.class)
                .put("attr", Role.ATTRIBUTE, String.class).build();

        DataPoint dataPoint = DataPoint.create(3);
        dataPoint.set(0, VTLObject.of("idValue"));
        dataPoint.set(0, VTLObject.of("measureValue"));
        dataPoint.set(0, VTLObject.of("attrValue"));

        dataset = new TestableDataset(
                Collections.singletonList(dataPoint), structure
        );
    }

    // Replacing identifier is not allowed.
    @Test
    public void testIdentifierFails() throws Exception {

        List<CalcOperation> operations = Lists.newArrayList(
            new CalcOperation(dataset, expression, "id", Role.IDENTIFIER, true),
            new CalcOperation(dataset, expression, "id", Role.ATTRIBUTE, true),
            new CalcOperation(dataset, expression, "id", Role.MEASURE, true),
            new CalcOperation(dataset, expression, "id", Role.IDENTIFIER, false),
            new CalcOperation(dataset, expression, "id", Role.ATTRIBUTE, false),
            new CalcOperation(dataset, expression, "id", Role.MEASURE, false)
        );

        for (CalcOperation operation : operations) {
            softly.assertThatThrownBy(operation::getDataStructure)
                    .hasMessageContaining("id");
        }
    }

    // When using implicit the role must be the same if the component is already present.
    @Test
    public void testImplicitFails() throws Exception {

        List<CalcOperation> operations = Lists.newArrayList(
                new CalcOperation(dataset, expression, "measure", Role.MEASURE, true),
                new CalcOperation(dataset, expression, "attr", Role.ATTRIBUTE, true),
                new CalcOperation(dataset, expression, "newMeasure", Role.MEASURE, true),
                new CalcOperation(dataset, expression, "newAttr", Role.ATTRIBUTE, true)
        );

        List<CalcOperation> operationsFailing = Lists.newArrayList(
                new CalcOperation(dataset, expression, "measure", Role.ATTRIBUTE, true),
                new CalcOperation(dataset, expression, "attr", Role.MEASURE, true)
                );

        for (CalcOperation operation : operations) {
            operation.getDataStructure();
        }

        for (CalcOperation operation : operationsFailing) {
            softly.assertThatThrownBy(operation::getDataStructure)
                    .hasMessageStartingWith("the role of the component");
        }
    }

    // Measure and attribute roles can be changed.
    @Test
    public void testChangeRoles() throws Exception {

        CalcOperation operation;

        operation = new CalcOperation(dataset, expression, "measure", Role.ATTRIBUTE, false);

        softly.assertThat(operation.getDataStructure()).containsKey("measure");
        softly.assertThat(operation.getDataStructure().get("measure").getRole()).isEqualTo(Role.ATTRIBUTE);

        operation = new CalcOperation(dataset, expression, "attr", Role.MEASURE, false);

        softly.assertThat(operation.getDataStructure()).containsKey("attr");
        softly.assertThat(operation.getDataStructure().get("attr").getRole()).isEqualTo(Role.MEASURE);

    }

    @Test
    public void testNewRoles() throws Exception {

        CalcOperation operation;

        operation = new CalcOperation(dataset, expression, "newMeasure", Role.MEASURE, false);
        softly.assertThat(operation.getDataStructure()).containsKey("newMeasure");
        softly.assertThat(operation.getDataStructure().get("newMeasure").getRole()).isEqualTo(Role.MEASURE);

        operation = new CalcOperation(dataset, expression, "newAttr", Role.ATTRIBUTE, false);
        softly.assertThat(operation.getDataStructure()).containsKey("newAttr");
        softly.assertThat(operation.getDataStructure().get("newAttr").getRole()).isEqualTo(Role.ATTRIBUTE);

        operation = new CalcOperation(dataset, expression, "newId", Role.IDENTIFIER, false);
        softly.assertThat(operation.getDataStructure()).containsKey("newId");
        softly.assertThat(operation.getDataStructure().get("newId").getRole()).isEqualTo(Role.IDENTIFIER);

    }

    private final class TestableDataset implements Dataset {

        private final List<DataPoint> data;
        private final DataStructure dataStructure;

        protected TestableDataset(List<DataPoint> data, DataStructure dataStructure) {
            this.data = data;
            this.dataStructure = dataStructure;
        }

        @Override
        public Stream<DataPoint> getData() {
            return data.stream();
        }

        @Override
        public Optional<Map<String, Integer>> getDistinctValuesCount() {
            return Optional.empty();
        }

        @Override
        public Optional<Long> getSize() {
            return Optional.of((long) data.size());
        }

        @Override
        public DataStructure getDataStructure() {
            return dataStructure;
        }

    }

}
