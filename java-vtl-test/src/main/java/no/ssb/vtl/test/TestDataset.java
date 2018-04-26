package no.ssb.vtl.test;

import com.google.common.collect.ForwardingObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.StaticDataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class TestDataset extends ForwardingObject implements Dataset {

    private final Random random;
    private final StaticDataset delegate;

    private boolean variableSize;
    private boolean shuffleData;
    private boolean shuffleStructure;

    public TestDataset(Random random, StaticDataset dataset) {
        this.delegate = checkNotNull(dataset);
        this.random = checkNotNull(random);
    }

    @Override
    public Stream<DataPoint> getData() {
        Stream<DataPoint> stream = delegate().getData();

        if (shuffleStructure)
            // TODO

        if (!shuffleData)
            return stream;

        List<DataPoint> collect = stream.collect(Collectors.toList());
        Collections.shuffle(collect, this.random);
        return collect.stream();

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
        DataStructure structure = delegate().getDataStructure();
        if (!shuffleStructure)
            return structure;

        ArrayList<Map.Entry<String, Component>> entries = Lists.newArrayList(structure.entrySet());
        Collections.shuffle(entries, this.random);
        return DataStructure.copyOf(ImmutableMap.copyOf(entries)).build();
    }

    @Override
    protected StaticDataset delegate() {
        return this.delegate;
    }
}
