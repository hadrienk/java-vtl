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

import com.google.common.collect.ImmutableList;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.OrderingSpecification;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Base class for DatasetOperations.
 */
public abstract class AbstractDatasetOperation implements Dataset {

    private final ImmutableList<AbstractDatasetOperation> children;
    private DataStructure cache;

    public AbstractDatasetOperation(Collection<Dataset> children) {
        ImmutableList.Builder<AbstractDatasetOperation> childrenCopy = ImmutableList.builder();
        for (Dataset child : children) {
            if (child instanceof AbstractDatasetOperation) {
                childrenCopy.add((AbstractDatasetOperation) child);
            } else {
                childrenCopy.add(new DatasetOperationWrapper(child));
            }
        }
        this.children = childrenCopy.build();
    }

    @Override
    public final Stream<DataPoint> getData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Optional<Stream<DataPoint>> getData(Ordering orders, Filtering filtering, Set<String> components) {
        return Optional.of(computeData(orders, filtering, components));
    }

    public abstract Stream<DataPoint> computeData(Ordering orders, Filtering filtering, Set<String> components);


    @Override
    public final Optional<Stream<DataPoint>> getData(Ordering order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Optional<Stream<DataPoint>> getData(Filtering filtering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Optional<Stream<DataPoint>> getData(Set<String> components) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final DataStructure getDataStructure() {
        return cache = (cache == null ? computeDataStructure() : cache);
    }

    /**
     * Implementation uses this method to create the datastructure only once.
     */
    protected abstract DataStructure computeDataStructure();

    /**
     * Returns true if asking for data stream with the given filtering is supported
     */
    public abstract Boolean supportsFiltering(FilteringSpecification filtering);

    /**
     * Returns true if asking for data stream with the given ordering is supported
     */
    public abstract Boolean supportsOrdering(OrderingSpecification filtering);

    /**
     * Returns the children {@link AbstractDatasetOperation} of this operation.
     */
    public ImmutableList<AbstractDatasetOperation> getChildren() {
        return children;
    }
}
