package no.ssb.vtl.connectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.cache.Cache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import kohl.hadrien.vtl.connector.Connector;
import kohl.hadrien.vtl.connector.ConnectorException;
import kohl.hadrien.vtl.connector.NotFoundException;
import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
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

    public boolean canHandle(String identifier) {
        return true;
    }

    public Dataset getDataset(String identifier) throws ConnectorException {

        ParameterizedTypeReference<Map<String, DatasetBuildable>> ref = new ParameterizedTypeReference<Map<String, DatasetBuildable>>() {
            // Just a reference.
        };

        try {
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

            // TODO: Fix json-stat-java so that we get access to the roles.
            Set<String> metric = Sets.newHashSet("ContentsCode");

            Map<List<String>, Number> values = dataset.asMap();

            List<Map<String, Object>> collect = values.entrySet().stream().collect(
                    Collectors.groupingBy(entry -> {
                        HashMap<String, Object> idMap = Maps.newHashMap();
                        Iterator<Map.Entry<String, Dimension>> dimensionEntry = dimensions.entrySet().iterator();
                        for (String name : entry.getKey()) {
                            Map.Entry<String, Dimension> dimension = dimensionEntry.next();
                            if (!metric.contains(dimension.getKey())) {
                                ImmutableMap<String, String> labelMap = dimension.getValue().getCategory().getLabel();
                                idMap.put(dimension.getKey(), labelMap.get(name));
                            }
                        }
                        return idMap;
                    })
            ).entrySet().stream()
                    .map(entry -> {
                        HashMap<String, Object> measureMap = Maps.newHashMap();
                        measureMap.putAll(entry.getKey());

                        entry.getValue().stream().map(listNumberEntry -> {
                            Map<String, Object> tmp = Maps.newHashMap();
                            Iterator<Map.Entry<String, Dimension>> dimensionEntry = dimensions.entrySet().iterator();
                            for (String name : listNumberEntry.getKey()) {
                                Map.Entry<String, Dimension> dimension = dimensionEntry.next();
                                if (metric.contains(dimension.getKey())) {
                                    tmp.put(name, listNumberEntry.getValue());
                                }
                            }
                            return tmp;
                        }).forEach(measureMap::putAll);

                        return measureMap;
                    }).collect(Collectors.toList());

            Map<String, Object> firstRow  = collect.get(0);
            Map<String, Component.Role> roles = Maps.newHashMap();
            Map<String, Class<?>> types = Maps.newHashMap();
            for (String name : firstRow.keySet()) {
                if (metric.contains(name)) {
                    roles.put(name, Component.Role.IDENTIFIER);
                } else {
                    roles.put(name, Component.Role.MEASURE);
                }
                types.put(name, firstRow.get(name).getClass());
            }
            DataStructure structure = DataStructure.of((o, aClass) -> o, types, roles);
            return new Dataset() {
                @Override
                public DataStructure getDataStructure() {
                    return structure;
                }

                @Override
                public Stream<Tuple> get() {
                    return collect.stream().map(structure::wrap);
                }
            };

        } catch (RestClientException rce) {
            throw new ConnectorException(
                    format("error when accessing the dataset with id %s", identifier)
            );
        }
    }

    public Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException {
        throw new ConnectorException("not supported");
    }
}
