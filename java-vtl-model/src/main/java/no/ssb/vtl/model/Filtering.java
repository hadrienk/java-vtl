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
        public Collection<? extends FilteringSpecification> getOperands() {
            return Collections.emptyList();
        }

        @Override
        public Operator getOperator() {
            return null;
        }

        @Override
        public String getColumn() {
            return null;
        }

        @Override
        public VTLObject getValue() {
            return null;
        }

        @Override
        public Boolean isNegated() {
            return null;
        }
    };

    Filtering NONE = new Filtering() {
        @Override
        public Collection<? extends FilteringSpecification> getOperands() {
            return null;
        }

        @Override
        public Operator getOperator() {
            return null;
        }

        @Override
        public String getColumn() {
            return null;
        }

        @Override
        public VTLObject getValue() {
            return null;
        }

        @Override
        public Boolean isNegated() {
            return null;
        }

        @Override
        public boolean test(DataPoint dataPoint) {
            return false;
        }
    };
}
