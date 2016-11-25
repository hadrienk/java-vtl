package kohl.hadrien.vtl.script.operations.join;

import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;

/**
 * A join clause can be applied to a {@link kohl.hadrien.vtl.model.DataStructure}
 * and {@link kohl.hadrien.vtl.model.Dataset.Tuple}.
 * <p>
 * Join clauses have access to a special "working dataset" that can be mutated
 * during the join.
 */
public interface JoinClause {

    DataStructure transformDataStructure(DataStructure structure);

    Dataset.Tuple transformTuple(Dataset.Tuple tuple);

}
