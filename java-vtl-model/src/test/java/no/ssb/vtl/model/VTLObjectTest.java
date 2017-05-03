package no.ssb.vtl.model;

import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class VTLObjectTest {

    @Test
    public void testIntegerCompare() throws Exception {

        VTLObject one = VTLObject.of(new Integer(1));
        VTLObject two = VTLObject.of(new Integer(2));
        VTLObject otherOne = VTLObject.of(new Integer(1));

        assertThat(one.compareTo(two)).isLessThan(0);
        assertThat(two.compareTo(one)).isGreaterThan(0);
        assertThat(one.compareTo(otherOne)).isEqualTo(0);
        assertThat(otherOne.compareTo(one)).isEqualTo(0);

    }

    @Test
    public void testDoubleCompare() throws Exception {

        VTLObject one = VTLObject.of(new Double(1));
        VTLObject two = VTLObject.of(new Double(2));
        VTLObject otherOne = VTLObject.of(new Double(1));

        assertThat(one.compareTo(two)).isLessThan(0);
        assertThat(two.compareTo(one)).isGreaterThan(0);
        assertThat(one.compareTo(otherOne)).isEqualTo(0);
        assertThat(otherOne.compareTo(one)).isEqualTo(0);

    }

    @Test
    public void testDifferentNumberCompare() throws Exception {

        VTLObject one = VTLObject.of(new Double(1));
        VTLObject two = VTLObject.of(new Integer(2));
        VTLObject otherOne = VTLObject.of(new Long(1));

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThatThrownBy(() -> one.compareTo(two))
                    .hasMessageContaining(" 1.0 ")
                    .hasMessageContaining("Double")
                    .hasMessageContaining(" 2 ")
                    .hasMessageContaining("Integer");

            softly.assertThatThrownBy(() -> two.compareTo(one))
                    .hasMessageContaining(" 2 ")
                    .hasMessageContaining("Double")
                    .hasMessageContaining(" 1.0 ")
                    .hasMessageContaining("Integer");

            softly.assertThatThrownBy(() -> assertThat(one.compareTo(otherOne)))
                    .hasMessageContaining(" 1 ")
                    .hasMessageContaining("Long")
                    .hasMessageContaining(" 1.0 ")
                    .hasMessageContaining("Double");


            softly.assertThatThrownBy(() -> assertThat(otherOne.compareTo(one)))
                    .hasMessageContaining(" 1 ")
                    .hasMessageContaining("Long")
                    .hasMessageContaining(" 1.0 ")
                    .hasMessageContaining("Double");
        }


    }
}
