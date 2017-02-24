package no.ssb.vtl.script.operations;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static no.ssb.vtl.model.Component.Role.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FoldClauseTest {

    private static Dataset.DataPoint tuple(DataStructure structure, Object... values) {
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

            softly.assertThatThrownBy(() -> new FoldClause(null, validDimensionReference, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dataset")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new FoldClause(dataset, null, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dimensionReference")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new FoldClause(dataset, validDimensionReference, null, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("measureReference")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new FoldClause(dataset, validDimensionReference, validMeasureReference, null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("elements")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new FoldClause(dataset, "", validMeasureReference, validElements))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("dimensionReference")
                    .hasMessageContaining("empty");

            softly.assertThatThrownBy(() -> new FoldClause(dataset, validDimensionReference, "", validElements))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("measureReference")
                    .hasMessageContaining("empty");

            softly.assertThatThrownBy(() -> new FoldClause(dataset, validDimensionReference, validMeasureReference, Collections.emptySet()))
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
                FoldClause clause = new FoldClause(dataset, "newDimension", "newMeasure", invalidElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("m1")
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> {
                FoldClause clause = new FoldClause(invalidDataset, "newDimension", "newMeasure", wrongTypesElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("same type");

            FoldClause clause = new FoldClause(dataset, "newDimension", "newMeasure", validElements);
            softly.assertThat(clause.getDataStructure()).isNotNull();
        }
    }

    @Test
    public void testUnfold() throws Exception {

        Dataset dataset = mock(Dataset.class);
        DataStructure structure = DataStructure.of((o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class, // TODO: What if the dataset already contains id2?
                "measure1", MEASURE, String.class,
                "measure2", MEASURE, String.class,
                "attribute1", ATTRIBUTE, String.class // TODO: Okay with attributes?
        );
        when(dataset.getDataStructure()).thenReturn(structure);

        when(dataset.getData()).then(invocation -> Stream.of(
                tuple(structure, "id1-1", "id2-1", "measure1-1", "measure2-1", "attribute1-1"),
                tuple(structure, "id1-1", "id2-2", null, "measure2-2", "attribute1-2"),
                tuple(structure, "id1-2", "id2-1", "measure1-3", null, "attribute1-3"),
                tuple(structure, "id1-2", "id2-2", "measure1-4", "measure2-4", null),
                tuple(structure, "id1-3", "id2-1", null, null, null)
        ));

        // TODO: Order should be irrelevant!
        // Set<String> elements = Sets.newLinkedHashSet(Lists.newArrayList("measure2", "measure1", "attribute1"));
        Set<Component> elements = Sets.newLinkedHashSet(
                Lists.newArrayList(
                        structure.get("measure1"),
                        structure.get("measure2"),
                        structure.get("attribute1")
                )
        );

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            FoldClause clause = new FoldClause(dataset, "newId", "newMeasure", elements);

            softly.assertThat(clause.getDataStructure()).containsOnlyKeys(
                    "id1", "id2", "newId", "newMeasure"
            );

            softly.assertThat(clause.get()).flatExtracting(input -> input).extracting(VTLObject::get)
                    .containsExactly(
                            "id1-1", "id2-1", "measure1", "measure1-1",
                            "id1-1", "id2-1", "measure2", "measure2-1",
                            "id1-1", "id2-1", "attribute1", "attribute1-1",

                            // null
                            "id1-1", "id2-2", "measure2", "measure2-2",
                            "id1-1", "id2-2", "attribute1", "attribute1-2",

                            "id1-2", "id2-1", "measure1", "measure1-3",
                            // null
                            "id1-2", "id2-1", "attribute1", "attribute1-3",

                            "id1-2", "id2-2", "measure1", "measure1-4",
                            "id1-2", "id2-2", "measure2", "measure2-4"
                            // null
                    );
        }
    }
}
