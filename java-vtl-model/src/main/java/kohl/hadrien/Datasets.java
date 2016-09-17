package kohl.hadrien;

import java.util.stream.Stream;

/**
 * Operations on data sets.
 */
public class Datasets {

    /**
     * Carries out the join.
     */
    public static Dataset merge(Dataset... datasets) {
        return Stream.of(datasets).reduce(Dataset::merge).get();
    }

    /**
     * Makes the set union of a and b.
     */
    public static Dataset union(Dataset a, Dataset b) {
        return a.union(b);
    }

    /**
     * Makes the set intersection of a and b.
     */
    public static Dataset intersect(Dataset a, Dataset b) {
        return a.intersect(b);
    }

    /**
     * Makes the set symmetric difference of a and b.
     */
    public static Dataset symdiff(Dataset a, Dataset b) {
        return a.symdiff(b);
    }

    /**
     * Makes the set difference of a and b.
     */
    public static Dataset setdiff(Dataset a, Dataset b) {
        return a.setdiff(b);
    }
}
