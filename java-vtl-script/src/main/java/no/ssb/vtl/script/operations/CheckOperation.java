package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.Dataset;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.*;

public class CheckOperation {

    private static final List<String> ROWS_TO_RETURN_POSSIBLE_VALUES = Arrays.asList("not_valid", "valid", "all");
    private static final List<String> COMPONENTS_TO_RETURN_POSSIBLE_VALUES = Arrays.asList("measures", "condition");

    private final Dataset dataset;
    private final String rowsToReturn;
    private final String componentsToReturn;
    private final String errorCode;
    private final Integer errorLevel;

    public CheckOperation(Dataset dataset, String rowsToReturn, String componentsToReturn, String errorCode, Integer errorLevel) {
        this.dataset = checkNotNull(dataset, "dataset was null");

        if (rowsToReturn != null) {
            checkArgument(!rowsToReturn.isEmpty(), "the rowsToReturn argument was empty");
            checkArgument(ROWS_TO_RETURN_POSSIBLE_VALUES.contains(rowsToReturn),
                    "the rowsToReturn argument has incorrect value: " + rowsToReturn
                            + ". Allowed values: " + Arrays.toString(ROWS_TO_RETURN_POSSIBLE_VALUES.toArray()));
            this.rowsToReturn = rowsToReturn;
        } else {
            this.rowsToReturn = "not_valid";
        }

        if (componentsToReturn != null) {
            checkArgument(!componentsToReturn.isEmpty(), "the componentsToReturn argument was empty");
            checkArgument(COMPONENTS_TO_RETURN_POSSIBLE_VALUES.contains(componentsToReturn),
                    "the componentsToReturn argument has incorrect value: " + componentsToReturn
                            + ". Allowed values: " + Arrays.toString(COMPONENTS_TO_RETURN_POSSIBLE_VALUES.toArray()));
            this.componentsToReturn = componentsToReturn;
        } else {
            this.componentsToReturn = "measures";
        }

        if (errorCode != null) {
            checkArgument(!errorCode.isEmpty(), "the errorCode argument was empty");
            this.errorCode = errorCode;
        } else {
            this.errorCode = null;
        }

        this.errorLevel = errorLevel;
    }
}
