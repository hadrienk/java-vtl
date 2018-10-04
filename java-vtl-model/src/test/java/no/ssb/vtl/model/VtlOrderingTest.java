package no.ssb.vtl.model;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static no.ssb.vtl.model.Component.Role;
import static org.assertj.core.api.Assertions.assertThat;

public class VtlOrderingTest {

    private DataStructure structure;
    private ArrayList<DataPoint> data;

    @Before
    public void setUp() {
        DataStructure.Builder structureBuilder = DataStructure.builder();
        structureBuilder.put("A", Role.IDENTIFIER, String.class);
        structureBuilder.put("B", Role.IDENTIFIER, String.class);
        structureBuilder.put("C", Role.IDENTIFIER, String.class);
        structureBuilder.put("D", Role.IDENTIFIER, String.class);
        structureBuilder.put("E", Role.IDENTIFIER, String.class);
        structure = structureBuilder.build();
        data = Lists.newArrayList(
                DataPoint.create("a", "b", "c", "d", "e"),
                DataPoint.create("f", "g", "h", "i", "j"),
                DataPoint.create("k", "l", "m", "n", "o"),
                DataPoint.create("p", "q", "r", "s", "t"),
                DataPoint.create("u", "v", "w", "x", "y"),
                DataPoint.create("z", "a", "b", "c", "d")
        );
    }

    @Test
    public void testSorted() {
        data.sort(VtlOrdering.using(structure).desc("B", "A").asc("D", "E").build());
        assertThat(data).containsExactly(
                //                A    B    C    D    E
                DataPoint.create("u", "v", "w", "x", "y"),
                DataPoint.create("p", "q", "r", "s", "t"),
                DataPoint.create("k", "l", "m", "n", "o"),
                DataPoint.create("f", "g", "h", "i", "j"),
                DataPoint.create("a", "b", "c", "d", "e"),
                DataPoint.create("z", "a", "b", "c", "d")
        );
    }

    @Test
    public void testSortedEdgeCases() {
        ArrayList<DataPoint> emptySort = new ArrayList<>(data);
        emptySort.sort(VtlOrdering.using(structure).build());
        assertThat(emptySort).containsExactlyElementsOf(data);

        ArrayList<DataPoint> empty = new ArrayList<>();
        empty.sort(VtlOrdering.using(structure).build());
        assertThat(empty).isEmpty();
    }
    
    @Test
    public void testEquals() {
        VtlOrdering order = VtlOrdering.using(structure).asc("A", "B", "C").build();
        assertThat(order)
                .isEqualTo(VtlOrdering.using(structure).asc("A", "B", "C").build())
                .describedAs("is equal to an order with the same columns and orders");
        assertThat(order)
                .isEqualTo(VtlOrdering.using(structure).asc("A", "B", "C", "D").build())
                .describedAs("is equal to an order with more columns but same order");
        assertThat(order)
                .isEqualTo(VtlOrdering.using(structure).asc("A", "B", "C").desc("D").build())
                .describedAs("is equal to an order with extra columns with different order");
        assertThat(order)
                .isNotEqualTo(VtlOrdering.using(structure).desc("A").asc("B", "C").build())
                .describedAs("is NOT equal to an order with the same columns but different orders");
        assertThat(order)
                .isNotEqualTo(VtlOrdering.using(structure).asc("A", "B").build())
                .describedAs("is NOT equal to an order with less columns and same order");
        assertThat(order)
                .isNotEqualTo(VtlOrdering.using(structure).desc("A").asc("B", "C", "D").build())
                .describedAs("is NOT equal to an order with more columns but different orders");
        assertThat(order).isNotEqualTo(null);
        assertThat(order).isEqualTo(order);
    }
}