package no.ssb.vtl.script.operations;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.DataStructure;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Addition and subtraction operation
 * TODO: Use join.
 */
public class SumOperation implements BinaryOperator<DataPoint>, Supplier<DataStructure> {

    private final Function<DataPoint, VTLObject> leftExtractor;
    private final DataStructure leftDataStructure;
    private final Function<DataPoint, VTLObject> rightExtractor;
    private final DataStructure rightDataStructure;
    private final Number scalar;

    public SumOperation(Function<DataPoint, VTLObject> leftExtractor,
                        DataStructure leftDataStructure,
                        Function<DataPoint, VTLObject> rightExtractor,
                        DataStructure rightDataStructure
    ) {
        this.leftExtractor = checkNotNull(leftExtractor);
        this.leftDataStructure = checkNotNull(leftDataStructure);
        this.rightExtractor = checkNotNull(rightExtractor);
        this.rightDataStructure = checkNotNull(rightDataStructure);
        this.scalar = null;
    }

    public SumOperation(Function<DataPoint, VTLObject> extractor, DataStructure dataStructure, Number scalar) {
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
    BinaryOperator<DataPoint> getTupleOperator() {
        return new BinaryOperator<DataPoint>() {
            @Override
            public DataPoint apply(DataPoint dataPoints, DataPoint dataPoints2) {
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
    public DataPoint apply(DataPoint left, DataPoint right) {
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
