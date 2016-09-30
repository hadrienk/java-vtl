package kohl.hadrien.vtl.script.connector;

import kohl.hadrien.Dataset;

/**
 * Interface that allows the dataset
 */
public interface Connector {

  boolean canHandle(String identifier);

  Dataset getDataset(String identifier) throws ConnectorException;

  Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException;

}
