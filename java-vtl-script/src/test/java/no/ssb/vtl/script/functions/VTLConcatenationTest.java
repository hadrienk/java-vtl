package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLObject;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VTLConcatenationTest {

    private VTLConcatenation vtlConcatenation;

    @Before
    public void setUp() throws Exception {
        vtlConcatenation = new VTLConcatenation();
    }

    @Test
    public void testInvoke() throws Exception {
        VTLObject<?> result = vtlConcatenation.invoke(
                Lists.newArrayList(
                        VTLObject.of("leftParameter"),
                        VTLObject.of("rightParameter")
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLObject.of("leftParameter".concat("rightParameter")));

    }

    @Test
    public void testInvokeLeftNull() throws Exception {
        VTLObject<?> result = vtlConcatenation.invoke(
                Lists.newArrayList(
                        VTLObject.of((String) null),
                        VTLObject.of("rightParameter")
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLObject.of("leftParameter".concat("rightParameter")));

    }

    @Test
    public void testInvokeRightNull() throws Exception {
        VTLObject<?> result = vtlConcatenation.invoke(
                Lists.newArrayList(
                        VTLObject.of("leftParameter"),
                        VTLObject.of((String) null)
                )
        );

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLObject.of("leftParameter".concat("rightParameter")));

    }
}
