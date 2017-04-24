package no.ssb.vtl.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.connectors.testutil.ConstantClockSource;
import no.ssb.vtl.connectors.util.TimeUtil;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import org.junit.After;
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
        SsbKlassApiConnector ssbConnector = new SsbKlassApiConnector(this.mapper, SsbKlassApiConnector.PeriodType.YEAR);
        this.connector = ssbConnector;
        mockServer = MockRestServiceServer.createServer(ssbConnector.getRestTemplate());
        TimeUtil.setClockSource(new ConstantClockSource(Instant.parse("2017-01-01T12:00:00.00Z")));
    }

    @After
    public void teardown() {
        TimeUtil.revertClockSource();
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
                entry("period", Component.Role.IDENTIFIER),
                entry("name", Component.Role.MEASURE)
        );

        assertThat(dataset.getDataStructure().getTypes()).containsExactly(
                entry("code", String.class),
                entry("period", String.class),
                entry("name", String.class)
        );

        assertThat(dataset.getData())
                .flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsSequence(
                        "0101", "2013", "Halden",
                        "0101", "2014", "Halden",
                        "0101", "2015", "Halden",
                        "0101", "2016", "Halden",
                        "0101", "2017", "Halden",
                        "0101", "2018", "Halden",
                        "0104", "2013", "Moss",
                        "0104", "2014", "Moss ny",
                        "0104", "2015", "Moss ny",
                        "0104", "2016", "Moss ny",
                        "0104", "2017", "Moss ny",
                        "0104", "2018", "Moss ny",
                        "0105", "2013", "Sarpsborg",
                        "0105", "2014", "Sarpsborg ny", //valid from 2014-06-01, but year 2014 gets the newest name
                        "0105", "2015", "Sarpsborg ny",
                        "0105", "2016", "Sarpsborg ny",
                        "0105", "2017", "Sarpsborg ny",
                        "0105", "2018", "Sarpsborg ny"
                );

    }

}
