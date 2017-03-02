package no.ssb.vtl.model;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import no.ssb.vtl.model.Order.Direction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.*;

public class DatasetTest extends RandomizedTest {

    private final DataStructure structure = DataStructure.builder()
            .put("id1", IDENTIFIER, String.class)
            .put("id2", IDENTIFIER, String.class)
            .put("id3", IDENTIFIER, String.class)
            .put("me1", MEASURE, String.class)
            .put("me2", MEASURE, String.class)
            .put("me3", MEASURE, String.class)
            .put("at1", MEASURE, String.class)
            .put("at2", MEASURE, String.class)
            .put("at3", MEASURE, String.class)
            .build();

    @Test
    public void testDefaultSorting() throws Exception {
        // Checks that default implementation sorts the streams.

        List<Map.Entry<String, Component>> entries = Lists.newArrayList(structure.entrySet());
        Collections.shuffle(entries, new Random(RandomizedTest.randomLong()));
        DataStructure dataStructure = DataStructure.builder().putAll(entries).build();

        int datasize = RandomizedTest.randomIntBetween(10, 100);
        ArrayList<DataPoint> data = Lists.newArrayListWithCapacity(datasize);
        for (int i = 0; i < datasize; i++) {
            ImmutableMap.Builder<String, Object> row = ImmutableMap.builder();
            for (Map.Entry<String, Component> column : dataStructure.entrySet()) {
                row.put(column.getKey(), RandomizedTest.randomAsciiOfLengthBetween(0, 50));
            }
            data.add(dataStructure.wrap(row.build()));
        }

        Collections.shuffle(data, new Random(RandomizedTest.randomLong()));

        TestableDataset dataset = new TestableDataset(data, dataStructure);


        int sortedColumns = RandomizedTest.randomIntBetween(1, dataStructure.size());
        ArrayList<Map.Entry<String, Component>> structureEntries = Lists.newArrayList(dataStructure.entrySet());
        ImmutableMap.Builder<String, Direction> entriesToSortBy = ImmutableMap.builder();
        for (int i = 0; i < sortedColumns; i++) {
            Map.Entry<String, Component> entry = RandomizedTest.randomFrom(structureEntries);
            structureEntries.remove(entry);
            entriesToSortBy.put(
                    entry.getKey(),
                    Direction.ASC
            );
        }

        Order order = Order.from(entriesToSortBy.build().entrySet());
        Optional<Stream<? extends DataPoint>> stream = dataset.getData(order);

    }

    private final class TestableDataset implements Dataset {

        private final List<DataPoint> data;
        private final DataStructure dataStructure;

        protected TestableDataset(List<DataPoint> data, DataStructure dataStructure) {
            this.data = data;
            this.dataStructure = dataStructure;
        }

        @Override
        public Stream<? extends DataPoint> getData() {
            return data.stream();
        }

        @Override
        public Optional<Map<String, Integer>> getDistinctValuesCount() {
            return Optional.empty();
        }

        @Override
        public Optional<Long> getSize() {
            return Optional.of((long) data.size());
        }

        @Override
        public DataStructure getDataStructure() {
            return dataStructure;
        }

        @Override
        public Stream<DataPoint> get() {
            return null;
        }
    }
}
