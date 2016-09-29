package kohl.hadrien.vtl.script;

import kohl.hadrien.Dataset;

/**
 * Interface that allows the dataset
 */
public interface DatasetConnector {

  Dataset getDataset(String identifier);

  void putDataset(String identifier, Dataset dataset);

}
