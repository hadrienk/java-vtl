package no.ssb.vtl.script.operations.repeat;

import com.google.common.collect.Streams;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A Dataset that returns its data based on an iterator.
 */
public class IteratorDataset implements Dataset {

    private final Iterator<DataPoint> source;
    private final DataStructure structure;

    public IteratorDataset(
            DataStructure structure,
            Iterator<DataPoint> source
    ) {
        this.source = source;
        this.structure = structure;
    }

    @Override
    public Stream<DataPoint> getData() {
        return Streams.stream(this.source);
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        return Optional.empty();
    }

    @Override
    public DataStructure getDataStructure() {
        return structure;
    }
}
