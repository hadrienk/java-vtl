package kohl.hadrien.console.sandbox;

import kohl.hadrien.vtl.model.Dataset;
import org.assertj.core.util.Compatibility;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SsbConnectorTest {

    @Test
    public void testStructure() throws Exception {

        SsbConnector ssbConnector = new SsbConnector();
        String identifier = "http://data.ssb.no/api/v0/en/table/04724";

        assertThat(ssbConnector.canHandle(identifier)).isTrue();

        Dataset dataset = ssbConnector.getDataset(identifier);

        assertThat(dataset).isNotNull();
        assertThat(dataset.get()).isNotNull();
        dataset.get().forEach(System.out::println);

    }
}