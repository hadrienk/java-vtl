package no.ssb.vtl.script.operations.fold;

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

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.StaticDataset;
import no.ssb.vtl.script.support.DatasetCloseWatcher;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static no.ssb.vtl.model.Component.Role.ATTRIBUTE;
import static no.ssb.vtl.model.Component.Role.IDENTIFIER;
import static no.ssb.vtl.model.Component.Role.MEASURE;

public class FoldOperationTest extends RandomizedTest {

    private static DataPoint tuple(DataStructure structure, Object... values) {
        checkArgument(values.length == structure.size());
        Map<String, Object> map = Maps.newLinkedHashMap();
        Iterator<Object> iterator = Lists.newArrayList(values).iterator();
        for (String name : structure.keySet()) {
            map.put(name, iterator.next());
        }
        return structure.wrap(map);
    }

    @Test
    public void testArguments() throws Exception {

        String validDimensionReference = "aDimension";
        String validMeasureReference = "aDimension";

        DataStructure structure = DataStructure.of(
                "element1", MEASURE, String.class,
                "element2", MEASURE, String.class
        );

        Set<String> validElements = Sets.newHashSet(structure.keySet());
        Dataset dataset = Mockito.mock(Dataset.class);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {

            softly.assertThatThrownBy(() -> new FoldOperation(null, validDimensionReference, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class);

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, null, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class);

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, validDimensionReference, null, validElements))
                    .isInstanceOf(NullPointerException.class);

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, validDimensionReference, validMeasureReference, null))
                    .isInstanceOf(NullPointerException.class);

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, "", validMeasureReference, validElements))
                    .isInstanceOf(IllegalArgumentException.class);

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, validDimensionReference, "", validElements))
                    .isInstanceOf(IllegalArgumentException.class);

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, validDimensionReference, validMeasureReference, Collections.emptySet()))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void testConstraint() throws Exception {

        Dataset dataset = Mockito.mock(Dataset.class);
        DataStructure structure = DataStructure.of(
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "m1", MEASURE, String.class,
                "m2", MEASURE, String.class,
                "m3", MEASURE, String.class
        );
        Dataset invalidDataset = Mockito.mock(Dataset.class);
        DataStructure wrongTypesDataset = DataStructure.of(
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "m1", MEASURE, Number.class,
                "m2", MEASURE, String.class,
                "m3", MEASURE, Instant.class
        );

        Set<String> validElements = Sets.newHashSet(
                "m1",
                "m2",
                "m3"
        );

        Set<String> invalidElements = Sets.newHashSet(
                "m1",
                "m2",
                "m3",
                "m4"
        );

        Set<String> wrongTypesElements = Sets.newHashSet(
                wrongTypesDataset.keySet()
        );

        Mockito.when(dataset.getDataStructure()).thenReturn(structure);
        Mockito.when(invalidDataset.getDataStructure()).thenReturn(wrongTypesDataset);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThatThrownBy(() -> {
                FoldOperation clause = new FoldOperation(dataset, "newDimension", "newMeasure", invalidElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("m4")
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> {
                FoldOperation clause = new FoldOperation(invalidDataset, "newDimension", "newMeasure", wrongTypesElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("same type");

            FoldOperation clause = new FoldOperation(dataset, "newDimension", "newMeasure", validElements);
            softly.assertThat(clause.getDataStructure()).isNotNull();
        }
    }

    @Test
    @Repeat(iterations = 10)
    public void testFold() {

        DatasetCloseWatcher dataset = DatasetCloseWatcher.wrap(StaticDataset.create()
                .addComponent("id1", IDENTIFIER, String.class)
                .addComponent("id2", IDENTIFIER, String.class )
                .addComponent("measure1", MEASURE, String.class)
                .addComponent("measure2", MEASURE, String.class)
                .addComponent("measure3", MEASURE, String.class)
                .addComponent("attribute", ATTRIBUTE, String.class)

                .addPoints("id1-1", "id2-1", "measure1-1", "measure2-1", "measure3-1", "attribute1-1")
                .addPoints("id1-1", "id2-2", null,         "measure2-2", "measure3-2", "attribute1-2")
                .addPoints("id1-2", "id2-1", "measure1-3", null,         "measure3-3", "attribute1-3")
                .addPoints("id1-2", "id2-2", "measure1-4", "measure2-4", null,         "attribute1-4")
                .addPoints("id1-3", "id2-1", "measure1-5", "measure2-5", "measure3-5",         null)

                .build());

        // Collections.shuffle(data, new Random(randomLong()));

        // Randomly shuffle the measures
        ArrayList<String> elements = Lists.newArrayList(
                "measure2",
                "measure1",
                "measure3"
        );
        Collections.shuffle(elements, new Random(randomLong()));

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {

            FoldOperation clause = new FoldOperation(
                    dataset,
                    "newId",
                    "newMeasure",
                    ImmutableSet.copyOf(elements)
            );

            softly.assertThat(clause.getDataStructure()).containsOnlyKeys(
                    "id1", "id2", "newId", "newMeasure", "attribute"
            );

            // Need to sort back before assert.
            Order order = Order.create(clause.getDataStructure())
                    .put("id1", Ordering.Direction.ASC)
                    .put("id2", Ordering.Direction.ASC)
                    .put("newId", Ordering.Direction.ASC)
                    .build();

            Stream<DataPoint> stream = clause.getData();
            softly.assertThat(stream.sorted(order))
                    .containsExactly(
                            DataPoint.create("id1-1", "id2-1", "attribute1-1", "measure1", "measure1-1"),
                            DataPoint.create("id1-1", "id2-1", "attribute1-1", "measure2", "measure2-1"),
                            DataPoint.create("id1-1", "id2-1", "attribute1-1", "measure3", "measure3-1"),

                            // DataPoint.create("id1-1", "id2-2", null, "measure1", "measure1-2"),
                            DataPoint.create("id1-1", "id2-2", "attribute1-2", "measure2", "measure2-2"),
                            DataPoint.create("id1-1", "id2-2", "attribute1-2", "measure3", "measure3-2"),

                            DataPoint.create("id1-2", "id2-1", "attribute1-3", "measure1", "measure1-3"),
                            // DataPoint.create("id1-2", "id2-1", null, "measure2", "measure2-3"),
                            DataPoint.create("id1-2", "id2-1", "attribute1-3", "measure3", "measure3-3"),

                            DataPoint.create("id1-2", "id2-2", "attribute1-4", "measure1", "measure1-4"),
                            DataPoint.create("id1-2", "id2-2", "attribute1-4", "measure2", "measure2-4"),
                            //DataPoint.create("id1-2", "id2-2", null, "measure3", "measure3-4"),

                            DataPoint.create("id1-3", "id2-1", null, "measure1", "measure1-5"),
                            DataPoint.create("id1-3", "id2-1", null, "measure2", "measure2-5"),
                            DataPoint.create("id1-3", "id2-1", null, "measure3", "measure3-5")
                    );
            stream.close();

            softly.assertThat(dataset.allStreamWereClosed()).isTrue();
        }
    }
}
