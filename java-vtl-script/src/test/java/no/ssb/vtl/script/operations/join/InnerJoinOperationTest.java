package no.ssb.vtl.script.operations.join;

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

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import com.carrotsearch.randomizedtesting.annotations.Seed;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.*;
import no.ssb.vtl.script.support.DatasetCloseWatcher;
import no.ssb.vtl.script.support.VTLPrintStream;
import org.junit.Test;

import java.time.Instant;
import java.time.Year;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.ATTRIBUTE;
import static no.ssb.vtl.model.Component.Role.IDENTIFIER;
import static no.ssb.vtl.model.Component.Role.MEASURE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class InnerJoinOperationTest extends RandomizedTest {

    @Test
    public void testDefaultJoin() throws Exception {

        Dataset ds1 = StaticDataset.create()
                .withName("time", "ref_area", "partner", "obs_value", "obs_status")
                .andRoles(IDENTIFIER, IDENTIFIER, IDENTIFIER, MEASURE, ATTRIBUTE)
                .andTypes(Instant.class, String.class, String.class, Long.class, String.class)

                .addPoints(Instant.ofEpochMilli(0), "EU25", "CA", 20L, "E")
                .addPoints(Instant.ofEpochMilli(0), "EU25", "BG", 2L, "P")
                .addPoints(Instant.ofEpochMilli(0), "EU25", "RO", 2L, "P")

                .build();

        Dataset ds2 = StaticDataset.create()

                .withName("time", "ref_area", "partner", "obs_value", "obs_status")
                .andRoles(IDENTIFIER, IDENTIFIER, IDENTIFIER, MEASURE, ATTRIBUTE)
                .andTypes(Instant.class, String.class, String.class, Long.class, String.class)

                .addPoints(Instant.ofEpochMilli(0), "EU25", "CA", 10L,"P")

                .build();

        AbstractJoinOperation result = new InnerJoinOperation(ImmutableMap.of("ds1", ds1, "ds2", ds2));

        new VTLPrintStream(System.out).println(result);

        DataStructure structure = result.getDataStructure();
        DataStructure structure1 = ds1.getDataStructure();
        DataStructure structure2 = ds2.getDataStructure();

        assertThat(structure)
                .containsOnly(
                        entry("time", structure1.get("time")),
                        entry("ref_area", structure1.get("ref_area")),
                        entry("partner", structure1.get("partner")),
                        entry("ds1_obs_value", structure1.get("obs_value")),
                        entry("ds1_obs_status", structure1.get("obs_status")),
                        entry("ds2_obs_value", structure2.get("obs_value")),
                        entry("ds2_obs_status", structure2.get("obs_status"))
                );

        assertThat(result.getData())
                .flatExtracting(dataPoint -> {
                    return structure.asMap(dataPoint).entrySet().stream()
                            .flatMap(e -> Stream.of(e.getKey().getRole(), e.getKey(), e.getValue().get()))
                            .collect(Collectors.toList());
                })

                .startsWith(
                        IDENTIFIER, structure1.get("time"), Instant.ofEpochMilli(0),
                        IDENTIFIER, structure1.get("ref_area"), "EU25",
                        IDENTIFIER, structure1.get("partner"), "CA",
                        MEASURE, structure1.get("obs_value"), 20L,
                        ATTRIBUTE, structure1.get("obs_status"), "E",
                        MEASURE, structure2.get("obs_value"), 10L,
                        ATTRIBUTE, structure2.get("obs_status"), "P"
                );
    }

    @Test
    @Seed("9DC9B02FF9A216E4")
    public void testRegression() throws Exception {
        //D0E9B354FC19C5A:9DC9B02FF9A216E4
        testRandomDatasets();
    }

    @Test
    @Repeat(iterations = 10)
    public void testRandomDatasets() throws Exception {

        Integer datasetAmount = scaledRandomIntBetween(1, 10);
        Integer rowAmount = scaledRandomIntBetween(0, 100);
        Set<Component> allComponents = Sets.newHashSet();

        Map<String, DatasetCloseWatcher> datasets = Maps.newLinkedHashMap();
        for (int i = 0; i < datasetAmount; i++) {

            StaticDataset.ValueBuilder datasetBuilder = StaticDataset.create()
                    .withName("id1", "id2", "id3", "measure", "attribute")
                    .andRoles(IDENTIFIER, IDENTIFIER, IDENTIFIER, MEASURE, ATTRIBUTE)
                    .andTypes(Instant.class, String.class, Instant.class, Long.class, String.class);

            int j = i;
            for (int rowNum = 0; rowNum < rowAmount; rowNum++) {
                datasetBuilder.addPoints(Year.of(2000).atDay(1).atStartOfDay().atOffset(ZoneOffset.UTC).toInstant(),
                        "id" + rowNum,
                        Instant.ofEpochMilli(60 * 60 * 24 * 100),
                        (long) (j + rowNum),
                        "attribute-" + j + "-" + rowNum
                );
            }

            StaticDataset dataset = datasetBuilder.build();
            datasets.put("ds" + i, DatasetCloseWatcher.wrap(dataset));
            allComponents.addAll(dataset.getDataStructure().values());

            new VTLPrintStream(System.out).println(datasetBuilder);
        }

        InnerJoinOperation result = new InnerJoinOperation(Maps.transformValues(datasets, ds -> ds));

        new VTLPrintStream(System.out).println(result);

        assertThat(result.getDataStructure())
                .describedAs("data structure of the inner join")
                .hasSize(datasetAmount * 2 + 3);

        assertThat(allComponents)
                .describedAs("all the components in the datasets")
                .hasSize(datasetAmount * 5);

        List<Object> data = datasets.values().stream()
                .flatMap(Dataset::getData)
                .flatMap(Collection::stream)
                .map(VTLObject::get)
                .collect(Collectors.toList());

        try (Stream<DataPoint> stream = result.getData()) {
            assertThat(stream.flatMap(Collection::stream))
                    .describedAs("the data")
                    .extracting(VTLObject::get)
                    .containsOnlyElementsOf(
                            data
                    );
        } finally {
            assertThat(datasets.values()).allMatch(DatasetCloseWatcher::allStreamWereClosed);
        }


    }

    @Test
    public void testJoinWithOneDatasetForwardsDistinctCount() {

        Dataset ds1 = StaticDataset.create()
                .addComponent("id", IDENTIFIER, String.class)
                .addPoints("a")
                .addPoints("b")
                .addPoints("c")
                .addPoints("d")
                .build();

        Optional<Long> originalSize = ds1.getSize();
        Optional<Map<String, Integer>> originalDistinctValues = ds1.getDistinctValuesCount();

        Map<String, Dataset> map = Maps.newLinkedHashMap();
        map.put("ds1", ds1);
        InnerJoinOperation innerJoinOperation = new InnerJoinOperation(map);

        assertThat(innerJoinOperation.getSize()).isEqualTo(originalSize);
        assertThat(innerJoinOperation.getDistinctValuesCount()).isEqualTo(originalDistinctValues);

    }

    @Test
    public void testInnerJoinWithCodeListDataset() throws Exception {

        Dataset ds1 = StaticDataset.create()

                .withName("kommune_nr", "periode", "m1", "at1")
                .andRoles(IDENTIFIER, IDENTIFIER, MEASURE, ATTRIBUTE)
                .andTypes(String.class, Instant.class, Long.class, String.class)
                .addPoints("0101", Instant.parse("2015-01-01T00:00:00.00Z"), 100L, "attr1")
                .addPoints("0111", Instant.parse("2014-01-01T00:00:00.00Z"), 101L, "attr2")
                .addPoints("9000", Instant.parse("2014-01-01T00:00:00.00Z"), 102L, "attr3")
                .build();

        Dataset dsCodeList2 = StaticDataset.create()
                .withName("kommune_nr", "name", "validFrom", "validTo")
                .andRoles(IDENTIFIER, MEASURE, IDENTIFIER, IDENTIFIER)
                .andTypes(String.class, /*code*/ String.class, Instant.class, Instant.class)
                .addPoints("0101", "Halden", Instant.parse("2013-01-01T00:00:00.00Z"), null)
                .addPoints("0111", "Hvaler", Instant.parse("2015-01-01T00:00:00.00Z"), null)
                .addPoints("1001", "Kristiansand", Instant.parse("2013-01-01T00:00:00.00Z"), Instant.parse("2015-01-01T00:00:00.00Z"))
                .build();

        AbstractJoinOperation ds3 = new InnerJoinOperation(ImmutableMap.of("ds1", ds1, "dsCodeList2", dsCodeList2));

        new VTLPrintStream(System.out).println(ds1);
        new VTLPrintStream(System.out).println(dsCodeList2);
        new VTLPrintStream(System.out).println(ds3);

        assertThat(ds3.getDataStructure().getRoles()).contains(
                entry("kommune_nr", IDENTIFIER),
                entry("periode", IDENTIFIER),
                entry("m1", MEASURE),
                entry("at1", ATTRIBUTE),
                entry("name", MEASURE),
                entry("validFrom", IDENTIFIER),
                entry("validTo", IDENTIFIER)
        );

        assertThat(ds3.getData()).flatExtracting(input -> input)
                .extracting(VTLObject::get)
                .containsExactly(
                        "0101", Instant.parse("2015-01-01T00:00:00.00Z"), 100L, "attr1", "Halden", Instant.parse("2013-01-01T00:00:00.00Z"), null,
                        "0111", Instant.parse("2014-01-01T00:00:00.00Z"), 101L, "attr2", "Hvaler", Instant.parse("2015-01-01T00:00:00.00Z"), null
                );
    }
}
