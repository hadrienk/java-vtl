package no.ssb.vtl.script.operations;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static no.ssb.vtl.model.Component.Role.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnfoldClauseTest {

    private static Dataset.Tuple tuple(DataStructure structure, Object... values) {
        checkArgument(values.length == structure.size());
        Map<String, Object> map = Maps.newHashMap();
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

            softly.assertThatThrownBy(() -> new UnfoldClause(null, validIdentifierReference, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dataset")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new UnfoldClause(dataset, null, validMeasureReference, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dimensionReference")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new UnfoldClause(dataset, validIdentifierReference, null, validElements))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("measureReference")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new UnfoldClause(dataset, validIdentifierReference, validMeasureReference, null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("elements")
                    .hasMessageContaining("null");

            softly.assertThatThrownBy(() -> new UnfoldClause(dataset, validIdentifierReference, validMeasureReference, Collections.emptySet()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("elements")
                    .hasMessageContaining("empty");
        }
    }

    @Test
    public void testConstraint() throws Exception {

        Set<String> validElements = Sets.newHashSet("some value");
        DataStructure structure = DataStructure.of((o, aClass) -> o,
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
                UnfoldClause clause = new UnfoldClause(dataset, invalidReference, validMeasure, validElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("dimension")
                    .hasMessageContaining(invalidReference.toString())
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> {
                UnfoldClause clause = new UnfoldClause(dataset, validDimension, invalidReference, validElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("measure")
                    .hasMessageContaining(invalidReference.toString())
                    .hasMessageContaining("not found");

            softly.assertThatThrownBy(() -> {
                UnfoldClause clause = new UnfoldClause(dataset, validMeasure, validDimension, validElements);
                clause.getDataStructure();
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(validMeasure.toString())
                    .hasMessageContaining("was not a dimension");


            UnfoldClause clause = new UnfoldClause(dataset, validDimension, validMeasure, validElements);
            clause.getDataStructure();
        }
    }

    @Test
    public void testUnfold() throws Exception {

        Set<String> elements = Sets.newLinkedHashSet(Arrays.asList("id2-1", "id2-2"));
        Dataset dataset = mock(Dataset.class);
        DataStructure structure = DataStructure.of((o, aClass) -> o,
                "id1", IDENTIFIER, String.class,
                "id2", IDENTIFIER, String.class,
                "measure1", MEASURE, String.class,
                "measure2", MEASURE, String.class,
                "attribute1", ATTRIBUTE, String.class
        );
        when(dataset.getDataStructure()).thenReturn(structure);

        when(dataset.get()).then(invocation -> Stream.of(
                tuple(structure, "id1-1", "id2-1", "measure1-1", "measure2-1", "attribute1-1"),
                tuple(structure, "id1-1", "id2-2", "measure1-2", "measure2-2", "attribute1-2"),
                tuple(structure, "id1-2", "id2-1", "measure1-3", "measure2-3", "attribute1-3"),
                tuple(structure, "id1-2", "id2-2", "measure1-4", "measure2-4", "attribute1-4"),
                tuple(structure, "id1-3", "id2-1", "measure1-5", "measure2-5", "attribute1-5")
        ));

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            UnfoldClause clause = new UnfoldClause(dataset, structure.get("id2"), structure.get("measure1"), elements);

            softly.assertThat(clause.getDataStructure()).containsOnlyKeys(
                    "id1", "id2-1", "id2-2"
            );

            softly.assertThat(clause.get()).flatExtracting(input -> input).extracting(DataPoint::getName)
                    .contains(
                            "id1", "id2-1", "id2-2",
                            "id1", "id2-1", "id2-2",
                            "id1", "id2-1", "id2-2"
                    );

            softly.assertThat(clause.get()).flatExtracting(input -> input).extracting(DataPoint::get)
                    .contains(
                            "id1-1", "measure1-1", "measure1-2",
                            "id1-2", "measure1-3", "measure1-4",
                            "id1-3", "measure1-5", null
                    );
        }
    }
}
