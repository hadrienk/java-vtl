package kohl.hadrien.vtl.script.operators;

import com.fasterxml.jackson.databind.ObjectMapper;
import kohl.hadrien.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnionOperatorTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testOneDatasetReturnedUnchanged() throws Exception {

        Dataset dataset = mock(Dataset.class);
        when(dataset.get()).thenReturn(Stream.empty());

        UnionOperator operator = new UnionOperator(dataset);

        assertThat(operator.get().get())
                .as("Check that result of union operation", null)
                .isSameAs(dataset.get());

    }


    @Test
    public void testSameIdentifierAndMeasures() throws Exception {

        SoftAssertions softly = new SoftAssertions();
        try {

            DataStructure dataStructure = DataStructure.of(mapper::convertValue,
                    "TIME", Identifier.class, String.class,
                    "GEO", Identifier.class, String.class,
                    "POP", Measure.class, Integer.class
            );

            Dataset dataset1 = mock(Dataset.class);
            Dataset dataset2 = mock(Dataset.class);
            Dataset dataset3 = mock(Dataset.class);

            when(dataset1.getDataStructure()).thenReturn(dataStructure);
            when(dataset2.getDataStructure()).thenReturn(dataStructure);
            when(dataset3.getDataStructure()).thenReturn(dataStructure);

            UnionOperator unionOperator = new UnionOperator(dataset1, dataset2, dataset3);
            softly.assertThat(unionOperator).isNotNull();

            DataStructure wrongStructure = DataStructure.of(mapper::convertValue,
                    "TIME2", Identifier.class, String.class,
                    "GEO2", Identifier.class, String.class,
                    "POP2", Measure.class, Integer.class
            );

            Dataset wrongDataset = mock(Dataset.class);
            when(wrongDataset.getDataStructure()).thenReturn(wrongStructure);
            Throwable expextedEx = null;
            try {
                new UnionOperator(dataset1, wrongDataset, dataset2, dataset3);
            } catch (Throwable t) {
                expextedEx = t;
            }
            softly.assertThat(expextedEx).isNotNull().hasMessageContaining("incompatible");

        } finally {
            softly.assertAll();
        }
    }

    @Test
    public void testUnion() throws Exception {

        DataStructure dataStructure = DataStructure.of(mapper::convertValue,
                "TIME", Identifier.class, String.class,
                "GEO", Identifier.class, String.class,
                "POP", Measure.class, Integer.class
        );

        Dataset totalPopulation1 = mock(Dataset.class);
        Dataset totalPopulation2 = mock(Dataset.class);
        when(totalPopulation1.getDataStructure()).thenReturn(dataStructure);
        when(totalPopulation2.getDataStructure()).thenReturn(dataStructure);

        when(totalPopulation1.get()).thenReturn(Stream.of(
                tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Belgium"),
                        dataStructure.wrap("TIME", 5)
                ), tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Greece"),
                        dataStructure.wrap("TIME", 2)
                ), tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "France"),
                        dataStructure.wrap("TIME", 3)
                ), tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Malta"),
                        dataStructure.wrap("TIME", 7)
                ), tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Finland"),
                        dataStructure.wrap("TIME", 9)
                ), tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Switzerland"),
                        dataStructure.wrap("TIME", 12)
                )
        ));

        when(totalPopulation1.get()).thenReturn(Stream.of(
                tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Netherlands"),
                        dataStructure.wrap("TIME", 23)
                ), tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Greece"),
                        dataStructure.wrap("TIME", 2)
                ), tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Spain"),
                        dataStructure.wrap("TIME", 5)
                ), tuple(
                        dataStructure.wrap("TIME", "2012"),
                        dataStructure.wrap("GEO", "Iceland"),
                        dataStructure.wrap("TIME", 1)
                )
        ));

        UnionOperator unionOperator = new UnionOperator(totalPopulation1, totalPopulation2);

        assertThat(unionOperator.get()).isNotNull();
        assertThat(unionOperator.get().stream()).isNotNull();

    }


    Dataset.Tuple tuple(Component... components) {
        return new Dataset.AbstractTuple() {
            @Override
            protected List<Component> delegate() {
                return Arrays.asList(components);
            }
        };
    }
}
