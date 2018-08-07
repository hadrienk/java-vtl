package no.ssb.vtl.model;

import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DataPointTest {

    @Test
    public void testCreateWithCapacity() {
        DataPoint point = DataPoint.create(5);
        assertThat(point).containsExactly(
                VTLObject.NULL,
                VTLObject.NULL,
                VTLObject.NULL,
                VTLObject.NULL,
                VTLObject.NULL
        );
    }

    @Test
    public void testCreateWithSource() {
        List<VTLObject> source = Arrays.asList(
                VTLObject.of(0L),
                VTLObject.of(0.0D),
                VTLObject.of(""),
                VTLObject.of(false),
                VTLObject.of(Instant.EPOCH),
                VTLObject.NULL
        );

        DataPoint point = DataPoint.create(source);
        assertThat(point).containsExactlyElementsOf(
                source
        );
    }

    @Test
    public void testCreateWithoutConversion() {
        List<VTLObject> source = Arrays.asList(
                VTLObject.of(0L),
                VTLObject.of(0.0D),
                VTLObject.of(""),
                VTLObject.of(false),
                VTLObject.of(Instant.EPOCH),
                VTLObject.NULL
        );



        DataPoint point = DataPoint.create(
                VTLObject.of(0L),
                VTLObject.of(0.0D),
                VTLObject.of(""),
                VTLObject.of(false),
                VTLObject.of(Instant.EPOCH),
                VTLObject.NULL
        );

        assertThat(point).containsExactlyElementsOf(
                source
        );
    }

    @Test
    public void testCreateWithConversion() {
        List<VTLObject> source = Arrays.asList(
                VTLObject.of(0L),
                VTLObject.of(0.0D),
                VTLObject.of(""),
                VTLObject.of(false),
                VTLObject.of(Instant.EPOCH),
                VTLObject.NULL
        );



        DataPoint point = DataPoint.create(
                0L,
                0.0D,
                "",
                false,
                Instant.EPOCH,
                null
        );

        assertThat(point).containsExactlyElementsOf(
                source
        );
    }
}