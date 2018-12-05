package no.ssb.vtl.script.operations.join;

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
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Streams;
import com.google.common.collect.Table;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Component.Role;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.OrderingSpecification;
import no.ssb.vtl.model.StaticDataset;
import no.ssb.vtl.model.VTLObject;
import org.junit.Test;

import javax.script.Bindings;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.guava.api.Assertions.assertThat;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AbstractJoinOperationTest {


    @Test
    public void testComponentMapping() {
        StaticDataset ds = datasetWith(
                ImmutableSet.of("id1", "id2", "id3"),
                ImmutableSet.of("me1", "me2", "me3"),
                ImmutableSet.of("at1", "at2", "at3")
        );

        Table<String, String, String> columnMapping = AbstractJoinOperation.getColumnMapping(ImmutableMap.of("ds", ds));

        assertThat(columnMapping).hasColumnCount(1).hasRowCount(9)
            .containsCell("id1","ds","id1")
            .containsCell("id2","ds","id2")
            .containsCell("id3","ds","id3")
            .containsCell("me1","ds","me1")
            .containsCell("me2","ds","me2")
            .containsCell("me3","ds","me3")
            .containsCell("at1","ds","at1")
            .containsCell("at2","ds","at2")
            .containsCell("at3","ds","at3");

        Table<String, String, String> columnMappingTwo = AbstractJoinOperation.getColumnMapping(
                ImmutableMap.of("ds1", ds, "ds2", ds)
        );

        assertThat(columnMappingTwo).hasColumnCount(2).hasRowCount(15)
                .containsCell("id1", "ds1", "id1")
                .containsCell("id2", "ds1", "id2")
                .containsCell("id3", "ds1", "id3")

                .containsCell("id1", "ds2", "id1")
                .containsCell("id2", "ds2", "id2")
                .containsCell("id3", "ds2", "id3")

                .containsCell("ds1_me1", "ds1", "me1")
                .containsCell("ds1_me2", "ds1", "me2")
                .containsCell("ds1_me3", "ds1", "me3")
                .containsCell("ds1_at1", "ds1", "at1")
                .containsCell("ds1_at2", "ds1", "at2")
                .containsCell("ds1_at3", "ds1", "at3")

                .containsCell("ds2_me1", "ds2", "me1")
                .containsCell("ds2_me2", "ds2", "me2")
                .containsCell("ds2_me3", "ds2", "me3")
                .containsCell("ds2_at1", "ds2", "at1")
                .containsCell("ds2_at2", "ds2", "at2")
                .containsCell("ds2_at3", "ds2", "at3");

        StaticDataset ds1 = datasetWith(
                ImmutableSet.of("id1", "id2", "idA"),
                ImmutableSet.of("me1", "me2"),
                ImmutableSet.of("at1", "at2")
        );

        StaticDataset ds2 = datasetWith(
                ImmutableSet.of("id1", "id2", "idB"),
                ImmutableSet.of("me1", "me3"),
                ImmutableSet.of("at1", "at3")
        );

        StaticDataset ds3 = datasetWith(
                ImmutableSet.of("id1", "id2", "idC"),
                ImmutableSet.of("me1"),
                ImmutableSet.of("at2", "at3")
        );


        Table<String, String, String> columnMappingThree = AbstractJoinOperation.getColumnMapping(
                ImmutableMap.of("ds1", ds1, "ds2", ds2, "ds3", ds3)
        );

        assertThat(columnMappingThree).hasColumnCount(3).hasRowCount(16)
                .containsCell("id1", "ds1", "id1")
                .containsCell("id1", "ds2", "id1")
                .containsCell("id1", "ds3", "id1")

                .containsCell("id2", "ds1", "id2")
                .containsCell("id2", "ds2", "id2")
                .containsCell("id2", "ds3", "id2")

                .containsCell("idA", "ds1", "idA")
                .containsCell("ds1_me1", "ds1", "me1")
                .containsCell("me2", "ds1", "me2")
                .containsCell("ds1_at1", "ds1", "at1")
                .containsCell("ds1_at2", "ds1", "at2")

                .containsCell("idB", "ds2", "idB")
                .containsCell("ds2_me1", "ds2", "me1")
                .containsCell("me3", "ds2", "me3")
                .containsCell("ds2_at1", "ds2", "at1")
                .containsCell("ds2_at3", "ds2", "at3")

                .containsCell("idC", "ds3", "idC")
                .containsCell("ds3_me1", "ds3", "me1")
                .containsCell("ds3_at2", "ds3", "at2")
                .containsCell("ds3_at3", "ds3", "at3")


        ;


    }

    private StaticDataset datasetWith(Set<String> identifiers, Set<String> measures, Set<String> attributes) {
        StaticDataset.StructureBuilder builder = StaticDataset.create();
        for (String identifier : identifiers) {
            builder.addComponent(identifier, Role.IDENTIFIER, String.class);
        }
        for (String measure : measures) {
            builder.addComponent(measure, Role.MEASURE, String.class);
        }
        for (String attribute : attributes) {
            builder.addComponent(attribute, Role.ATTRIBUTE, String.class);
        }
        return builder.build();
    }

    @Test
    // TODO: Move to ComponentBindingsTest
    public void testJoinBindingsOneDataset() {

        StaticDataset t1 = StaticDataset.create()
                .addComponent("id1", Role.IDENTIFIER, String.class)
                .addComponent("id2", Role.IDENTIFIER, Long.class)
                .addComponent("uni1", Role.IDENTIFIER, Double.class)
                .addComponent("m1", Role.MEASURE, Instant.class)
                .addComponent("a1", Role.MEASURE, Boolean.class)
                .build();

        Bindings result = AbstractJoinOperation.createJoinScope(ImmutableMap.of(
                "t1", t1
        ));

        assertThat(result).containsOnlyKeys("id1", "id2", "uni1", "m1", "a1", "t1");

    }

    @Test
    public void testGetDataOnlyOnce() {

        // Makes sure getData is not called when sorting is available.

        ArrayList<Long> leftData = Lists.newArrayList(
                1L, 3L, 3L, 3L, 3L, 4L, 5L, 6L, 8L, 9L, 9L, 9L, 10L
        );

        ArrayList<Long> rightData = Lists.newArrayList(
                1L, 1L, 1L, 2L, 3L, 5L, 7L, 7L, 8L, 8L, 9L, 9L, 9L, 10L
        );

        Dataset left = mock(Dataset.class);
        when(left.getDataStructure()).thenReturn(
                DataStructure.builder()
                        .put("id", Role.IDENTIFIER, Long.class)
                        .put("m", Role.MEASURE, String.class)
                        .build()
        );
        when(left.getData()).then(o -> {
            return Streams.mapWithIndex(leftData.stream(), (id, index) -> {
                return Lists.newArrayList(VTLObject.of(id), VTLObject.of("left " + (index + 1)));
            }).map(DataPoint::create);
        });
        when(left.getData(any(Order.class))).then(invocation -> {
            Order order = invocation.getArgumentAt(0, Order.class);
            Stream<DataPoint> sortedStream = Streams.mapWithIndex(leftData.stream(), (id, index) -> Lists.newArrayList(VTLObject.of(id), VTLObject.of("left " + (index + 1)))).map(DataPoint::create).sorted(order);
            return Optional.of(sortedStream);
        });

        Dataset right = mock(Dataset.class);
        when(right.getDataStructure()).thenReturn(
                DataStructure.builder()
                        .put("id", Role.IDENTIFIER, Long.class)
                        .put("m", Role.MEASURE, String.class)
                        .build()
        );
        when(right.getData()).then(o -> {
            return Streams.mapWithIndex(rightData.stream(), (id, index) -> {
                return Lists.newArrayList(VTLObject.of(id), VTLObject.of("right " + (index + 1)));
            }).map(DataPoint::create);
        });
        when(right.getData(any(Order.class))).then(invocation -> {
            Order order = invocation.getArgumentAt(0, Order.class);
            Stream<DataPoint> sortedStream = Streams.mapWithIndex(leftData.stream(), (id, index) -> Lists.newArrayList(VTLObject.of(id), VTLObject.of("left " + (index + 1)))).map(DataPoint::create).sorted(order);
            return Optional.of(sortedStream);
        });

        TestAbstractJoinOperation result = new TestAbstractJoinOperation(ImmutableMap.of("left", left, "right", right));

        result.getData().forEach(dp -> {
        });

        verify(left, never()).getData();
        verify(right, never()).getData();

    }

    @Test
    public void testSortMerge() throws Exception {

        ArrayList<Long> leftData = Lists.newArrayList(
                1L, 3L, 3L, 3L, 3L, 4L, 5L, 6L, 8L, 9L, 9L, 9L, 10L
        );

        ArrayList<Long> rightData = Lists.newArrayList(
                1L, 1L, 1L, 2L, 3L, 5L, 7L, 7L, 8L, 8L, 9L, 9L, 9L, 10L
        );

        Dataset left = mock(Dataset.class);
        when(left.getDataStructure()).thenReturn(
                DataStructure.builder()
                        .put("id", Role.IDENTIFIER, Long.class)
                        .put("m", Role.MEASURE, String.class)
                        .build()
        );

        when(left.getData()).then(o -> {
            return Streams.mapWithIndex(leftData.stream(), (id, index) -> {
                return Lists.newArrayList(VTLObject.of(id), VTLObject.of("left " + (index + 1)));
            }).map(DataPoint::create);
        });
        when(left.getData(any(Order.class))).thenReturn(Optional.empty());

        Dataset right = mock(Dataset.class);
        when(right.getDataStructure()).thenReturn(
                DataStructure.builder()
                        .put("id", Role.IDENTIFIER, Long.class)
                        .put("m", Role.MEASURE, String.class)
                        .build()
        );
        when(right.getData()).then(o -> {
            return Streams.mapWithIndex(rightData.stream(), (id, index) -> {
                return Lists.newArrayList(VTLObject.of(id), VTLObject.of("right " + (index + 1)));
            }).map(DataPoint::create);
        });
        when(right.getData(any(Order.class))).thenReturn(Optional.empty());

        TestAbstractJoinOperation result = new TestAbstractJoinOperation(ImmutableMap.of("left", left, "right", right));

        result.getData().forEach(dp -> {
        });

        /*
         * H: hit
         * L: left hit
         * R: right hit
         * M: miss
         *
         * +----+-------+-------------------------------------------------------+
         * |   Right    |                          Left                         |
         * +----+-------+-------------------------------------------------------+
         * | pos        | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 |
         * +    +-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |    | value | 1 | 3 | 3 | 3 | 3 | 4 | 5 | 6 | 8 | 9  | 9  | 9  | 10 |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  1 | 1     | H |   |   |   |   |   |   |   |   |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  2 | 1     | H |   |   |   |   |   |   |   |   |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  3 | 1     | H |   |   |   |   |   |   |   |   |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  4 | 2     |   | R |   |   |   |   |   |   |   |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  5 | 3     |   | H | H | H | H |   |   |   |   |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  6 | 5     |   |   |   |   |   | L | H |   |   |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  7 | 7     |   |   |   |   |   |   |   | R |   |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  8 | 7     |   |   |   |   |   |   |   | R |   |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * |  9 | 8     |   |   |   |   |   |   |   | L | H |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * | 10 | 8     |   |   |   |   |   |   |   |   | H |    |    |    |    |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * | 11 | 9     |   |   |   |   |   |   |   |   | M | H  | H  | H  | M  |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * | 12 | 9     |   |   |   |   |   |   |   |   |   | H  | H  | H  | M  |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * | 13 | 9     |   |   |   |   |   |   |   |   |   | H  | H  | H  | M  |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         * | 14 | 10    |   |   |   |   |   |   |   |   |   | M  | M  | M  | H  |
         * +----+-------+---+---+---+---+---+---+---+---+---+----+----+----+----+
         *
         */

        System.out.println("left:");
        result.leftMiss.forEach(System.out::println);
        assertThat(result.leftMiss)
                .extracting(p -> p.get(0).get(1)) // get the measure
                .extracting(VTLObject::get)
                .containsExactly(
                        "left 6", "left 8"
                );

        System.out.println("right:");
        result.rightMiss.forEach(System.out::println);
        assertThat(result.rightMiss)
                .extracting(p -> p.get(1).get(1)) // get the measure
                .extracting(VTLObject::get)
                .containsExactly(
                        "right 4", "right 7", "right 8"
                );

        System.out.println("match:");
        result.hits.forEach(System.out::println);
        assertThat(result.hits)
                .extracting(p -> Arrays.asList(p.get(0).get(1).get(), p.get(1).get(1).get())) // get the measure
                .containsExactly(

                        Arrays.asList("left 1", "right 1"),
                        Arrays.asList("left 1", "right 2"),
                        Arrays.asList("left 1", "right 3"),

                        Arrays.asList("left 2", "right 5"),
                        Arrays.asList("left 3", "right 5"),
                        Arrays.asList("left 4", "right 5"),
                        Arrays.asList("left 5", "right 5"),

                        Arrays.asList("left 7", "right 6"),
                        Arrays.asList("left 9", "right 9"),  // TODO: Those two sometimes swap?
                        Arrays.asList("left 9", "right 10"), // ???
                        Arrays.asList("left 10", "right 11"),
                        Arrays.asList("left 10", "right 12"),
                        Arrays.asList("left 10", "right 13"),
                        Arrays.asList("left 11", "right 11"),
                        Arrays.asList("left 11", "right 12"),
                        Arrays.asList("left 11", "right 13"),
                        Arrays.asList("left 12", "right 11"),
                        Arrays.asList("left 12", "right 12"),
                        Arrays.asList("left 12", "right 13"),
                        Arrays.asList("left 13", "right 14")

                );


    }

    @Test
    public void testNoCommonComponent() {

        Dataset ds1 = mock(Dataset.class);
        DataStructure s1 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Long.class)
                .put("id2", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id3", Role.IDENTIFIER, Long.class)
                .put("id4", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds2.getDataStructure()).thenReturn(s2);

        assertThatThrownBy(() -> {
            new TestAbstractJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));
        }).isNotNull()
                .hasMessageContaining("common")
                .hasMessageContaining("identifiers");
    }

    @Test
    public void testCommonComponentsButWrongType() {

        Dataset ds1 = mock(Dataset.class);
        DataStructure s1 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, String.class)
                .put("id2", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Long.class)
                .put("id2", Role.IDENTIFIER, String.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds2.getDataStructure()).thenReturn(s2);

        assertThatThrownBy(() -> {
            new TestAbstractJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));
        }).isNotNull().hasMessageContaining("types").hasMessageContaining("identifier");
    }

    @Test
    public void testWrongTypeWithIdentifierSelection() {

        Dataset ds1 = mock(Dataset.class);
        DataStructure s1 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, String.class)
                .put("id2", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, String.class)
                .put("id2", Role.IDENTIFIER, String.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds2.getDataStructure()).thenReturn(s2);

        new TestAbstractJoinOperation(
                ImmutableMap.of("ds1", ds1, "ds2", ds2),
                ImmutableSet.of(s1.get("id1"))
        );

        assertThatThrownBy(() -> {
            new TestAbstractJoinOperation(
                    ImmutableMap.of("ds1", ds1, "ds2", ds2),
                    ImmutableSet.of(s1.get("id2"))
            );
        }).isNotNull().hasMessageContaining("types");
    }


    @Test
    public void testCreateComponentMapping() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        DataStructure s1 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Long.class)
                .put("id2", Role.IDENTIFIER, Long.class)
                .put("id4", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Long.class)
                .put("id3", Role.IDENTIFIER, Long.class)
                .put("id4", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds2.getDataStructure()).thenReturn(s2);

        Dataset ds3 = mock(Dataset.class);
        DataStructure s3 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Long.class)
                .put("id4", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds3.getDataStructure()).thenReturn(s3);

        Table<Component, Dataset, Component> componentTable = AbstractJoinOperation.createComponentMapping(
                Lists.newArrayList(ds1, ds3, ds2)
        );

        assertThat(componentTable).hasRowCount(10).hasColumnCount(3)

                .containsCell(s1.get("id1"), ds1, s1.get("id1"))
                .containsCell(s1.get("id1"), ds2, s2.get("id1"))
                .containsCell(s1.get("id1"), ds3, s3.get("id1"))

                .containsCell(s1.get("id2"), ds1, s1.get("id2"))

                .containsCell(s2.get("id3"), ds2, s2.get("id3"))

                .containsCell(s1.get("id4"), ds1, s1.get("id4"))
                .containsCell(s1.get("id4"), ds2, s2.get("id4"))
                .containsCell(s1.get("id4"), ds3, s3.get("id4"))

                .containsCell(s1.get("me1"), ds1, s1.get("me1"))
                .containsCell(s2.get("me1"), ds2, s2.get("me1"))
                .containsCell(s3.get("me1"), ds3, s3.get("me1"))

                .containsCell(s1.get("at1"), ds1, s1.get("at1"))
                .containsCell(s2.get("at1"), ds2, s2.get("at1"))
                .containsCell(s3.get("at1"), ds3, s3.get("at1"));

    }

    // TODO(hk): Operating on the same dataset is still problematic.
    // We are closer to stop using Component as positions key in datapoints.
    // We just need to use the column mapping instead of the component mapping
    // @Test
    public void testSameDatasetShouldNotFail() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        Dataset ds2 = mock(Dataset.class);

        DataStructure structure = DataStructure.builder()
                .put("m", Role.IDENTIFIER, Long.class)
                .build();

        when(ds1.getDataStructure()).thenReturn(structure);
        when(ds2.getDataStructure()).thenReturn(structure);

        HashMap<String, Dataset> datasets = Maps.newHashMap();
        datasets.put("ds1", ds1);
        datasets.put("ds2", ds1);

        new TestAbstractJoinOperation(datasets);
    }

    @Test
    public void testEmptyFails() {
        Throwable ex = null;
        try {
            new TestAbstractJoinOperation(Collections.emptyMap());
        } catch (Throwable t) {
            ex = t;
        }
        assertThat(ex)
                .hasMessageContaining("join")
                .hasMessageContaining("empty")
                .hasMessageContaining("dataset");
    }


    @Test
    public void testComponentNameIsUnique() {

        Dataset ds1 = mock(Dataset.class);
        DataStructure s1 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("me2", Role.MEASURE, Long.class)
                .put("me3", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Long.class)
                .put("me1", Role.MEASURE, Long.class)
                .put("at1", Role.ATTRIBUTE, Long.class)
                .build();
        when(ds2.getDataStructure()).thenReturn(s2);

        TestAbstractJoinOperation joinOperation = new TestAbstractJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        assertThat(joinOperation.componentNameIsUnique("ds1", "me1"))
                .isFalse();
        assertThat(joinOperation.componentNameIsUnique("ds1", "me2"))
                .isTrue();
        assertThat(joinOperation.componentNameIsUnique("ds1", "me3"))
                .isTrue();
        assertThat(joinOperation.componentNameIsUnique("ds1", "at1"))
                .isFalse();
    }

    private static class TestAbstractJoinOperation extends AbstractJoinOperation {

        List<List<DataPoint>> leftMiss = Lists.newArrayList();
        List<List<DataPoint>> rightMiss = Lists.newArrayList();
        List<List<DataPoint>> hits = Lists.newArrayList();


        public TestAbstractJoinOperation(Map<String, Dataset> namedDatasets) {
            super(namedDatasets, Collections.emptySet());
        }

        public TestAbstractJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
            super(namedDatasets, identifiers);
        }

        @Override
        public Optional<Map<String, Integer>> getDistinctValuesCount() {
            return Optional.empty();
        }

        @Override
        public Optional<Long> getSize() {
            return Optional.empty();
        }

        @Override
        public Stream<DataPoint> computeData(Ordering orders, Filtering filtering, Set<String> components) {
            return null;
        }

        @Override
        public FilteringSpecification computeRequiredFiltering(FilteringSpecification filtering) {
            return null;
        }

        @Override
        public OrderingSpecification computeRequiredOrdering(OrderingSpecification filtering) {
            return null;
        }
    }
}
