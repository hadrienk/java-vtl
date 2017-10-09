package no.ssb.vtl.script.visitors;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.operations.AggregationOperation;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class AggregationVisitorTest {
    
    private AggregationVisitor visitor = new AggregationVisitor(null);
    private TestableDataset datasetSingleMeasure;
    private TestableDataset datasetMultiMeasure;
    private DataStructure dataStructureSingleMeasure;
    
    @Before
    public void setUp() throws Exception {
        dataStructureSingleMeasure = DataStructure.of(
                "time", Component.Role.IDENTIFIER, String.class,
                "geo", Component.Role.IDENTIFIER, String.class,
                "m1", Component.Role.MEASURE, Long.class);
        datasetSingleMeasure = new TestableDataset(
                Arrays.asList(dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "NO", "m1", 20L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "SE", "m1", 31L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "SE", "m1", 41L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "NO", "m1", 72L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "SE", "m1", 40L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "NO", "m1", 11L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "SE", "m1", 41L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "DK", "m1", 60L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "DK", "m1", 51L)),
                        dataStructureSingleMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "DK", "m1", 92L))),
                dataStructureSingleMeasure);
    
    
        DataStructure dataStructureMultiMeasure = DataStructure.of(
                "time",Component.Role.IDENTIFIER,String.class,
                "geo", Component.Role.IDENTIFIER, String.class,
                "m1", Component.Role.MEASURE,Long.class,
                "m2", Component.Role.MEASURE,Long.class);
        datasetMultiMeasure = new TestableDataset(
                Arrays.asList(dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "NO", "m1", 20L, "m2", 2L)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "SE", "m1", 40L, "m2", 4L)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2010", "geo", "DK", "m1", 60L, "m2", 6L)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "NO", "m1", 11L, "m2", 1L)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "SE", "m1", 31L, "m2", 3L)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2011", "geo", "DK", "m1", 51L, "m2", 5L)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "NO", "m1", 72L, "m2", 7L)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "SE", "m1", 82L, "m2", 8L)),
                        dataStructureMultiMeasure.wrap(ImmutableMap.of("time", "2012", "geo", "DK", "m1", 92L, "m2", 9L))),
                dataStructureMultiMeasure);
    
    
    }
    
    @Test
    public void testSumSingleMeasureDataSet() throws Exception {
    
        List<Component> components = Lists.newArrayList(datasetSingleMeasure.getDataStructure().getOrDefault("time", null));
        AggregationOperation sumOperation = visitor.getSumOperation(datasetSingleMeasure,components);
        sumOperation.getData().forEach(System.out::println);
    
        DataStructure dataStructure = sumOperation.getDataStructure();
    
        assertThat(dataStructure.getRoles()).contains(
                entry("time", Component.Role.IDENTIFIER),
                entry("m1", Component.Role.MEASURE)
        );
    
        assertThat(dataStructure.getTypes()).contains(
                entry("time", String.class),
                entry("m1", Long.class)
        );
    
    
        assertThat(sumOperation.getData()).contains(
                dataStructure.wrap(ImmutableMap.of("time", "2010", "m1", 20L+40L+60L)),
                dataStructure.wrap(ImmutableMap.of("time", "2011", "m1", 11L+31L+51L)),
                dataStructure.wrap(ImmutableMap.of("time", "2012", "m1", 72L+82L+92L))
        );
    
    }
    
    
    @Test
    public void testSumMultiMeasureDataSetAll() throws Exception {
        List<Component> groupBy = Lists.newArrayList(datasetMultiMeasure.getDataStructure().getOrDefault("time", null));
        AggregationOperation sumOperation = visitor.getSumOperation(datasetMultiMeasure,groupBy);
    
        DataStructure dataStructure = sumOperation.getDataStructure();
        
        assertThat(dataStructure.getRoles()).contains(
                entry("time", Component.Role.IDENTIFIER),
                entry("m1", Component.Role.MEASURE),
                entry("m2", Component.Role.MEASURE)
        );
    
        assertThat(dataStructure.getTypes()).contains(
                entry("time", String.class),
                entry("m1", Long.class),
                entry("m2", Long.class)
        );
    
        assertThat(sumOperation.getData()).contains(
                dataStructure.wrap(ImmutableMap.of("time", "2010", "m1", 20L+40L+60L, "m2", 2L+4L+6L )),
                dataStructure.wrap(ImmutableMap.of("time", "2011", "m1", 11L+31L+51L, "m2", 1L+3L+5L)),
                dataStructure.wrap(ImmutableMap.of("time", "2012", "m1", 72L+82L+92L, "m2", 7L+8L+9L))
        );
    }
    
    @Test
    public void testSumMultiMeasureDataSet() throws Exception {
    
        DataStructure dataStructure = datasetMultiMeasure.getDataStructure();
        Component m1 = dataStructure.getOrDefault("m1", null);
        List<Component> groupBy = Lists.newArrayList(dataStructure.getOrDefault("time", null));
        
        AggregationOperation sumOperation = visitor.getSumOperation(datasetMultiMeasure,groupBy, Collections.singletonList(m1));
        
        DataStructure resultingDataStructure = sumOperation.getDataStructure();
        
        assertThat(resultingDataStructure.getRoles()).contains(
                entry("time", Component.Role.IDENTIFIER),
                entry("m1", Component.Role.MEASURE)
        );
        
        assertThat(resultingDataStructure.getTypes()).contains(
                entry("time", String.class),
                entry("m1", Long.class)
        );
        
        assertThat(sumOperation.getData()).contains(
                resultingDataStructure.wrap(ImmutableMap.of("time", "2010", "m1", 20L+40L+60L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2011", "m1", 11L+31L+51L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2012", "m1", 72L+82L+92L))
        );
        
    }
    
    
    @Test
    public void testSumGroupedByMultipleIdentifiers() throws Exception {
        DataStructure dataStructure = datasetSingleMeasure.dataStructure;
        List<Component> components = Lists.newArrayList(dataStructure.get("time"), dataStructure.get("geo"));
        System.out.println("Group By " + components);
        AggregationOperation sumOperation = visitor.getSumOperation(datasetSingleMeasure,components);
        sumOperation.getData().forEach(System.out::println);
    
        DataStructure resultingDataStructure = sumOperation.getDataStructure();
    
        assertThat(resultingDataStructure.getRoles()).contains(
                entry("time", Component.Role.IDENTIFIER),
                entry("m1", Component.Role.MEASURE)
        );
    
        assertThat(resultingDataStructure.getTypes()).contains(
                entry("time", String.class),
                entry("m1", Long.class)
        );
    
        assertThat(sumOperation.getData()).contains(
                resultingDataStructure.wrap(ImmutableMap.of("time", "2010", "geo", "NO", "m1", 20L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2010", "geo", "SE", "m1", 40L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2010", "geo", "DK", "m1", 60L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2011", "geo", "NO", "m1", 11L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2011", "geo", "SE", "m1", 31L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2011", "geo", "DK", "m1", 51L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2012", "geo", "NO", "m1", 72L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2012", "geo", "SE", "m1", 82L)),
                resultingDataStructure.wrap(ImmutableMap.of("time", "2012", "geo", "DK", "m1", 92L))
        );
    }
    
    
    @Test
    public void testSumAlongMultipleIdentifiers() throws Exception {
        DataStructure dataStructure = DataStructure.builder()
                .put("eieform", Component.Role.IDENTIFIER, String.class)
                .put("enhet", Component.Role.IDENTIFIER, String.class)
                .put("feltnavn", Component.Role.IDENTIFIER, String.class)
                .put("funksjon", Component.Role.IDENTIFIER, String.class)
                .put("periode", Component.Role.IDENTIFIER, String.class)
                .put("region", Component.Role.IDENTIFIER, String.class)
                .put("regnskapsomfang", Component.Role.IDENTIFIER, String.class)
                .put("areal", Component.Role.MEASURE, Long.class)
                .build();
        TestableDataset datasetToBeSummed = new TestableDataset(
                Arrays.asList(
                        dataPoint("EIER","981548183", "F130KFEIER", "130", "2015", "0104", "SBDR", 8418L),
                        dataPoint("EIER","988935816", "F130KFEIER", "130", "2015", "0105", "SBDR", 0L),
                        dataPoint("EIER","979952171", "F130KFEIER", "130", "2015", "0106", "SBDR", 1092L),
                        dataPoint("EIER","986386947", "F130KFEIER", "130", "2015", "0226", "SBDR", 5367L),
                        dataPoint("EIER","994860216", "F130KFEIER", "130", "2015", "0233", "SBDR", 4370L),
                        dataPoint("EIER","997756215", "F130KFEIER", "130", "2015", "0236", "SBDR", 318L),
                        dataPoint("EIER","984070659", "F130KFEIER", "130", "2015", "0301", "SBDR", 610L),
                        dataPoint("EIER","985987246", "F130KFEIER", "130", "2015", "0301", "SBDR", 3888L),
                        dataPoint("EIER","987592567", "F130KFEIER", "130", "2015", "0301", "SBDR", 24L),
                        dataPoint("EIER","983529968", "F130KFEIER", "130", "2015", "0417", "SBDR", 0L)), dataStructure);
    
    
        
    
        List<Component> alongComponents = Lists.newArrayList(dataStructure.get("enhet"), dataStructure.get("region"));
        List<Component> groupBy = dataStructure.values()
                .stream()
                .filter(Component::isIdentifier)
                .filter(component -> !alongComponents.contains(component))
                .collect(Collectors.toList());
        System.out.println(groupBy);
        AggregationOperation sumOperation = visitor.getSumOperation(datasetToBeSummed,groupBy);
        
        assertThat(sumOperation.getData()).containsOnly(dataPoint("EIER", "F130KFEIER", "130", "2015", "SBDR", 8418L+1092L+5367L+4370L+318L+610L+3888L+24L));
        
    }
    
    @Test
    public void testSumWithNullValues() throws Exception {
        TestableDataset dataset = new TestableDataset(
                Arrays.asList(dataPoint("2010", "NO",20L),
                        dataPoint("2011", "SE", 31L),
                        dataPoint("2012", "SE", null),
                        dataPoint("2012", "NO", null),
                        dataPoint("2010", "SE", 40L),
                        dataPoint("2011", "NO", 11L),
                        dataPoint("2012", "SE", 41L),
                        dataPoint("2010", "DK", null),
                        dataPoint("2011", "DK", 51L),
                        dataPoint("2012", "DK", 92L)), dataStructureSingleMeasure);
    
        AggregationOperation sumOperation = visitor.getSumOperation(dataset,
                Collections.singletonList(dataStructureSingleMeasure.get("time")));
        assertThat(sumOperation.getData()).contains(dataPoint("2012", 41L + 92L));
    
    }
    
    @Test(expected = ParseCancellationException.class)
    public void testAggregationWithoutNumber() throws Exception {
        DataStructure dataStructure = DataStructure.builder()
                .put("id1", Component.Role.IDENTIFIER, String.class)
                .put("m1", Component.Role.MEASURE, String.class)
                .build();
        TestableDataset dataset = new TestableDataset(
                Arrays.asList(
                        dataPoint("1", "notANumeric"),
                        dataPoint("2", "shouldFail"))
                , dataStructure);
        AggregationOperation sumOperation = visitor.getSumOperation(dataset,
                Collections.singletonList(dataStructureSingleMeasure.get("time")));
        sumOperation.getDataStructure();
        fail("Expected an exception but none was thrown");
    }
    
    
    @Test
    public void testSumWithEmptyAggregationGroup() throws Exception {
        TestableDataset dataset = new TestableDataset( //dataset with several null values. In fact ALL 2010 values are null
                Arrays.asList(dataPoint("2010", "NO",null),
                        dataPoint("2011", "SE", 31L),
                        dataPoint("2012", "SE", null),
                        dataPoint("2012", "NO", null),
                        dataPoint("2010", "SE", null),
                        dataPoint("2011", "NO", 11L),
                        dataPoint("2012", "SE", 41L),
                        dataPoint("2010", "DK", null),
                        dataPoint("2011", "DK", 51L),
                        dataPoint("2012", "DK", 92L)), dataStructureSingleMeasure);
    
        AggregationOperation sumOperation = visitor.getSumOperation(dataset,
                Collections.singletonList(dataStructureSingleMeasure.get("time")));
        assertThat(sumOperation.getData()).contains(dataPoint("2012", 41L + 92L));
        assertThat(sumOperation.getData()).contains(dataPoint("2010", null));
    }
    
    private DataPoint dataPoint(Object... objects) {
        List<VTLObject> vtlObjects = Stream.of(objects).map(VTLObject::of).collect(Collectors.toList());
        return DataPoint.create(vtlObjects);
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
