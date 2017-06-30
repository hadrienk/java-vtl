package no.ssb.vtl.connectors;

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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.cache.Cache;
import com.google.common.collect.*;
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.connector.ConnectorException;
import no.ssb.vtl.connector.NotFoundException;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.jsonstat.JsonStatModule;
import no.ssb.jsonstat.v2.DatasetBuildable;
import no.ssb.jsonstat.v2.Dimension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;
import static java.util.Arrays.asList;

/**
 * A VTL connector that gets data from api.ssb.no.
 */
public class SsbApiConnector implements Connector {

    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;

    /*
        The list of available datasets:
        http://data.ssb.no/api/v0/dataset/list.json?lang=en

        Example dataset:
        http://data.ssb.no/api/v0/dataset/1106.json?lang=en

     */

    Cache<String, Dataset> datasetCache;
    Cache<String, URI> uriCache;

    public SsbApiConnector(ObjectMapper mapper) {

        this.mapper = checkNotNull(mapper, "the mapper was null").copy();

        this.mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
        this.mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

        this.mapper.registerModule(new GuavaModule());
        this.mapper.registerModule(new Jdk8Module());
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.registerModule(new JsonStatModule());

        ResourceHttpMessageConverter resourceConverter = new ResourceHttpMessageConverter();
        MappingJackson2HttpMessageConverter jacksonConverter;
        jacksonConverter = new MappingJackson2HttpMessageConverter(this.mapper);

        this.restTemplate = new RestTemplate(asList(
                resourceConverter,
                jacksonConverter
        ));


    }

    /**
     * Gives access to the rest template to tests.
     */
    RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public boolean canHandle(String identifier) {
        return identifier.startsWith("http://data.ssb.no/api/v0/dataset/");
    }

    public Dataset getDataset(String identifier) throws ConnectorException {

        ParameterizedTypeReference<Map<String, DatasetBuildable>> ref = new ParameterizedTypeReference<Map<String, DatasetBuildable>>() {
            // Just a reference.
        };

        try {

            if (identifier.startsWith("http://data.ssb.no/api/v0/dataset/")) {
                identifier = identifier.replace("http://data.ssb.no/api/v0/dataset/", "");
            }
            //http://data.ssb.no/api/v0/dataset/1106.json?lang=en;
            ResponseEntity<Map<String, DatasetBuildable>> exchange = restTemplate.exchange(
                    "http://data.ssb.no/api/v0/dataset/{id}.json?lang=en",
                    HttpMethod.GET,
                    null, ref, identifier);

            if (!exchange.getBody().values().iterator().hasNext()) {
                throw new NotFoundException(format("empty dataset returned for the identifier %s", identifier));
            }

            no.ssb.jsonstat.v2.Dataset dataset = exchange.getBody().values().iterator().next().build();

            Map<String, Dimension> dimensions = dataset.getDimension();

            ImmutableMultimap<Dimension.Roles, String> role = dataset.getRole();
            Set<String> metric = ImmutableSet.copyOf(role.get(Dimension.Roles.METRIC));
            Set<String> ids = Sets.symmetricDifference(dataset.getId(), metric);

            Set<String> rotatedMetricName = computeMetricNames(dimensions, metric);
            DataStructure structure = generateStructure(ids, rotatedMetricName);

            Table<List<String>, List<String>, Number> table = dataset.asTable(ids, metric);

            return new Dataset() {
                @Override
                public Stream<DataPoint> getData() {
                    return table.rowMap().entrySet().stream()
                            .map(entry -> {
                                Map<String, Object> row = Maps.newHashMap();
                                Iterator<String> identifierValues = entry.getKey().iterator();
                                for (String id : ids) {
                                    row.put(id, identifierValues.next());
                                }
                                entry.getValue().entrySet().forEach(metrics -> {
                                    row.put(String.join("_", metrics.getKey()), metrics.getValue());
                                });
                                return row;
                            }).map(structure::wrap);
                }

                @Override
                public Optional<Map<String, Integer>> getDistinctValuesCount() {
                    // TODO
                    return Optional.empty();
                }

                @Override
                public Optional<Long> getSize() {
                    return Optional.of((long) dataset.getRows().size() * metric.size());
                }

                @Override
                public DataStructure getDataStructure() {
                    return structure;
                }

            };

        } catch (RestClientException rce) {
            throw new ConnectorException(
                    format("error when accessing the dataset with id %s", identifier),
                    rce
            );
        }
    }

    private Set<String> computeMetricNames(Map<String, Dimension> dimensions, Set<String> metric) {
        List<Set<String>> metricValues = Lists.newArrayList();
        for (String metricName : metric) {
            metricValues.add(dimensions.get(metricName).getCategory().getIndex());
        }
        return Sets.cartesianProduct(metricValues).stream().map(
                strings -> String.join("_", strings)
        ).collect(Collectors.toSet());
    }

    private DataStructure generateStructure(Set<String> ids, Set<String> metrics) {
        Map<String, Component.Role> roles = Maps.newHashMap();
        Map<String, Class<?>> types = Maps.newHashMap();
        for (String name : ids) {
            roles.put(name, Component.Role.IDENTIFIER);
            types.put(name, String.class);
        }
        for (String name : metrics) {
            roles.put(name, Component.Role.MEASURE);
            types.put(name, Number.class);
        }
        return DataStructure.of((o, aClass) -> o, types, roles);
    }

    public Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException {
        throw new ConnectorException("not supported");
    }
}
