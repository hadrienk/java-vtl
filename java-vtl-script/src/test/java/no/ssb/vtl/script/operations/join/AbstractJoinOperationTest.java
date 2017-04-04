package no.ssb.vtl.script.operations.join;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Component.Role;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.script.support.JoinSpliterator;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.guava.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractJoinOperationTest {

    @Test
    public void testCreateComponentTable() throws Exception {

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

        Table<Component, Dataset, Component> componentTable = AbstractJoinOperation.createComponentTable(
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

    /**
     * The specification states:
     * "A Dataset ds should appear only once in the list of Datasets"
     * <p>
     * This is preventing self joins. Which are very useful. This
     * test is therefore not asserting the specified behaviour.
     */
    @Test
    public void testSameDatasetShouldFail() throws Exception {
        SoftAssertions softly = new SoftAssertions();
        try {
            Dataset ds1 = mock(Dataset.class);
            Dataset ds2 = mock(Dataset.class);

            DataStructure structure = DataStructure.builder()
                    .put("m", Role.IDENTIFIER, Integer.class)
                    .build();

            when(ds1.getDataStructure()).thenReturn(structure);
            when(ds2.getDataStructure()).thenReturn(structure);

            Throwable ex;

            ex = null;
            try {
                new TestAbstractJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));
            } catch (Throwable t) {
                ex = t;
            }
            softly.assertThat(ex)
                    .as("exception thrown on control")
                    .isNull();


            ex = null;
            try {
                new TestAbstractJoinOperation(ImmutableMap.of("ds1", ds1, "ds12", ds1));
            } catch (Throwable t) {
                ex = t;
            }
            softly.assertThat(ex)
                    .as("exception thrown when using same dataset" +
                            " more than once in a join operation")
                    // TODO: Reevaluate after the 1.1 release of the spec.
                    //.isNotNull();
                    .isNull();

        } finally {
            softly.assertAll();
        }
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

        public TestAbstractJoinOperation(Map<String, Dataset> namedDatasets) {
            super(namedDatasets, Collections.emptySet());
        }

        @Override
        protected JoinSpliterator.TriFunction<JoinDataPoint, JoinDataPoint, Integer, List<JoinDataPoint>> getMerger(
                final DataStructure leftStructure, final DataStructure rightStructure
        ) {
            return null;
        }

        @Override
        protected ImmutableSet<Component> getIdentifiers() {
            return null;
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
