package no.ssb.vtl.script.operations.join;

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
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.guava.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractJoinOperationTest {

    @Test
    public void testSortMerge() throws Exception {

        ArrayList<Integer> leftData = Lists.newArrayList(
                1, 3, 3, 3, 3, 4, 5, 6, 8, 9, 9, 9, 10
        );

        ArrayList<Integer> rightData = Lists.newArrayList(
                1, 1, 1, 2, 3, 5, 7, 7, 8, 8, 9, 9, 9, 10
        );

        Dataset left = mock(Dataset.class);
        when(left.getDataStructure()).thenReturn(
                DataStructure.builder()
                        .put("id", Role.IDENTIFIER, Integer.class)
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
                        .put("id", Role.IDENTIFIER, Integer.class)
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
                .put("id1", Role.IDENTIFIER, Integer.class)
                .put("id2", Role.IDENTIFIER, Integer.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id3", Role.IDENTIFIER, Integer.class)
                .put("id4", Role.IDENTIFIER, Integer.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
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
                .put("id2", Role.IDENTIFIER, Integer.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Integer.class)
                .put("id2", Role.IDENTIFIER, String.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
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
                .put("id2", Role.IDENTIFIER, Integer.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, String.class)
                .put("id2", Role.IDENTIFIER, String.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
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
                .put("id1", Role.IDENTIFIER, Integer.class)
                .put("id2", Role.IDENTIFIER, Integer.class)
                .put("id4", Role.IDENTIFIER, Integer.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
                .build();
        when(ds1.getDataStructure()).thenReturn(s1);

        Dataset ds2 = mock(Dataset.class);
        DataStructure s2 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Integer.class)
                .put("id3", Role.IDENTIFIER, Integer.class)
                .put("id4", Role.IDENTIFIER, Integer.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
                .build();
        when(ds2.getDataStructure()).thenReturn(s2);

        Dataset ds3 = mock(Dataset.class);
        DataStructure s3 = DataStructure.builder()
                .put("id1", Role.IDENTIFIER, Integer.class)
                .put("id4", Role.IDENTIFIER, Integer.class)
                .put("me1", Role.MEASURE, Integer.class)
                .put("at1", Role.ATTRIBUTE, Integer.class)
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
    // @Test
    public void testSameDatasetShouldNotFail() throws Exception {

        Dataset ds1 = mock(Dataset.class);
        Dataset ds2 = mock(Dataset.class);

        DataStructure structure = DataStructure.builder()
                .put("m", Role.IDENTIFIER, Integer.class)
                .build();

        when(ds1.getDataStructure()).thenReturn(structure);
        when(ds2.getDataStructure()).thenReturn(structure);

        HashMap<String, Dataset> datasets = Maps.newHashMap();
        datasets.put("ds1", ds1);
        datasets.put("ds2", ds1);

        new TestAbstractJoinOperation(datasets);
    }

    @Test
    public void testEmptyFails() throws Exception {
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
    public void testOptimization() throws Exception {

        Dataset ds1 = mock(Dataset.class);

        DataStructure ds1Struct = DataStructure.builder()
                .put("m", Role.IDENTIFIER, Integer.class)
                .build();

        given(ds1.getDataStructure()).willReturn(ds1Struct);
        given(ds1.getData(any(Order.class))).willReturn(Optional.empty());
        given(ds1.getData()).will(invocation -> {
            return IntStream.rangeClosed(0, 10).boxed().map(
                    integer -> ds1Struct.wrap(
                            ImmutableMap.of(
                                    "m", integer
                            )
                    )
            );
        });
        AbstractJoinOperation result = new TestAbstractJoinOperation(ImmutableMap.of("ds1", ds1)) {

        };

        assertThat(result.getData())
                .containsAll(ds1.getData().collect(Collectors.toList()));

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
        protected BiFunction<DataPoint, DataPoint, DataPoint> getMerger(Dataset leftDataset, Dataset rightDataset) {
            return (left, right) -> {
                if (left != null && right != null) {
                    hits.add(Lists.newArrayList(left, right));
                } else {
                    if (left != null)
                        leftMiss.add(Lists.newArrayList(left, null));
                    if (right != null)
                        rightMiss.add(Lists.newArrayList(null, right));
                }
                return null;
            };
        }

        @Override
        public Optional<Map<String, Integer>> getDistinctValuesCount() {
            return Optional.empty();
        }

        @Override
        public Optional<Long> getSize() {
            return Optional.empty();
        }
    }
}
