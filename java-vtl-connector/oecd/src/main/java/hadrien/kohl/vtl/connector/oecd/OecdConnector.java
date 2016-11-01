package hadrien.kohl.vtl.connector.oecd;

import kohl.hadrien.vtl.connector.Connector;
import kohl.hadrien.vtl.connector.ConnectorException;
import kohl.hadrien.vtl.model.Dataset;
import org.springframework.web.client.RestTemplate;

/**
 * A {@link Connector} implementation to access data from OECD.
 */
public class OecdConnector implements Connector {

    // Search URL:
    static final String SEARCH = "https://data.oecd.org/search-api/";
    private final RestTemplate restTemplate;

    public OecdConnector() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public boolean canHandle(String identifier) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Dataset getDataset(String identifier) throws ConnectorException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
