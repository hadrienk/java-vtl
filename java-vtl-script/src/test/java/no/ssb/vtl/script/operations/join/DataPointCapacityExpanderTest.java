package no.ssb.vtl.script.operations.join;

import no.ssb.vtl.model.DataPoint;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DataPointCapacityExpanderTest {

    private DataPoint datapoint;

    @Before
    public void setUp() throws Exception {
        datapoint = DataPoint.create("1", "2", "3", "4");
    }

    @Test
    public void testDoesNothingIfCapacityIsUnder() {
        DataPointCapacityExpander expander = new DataPointCapacityExpander(datapoint.size() - 1);

        Integer sizeBefore = datapoint.size();
        expander.accept(datapoint);
        assertThat(datapoint.size()).isEqualTo(sizeBefore);
    }

    @Test
    public void testDoesNothingIfCapacityIsEqual() {
        DataPointCapacityExpander expander = new DataPointCapacityExpander(datapoint.size());

        Integer sizeBefore = datapoint.size();
        expander.accept(datapoint);
        assertThat(datapoint.size()).isEqualTo(sizeBefore);
    }

    @Test
    public void testCapacityIsUnder() {
        DataPointCapacityExpander expander = new DataPointCapacityExpander(datapoint.size() * 2);

        Integer sizeBefore = datapoint.size();
        expander.accept(datapoint);
        assertThat(datapoint.size()).isEqualTo(sizeBefore * 2);

    }
}