package no.ssb.vtl.model;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2018 Hadrien Kohl
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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static no.ssb.vtl.model.Ordering.Direction.ASC;

/**
 * Use this class to simplify implementation of the {@link Ordering} and {@link OrderingSpecification}
 */
public class VtlOrdering implements Ordering, OrderingSpecification {

    private final ImmutableMap<String, Direction> delegate;
    private final int[] indices;
    private final Direction[] directions;

    public VtlOrdering(OrderingSpecification specification, DataStructure structure) {
        this(toMap(specification), structure);
    }

    public VtlOrdering(Map<String, Direction> specification, DataStructure structure) {
        this.delegate = ImmutableMap.copyOf(specification);

        ArrayList<Integer> indices = Lists.newArrayList();
        ArrayList<Direction> directions = Lists.newArrayList();

        ImmutableList<String> columns = ImmutableList.copyOf(structure.keySet());
        for (String column : specification.keySet()) {
            indices.add(columns.indexOf(column));
            directions.add(specification.get(column));
        }

        this.indices = Ints.toArray(indices);
        this.directions = directions.toArray(new Direction[]{});
    }

    public static ImmutableMap<String, Direction> toMap(OrderingSpecification specification) {
        ImmutableMap.Builder<String, Direction> delegate = ImmutableMap.builder();
        for (String column : specification.columns()) {
            delegate.put(column, specification.getDirection(column));
        }
        return delegate.build();
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        for (String column : columns()) {
            stringHelper.add(column, getDirection(column));
        }
        return stringHelper.toString();
    }

    @Override
    public List<String> columns() {
        return delegate.keySet().asList();
    }

    @Override
    public Direction getDirection(String column) {
        return delegate.get(column);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compare(DataPoint dp1, DataPoint dp2) {
        int result;

        for (int i = 0; i < indices.length; i++) {

            Comparable o1 = (Comparable) dp1.get(indices[i]).get();
            Comparable o2 = (Comparable) dp2.get(indices[i]).get();

            result = o1.compareTo(o2);
            if (result != 0) {
                return directions[i] == ASC ? result : -result;
            }
        }
        return 0;
    }
}
