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

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DatapointNormalizerTest extends RandomizedTest {

    private DataStructure structure;
    private List<VTLObject> datum;

    @Before
    public void setUp() {
        this.structure = DataStructure.builder()
                .put("A", Component.Role.IDENTIFIER, String.class)
                .put("B", Component.Role.IDENTIFIER, String.class)
                .put("C", Component.Role.IDENTIFIER, String.class)
                .put("D", Component.Role.IDENTIFIER, String.class)
                .put("E", Component.Role.IDENTIFIER, String.class)
                .put("F", Component.Role.IDENTIFIER, String.class)
                .put("G", Component.Role.IDENTIFIER, String.class)
                .put("H", Component.Role.IDENTIFIER, String.class)
                .put("I", Component.Role.IDENTIFIER, String.class)
                .put("J", Component.Role.IDENTIFIER, String.class)
                .put("K", Component.Role.IDENTIFIER, String.class)
                .build();

        this.datum = Stream.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k").map(VTLObject::of).collect(Collectors.toList());

    }

    @Test
    @Repeat(iterations = 100)
    public void testApplyWithFilter() {

        int size = between(1, 10);

        DataStructure from = reduceStructure(structure, size);
        DataStructure shuffledStructure = shuffleStructure(from, getRandom());

        Set<Object> filter = Sets.newHashSet();
        List<String> columns = Lists.newArrayList(from.keySet());
        int numFilter = between(1, from.size());
        for (int i = 0; i < numFilter; i++)
            filter.add(randomFrom(columns));

        DatapointNormalizer normalizer = new DatapointNormalizer(from, shuffledStructure, filter::contains);

        DataPoint datapoint = DataPoint.create(datum.subList(0, size));
        DataPoint result = normalizer.apply(datapoint);

        assertThat(
                from.asMap(datapoint).values()
        ).containsExactlyElementsOf(
                shuffledStructure.asMap(result).values()
        );

    }

    @Test
    @Repeat(iterations = 100)
    public void testApply() {

        int size = between(1, 10);

        DataStructure from = reduceStructure(structure, size);
        DataStructure shuffledStructure = shuffleStructure(from, getRandom());

        DatapointNormalizer normalizer = new DatapointNormalizer(from, shuffledStructure);

        DataPoint datapoint = DataPoint.create(datum.subList(0, size));
        DataPoint result = normalizer.apply(datapoint);

        assertThat(
                from.asMap(datapoint).values()
        ).containsExactlyElementsOf(
                shuffledStructure.asMap(result).values()
        );

    }

    private DataStructure shuffleStructure(DataStructure structure, Random random) {
        List<Map.Entry<String, Component>> list = Lists.newArrayList(structure.entrySet());
        Collections.shuffle(list, getRandom());
        return DataStructure.copyOf(ImmutableMap.copyOf(list)).build();
    }

    private DataStructure reduceStructure(DataStructure structure, int size) {
        List<Map.Entry<String, Component>> list = Lists.newArrayList(structure.entrySet()).subList(0, size);
        return DataStructure.copyOf(ImmutableMap.copyOf(list)).build();
    }
}
