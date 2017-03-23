package no.ssb.vtl.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.InputStream;
import java.time.Instant;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

public class SsbKlassApiConnectorTest {

    private ObjectMapper mapper;
    private Connector connector;
    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        this.mapper = new ObjectMapper();
        SsbKlassApiConnector ssbConnector = new SsbKlassApiConnector(this.mapper);
        this.connector = ssbConnector;
        mockServer = MockRestServiceServer.createServer(ssbConnector.getRestTemplate());
    }


    @Test
    public void testCanHandle() throws Exception {

        String testUri = "http://data.ssb.no/api/klass/v1/classifications/131/codes?from=2013-01-01";
        assertThat(this.connector.canHandle(testUri));

        testUri = "http://data.ssb.no/api/v0/dataset/1106.json?lang=en";
        assertThat(!this.connector.canHandle(testUri));

    }

    @Test
    public void testGetDataset() throws Exception {

        InputStream fileStream = Resources.getResource(this.getClass(), "/codes131_from2013.json").openStream();

        mockServer.expect(requestTo("http://data.ssb.no/api/klass/v1/classifications/131/codes?from=2013-01-01"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        new InputStreamResource(fileStream),
                        MediaType.APPLICATION_JSON)
                );

        Dataset dataset = this.connector.getDataset("http://data.ssb.no/api/klass/v1/classifications/131/codes?from=2013-01-01");

        assertThat(dataset.getDataStructure().getRoles()).containsExactly(
                entry("code", Component.Role.IDENTIFIER),
                entry("validFrom", Component.Role.IDENTIFIER),
                entry("validTo", Component.Role.IDENTIFIER),
                entry("name", Component.Role.MEASURE)
        );

        assertThat(dataset.getDataStructure().getTypes()).containsExactly(
                entry("code", String.class),
                entry("validFrom", Instant.class),
                entry("validTo", Instant.class),
                entry("name", String.class)
        );

        assertThat(dataset.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsSequence(
                        "0101", Instant.parse("2012-12-31T23:00:00Z"), Instant.parse("9999-12-31T23:59:59.999Z"), "Halden",
                        "0104", Instant.parse("2012-12-31T23:00:00Z"), Instant.parse("9999-12-31T23:59:59.999Z"), "Moss"
                );

    }

}
