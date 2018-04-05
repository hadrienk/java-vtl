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

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.*;
import no.ssb.vtl.script.support.DatasetCloseWatcher;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static no.ssb.vtl.model.Component.Role.*;
import static org.mockito.Mockito.*;

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

        Set<Component> validElements = Sets.newHashSet(structure.values());
        Dataset dataset = mock(Dataset.class);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {

            softly.assertThatThrownBy(() -> new FoldOperation(null, validDimensionReference, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dataset")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, null, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dimensionReference")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, validDimensionReference, null, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("measureReference")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, validDimensionReference, validMeasureReference, null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("elements")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, "", validMeasureReference, validElements))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("dimensionReference")
                    .hasMessageContaining("empty");

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, validDimensionReference, "", validElements))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("measureReference")
                    .hasMessageContaining("empty");

            softly.assertThatThrownBy(() -> new FoldOperation(dataset, validDimensionReference, validMeasureReference, Collections.emptySet()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("elements")
                    .hasMessageContaining("empty");
        }
    }

    @Test
    public void testConstraint() throws Exception {

        Dataset dataset = mock(Dataset.class);
        DataStructure structure = DataStructure.of(
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "m1", MEASURE, String.class,
                "m2", MEASURE, String.class,
                "m3", MEASURE, String.class
        );
        Dataset invalidDataset = mock(Dataset.class);
        DataStructure wrongTypesDataset = DataStructure.of(
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "m1", MEASURE, Number.class,
                "m2", MEASURE, String.class,
                "m3", MEASURE, Instant.class
        );

        Set<Component> validElements = Sets.newHashSet(
                structure.get("m1"),
                structure.get("m2"),
                structure.get("m3")
        );

        Set<Component> invalidElements = Sets.newHashSet(
                structure.get("m1"),
                structure.get("m2"),
                structure.get("m3"),
                wrongTypesDataset.get("m1")
        );

        Set<Component> wrongTypesElements = Sets.newHashSet(
                wrongTypesDataset.values()
        );


        when(dataset.getDataStructure()).thenReturn(structure);
        when(invalidDataset.getDataStructure()).thenReturn(wrongTypesDataset);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThatThrownBy(() -> {
                FoldOperation clause = new FoldOperation(dataset, "newDimension", "newMeasure", invalidElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("m1")
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
                .addComponent("id2", IDENTIFIER, String.class ) // TODO: What if the dataset already contains id2?
                .addComponent("measure1", MEASURE, String.class)
                .addComponent("measure2", MEASURE, String.class) // TODO: Okay with attributes?
                .addComponent("attribute1", ATTRIBUTE, String.class)

                .addPoints("id1-1", "id2-1", "measure1-1", "measure2-1", "attribute1-1")
                .addPoints("id1-1", "id2-2", null, "measure2-2", "attribute1-2")
                .addPoints("id1-2", "id2-1", "measure1-3", null, "attribute1-3")
                .addPoints("id1-2", "id2-2", "measure1-4", "measure2-4", null)
                .addPoints("id1-3", "id2-1", null, null, null)

                .build());

        // Collections.shuffle(data, new Random(randomLong()));

        DataStructure structure = dataset.getDataStructure();

        // Randomly shuffle the measures
        ArrayList<Component> elements = Lists.newArrayList(
                structure.get("measure2"),
                structure.get("measure1"),
                structure.get("attribute1")
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
                    "id1", "id2", "newId", "newMeasure"
            );

            // Need to sort back before assert.
            Order order = Order.create(clause.getDataStructure())
                    .put("id1", Order.Direction.ASC)
                    .put("id2", Order.Direction.ASC)
                    .put("newId", Order.Direction.ASC)
                    .build();

            Stream<DataPoint> stream = clause.getData();
            softly.assertThat(stream.sorted(order)).flatExtracting(input -> input).extracting(VTLObject::get)
                    .containsExactly(
                            "id1-1", "id2-1", "attribute1", "attribute1-1",
                            "id1-1", "id2-1", "measure1", "measure1-1",
                            "id1-1", "id2-1", "measure2", "measure2-1",

                            "id1-1", "id2-2", "attribute1", "attribute1-2",
                            // null
                            "id1-1", "id2-2", "measure2", "measure2-2",

                            "id1-2", "id2-1", "attribute1", "attribute1-3",
                            "id1-2", "id2-1", "measure1", "measure1-3",
                            // null

                            // null
                            "id1-2", "id2-2", "measure1", "measure1-4",
                            "id1-2", "id2-2", "measure2", "measure2-4"
                    );
            stream.close();

            softly.assertThat(dataset.allStreamWereClosed()).isTrue();
        }
    }
}
