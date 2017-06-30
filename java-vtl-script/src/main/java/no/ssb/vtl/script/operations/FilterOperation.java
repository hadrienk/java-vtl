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

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLPredicate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class FilterOperation extends AbstractUnaryDatasetOperation {

    private final Predicate<DataPoint> predicate;

    public FilterOperation(Dataset dataset, VTLPredicate vtlPredicate) {
        super(checkNotNull(dataset, "the dataset was null"));
        checkNotNull(vtlPredicate, "the predicate was null");
        this.predicate = vtlPredicate.toPredicate(false);
    }
    
    protected DataStructure computeDataStructure() {
        return getChild().getDataStructure();
    }


    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        Map<Boolean, List<DataPoint>> predicateResultMap = getChild().getData().collect(Collectors.partitioningBy(predicate));
        helper.addValue(predicateResultMap);
        helper.add("structure", getDataStructure());
        return helper.omitNullValues().toString();
    }

    @Override
    public Stream<DataPoint> getData() {
        return getChild().getData().filter(predicate);
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        return Optional.empty();
    }
}
