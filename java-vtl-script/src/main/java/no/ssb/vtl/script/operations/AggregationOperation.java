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

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.error.TypeException;
import no.ssb.vtl.script.operations.aggregation.AbstractAggregationFunction;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class AggregationOperation extends AbstractUnaryDatasetOperation {

    private final List<Component> groupBy;
    private final List<Component> aggregationComponents;
    private final AbstractAggregationFunction<? extends VTLNumber> aggregationFunction;
    private final List<String> columnNameToAggregate;

    public AggregationOperation(Dataset child, List<Component> groupBy, List<Component> aggregationComponents, AbstractAggregationFunction<? extends VTLNumber> aggregationFunction) {
        super(child);
        this.groupBy = groupBy;
        this.aggregationComponents = aggregationComponents;
        this.aggregationFunction = aggregationFunction;

        ImmutableList.Builder<String> columnNamesBuilder = ImmutableList.builder();
        DataStructure childStructure = getChild().getDataStructure();
        for (String columnName : childStructure.keySet()) {
            Component component = childStructure.get(columnName);
            if (aggregationComponents.contains(component)) {
                columnNamesBuilder.add(columnName);
            }
        }
        this.columnNameToAggregate = columnNamesBuilder.build();
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

    public DataPoint aggregate(List<DataPoint> datapoints) {
        ImmutableList<String> columns = ImmutableList.copyOf(getDataStructure().keySet());
        ImmutableList<String> childColumns = ImmutableList.copyOf(getChild().getDataStructure().keySet());

        DataPoint result = DataPoint.create(columns.size());
        DataPoint firstRow = datapoints.get(0);
        for (String columnName : columns) {
            result.set(
                    columns.indexOf(columnName), firstRow.get(childColumns.indexOf(columnName))
            );
        }

        for (String columnName : columnNameToAggregate) {
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
        return result;
    }

    @Override
    public Stream<DataPoint> getData() {
        DataStructure childStructure = getChild().getDataStructure();
    
        Order.Builder builder = Order.create(childStructure);
        groupBy.forEach(component -> builder.put(component, Order.Direction.ASC));
        Order order = builder.build();
    
        Stream<DataPoint> data = getChild().getData(order).orElseGet(() -> getChild().getData().sorted(order));
        Stream<List<DataPoint>> groupedDataPoints = StreamUtils.aggregate(data,
                (previous, current) -> order.compare(previous, current) == 0)
                .onClose(data::close);
        
        return groupedDataPoints.map(this::aggregate);
    }

    /**
     * Returns the count of unique values by column.
     */
    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    /**
     * Return the amount of {@link DataPoint} the stream obtained by the
     * method {@link Dataset#getData()} will return.
     */
    @Override
    public Optional<Long> getSize() {
        return Optional.empty();
    }
}
