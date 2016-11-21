package kohl.hadrien.vtl.script.operations;

import kohl.hadrien.vtl.model.Dataset;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Addition and substraction operation
 */
public class SumOperation {

    // Two datasets as input.

    private final Dataset left;

    private final Dataset right;

    public SumOperation(Dataset left, Dataset right) {
        this.left = checkNotNull(left);
        this.right = checkNotNull(right);

    }

    enum Op {
        ADDITION,
        SUBSTRACTION
    }

    private void checkDataStructure() {

    }

}
