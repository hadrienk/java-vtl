package no.ssb.vtl.script.operations;

import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.StaticDataset;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import static no.ssb.vtl.model.Component.Role.ATTRIBUTE;
import static no.ssb.vtl.model.Component.Role.IDENTIFIER;
import static no.ssb.vtl.model.Component.Role.MEASURE;

public class FoldOperationBenchmark {

    @State(Scope.Benchmark)
    public static class FoldState {
        private StaticDataset dataset;
        private FoldOperation clause;

        @Setup
        public void setup() {
            dataset = StaticDataset.create()
                    .addComponent("id1", IDENTIFIER, String.class)
                    .addComponent("id2", IDENTIFIER, String.class)
                    .addComponent("measure1", MEASURE, String.class)
                    .addComponent("measure2", MEASURE, String.class)
                    .addComponent("attribute", ATTRIBUTE, String.class)

                    .addPoints("id1-1", "id2-1", "measure1-1", "measure2-1", "attribute1-1")
                    .addPoints("id1-1", "id2-2", null, "measure2-2", "attribute1-2")
                    .addPoints("id1-2", "id2-1", "measure1-3", null, "attribute1-3")
                    .addPoints("id1-2", "id2-2", "measure1-4", "measure2-4", null)
                    .addPoints("id1-3", "id2-1", null, null, null)

                    .build();

            DataStructure structure = dataset.getDataStructure();
            clause = new FoldOperation(
                    dataset,
                    "newId",
                    "newMeasure",
                    ImmutableSet.of(structure.get("measure1"), structure.get("measure2"))
            );
        }
    }

    @Benchmark
    public void foldBenchmark(FoldState state, Blackhole blackhole) {
        state.clause.getData().forEach(blackhole::consume);
    }
}
