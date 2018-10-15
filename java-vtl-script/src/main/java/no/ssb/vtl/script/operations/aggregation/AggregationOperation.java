package no.ssb.vtl.script.operations.aggregation;

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

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.OrderingSpecification;
import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VtlFiltering;
import no.ssb.vtl.model.VtlOrdering;
import no.ssb.vtl.script.error.TypeException;
import no.ssb.vtl.script.operations.AbstractDatasetOperation;
import no.ssb.vtl.script.operations.AbstractUnaryDatasetOperation;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class AggregationOperation extends AbstractUnaryDatasetOperation {

    private final List<Component> groupBy;
    private final List<Component> aggregationComponents;
    private final AbstractAggregationFunction<? extends VTLNumber> aggregationFunction;

    private final List<String> aggregateColumns;
    private final ImmutableList<String> columns;
    private final ImmutableList<String> childColumns;
    private final ImmutableList<String> groupByColumns;

    public AggregationOperation(Dataset child, List<Component> groupBy, List<Component> aggregationComponents, AbstractAggregationFunction<? extends VTLNumber> aggregationFunction) {
        super(child);
        this.groupBy = groupBy;
        this.aggregationComponents = aggregationComponents;
        this.aggregationFunction = aggregationFunction;

        this.groupByColumns = computeGroupByColumns();
        this.aggregateColumns = computeColumnsToAggregate(aggregationComponents);
        this.columns = ImmutableList.copyOf(getDataStructure().keySet());
        this.childColumns = ImmutableList.copyOf(getChild().getDataStructure().keySet());
    }

    private ImmutableList<String> computeGroupByColumns() {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        DataStructure structure = getDataStructure();
        for (String columnName : structure.keySet()) {
            Component component = structure.get(columnName);
            if (groupBy.contains(component)) {
                builder.add(columnName);
            }
        }
        return builder.build();
    }

    private ImmutableList<String> computeColumnsToAggregate(List<Component> aggregationComponents) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        DataStructure childStructure = getChild().getDataStructure();
        for (String columnName : childStructure.keySet()) {
            Component component = childStructure.get(columnName);
            if (aggregationComponents.contains(component)) {
                builder.add(columnName);
            }
        }
        return builder.build();
    }

    @Override
    protected DataStructure computeDataStructure() {
        DataStructure.Builder newDataStructure = DataStructure.builder();
        DataStructure childStructure = getChild().getDataStructure();
        for (String column : childStructure.keySet()) {
            Component component = childStructure.get(column);
            if (groupBy.contains(component)) {
                newDataStructure.put(column, component);
            } else if (aggregationComponents.contains(component)) {
                Class<?> originalType = component.getType();
                if (Number.class.isAssignableFrom(originalType)) {
                    Class<?> newType = aggregationFunction.getVTLReturnTypeFor(originalType);
                    newDataStructure.put(column, component.getRole(), newType);
                } else {
                    // TODO: This should be handled in the visitor (before execution)
                    throw new ParseCancellationException(
                            new TypeException(String.format("Cannot aggregate component %s of type %s. It must be numeric", column, component.getType()), "VTL-02xx"));
                }
            }
        }
        return newDataStructure.build();
    }

    @Override
    public FilteringSpecification unsupportedFiltering(FilteringSpecification filtering) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public OrderingSpecification unsupportedOrdering(OrderingSpecification ordering) {
        // We need all the aggregation columns to be ordered first.
        // The direction does not matter.
        List<String> firstColumns = ordering.columns().subList(0, groupByColumns.size());
        throw new UnsupportedOperationException("TODO");
    }

    private DataPoint aggregate(List<DataPoint> datapoints) {

        DataPoint result = DataPoint.create(columns.size());

        // Aggregate and copy into the result.
        for (String columnName : aggregateColumns) {
            List<VTLNumber> list = Lists.newArrayListWithExpectedSize(datapoints.size());
            int childIndex = childColumns.indexOf(columnName);
            int index = columns.indexOf(columnName);
            for (DataPoint datapoint : datapoints) {
                VTLObject value = datapoint.get(childIndex);
                // That's why VTLObject.NULL should be removed.
                if (value == VTLObject.NULL) {
                    if (getChild().getDataStructure().get(columnName).getType() == Double.class) {
                        value = VTLFloat.of((Double) null);
                    } else {
                        value = VTLInteger.of((Long) null);
                    }
                }
                list.add((VTLNumber) value);
            }
            result.set(index, aggregationFunction.apply(list));
        }

        // Copy the values of the group by columns.
        DataPoint firstRow = datapoints.get(0);
        for (String columnName : groupByColumns) {
            result.set(
                    columns.indexOf(columnName), firstRow.get(childColumns.indexOf(columnName))
            );
        }

        return result;
    }

    @Override
    public Stream<DataPoint> computeData(Ordering orders, Filtering filtering, Set<String> components) {

        AbstractDatasetOperation childOperation = getChild();

        // Adjust the order to support aggregation. In order to reduce load we should
        // try to put the columns with the least values last.
        ImmutableMap.Builder<String, Ordering.Direction> groupByOrder = ImmutableMap.builder();
        for (String column : groupByColumns) {
            Ordering.Direction direction = orders.columns().size() > 0 ? orders.getDirection(column) : Ordering.Direction.ASC;
            if (direction.equals(Ordering.Direction.ANY)) {
                direction = Ordering.Direction.ASC;
            }
            groupByOrder.put(column, direction);
        }
        VtlOrdering groupByOrdering = new VtlOrdering(groupByOrder.build(), childOperation.getDataStructure());

        // Pass the filter to the child op. Keep result as post filter.
        VtlFiltering aggregationFilter = VtlFiltering.using(childOperation).transpose(filtering);

        Stream<DataPoint> stream = childOperation.computeData(groupByOrdering, aggregationFilter, components);

        stream = StreamUtils.aggregate(stream, (previous, current) -> groupByOrdering.compare(previous, current) == 0)
                .onClose(stream::close).map(this::aggregate);

        //if (postFilter != null) {
        //    stream = stream.filter(new VtlFiltering(getDataStructure(), filtering));
        //}

        // Need to reorder.
        if (!groupByOrdering.equals(orders)) {
            stream = stream.sorted(orders);
        }

        return stream;
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return getChild().getDistinctValuesCount().map(distinct ->
                Maps.filterKeys(distinct, groupByColumns::contains)
        );
    }

    @Override
    public Optional<Long> getSize() {
        //
        return Optional.empty();
    }
}
