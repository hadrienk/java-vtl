package no.ssb.vtl.script.functions;

import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class VTLSubstrTest implements VTLNumberFunctionTest {

    private VTLSubstr vtlSubstr;

    @Before
    public void setUp() throws Exception {
        vtlSubstr = VTLSubstr.getInstance();
    }

    @Test
    @Override
    public void testInvokeWithPositiveNumber() throws Exception {
        VTLObject<?> result = vtlSubstr.invoke(
                createArguments("Hello, world!", 2, 5)
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLString.of("llo, "));

        result = vtlSubstr.invoke(
                createArguments("Hello, world!", 0, 4)
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLString.of("Hell"));

        result = vtlSubstr.invoke(
                createArguments("", 0, 4)
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLString.of(""));

        //deviation from the VTL specification 1.1: return empty string
        //if start position greater than the whole length of the input string
        result = vtlSubstr.invoke(
                createArguments("Hello", 5, 2)
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLString.of(""));

        //length argument will be ignored
        result = vtlSubstr.invoke(
                createArguments("Hello", 3, 3)
        );
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(VTLString.of("lo"));
    }


    @Override
    public void testInvokeWithNegativeNumber() throws Exception {
        assertThatThrownBy(() -> vtlSubstr.invoke(
                createArguments("Hello, world!", -1, 4)
        ))
                .as("exception when passing -1 as start position")
                .hasMessage("Argument{name=startPosition, type=VTLInteger} must be greater than zero, was -1")
                .isExactlyInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> vtlSubstr.invoke(
                createArguments("Hello, world!", 1, -1)
        ))
                .as("exception when passing -1 as length")
                .hasMessage("Argument{name=length, type=VTLInteger} must be greater than zero, was -1")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Override
    public void testInvokeWithString() throws Exception {
    }

    @Test
    @Override
    public void testInvokeWithTooManyArguments() throws Exception {
        assertThatThrownBy(() -> vtlSubstr.invoke(
                Lists.newArrayList(
                        VTLString.of("Hello, world!"),
                        VTLNumber.of(1),
                        VTLNumber.of(1),
                        VTLNumber.of(1)
                )
        ))
                .as("exception when passing too many arguments")
                .hasMessage("expected 3 argument(s) but got 4")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Override
    public void testInvokeWithEmptyArgumentsList() throws Exception {
        assertThatThrownBy(() -> vtlSubstr.invoke(
                Lists.emptyList()
        ))
                .as("exception when passing no arguments")
                .hasMessage("Required argument not present")
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Override
    public void testInvokeWithNullValue() throws Exception {
        VTLObject<?> result = vtlSubstr.invoke(
                createArguments(null, 0, 4)
        );

        assertThat(result).isEqualTo(VTLNumber.of((Number)null));

        result = vtlSubstr.invoke(
                Lists.newArrayList(
                        VTLString.of("Hello, world!"),
                        VTLNumber.of(0),
                        VTLNumber.of((Long)null)
                )
        );

        assertThat(result).isEqualTo(VTLString.of("Hello, world!"));

        result = vtlSubstr.invoke(
                Lists.newArrayList(
                        VTLString.of("Hello, world!"),
                        VTLNumber.of((Long) null),
                        VTLNumber.of(2)
                )
        );

        assertThat(result).isEqualTo(VTLString.of("Hello, world!"));
    }

    private List<VTLObject> createArguments(String ds, Integer startPosition, Integer length) {
        return Lists.newArrayList(
                VTLString.of(ds),
                VTLNumber.of(startPosition),
                VTLNumber.of(length)
        );
    }
}
