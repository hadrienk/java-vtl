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

import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.VTLObject;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static no.ssb.vtl.model.Component.Role.*;
import static org.mockito.Mockito.*;

public class UnfoldOperationTest {

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

        Dataset dataset = mock(Dataset.class);
        Component validIdentifierReference = mock(Component.class);
        Component validMeasureReference = mock(Component.class);
        Set<String> validElements = Sets.newHashSet("element1, element2");

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {

            softly.assertThatThrownBy(() -> new UnfoldOperation(null, validIdentifierReference, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dataset")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new UnfoldOperation(dataset, null, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dimensionReference")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new UnfoldOperation(dataset, validIdentifierReference, null, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("measureReference")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new UnfoldOperation(dataset, validIdentifierReference, validMeasureReference, null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("elements")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new UnfoldOperation(dataset, validIdentifierReference, validMeasureReference, Collections.emptySet()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("elements")
                    .hasMessageContaining("empty");
        }
    }

    @Test
    public void testConstraint() throws Exception {

        Set<String> validElements = Sets.newHashSet("some value");
        DataStructure structure = DataStructure.of(
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "measure1", MEASURE, String.class
        );
        Dataset dataset = mock(Dataset.class);
        when(dataset.getDataStructure()).thenReturn(structure);

        Component validDimension = structure.get("id2");
        Component validMeasure = structure.get("measure1");
        Component invalidReference = mock(Component.class);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThatThrownBy(() -> {
                UnfoldOperation clause = new UnfoldOperation(dataset, invalidReference, validMeasure, validElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("dimension")
                    .hasMessageContaining(invalidReference.toString())
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> {
                UnfoldOperation clause = new UnfoldOperation(dataset, validDimension, invalidReference, validElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("measure")
                    .hasMessageContaining(invalidReference.toString())
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> {
                UnfoldOperation clause = new UnfoldOperation(dataset, validMeasure, validDimension, validElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(validMeasure.toString())
                    .hasMessageContaining("was not a dimension");


            UnfoldOperation clause = new UnfoldOperation(dataset, validDimension, validMeasure, validElements);
            clause.getDataStructure();
        }
    }

    @Test
    public void testUnfoldUnsorted() throws Exception {
        Set<String> elements = Sets.newLinkedHashSet(Arrays.asList("id2-1", "id2-2"));
        Dataset dataset = mock(Dataset.class);

        DataStructure structure = DataStructure.of(
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "measure1", MEASURE, String.class,
                "measure2", MEASURE, String.class,
                "attribute1", ATTRIBUTE, String.class
        );
        when(dataset.getDataStructure()).thenReturn(structure);

        ArrayList<DataPoint> data = Lists.newArrayList(
                tuple(structure, "id1-1", "id2-1", "measure1-1", "measure2-1", "attribute1-1"),
                tuple(structure, "id1-1", "id2-2", "measure1-2", "measure2-2", "attribute1-2"),
                tuple(structure, "id1-2", "id2-1", "measure1-3", "measure2-3", "attribute1-3"),
                tuple(structure, "id1-2", "id2-2", "measure1-4", "measure2-4", "attribute1-4"),
                tuple(structure, "id1-3", "id2-1", "measure1-5", "measure2-5", "attribute1-5")
        );
        Collections.shuffle(data);

        when(dataset.getData()).then(invocation -> Stream.of(

        ));

    }

    @Test
    @Repeat(iterations = 10)
    public void testUnfold() throws Exception {

        Set<String> elements = Sets.newLinkedHashSet(Arrays.asList("id2-1", "id2-2"));
        Dataset dataset = mock(Dataset.class);

        DataStructure structure = DataStructure.of(
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "measure1", MEASURE, String.class,
                "measure2", MEASURE, String.class,
                "attribute1", ATTRIBUTE, String.class
        );
        when(dataset.getDataStructure()).thenReturn(structure);

        ArrayList<DataPoint> data = Lists.newArrayList(
                tuple(structure, "id1-1", "id2-1", "measure1-1", "measure2-1", "attribute1-1"),
                tuple(structure, "id1-1", "id2-2", "measure1-2", "measure2-2", "attribute1-2"),
                tuple(structure, "id1-2", "id2-1", "measure1-3", "measure2-3", "attribute1-3"),
                tuple(structure, "id1-2", "id2-2", "measure1-4", "measure2-4", "attribute1-4"),
                tuple(structure, "id1-3", "id2-1", "measure1-5", "measure2-5", "attribute1-5")
        );
        Collections.shuffle(data);

        when(dataset.getData()).then(invocation -> data.stream());
        when(dataset.getData(any(Order.class))).thenReturn(Optional.empty());

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            UnfoldOperation clause = new UnfoldOperation(dataset, structure.get("id2"), structure.get("measure1"), elements);

            softly.assertThat(clause.getDataStructure()).containsOnlyKeys(
                    "id1", "id2-1", "id2-2"
            );

            softly.assertThat(clause.getData()).flatExtracting(input -> input).extracting(VTLObject::get)
                    .containsExactly(
                            "id1-1", "measure1-1", "measure1-2",
                            "id1-2", "measure1-3", "measure1-4",
                            "id1-3", "measure1-5", null
                    );
        }
    }
}
