package no.ssb.vtl.script.operations.filter;

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
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.OrderingSpecification;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VtlFiltering;
import no.ssb.vtl.model.VtlOrdering;
import no.ssb.vtl.script.expressions.LiteralExpression;
import no.ssb.vtl.script.expressions.VariableExpression;
import no.ssb.vtl.script.expressions.VtlFilteringConverter;
import no.ssb.vtl.script.expressions.equality.AbstractEqualityExpression;
import no.ssb.vtl.script.expressions.equality.EqualExpression;
import no.ssb.vtl.script.expressions.equality.GraterThanExpression;
import no.ssb.vtl.script.expressions.equality.GreaterOrEqualExpression;
import no.ssb.vtl.script.expressions.equality.LesserOrEqualExpression;
import no.ssb.vtl.script.expressions.equality.LesserThanExpression;
import no.ssb.vtl.script.expressions.equality.NotEqualExpression;
import no.ssb.vtl.script.expressions.logic.AndExpression;
import no.ssb.vtl.script.expressions.logic.OrExpression;
import no.ssb.vtl.script.expressions.logic.XorExpression;
import no.ssb.vtl.script.operations.AbstractUnaryDatasetOperation;
import no.ssb.vtl.script.operations.join.ComponentBindings;
import no.ssb.vtl.script.operations.join.DataPointBindings;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

public class FilterOperation extends AbstractUnaryDatasetOperation {

    private final VTLExpression predicate;
    private final ComponentBindings componentBindings;

    public FilterOperation(Dataset dataset, VTLExpression predicate, ComponentBindings componentBindings) {
        super(checkNotNull(dataset, "the dataset was null"));
        this.predicate = checkNotNull(predicate);
        this.componentBindings = checkNotNull(componentBindings);
    }

    protected DataStructure computeDataStructure() {
        return getChild().getDataStructure();
    }

    @Override
    public Stream<DataPoint> computeData(Ordering ordering, Filtering filtering, Set<String> components) {
        DataPointBindings dataPointBindings = new DataPointBindings(componentBindings, getDataStructure());

        VtlOrdering childrenOrdering = (VtlOrdering) unsupportedOrdering(ordering);
        VtlFiltering childrenFiltering = (VtlFiltering) unsupportedFiltering(filtering);

        Stream<DataPoint> data = getChild().computeData(childrenOrdering, childrenFiltering, components)
                .map(dataPointBindings::setDataPoint)
                .filter(bindings -> {
                    VTLObject resolved = predicate.resolve(dataPointBindings);
                    return resolved.get() == null ? false : VTLBoolean.of((Boolean) resolved.get()).get();
                })
                .map(DataPointBindings::getDataPoint);
        return data;
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        return Optional.empty();
    }

    /**
     * In the case of the filter operation, any filter than was received is combined with the
     * actual expression filter.
     */
    @Override
    public FilteringSpecification unsupportedFiltering(FilteringSpecification filtering) {
        try {
            VtlFiltering operationFilter = VtlFilteringConverter.convert(predicate);
            if (Filtering.ALL != filtering) {
                VtlFiltering transposed = VtlFiltering.using(this).transpose(filtering);
                return VtlFiltering.using(this).and(transposed, operationFilter).build();
            } else {
                return VtlFiltering.using(this).with(operationFilter);
            }
        } catch (Exception e) {
            throw new ParseCancellationException("could not transform filter expression to specification", e);
        }
    }

    @Override
    public OrderingSpecification unsupportedOrdering(OrderingSpecification ordering) {
        return new VtlOrdering(ordering, getChild().getDataStructure());
    }
}
