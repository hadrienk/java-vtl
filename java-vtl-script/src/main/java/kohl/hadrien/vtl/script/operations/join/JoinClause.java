package kohl.hadrien.vtl.script.operations.join;

import java.util.function.UnaryOperator;

/**
 * A join clause can be applied to a {@link kohl.hadrien.vtl.model.DataStructure}
 * and {@link kohl.hadrien.vtl.model.Dataset.Tuple}.
 * <p>
 * Join clauses have access to a special "working dataset" that can be mutated
 * during the join.
 */
public interface JoinClause extends UnaryOperator<WorkingDataset> {
}
