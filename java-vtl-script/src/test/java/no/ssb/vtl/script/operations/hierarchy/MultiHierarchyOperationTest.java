package no.ssb.vtl.script.operations.hierarchy;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.StaticDataset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.IDENTIFIER;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiHierarchyOperationTest {

    @Test
    public void testSlice() {
        ArrayList<Integer> data = Lists.newArrayList(
                1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6
        );

        Stream<Integer> split = MultiHierarchyOperation.split(data.stream(), (left, right) -> left.equals(right), (first, stream) -> {
            System.out.println("Stream " + first);
            return stream.map(integer -> integer * 2);
        });

        assertThat(split).containsExactly(
                2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 6, 6, 6, 6, 8, 8, 8, 8, 8, 10, 10, 10, 12
        );
    }

    @Test
    public void testMutlipleHierarchy() {

        Dataset hierarchy = StaticDataset.create()
                .addComponent("year", IDENTIFIER, Long.class)
                .addComponent("from", IDENTIFIER, String.class)
                .addComponent("to", IDENTIFIER, String.class)
                .addComponent("sign", IDENTIFIER, String.class)

                .addPoints(2016, "C", "A", "+")
                .addPoints(2016, "C", "B", "+")
                .addPoints(2016, "D", "B", "-")
                .addPoints(2016, "E", "C", "+")
                .addPoints(2016, "E", "D", "-")

                .addPoints(2017, "C", "A", "+")
                .addPoints(2017, "D", "A", "-")
                .addPoints(2017, "D", "B", "+")
                .addPoints(2017, "F", "C", "+")
                .addPoints(2017, "F", "D", "-")

                .build();

        StaticDataset data = StaticDataset.create()
                .addComponent("year", IDENTIFIER, Long.class)
                .addComponent("ic1", IDENTIFIER, String.class)
                .addComponent("ic2", IDENTIFIER, String.class)
                .addComponent("m1", IDENTIFIER, Long.class)
                .addComponent("m2", IDENTIFIER, Double.class)

                .addPoints(2016, "A", "G", 1L, 1.5D)
                .addPoints(2016, "B", "F", 1L, 1.5D)
                .addPoints(2016, "C", "E", 1L, 1.5D)
                .addPoints(2016, "D", "D", 1L, 1.5D)
                .addPoints(2016, "E", "C", 1L, 1.5D)
                .addPoints(2016, "F", "B", 1L, 1.5D)
                .addPoints(2016, "G", "A", 1L, 1.5D)
                .build();

        MultiHierarchyOperation multiHierarchyOperation = new MultiHierarchyOperation(
                data,
                hierarchy,
                "ic1",
                Sets.newHashSet("year")
        );

        Stream<DataPoint> result = multiHierarchyOperation.getData();

        assertThat(result).containsExactly(
                DataPoint.create("A")
        );


    }
}