package no.ssb.vtl.test;

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

import com.google.common.collect.ForwardingObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.DatapointNormalizer;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.StaticDataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class RandomizedDataset extends ForwardingObject implements Dataset {

    private final Random random;
    private final StaticDataset delegate;

    private List<DataPoint> shuffledData;
    private DataStructure shuffledStructure;

    public static RandomizedDataset create(Random random, StaticDataset dataset) {
        return new RandomizedDataset(random, dataset);
    }

    private RandomizedDataset(Random random, StaticDataset dataset) {
        this.delegate = checkNotNull(dataset);
        this.random = checkNotNull(random);
    }

    public RandomizedDataset shuffleData() {
        if (shuffledData == null) {
            List<DataPoint> collect = delegate.getData().collect(Collectors.toList());
            Collections.shuffle(collect, this.random);
            this.shuffledData = collect;
        }
        return this;
    }

    public RandomizedDataset shuffleStructure() {
        if (shuffledStructure == null) {
            DataStructure structure = delegate().getDataStructure();
            ArrayList<Map.Entry<String, Component>> entries = Lists.newArrayList(structure.entrySet());
            Collections.shuffle(entries, this.random);
            shuffledStructure = DataStructure.copyOf(ImmutableMap.copyOf(entries)).build();
        }
        return this;
    }

    @Override
    public Stream<DataPoint> getData() {
        Stream<DataPoint> stream = shuffledData != null ? Lists.newArrayList(shuffledData).stream() : delegate.getData();
        return stream.map(DataPoint::create).map(new DatapointNormalizer(delegate.getDataStructure(), getDataStructure()));
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        return shuffledData != null ? Optional.of((long) shuffledData.size()) : delegate.getSize();
    }

    @Override
    public DataStructure getDataStructure() {
        return shuffledStructure != null ? shuffledStructure : delegate.getDataStructure();
    }

    @Override
    protected StaticDataset delegate() {
        return this.delegate;
    }
}
