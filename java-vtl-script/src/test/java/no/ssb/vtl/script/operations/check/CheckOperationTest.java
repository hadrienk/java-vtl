package no.ssb.vtl.script.operations.check;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.operations.CheckOperation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CheckOperationTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testArgumentDataset() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expect(hasProperty("message", containsString("dataset was null")));
        new CheckOperation(null, "", "", null, null);
    }

    @Test
    public void testArgumentRowsToReturn() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expect(hasProperty("message", containsString("notInSpec")));
        new CheckOperation(mock(Dataset.class), "notInSpec", "measures", null, null);
    }

    @Test
    public void testArgumentComponentsToReturn() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expect(hasProperty("message", containsString("notInSpec")));
        new CheckOperation(mock(Dataset.class), "valid", "notInSpec", null, null);
    }
}
