package kohl.hadrien.vtl.script.operators;

import kohl.hadrien.Dataset;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnionOperatorTest {


    @Test
    public void testOneDatasetReturnedUnchanged() throws Exception {

        Dataset dataset = mock(Dataset.class);
        when(dataset.get()).thenReturn(Stream.empty());

        UnionOperator operator = new UnionOperator(dataset);

        assertThat(operator.get())
                .as("Check that result of union operation", null)
                .isSameAs(dataset.get());

    }



    @Test
    public void testSameIdentifierAndMeasures() throws Exception {

        SoftAssertions softly = new SoftAssertions();
        try {

            Dataset dataset1 = null;
            Dataset dataset2 = null;
            Dataset dataset3 = null;

            UnionOperator unionOperator = new UnionOperator(dataset1, dataset2, dataset3);
            softly.assertThat(unionOperator).isNotNull();

            Dataset wrongId = null;
            Throwable expextedEx = null;
            try {
                new UnionOperator(dataset1, wrongId, dataset2, dataset3);
            } catch (Throwable t) {
                expextedEx = t;
            }
            assertThat(expextedEx).isNotNull();

            Dataset wrongMeasure = null;
            try {
                new UnionOperator(dataset1, wrongMeasure, dataset2, dataset3);
            } catch (Throwable t) {
                expextedEx = t;
            }
            assertThat(expextedEx).isNotNull();

        } finally {
            softly.assertAll();
        }
    }
}
