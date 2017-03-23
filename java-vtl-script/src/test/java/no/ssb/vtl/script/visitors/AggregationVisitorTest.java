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
    
    private AggregationVisitor visitor = new AggregationVisitor();
    private TestableDataset datasetSingleMeasure;
    private TestableDataset datasetMultiMeasure;
    
    @Before
    public void setUp() throws Exception {
        DataStructure dataStructureSingleMeasure = DataStructure.of((o, aClass) -> o, "time", Component.Role.IDENTIFIER,
                String.class, "geo", Component.Role.IDENTIFIER, String.class, "m1", Component.Role.MEASURE,
                Integer.class);
        datasetSingleMeasure = new TestableDataset(
                Arrays.asList(dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "NO", "m1", 20)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "SE", "m1", 40)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "DK", "m1", 60)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "NO", "m1", 11)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "SE", "m1", 31)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "DK", "m1", 51)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "NO", "m1", 72)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "SE", "m1", 82)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "DK", "m1", 92))), dataStructureSingleMeasure);
    
    
        DataStructure dataStructureMultiMeasure = DataStructure.of((o, aClass) -> o,
                "time",Component.Role.IDENTIFIER,String.class,
                "geo", Component.Role.IDENTIFIER, String.class,
                "m1", Component.Role.MEASURE,Integer.class,
                "m2", Component.Role.MEASURE,Integer.class);
        datasetMultiMeasure = new TestableDataset(
                Arrays.asList(dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "NO", "m1", 20, "m2", 2)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "SE", "m1", 40, "m2", 4)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "DK", "m1", 60, "m2", 6)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "NO", "m1", 11, "m2", 1)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "SE", "m1", 31, "m2", 3)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "DK", "m1", 51, "m2", 5)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "NO", "m1", 72, "m2", 7)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "SE", "m1", 82, "m2", 8)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "DK", "m1", 92, "m2", 9))),
                dataStructureMultiMeasure);
    
    
    }
    
    @Test
    public void testSumSingleMeasureDataSet() throws Exception {
    
        List<Component> components = Lists.newArrayList(datasetSingleMeasure.getDataStructure().getOrDefault("time", null));
        AggregationVisitor.AggregationOperation sumOperation = visitor.getSumOperation(datasetSingleMeasure,components);
        sumOperation.getData().forEach(System.out::println);
    
        DataStructure resultingDataStructure = sumOperation.getDataStructure();
    
        assertThat(resultingDataStructure.getRoles()).contains(
                entry("time", Component.Role.IDENTIFIER),
                entry("m1", Component.Role.MEASURE)
        );
    
        assertThat(resultingDataStructure.getTypes()).contains(
                entry("time", String.class),
                entry("m1", Integer.class)
        );
        
        assertThat(sumOperation.getData()).contains(
                resultingDataStructure.wrap(ImmutableMap.of("time", "2010", "m1", 20+40+60)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2011", "m1", 11+31+51)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2012", "m1", 72+82+92))
        );
    
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testSumMultiMeasureDataSetFail() throws Exception {
        List<Component> components = Lists.newArrayList(datasetMultiMeasure.getDataStructure().getOrDefault("time", null));
        AggregationVisitor.AggregationOperation sumOperation = visitor.getSumOperation(datasetMultiMeasure,components);
        fail("Expected an IllegalArgumentException when attempting to create a sum operation on a data set with more than one measure component");
    }
    
    @Test
    public void testSumMultiMeasureDataSet() throws Exception {
    
        DataStructure dataStructure = datasetMultiMeasure.getDataStructure();
        Component m1 = dataStructure.getOrDefault("m1", null);
        List<Component> groupBy = Lists.newArrayList(dataStructure.getOrDefault("time", null));
        
        AggregationVisitor.AggregationOperation sumOperation = visitor.getSumOperation(datasetMultiMeasure,groupBy, m1);
        
        DataStructure resultingDataStructure = sumOperation.getDataStructure();
        
        assertThat(resultingDataStructure.getRoles()).contains(
                entry("time", Component.Role.IDENTIFIER),
                entry("m1", Component.Role.MEASURE)
        );
        
        assertThat(resultingDataStructure.getTypes()).contains(
                entry("time", String.class),
                entry("m1", Integer.class)
        );
        
        assertThat(sumOperation.getData()).contains(
                resultingDataStructure.wrap(ImmutableMap.of("time", "2010", "m1", 20+40+60)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2011", "m1", 11+31+51)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2012", "m1", 72+82+92))
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