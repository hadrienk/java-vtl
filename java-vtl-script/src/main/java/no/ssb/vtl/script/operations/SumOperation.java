package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Addition and subtraction operation
 * TODO: Use join.
 */
public class SumOperation implements BinaryOperator<Dataset.DataPoint>, Supplier<DataStructure> {

    private final Function<Dataset.DataPoint, VTLObject> leftExtractor;
    private final DataStructure leftDataStructure;
    private final Function<Dataset.DataPoint, VTLObject> rightExtractor;
    private final DataStructure rightDataStructure;
    private final Number scalar;

    public SumOperation(Function<Dataset.DataPoint, VTLObject> leftExtractor,
                        DataStructure leftDataStructure,
                        Function<Dataset.DataPoint, VTLObject> rightExtractor,
                        DataStructure rightDataStructure
    ) {
        this.leftExtractor = checkNotNull(leftExtractor);
        this.leftDataStructure = checkNotNull(leftDataStructure);
        this.rightExtractor = checkNotNull(rightExtractor);
        this.rightDataStructure = checkNotNull(rightDataStructure);
        this.scalar = null;
    }

    public SumOperation(Function<Dataset.DataPoint, VTLObject> extractor, DataStructure dataStructure, Number scalar) {
        this.leftExtractor = checkNotNull(extractor);
        this.leftDataStructure = checkNotNull(dataStructure);
        this.rightExtractor = null;
        this.rightDataStructure = null;
        this.scalar = checkNotNull(scalar);
    }

    // TODO: Candidate for abstraction?
    BinaryOperator<DataStructure> getDataStructureOperator() {
        return (dataStructure, dataStructure2) -> dataStructure;
    }

    // TODO: Candidate for abstraction?
    BinaryOperator<Dataset.DataPoint> getTupleOperator() {
        return new BinaryOperator<Dataset.DataPoint>() {
            @Override
            public Dataset.DataPoint apply(Dataset.DataPoint dataPoints, Dataset.DataPoint dataPoints2) {
                return null;
            }
        };
    }

    public DataStructure getDataStructure() {
        if (scalar != null) {
            return leftDataStructure;
        }
        return null;
    }


    @Override
    public Dataset.DataPoint apply(Dataset.DataPoint left, Dataset.DataPoint right) {
        VTLObject leftValue = leftExtractor.apply(left);
        if (scalar != null) {
            return left;
        } else {
            return null;
        }
    }

    @Override
    public DataStructure get() {
        return null;
    }

    enum Op {
        ADDITION,
        SUBSTRACTION
    }

}
