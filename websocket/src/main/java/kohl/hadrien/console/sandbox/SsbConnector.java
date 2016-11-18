package kohl.hadrien.console.sandbox;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import kohl.hadrien.vtl.connector.Connector;
import kohl.hadrien.vtl.connector.ConnectorException;
import kohl.hadrien.vtl.model.*;
import kohl.hadrien.vtl.model.Dataset;
import no.ssb.jsonstat.JsonStatModule;
import no.ssb.jsonstat.v2.*;
import no.ssb.jsonstat.v2.deser.DatasetDeserializer;
import no.ssb.jsonstat.v2.deser.DimensionDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SsbConnector implements Connector {

    private static final Logger logger = LoggerFactory.getLogger(SsbConnector.class);
    private static final String BASE_URL = "http://data.ssb.no/api/v0/";
    private final ObjectMapper mapper;
    private final RestTemplate restClient;
    private String lang = "en";

    public SsbConnector() {
        this.mapper = new ObjectMapper();
        SimpleModule module = new JsonStatModule();
        module.addDeserializer(no.ssb.jsonstat.v2.DatasetBuildable.class, new DatasetDeserializer());
        module.addDeserializer(Dimension.Builder.class, new DimensionDeserializer());
        this.mapper.registerModule(module);
        this.mapper.registerModule(new GuavaModule());

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(true);
        factory.setOutputStreaming(true);

        // Use the our object mapper.
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setPrettyPrint(false);
        messageConverter.setObjectMapper(this.mapper);

        this.restClient = new RestTemplate(factory);
        this.restClient.getMessageConverters().removeIf(m -> m.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
        this.restClient.getMessageConverters().add(messageConverter);

    }

    @Override
    public boolean canHandle(String identifier) {
        try {
            URI uri = new URI(identifier);
            if (uri.normalize().toString().startsWith(BASE_URL))
                return true;
        } catch (NullPointerException | URISyntaxException e) {
            // Ignore.
        }
        return false;
    }

    @Override
    public Dataset getDataset(String identifier) throws ConnectorException {
        final DataStructure dataStructure = getDataStructure(identifier);

        return new Dataset() {
            @Override
            public DataStructure getDataStructure() {
                return dataStructure;
            }

            @Override
            public Stream<Tuple> get() {
                ObjectNode root = mapper.createObjectNode();
                ArrayNode queries = root.putArray("query");
                for (String names : getDataStructure().names()) {
                    ObjectNode query = queries.addObject();
                    query.put("code", names);
                    query.with("selection")
                            .put("filter", "all")
                            .putArray("values").add("*");
                }
                root.with("response").put("format", "json-stat");

                ParameterizedTypeReference<Map<String, DatasetBuildable>> ref
                        = new ParameterizedTypeReference<Map<String, DatasetBuildable>>() {};

                ResponseEntity<Map<String, DatasetBuildable>> datasets = restClient.exchange(
                        identifier, HttpMethod.POST, new HttpEntity<>(root), ref);

                no.ssb.jsonstat.v2.Dataset dataset = datasets.getBody().get("dataset").build();
                List<Map<String, Object>> collect = dataset.asMap().entrySet().stream()
                        .map(entryRow -> {
                            List<String> key = entryRow.getKey();
                            List<Number> value = entryRow.getValue();
                            Map<String, Object> row = Maps.newHashMap();
                            Iterator<String> iterator = key.iterator();
                            return row;
                        }).collect(Collectors.toList());

                return collect.stream().map(row -> getDataStructure().wrap(row));
            }
        };
    }

    @Override
    public Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException {
        return null;
    }

    DataStructure getDataStructure(String identifier) {
        JsonNode dataset = restClient.getForObject(identifier, JsonNode.class);
        JsonNode variables = dataset.get("variables");

        ImmutableMap.Builder<String, Class<? extends Component>> rolesBuilder = ImmutableMap.builder();
        ImmutableMap.Builder<String, Class<?>> typesBuilder = ImmutableMap.builder();

        for (JsonNode variable : variables) {
            String name = variable.get("code").asText();
            Class<? extends Component> role = variable.has("elimination") ? Identifier.class : Measure.class;
            Class<?> type = String.class;
            rolesBuilder.put(name, role);
            typesBuilder.put(name, type);
        }

        return new DataStructure() {

            public ImmutableMap<String, Class<?>> types = typesBuilder.build();
            private Map<String, Class<? extends Component>> roles = rolesBuilder.build();

            @Override
            public BiFunction<Object, Class<?>, ?> converter() {
                return mapper::convertValue;
            }

            @Override
            public Map<String, Class<? extends Component>> roles() {
                return roles;
            }

            @Override
            public Map<String, Class<?>> types() {
                return types;
            }

            @Override
            public Set<String> names() {
                return types.keySet();
            }
        };

    }
}
