package no.ssb.vtl.script.operations.repeat;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Streams;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RepeatDataset implements Dataset {

    private final Dataset source;
    private final ImmutableSet<String> identifiers;

    public RepeatDataset(Dataset source, Set<String> identifiers) {
        this.source = source;
        this.identifiers = ImmutableSet.copyOf(identifiers);
    }

    @Override
    public Stream<DataPoint> getData() {
        // Delay the creation.
        StreamSupport.stream(

        );
        Stream<DataPoint> data = source.getData();
        return null;
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return source.getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return source.getSize();
    }

    @Override
    public DataStructure getDataStructure() {
        return source.getDataStructure();
    }
}
