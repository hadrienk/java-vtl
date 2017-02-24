package no.ssb.vtl.script.operations.join;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.junit.Test;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CrossJoinOperationTest {

    @Test
    public void testDefault() throws Exception {
        Dataset ds1 = mock(Dataset.class, "ds1");
        Dataset ds2 = mock(Dataset.class, "ds1");

        DataStructure structure1 = DataStructure.of(
                (o, aClass) -> o,
                "time", IDENTIFIER, Year.class,
                "ref_area", IDENTIFIER, String.class,
                "partner", IDENTIFIER, String.class,
                "obs_value", MEASURE, Integer.class,
                "obs_status", ATTRIBUTE, String.class
        );

        DataStructure structure2 = DataStructure.of(
                (o, aClass) -> o,
                "time", IDENTIFIER, Year.class,
                "ref_area", IDENTIFIER, String.class,
                "partner", IDENTIFIER, String.class,
                "obs_value", MEASURE, Integer.class,
                "obs_status", ATTRIBUTE, String.class
        );

        given(ds1.getDataStructure()).willReturn(structure1);
        given(ds2.getDataStructure()).willReturn(structure2);

        given(ds1.get()).willReturn(Stream.of(
                tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "CA"),
                        structure1.wrap("obs_value", "20"),
                        structure1.wrap("obs_status", "E")
                ), tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "LEFT"),
                        structure1.wrap("obs_value", "2"),
                        structure1.wrap("obs_status", "P")
                ), tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "NONELEFT"),
                        structure1.wrap("obs_value", "2"),
                        structure1.wrap("obs_status", "P")
                )
        ));

        given(ds2.get()).willReturn(Stream.of(
                tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "CA"),
                        structure1.wrap("obs_value", "20"),
                        structure1.wrap("obs_status", "E")
                ), tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "RIGHT"),
                        structure1.wrap("obs_value", "2"),
                        structure1.wrap("obs_status", "P")
                ), tuple(
                        structure1.wrap("time", Year.of(2010)),
                        structure1.wrap("ref_area", "EU25"),
                        structure1.wrap("partner", "NONERIGHT"),
                        structure1.wrap("obs_value", "2"),
                        structure1.wrap("obs_status", "P")
                )
        ));

        AbstractJoinOperation result = new CrossJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        VTLPrintStream vtlPrintStream = new VTLPrintStream(System.out);

        vtlPrintStream.println(result.getDataStructure());

        vtlPrintStream.println(result);
    }

    private DataPoint tuple(VTLObject... components) {
        return new DataPoint.AbstractDataPoint() {
            @Override
            protected List<VTLObject> delegate() {
                return Arrays.asList(components);
            }
        };
    }
}
