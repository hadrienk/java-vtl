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
 * Base class for dataset operations.
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

    public abstract Stream<DataPoint> computeData(Ordering orders, Filtering filtering, Set<String> components);

    @Override
    public final Stream<DataPoint> getData() {
        return computeData(Ordering.ANY, Filtering.ALL, computeDataStructure().keySet());
    }

    @Override
    public final Optional<Stream<DataPoint>> getData(Ordering orders, Filtering filtering, Set<String> components) {
        return Optional.of(computeData(orders, filtering, components));
    }

    @Override
    public final Optional<Stream<DataPoint>> getData(Ordering order) {
        return Optional.of(computeData(order, Filtering.ALL, computeDataStructure().keySet()));
    }

    @Override
    public final Optional<Stream<DataPoint>> getData(Filtering filtering) {
        return Optional.of(computeData(Ordering.ANY, filtering, computeDataStructure().keySet()));
    }

    @Override
    @Deprecated
    public final Optional<Stream<DataPoint>> getData(Set<String> components) {
        return Optional.of(computeData(Ordering.ANY, Filtering.ALL, components));
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
     * Returns a copy of the filtering specification this operation cannot handle.
     */
    public abstract FilteringSpecification unsupportedFiltering(FilteringSpecification filtering);

    /**
     * Returns a copy of the ordering specification this operation cannot handle.
     */
    public abstract OrderingSpecification unsupportedOrdering(OrderingSpecification filtering);

    /**
     * Returns the children {@link AbstractDatasetOperation} of this operation.
     */
    public ImmutableList<AbstractDatasetOperation> getChildren() {
        return children;
    }
}
