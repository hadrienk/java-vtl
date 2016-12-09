package no.ssb.vtl.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import kohl.hadrien.vtl.connector.Connector;
import kohl.hadrien.vtl.model.Dataset;
import org.junit.Before;
import org.junit.Test;

public class SsbApiConnectorTest {

    private ObjectMapper mapper;
    private Connector connector;

    @Before
    public void setUp() throws Exception {
        this.mapper = new ObjectMapper();
        //this.mapper.registerModule(new GuavaModule());
        //this.mapper.registerModule(new Jdk8Module());
        //this.mapper.registerModule(new JavaTimeModule());
        //this.mapper.registerModule(new JsonStatModule());
        this.connector = new SsbApiConnector(this.mapper);
    }

    @Test
    public void testGetDataset() throws Exception {

        Dataset dataset = this.connector.getDataset("1106");

    }
}
