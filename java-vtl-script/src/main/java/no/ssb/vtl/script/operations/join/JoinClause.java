package no.ssb.vtl.script.operations.join;

import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.DataPoint;

import java.util.function.UnaryOperator;

/**
 * A join clause can be applied to a {@link DataStructure}
 * and {@link DataPoint}.
 * <p>
 * Join clauses have access to a special "working dataset" that can be mutated
 * during the join.
 */
public interface JoinClause extends UnaryOperator<WorkingDataset> {
}
