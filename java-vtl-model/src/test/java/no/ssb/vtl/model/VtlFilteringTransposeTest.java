package no.ssb.vtl.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VtlFilteringTransposeTest {

    DataStructure ab = DataStructure.of(
            "a", Component.Role.IDENTIFIER, String.class,
            "b", Component.Role.IDENTIFIER, String.class
    );

    DataStructure bc = DataStructure.of(
            "b", Component.Role.IDENTIFIER, String.class,
            "c", Component.Role.IDENTIFIER, String.class
    );

    @Test
    public void testTransposeSingle() {
        VtlFiltering filteringA = VtlFiltering.using(ab).with(
                VtlFiltering.eq("a", "x")
        );

        // A is not present in bc.
        assertThat(
                VtlFiltering.using(bc).transpose(filteringA).toString()
        ).isEqualTo("TRUE");

        VtlFiltering filteringB = VtlFiltering.using(ab).with(
                VtlFiltering.eq("b", "x")
        );

        // B is present in bc.
        assertThat(
                VtlFiltering.using(bc).transpose(filteringB).toString()
        ).isEqualTo("b=x");
    }

    @Test
    public void testTransposeAnds() {

        VtlFiltering filteringAB = VtlFiltering.using(ab).and(
                VtlFiltering.eq("a", "x"),
                VtlFiltering.eq("b", "x")
        ).build();

        // A is not present in bc.
        assertThat(
                VtlFiltering.using(bc).transpose(filteringAB).toString()
        ).isEqualTo("(TRUE&b=x)");

        VtlFiltering filteringAA = VtlFiltering.using(ab).and(
                VtlFiltering.lt("b", "d"),
                VtlFiltering.gt("b", "g")
        ).build();

        assertThat(
                VtlFiltering.using(bc).transpose(filteringAA).toString()
        ).isEqualTo("(b<d&b>g)");

        VtlFiltering filteringNotAB = VtlFiltering.using(ab).and(
                VtlFiltering.not(VtlFiltering.eq("a", "x")),
                VtlFiltering.eq("b", "x")
        ).build();

        assertThat(
                VtlFiltering.using(bc).transpose(filteringNotAB).toString()
        ).as("transposed %s", filteringNotAB).isEqualTo("(TRUE&b=x)");

    }

    @Test
    public void testTransposeOrs() {
        VtlFiltering filteringAB = VtlFiltering.using(ab).or(
                VtlFiltering.eq("a", "x"),
                VtlFiltering.eq("b", "x")
        ).build();

        // A is not present in bc.
        assertThat(
                VtlFiltering.using(bc).transpose(filteringAB).toString()
        ).isEqualTo("TRUE");

        VtlFiltering filteringAA = VtlFiltering.using(ab).or(
                VtlFiltering.lt("b", "d"),
                VtlFiltering.gt("b", "g")
        ).build();

        assertThat(
                VtlFiltering.using(bc).transpose(filteringAA).toString()
        ).isEqualTo("(b<d|b>g)");

        VtlFiltering filteringNotAB = VtlFiltering.using(ab).or(
                VtlFiltering.neq("a", "x"),
                VtlFiltering.eq("b", "x")
        ).build();

        assertThat(
                VtlFiltering.using(bc).transpose(filteringNotAB).toString()
        ).as("transposed %s", filteringNotAB).isEqualTo("TRUE");
    }
}