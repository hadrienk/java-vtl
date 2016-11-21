package kohl.hadrien.vtl.script.operations.join;

import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;

/**
 * A join clause can be applied to a {@link kohl.hadrien.vtl.model.DataStructure} and {@link kohl.hadrien.vtl.model.Dataset.Tuple}
 */
public interface JoinClause {

    DataStructure transformDataStructure(DataStructure structure);

    Dataset.Tuple transformTuple(Dataset.Tuple tuple);

}
