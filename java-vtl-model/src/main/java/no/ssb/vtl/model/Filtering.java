package no.ssb.vtl.model;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * Represent the filtering of the {@link DataPoint}s in a Dataset.
 */
public interface Filtering extends Predicate<DataPoint>, FilteringSpecification {
    Filtering ALL = new Filtering() {
        @Override
        public boolean test(DataPoint dataPoint) {
            return true;
        }

        @Override
        public Collection<Clause> getClauses() {
            return Collections.emptyList();
        }
    };

    Filtering NONE = new Filtering() {
        @Override
        public boolean test(DataPoint dataPoint) {
            return false;
        }

        @Override
        public Collection<Clause> getClauses() {
            return Collections.emptyList();
        }
    };
}
