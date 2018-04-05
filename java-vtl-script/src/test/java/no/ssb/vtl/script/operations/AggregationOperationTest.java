package no.ssb.vtl.script.operations;

import com.google.common.collect.ImmutableList;
import no.ssb.vtl.model.*;
import no.ssb.vtl.script.support.DatasetCloseWatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AggregationOperationTest {

    private DatasetCloseWatcher dataset;

    @Before
    public void setUp() throws Exception {

        StaticDataset staticDataset = StaticDataset.create()
                .addComponent("id1", Role.IDENTIFIER, String.class)
                .addComponent("id2", Role.IDENTIFIER, String.class)
                .addComponent("m1", Role.IDENTIFIER, Long.class)

                .addPoints("a", "1", 1L)
                .addPoints("a", "2", 2L)
                .addPoints("a", "3", 4L)
                .addPoints("a", "4", 8L)

                .addPoints("b", "1", 1L)
                .addPoints("b", "2", 2L)
                .addPoints("b", "3", 4L)
                .addPoints("b", "4", 8L)

                .addPoints("c", "1", 1L)
                .addPoints("c", "2", 2L)
                .addPoints("c", "3", 4L)
                .addPoints("c", "4", 8L)

                .build();

        this.dataset = DatasetCloseWatcher.wrap(staticDataset);
    }

    @Test
    public void testClosesStreams() {


        DataStructure structure = this.dataset.getDataStructure();
        AggregationOperation aggregationOperation = new AggregationOperation(
                this.dataset,
                ImmutableList.of(structure.get("id1")),
                ImmutableList.of(structure.get("m1")),
                vtlNumbers -> vtlNumbers.stream().reduce(VTLNumber.of(0L), VTLNumber::add)
        );

        try (Stream<DataPoint> data = aggregationOperation.getData()) {
            assertThat(data).containsExactly(
                    DataPoint.create("a",15),
                    DataPoint.create("b",15),
                    DataPoint.create("c",15)
            );
        } finally {
            assertThat(dataset.allStreamWereClosed()).isTrue();
        }

    }
}