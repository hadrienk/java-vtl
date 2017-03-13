package no.ssb.vtl.script.operations;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.google.common.collect.ImmutableSet;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

        DataStructure structure = DataStructure.of((o, aClass) -> o,
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
        DataStructure structure = DataStructure.of((o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "m1", MEASURE, String.class,
                "m2", MEASURE, String.class,
                "m3", MEASURE, String.class
        );
        Dataset invalidDataset = mock(Dataset.class);
        DataStructure wrongTypesDataset = DataStructure.of((o, aClass) -> o,
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
    public void testFold() throws Exception {

        Dataset dataset = mock(Dataset.class);
        DataStructure structure = DataStructure.of((o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class, // TODO: What if the dataset already contains id2?
                "measure1", MEASURE, String.class,
                "measure2", MEASURE, String.class,
                "attribute1", ATTRIBUTE, String.class // TODO: Okay with attributes?
        );
        when(dataset.getDataStructure()).thenReturn(structure);

        // Randomly shuffle the data in.
        ArrayList<DataPoint> data = Lists.newArrayList(
                tuple(structure, "id1-1", "id2-1", "measure1-1", "measure2-1", "attribute1-1"),
                tuple(structure, "id1-1", "id2-2", null, "measure2-2", "attribute1-2"),
                tuple(structure, "id1-2", "id2-1", "measure1-3", null, "attribute1-3"),
                tuple(structure, "id1-2", "id2-2", "measure1-4", "measure2-4", null),
                tuple(structure, "id1-3", "id2-1", null, null, null)
        );
        Collections.shuffle(data, new Random(randomLong()));
        when(dataset.getData()).then(invocation -> data.stream());

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

            softly.assertThat(clause.getData().sorted(order)).flatExtracting(input -> input).extracting(VTLObject::get)
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
        }
    }
}
