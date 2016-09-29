package kohl.hadrien;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;

import org.junit.Test;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AbstractRelationalDatasetTest {
    @Test
    public void testMerge() throws Exception {

        Dataset dsA = getDatasetFrom(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), "A");
        Dataset dsB = getDatasetFrom(Stream.of(      3, 4, 5, 6, 7          ), "B");

        List<Dataset.Tuple> result = dsA.merge(dsB).collect(toList());
        assertThat(result)
                .extracting(Dataset.Tuple::ids)
                .flatExtracting(input -> input)
                .extracting(Supplier::get)
                .containsExactly(
                        3, 4, 5, 6, 7
                );
    }

    private Dataset getDatasetFrom(final Stream<Integer> ids, String name) {
        return new AbstractRelationalDataset() {
            @Override
            Stream<Tuple> delegate() {
                return getTupleStreamFrom(ids, name);
            }

            @Override
            public String toString() {
                return "dataset " + name;
            }
        };
    }

    private Stream<Dataset.Tuple> getTupleStreamFrom(Stream<Integer> ids, String name) {
        return ids.map(i -> new Dataset.AbstractTuple() {

            @Override
            public List<Identifier> ids() {
                return ImmutableList.of(new Identifier<Integer>() {

                    @Override
                    public Integer get() {
                        return i;
                    }
                });
            }

            @Override
            public List<Component> values() {
                return ImmutableList.of(new Measure<String>() {

                    @Override
                    public String get() {
                        return name + i;
                    }
                });
            }
        });
    }
}
