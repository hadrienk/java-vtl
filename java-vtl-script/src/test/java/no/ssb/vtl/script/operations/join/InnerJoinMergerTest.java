package no.ssb.vtl.script.operations.join;

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
import com.google.common.base.CharMatcher;
import com.google.common.collect.ImmutableList;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class InnerJoinMergerTest extends RandomizedTest {


    private DataStructure dataStructure;

    @Before
    public void setUp() throws Exception {
        dataStructure = DataStructure.builder()
                .put("A", Component.Role.IDENTIFIER, String.class)
                .put("B", Component.Role.IDENTIFIER, String.class)
                .put("C", Component.Role.IDENTIFIER, String.class)
                .put("D", Component.Role.IDENTIFIER, String.class)
                .put("E", Component.Role.IDENTIFIER, String.class)
                .put("F", Component.Role.IDENTIFIER, String.class)
                .build();

    }

    private DataStructure randomDataStructure(int size) {
        DataStructure.Builder builder = DataStructure.builder();
        ImmutableList<Component> values = ImmutableList.copyOf(dataStructure.values());
        for (int i = 0; i < size; i++) {
            if (rarely()) {
                builder.put("NULL" + i, Component.Role.IDENTIFIER, String.class);
            } else {
                Component randomComponent = randomFrom(values);
                builder.put(dataStructure.getName(randomComponent) + i, randomComponent);
            }
        }
        return builder.build();
    }

    @Test
    @Repeat(iterations = 100)
    public void testRandom() {
        int rightStructureSize = randomIntBetween(dataStructure.size() / 2, dataStructure.size());
        int leftStructureSize = randomIntBetween(dataStructure.size() / 2, dataStructure.size());

        DataStructure left = randomDataStructure(leftStructureSize);
        DataStructure right = randomDataStructure(rightStructureSize);

        InnerJoinMerger merger = new InnerJoinMerger(left, right);

        DataPoint leftDataPoint = DataPoint.create(left.size());

        // Extracting the identifiers of the components from the values.
        List<String> rightNames = right.keySet().stream().map(s -> CharMatcher.digit().removeFrom(s)).collect(toList());
        DataPoint rightDataPoint = DataPoint.create(rightNames.toArray());

        DataPoint result = merger.apply(leftDataPoint, rightDataPoint);

        Map<Component, VTLObject> resultAsMap = left.asMap(result);
        Map<Component, VTLObject> rightAsMap = right.asMap(rightDataPoint);

        for (Component component : resultAsMap.keySet()) {
            if (rightAsMap.containsKey(component)) {
                assertThat(resultAsMap.get(component).get()).isEqualTo(rightAsMap.get(component).get());
            }
        }

    }
}
