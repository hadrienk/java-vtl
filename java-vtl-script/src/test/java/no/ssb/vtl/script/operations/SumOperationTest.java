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

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.StaticDataset;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.operations.join.InnerJoinOperation;
import org.assertj.core.api.SoftAssertions;

import java.util.Arrays;
import java.util.Map;

import static no.ssb.vtl.model.Component.Role;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hadrien on 21/11/2016.
 */
public class SumOperationTest {

    /**
     * Both Datasets must have at least one Identifier Component
     * in common (with the same name and data type).
     * <p>
     * VTL 1.1 line 2499.
     */
    //@Test
    public void testIdenfitierNotASubset() throws Exception {

        Dataset left = mock(Dataset.class);
        Dataset right = mock(Dataset.class);

        SoftAssertions softly = new SoftAssertions();
        try {
            when(left.getDataStructure()).thenReturn(DataStructure.of(
                    "ID1", Role.IDENTIFIER, String.class,
                    "ID2", Role.IDENTIFIER, String.class,
                    "ME1", Role.MEASURE, Long.class
            ));
            Throwable expectedThrowable = null;

            // Different name
            when(right.getDataStructure()).thenReturn(DataStructure.of(
                    "ID1DIFFERENTNAME", Role.IDENTIFIER, String.class,
                    "ID2", Role.IDENTIFIER, String.class,
                    "ME1", Role.MEASURE, Long.class
            ));
            expectedThrowable = null;
            try {
                new SumOperation(tuple -> null, left.getDataStructure(), tuple -> null, right.getDataStructure());
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common id name")
                    .isNotNull()
                    .hasMessageContaining("ID1DIFFERENTNAME");

            // any order
            expectedThrowable = null;
            try {
                new SumOperation(tuple -> null, right.getDataStructure(), tuple -> null, left.getDataStructure());
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common id name (reversed)")
                    .isNotNull()
                    .hasMessageContaining("ID1DIFFERENTNAME");

            // Different type
            when(left.getDataStructure()).thenReturn(DataStructure.of(
                    "ID1", Role.IDENTIFIER, String.class,
                    "ID2", Role.IDENTIFIER, String.class,
                    "ME1", Role.MEASURE, Long.class
            ));
            expectedThrowable = null;
            try {
                new SumOperation(tuple -> null, left.getDataStructure(), tuple -> null, right.getDataStructure());
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common id type")
                    .isNotNull();

            // any order.
            expectedThrowable = null;
            try {
                new SumOperation(tuple -> null, right.getDataStructure(), tuple -> null, left.getDataStructure());
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common id type (reversed)")
                    .isNotNull();

        } finally {
            softly.assertAll();
        }
    }

    /**
     * If both ds_1 and ds_2 are Datasets then either they have
     * one or more measures in common, or at least one of them
     * has only a measure.
     * <p>
     * VTL 1.1 line 2501.
     */
    //@Test
    public void testNoCommonMeasure() throws Exception {

        Dataset left = mock(Dataset.class);
        Dataset right = mock(Dataset.class);

        SoftAssertions softly = new SoftAssertions();
        try {
            when(left.getDataStructure()).thenReturn(DataStructure.of(
                    "ID1", Role.IDENTIFIER, String.class,
                    "ID2", Role.IDENTIFIER, String.class,
                    "ME1", Role.MEASURE, Long.class
            ));
            Throwable expectedThrowable = null;

            // Different measure
            when(right.getDataStructure()).thenReturn(DataStructure.of(
                    "ID1", Role.IDENTIFIER, String.class,
                    "ID2", Role.IDENTIFIER, String.class,
                    "ME1NOTSAMEMEASURE", Role.MEASURE, Long.class
            ));
            expectedThrowable = null;
            try {
                new SumOperation(tuple -> null, left.getDataStructure(), tuple -> null, right.getDataStructure());
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common measure name")
                    .isNotNull()
                    .hasMessageContaining("ME1NOTSAMEMEASURE");

            // any order
            expectedThrowable = null;
            try {
                new SumOperation(tuple -> null, right.getDataStructure(), tuple -> null, left.getDataStructure());
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common measure name (reversed)")
                    .isNotNull();

        } finally {
            softly.assertAll();
        }
    }

    /**
     * Test the example 1
     * <p>
     * VTL 1.1 line 2522-2536.
     *
     * @throws Exception
     */
    //@Test()
    public void testSumEx1() throws Exception {

        StaticDataset left = StaticDataset.create()

                .addComponent("TIME", Role.IDENTIFIER, String.class)
                .addComponent("GEO", Role.IDENTIFIER, String.class)
                .addComponent("POPULATION", Role.MEASURE, Long.class)

                .addPoints("2013", "Belgium", 5L)
                .addPoints("2013","Denmark", 2L)
                .addPoints("2013", "France", 3L)
                .addPoints("2013", "Spain", 4L)
                .build();

        StaticDataset right = StaticDataset.create()

                .addComponent("TIME", Role.IDENTIFIER, String.class)
                .addComponent("GEO", Role.IDENTIFIER, String.class)
                .addComponent( "AGE", Role.IDENTIFIER, String.class)
                .addComponent("POPULATION", Role.MEASURE, Long.class)

                .addPoints("2013", "Belgium", "Total", 10L)
                .addPoints("2013", "Greece", "Total", 11L)
                .addPoints("2013", "Belgium", "Y15-24", null)
                .addPoints( "2013", "Greece", "Y15-24", 2L)
                .addPoints("2013", "Spain", "Y15-24", 6L)
                .build();

        SoftAssertions softly = new SoftAssertions();
        try {

            InnerJoinOperation join = new InnerJoinOperation(ImmutableMap.of(
                    "left", left, "right", right
            ));
            SumOperation sumOperation = new SumOperation(
                    tuple -> tuple.get(3), left.getDataStructure(),
                    tuple -> tuple.get(3), right.getDataStructure()
            );

            softly.assertThat(
                    join.getDataStructure()
            ).as("data structure of the sum operation of %s and %s", left, right)
                    .isNotNull();
            // TODO: Better check.

            DataStructure sumDs = sumOperation.getDataStructureOperator().apply(
                    left.getDataStructure(), right.getDataStructure()
            );
            softly.assertThat(
                    StreamUtils.zip(
                            left.getData(), right.getData(), sumOperation.getTupleOperator()
                    )
            ).as("data tuple of the sum operation of %s and %s", left, right)
                    .extracting(sumDs::asMap)
                    .extracting(Map::values)
                    .containsExactly(
                            DataPoint.create("2013", "Belgium", "Total", 15L),
                            DataPoint.create("2013", "Belgium", "Y15-24", null),
                            DataPoint.create("2013","Spain", "Y15-24",  10L)
                    );

        } finally {
            softly.assertAll();
        }
    }

    /**
     * Test the example 2
     * <p>
     * VTL 1.1 line 2537-2541.
     *
     * @throws Exception
     */
    //@Test
    public void testSumEx2() throws Exception {

        Dataset left = StaticDataset.create()
                .addComponent("TIME", Role.IDENTIFIER, String.class)
                .addComponent("REF_AREA", Role.IDENTIFIER, String.class)
                .addComponent("PARTNER", Role.IDENTIFIER, String.class)
                .addComponent("OBS_VALUE", Role.MEASURE, String.class)
                .addComponent("OBS_STATUS", Role.ATTRIBUTE, String.class)

                .addPoints("2010", "EU25", "CA", 20, "D")
                .addPoints("2010", "BG", "CA", 2, "D")
                .addPoints("2010", "RO", "CA", 2, "D")
                .build();

        SoftAssertions softly = new SoftAssertions();
        try {

            InnerJoinOperation join = new InnerJoinOperation(ImmutableMap.of(
                    "left", left
            ));
            SumOperation sumOperation = new SumOperation(tuple -> tuple.get(3), left.getDataStructure(), 1);

            softly.assertThat(
                    join.getDataStructure()
            ).as("data structure of the sum operation of %s and 1", left)
                    .isEqualTo(join.getDataStructure());

            DataStructure sumDs = sumOperation.getDataStructure();
            softly.assertThat(
                    join.getData()
            ).as("data of the sum operation of %s and 1", left)
                    .extracting(sumDs::asMap)
                    .extracting(Map::values)
                    .containsExactly(
                            DataPoint.create("2010", "EU25", "CA", 21, "D"),
                            DataPoint.create("2010", "BG", "CA", 3, "D"),
                            DataPoint.create("2010", "RO", "CA", 3, "D")
                    );

        } finally {
            softly.assertAll();
        }
    }

    /**
     * Test the example 2
     * <p>
     * VTL 1.1 line 2537-2541.
     *
     * @throws Exception
     */
    //@Test
    public void testSumEx3() throws Exception {

        StaticDataset left = StaticDataset.create()

                .withName("TIME", "GEO", "POPULATION")
                .andRoles(Role.IDENTIFIER, Role.IDENTIFIER, Role.MEASURE)
                .andTypes(String.class, String.class, Long.class)

                .addPoints("2013", "Belgium", 5L)
                .addPoints("2013","Denmark", 2L)
                .addPoints("2013", "France", 3L)
                .addPoints("2013", "Spain", 4L)
                .build();

        StaticDataset right = StaticDataset.create()

                .withName("TIME", "GEO",  "AGE", "POPULATION")
                .andRoles(Role.IDENTIFIER, Role.IDENTIFIER, Role.IDENTIFIER, Role.MEASURE)
                .andTypes(String.class, String.class, String.class, Long.class)


                .addPoints()
                .addPoints()
                .addPoints()
                .addPoints()
                .build();

        SoftAssertions softly = new SoftAssertions();
        try {

            InnerJoinOperation join = new InnerJoinOperation(ImmutableMap.of(
                    "left", left,
                    "right", right
            ));


            SumOperation sumOperation = new SumOperation(
                    tuple -> tuple.get(3),
                    left.getDataStructure(),
                    tuple -> tuple.get(4),
                    right.getDataStructure()
            );

            softly.assertThat(
                    sumOperation.getDataStructure()
            ).as("data structure of the sum operation of %s and %s", left, right)
                    .isNotEqualTo(left.getDataStructure());

            DataStructure sumDs = join.getDataStructure();
            softly.assertThat(
                    join.getData()
            ).as("data of the sum operation of %s and %s", left, right)
                    .extracting(sumDs::asMap)
                    .extracting(Map::values)
                    .containsExactly(
                            DataPoint.create("2010", "EU25", "CA", 30L)
                    );

        } finally {
            softly.assertAll();
        }
    }

    private DataPoint tuple(VTLObject... components) {
        return DataPoint.create(Arrays.asList(components));
    }
}
