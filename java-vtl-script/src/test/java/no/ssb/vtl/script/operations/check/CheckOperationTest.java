package no.ssb.vtl.script.operations.check;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.operations.CheckOperation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CheckOperationTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testArgumentDataset() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expect(hasProperty("message", containsString("dataset was null")));
        new CheckOperation(null, null, null, null, null);
    }

    /**
     * NOTE: This is not in the spec for check with single rule, but exists for check with datapoint rulesets.
     * <p>
     * VTL 1.1, line 4799.
     */
    @Test
    public void testArgumentAllAndMeasuresToReturn() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expect(hasProperty("message", containsString("cannot use 'all' with 'measures'")));
        new CheckOperation(mock(Dataset.class), Optional.of(CheckOperation.RowsToReturn.ALL),
                Optional.of(CheckOperation.ComponentsToReturn.MEASURES), null, null);
    }

    /**
     * The input Dataset must have all Boolean Measure Components.
     * NOTE: The spec is not consistent with regard to parameters and constraints sections (line 4925-4926 and 4949).
     * We decided to require one and only one Boolean Measure Component.
     * <p>
     * VTL 1.1 line 4949.
     */
    @Test
    public void testArgumentDatasetComponentsTooManyBooleans() throws Exception {
        Dataset dataset = mock(Dataset.class);
        when(dataset.getDataStructure()).thenReturn(
                DataStructure.of((s, o) -> null,
                        "id1", Component.Role.IDENTIFIER, String.class,
                        "me1", Component.Role.MEASURE, Boolean.class,
                        "me2", Component.Role.MEASURE, Boolean.class,
                        "at1", Component.Role.ATTRIBUTE, String.class
                )
        );

        thrown.expect(IllegalArgumentException.class);
        thrown.expect(hasProperty("message", containsString("too many boolean")));
        new CheckOperation(dataset, Optional.of(CheckOperation.RowsToReturn.VALID),
                Optional.of(CheckOperation.ComponentsToReturn.MEASURES), null, null);
    }

    @Test
    public void testCheck() throws Exception {
        DataStructure dataStructure = DataStructure.of((s, o) -> s,
                "kommune_nr", Component.Role.IDENTIFIER, String.class,
                "code", Component.Role.IDENTIFIER, String.class, //from KLASS
                "attr1", Component.Role.ATTRIBUTE, String.class,
                "CONDITION", Component.Role.MEASURE, Boolean.class
        );

        Dataset ds = mock(Dataset.class);
        when(ds.getDataStructure()).thenReturn(dataStructure);

        when(ds.get()).thenReturn(Stream.of(
                tuple(
                        dataStructure.wrap("kommune_nr", "0101"),
                        dataStructure.wrap("code", "0101"),
                        dataStructure.wrap("attr1", "test"),
                        dataStructure.wrap("CONDITION", true)
                ), tuple(
                        dataStructure.wrap("kommune_nr", "9990"),
                        dataStructure.wrap("code", null), //not in the code list, so a null value
                        dataStructure.wrap("attr1", "-test-"),
                        dataStructure.wrap("CONDITION", false)
                ), tuple(
                        dataStructure.wrap("kommune_nr", "0104"),
                        dataStructure.wrap("code", "0104"),
                        dataStructure.wrap("attr1", "test"),
                        dataStructure.wrap("CONDITION", true)
                )
        ));

        CheckOperation checkOperation = new CheckOperation(ds, Optional.of(CheckOperation.RowsToReturn.NOT_VALID),
                Optional.of(CheckOperation.ComponentsToReturn.MEASURES), null, null);

        assertThat(checkOperation.getDataStructure().getRoles()).contains(
                entry("kommune_nr", Component.Role.IDENTIFIER),
                entry("code", Component.Role.IDENTIFIER),
                entry("attr1", Component.Role.ATTRIBUTE),
                entry("CONDITION", Component.Role.MEASURE),
                entry("errorcode", Component.Role.ATTRIBUTE),
                entry("errorlevel", Component.Role.ATTRIBUTE)
        );

        Stream<Dataset.Tuple> stream = checkOperation.stream();
        assertThat(stream).isNotNull();

        List<Dataset.Tuple> collect = stream.collect(toList());
        assertThat(collect).
                extractingResultOf("ids").isNotEmpty().hasSize(1);

        assertThat(getDataPoint(collect, "kommune_nr").get()).isEqualTo("9990");
        assertThat(getDataPoint(collect, "code").get()).isNull(); //not in the code list, so a null value
        assertThat(getDataPoint(collect, "attr1").get()).isEqualTo("-test-");
        assertThat(getDataPoint(collect, "CONDITION").get()).isEqualTo(false);
        assertThat(getDataPoint(collect, "errorlevel").get()).isNull();
        assertThat(getDataPoint(collect, "errorcode").get()).isNull();
    }

    private DataPoint getDataPoint(List<Dataset.Tuple> tuple, String componentName) {
        List<DataPoint> dpListOfOne = tuple.get(0).stream()
                .filter(dp -> dp.getName().equals(componentName))
                .collect(toList());

        assertThat(dpListOfOne).hasSize(1);

        return dpListOfOne.get(0);
    }

    private Dataset.Tuple tuple(DataPoint... components) {
        return new Dataset.AbstractTuple() {
            @Override
            protected List<DataPoint> delegate() {
                return Arrays.asList(components);
            }
        };
    }

}
