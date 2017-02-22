package no.ssb.vtl.script.operations.join;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.JoinSpliterator;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractJoinOperationTest {

    ObjectMapper mapper = new ObjectMapper();

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

            DataStructure structure = DataStructure.of(
                    mapper::convertValue,
                    "m",
                    Component.Role.IDENTIFIER,
                    Integer.class
            );

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
    public void testSimple() throws Exception {

        Dataset ds1 = mock(Dataset.class);

        DataStructure ds1Struct = DataStructure.of(
                mapper::convertValue,
                "m",
                Component.Role.IDENTIFIER,
                Integer.class
        );

        given(ds1.getDataStructure()).willReturn(ds1Struct);
        given(ds1.get()).will(invocation -> {
            return IntStream.rangeClosed(0, 10).boxed().map(
                    integer -> ds1Struct.wrap(
                            ImmutableMap.of(
                                    "m", integer
                            )
                    )
            );
        });
        AbstractJoinOperation result = new TestAbstractJoinOperation(ImmutableMap.of("ds1", ds1)) {

            @Override
            public WorkingDataset workDataset() {
                return new WorkingDataset() {
                    @Override
                    public DataStructure getDataStructure() {
                        return ds1.getDataStructure();
                    }

                    @Override
                    public Stream<DataPoint> get() {
                        return ds1.get();
                    }
                };
            }

        };

        assertThat(result.workDataset().get())
                .containsAll(ds1.get().collect(Collectors.toList()));

    }

    private static class TestAbstractJoinOperation extends AbstractJoinOperation {

        public TestAbstractJoinOperation(Map<String, Dataset> namedDatasets) {
            super(namedDatasets, Collections.emptySet());
        }

        @Override
        protected JoinSpliterator.TriFunction<JoinDataPoint, JoinDataPoint, Integer, List<JoinDataPoint>> getMerger() {
            return null;
        }

        @Override
        protected Comparator<List<VTLObject>> getKeyComparator() {
            return null;
        }

        @Override
        protected ImmutableSet<Component> getIdentifiers() {
            return null;
        }

        @Override
        public WorkingDataset workDataset() {
            return null;
        }
    }
}
