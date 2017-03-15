package no.ssb.vtl.script.visitors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class AggregationVisitorTest {
    
    private DataStructure dataStructure;
    private AggregationVisitor visitor;
    private TestableDataset dataset;
    
    @Before
    public void setUp() throws Exception {
        dataStructure = DataStructure.of(
                (o, aClass) -> o,
                "time", Component.Role.IDENTIFIER, String.class,
                "geo", Component.Role.IDENTIFIER, String.class,
                "m1", Component.Role.MEASURE, Integer.class,
                "m2", Component.Role.MEASURE, Double.class,
                "at1", Component.Role.ATTRIBUTE, String.class);
        visitor = new AggregationVisitor();
        dataset = new TestableDataset(Arrays.asList(
                dataStructure.wrap(ImmutableMap.of("time", "2010", "geo", "NO", "m1", 20, "m2", 10.0, "at1", "blabla")),
                dataStructure.wrap(ImmutableMap.of("time", "2010", "geo", "SE", "m1", 40, "m2", 10.0, "at1", "blabla")),
                dataStructure.wrap(ImmutableMap.of("time", "2010", "geo", "DK", "m1", 60, "m2", 10.0, "at1", "blabla")),
                dataStructure.wrap(ImmutableMap.of("time", "2011", "geo", "NO", "m1", 11, "m2", 10.0, "at1", "blabla")),
                dataStructure.wrap(ImmutableMap.of("time", "2011", "geo", "SE", "m1", 31, "m2", 10.0, "at1", "blabla")),
                dataStructure.wrap(ImmutableMap.of("time", "2011", "geo", "DK", "m1", 51, "m2", 10.0, "at1", "blabla")),
                dataStructure.wrap(ImmutableMap.of("time", "2012", "geo", "NO", "m1", 72, "m2", 10.0, "at1", "blabla")),
                dataStructure.wrap(ImmutableMap.of("time", "2012", "geo", "SE", "m1", 82, "m2", 10.0, "at1", "blabla")),
                dataStructure.wrap(ImmutableMap.of("time", "2012", "geo", "DK", "m1", 92, "m2", 10.0, "at1", "blabla"))
            ),dataStructure);
        
    }
    
    @Test
    public void testSum() throws Exception {
    
        List<Component> components = Lists.newArrayList(dataStructure.getOrDefault("time", null));
        AggregationVisitor.AggregationOperation sumOperation = visitor.getSumOperation(dataset,components);
        sumOperation.getData().forEach(System.out::println);
    
        assertThat(sumOperation.getData()).contains(
                dataStructure.wrap(ImmutableMap.of("time", "2010", "m1", 20+40+60)),
                dataStructure.wrap(ImmutableMap.of("time", "2011", "m1", 11+31+51)),
                dataStructure.wrap(ImmutableMap.of("time", "2012", "m1", 72+82+92))
        );
    
    }
    
    
    private final class TestableDataset implements Dataset {
        
        private final List<DataPoint> data;
        private final DataStructure dataStructure;
        
        protected TestableDataset(List<DataPoint> data, DataStructure dataStructure) {
            this.data = data;
            this.dataStructure = dataStructure;
        }
        
        @Override
        public Stream<DataPoint> getData() {
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
        
    }
}