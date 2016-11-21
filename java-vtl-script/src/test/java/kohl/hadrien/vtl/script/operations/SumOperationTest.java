package kohl.hadrien.vtl.script.operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.model.Identifier;
import kohl.hadrien.vtl.model.Measure;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hadrien on 21/11/2016.
 */
public class SumOperationTest {

    ObjectMapper mapper = new ObjectMapper();


    /**
     * Both Datasets must have at least one Identifier Component
     * in common (with the same name and data type).
     */
    @Test
    public void testIdenfitierNotASubset() throws Exception {

        Dataset left = mock(Dataset.class);
        Dataset right = mock(Dataset.class);

        SoftAssertions softly = new SoftAssertions();
        try {
            when(left.getDataStructure()).thenReturn(DataStructure.of(mapper::convertValue,
                    "ID1", Identifier.class, String.class,
                    "ID2", Identifier.class, String.class,
                    "ME1", Measure.class, Integer.class
            ));
            Throwable expectedThrowable = null;

            // Different name
            when(right.getDataStructure()).thenReturn(DataStructure.of(mapper::convertValue,
                    "ID1DIFFERENTNAME", Identifier.class, String.class,
                    "ID2", Identifier.class, String.class,
                    "ME1", Measure.class, Integer.class
            ));
            expectedThrowable = null;
            try {
                new SumOperation(left, right);
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common id name")
                    .isNotNull();

            // any order
            expectedThrowable = null;
            try {
                new SumOperation(right, left);
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common id name (reversed)")
                    .isNotNull();

            // Different type
            when(left.getDataStructure()).thenReturn(DataStructure.of(mapper::convertValue,
                    "ID1", Identifier.class, Integer.class,
                    "ID2", Identifier.class, String.class,
                    "ME1", Measure.class, Integer.class
            ));
            expectedThrowable = null;
            try {
                new SumOperation(left, right);
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common id type")
                    .isNotNull();

            // any order.
            expectedThrowable = null;
            try {
                new SumOperation(right, left);
            } catch (Throwable t) {
                expectedThrowable = t;
            }
            softly.assertThat(expectedThrowable)
                    .as("SumOperation exception when no common id type (reversed)")
                    .isNotNull();

        } finally {
            softly.assertAll();
        }
    }
}
