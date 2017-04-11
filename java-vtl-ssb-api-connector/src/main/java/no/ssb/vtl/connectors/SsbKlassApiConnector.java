package no.ssb.vtl.connectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.connector.ConnectorException;
import no.ssb.vtl.connector.NotFoundException;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;
import static java.lang.String.*;
import static java.time.Month.*;
import static java.time.temporal.TemporalAdjusters.*;
import static java.util.Arrays.*;


/**
 * A VTL connector that gets data from KLASS part of api.ssb.no.
 */
public class SsbKlassApiConnector implements Connector {

    private static final String SERVICE_URL = "http://data.ssb.no/api/klass/v1";
    private static final String[] DATA_PATHS = new String[]{
            "/classifications/{classificationId}/codes?from={codesFrom}"
            //for example "/classifications/{classificationId}/codes?from={codesFrom}&to={codesTo}"
    };
    private static final String FIELD_CODE = "code";
    private static final String FIELD_PERIOD = "period";
    private static final String FIELD_VALID_FROM = "validFrom";
    private static final String FIELD_VALID_TO = "validTo";
    private static final String FIELD_NAME = "name";

    private static final DataStructure DATA_STRUCTURE =
            DataStructure.builder()
                    .put(FIELD_CODE, Component.Role.IDENTIFIER, String.class)
                    .put(FIELD_PERIOD, Component.Role.IDENTIFIER, String.class)
                    //Note: validTo can contain nulls and VTL specification states that ICs cannot contain null values (VTL 1.1, user manual, line 2283).
                    //Nevertheless we set validTo to be an Identifier as we're not sure at this point what implications we
                    //could come upon.
//                    .put(FIELD_VALID_TO, Component.Role.IDENTIFIER, Instant.class)
                    .put(FIELD_NAME, Component.Role.MEASURE, String.class)
                    .build();

    //Instead of using null when validTo not specified
    private static final String KLASS_TIME_ZONE = "Europe/Oslo";
    private static final ZonedDateTime MAX_DATE = ZonedDateTime.ofInstant(Instant.parse("9999-12-31T00:00:00.000Z"), ZoneId.of(KLASS_TIME_ZONE));

    private final PeriodType periodType;
    private final List<UriTemplate> dataTemplates;
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;

    /*
        The list of available datasets:
        http://data.ssb.no/api/klass/v1/classifications/search?query=kommune

        Example dataset:
        http://data.ssb.no/api/klass/v1/classifications/131/codes?from=2016-01-01

     */

    public SsbKlassApiConnector(ObjectMapper mapper, PeriodType periodType) {
        this.periodType = periodType;

        this.dataTemplates = Lists.newArrayList();
        for (String path : DATA_PATHS) {
            this.dataTemplates.add(new UriTemplate(SERVICE_URL + path));
        }

        this.mapper = checkNotNull(mapper, "the mapper was null").copy();

        this.mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
        this.mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

        this.mapper.registerModule(new GuavaModule());
        this.mapper.registerModule(new Jdk8Module());
        this.mapper.registerModule(new JavaTimeModule());

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Map.class, new KlassDeserializer());
        this.mapper.registerModule(module);

        MappingJackson2HttpMessageConverter jacksonConverter;
        jacksonConverter = new MappingJackson2HttpMessageConverter(this.mapper);

        this.restTemplate = new RestTemplate(asList(
                jacksonConverter
        ));

    }

    /**
     * Gives access to the rest template to tests.
     */
    RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public boolean canHandle(String url) {
        UriTemplate matchFound = findFirstMatchingUriTemplate(url).orElse(null);
        return matchFound != null;
    }

    private Optional<UriTemplate> findFirstMatchingUriTemplate(String url) {
        return dataTemplates.stream()
                .filter(t -> t.matches(url))
                .findFirst();
    }

    public Dataset getDataset(String url) throws ConnectorException {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            //http://data.ssb.no/api/klass/v1/classifications/131/codes?from=2016-01-01
            ResponseEntity<DatasetWrapper> exchange = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity, DatasetWrapper.class);

            if (exchange.getBody() == null || exchange.getBody().getCodes().size() == 0) {
                throw new NotFoundException(format("empty dataset returned for the identifier %s", url));
            }

            List<Map<String, Object>> rows = exchange.getBody().getCodes();
            List<Map<String, Object>> rowsExpanded = expand(rows);

            return new Dataset() {

                @Override
                public DataStructure getDataStructure() {
                    return DATA_STRUCTURE;
                }

                @Override
                public Stream<DataPoint> getData() {
                    DataStructure dataStructure = getDataStructure();
                    return rowsExpanded.stream()
                            .map(dataStructure::fromStringMap);
                }

                @Override
                public Optional<Map<String, Integer>> getDistinctValuesCount() {
                    return Optional.empty();
                }

                @Override
                public Optional<Long> getSize() {
                    return Optional.empty();
                }
            };

        } catch (RestClientException rce) {
            throw new ConnectorException(
                    format("error when accessing the dataset with ids %s", url),
                    rce
            );
        }
    }

    private List<Map<String, Object>> expand(List<Map<String, Object>> rows) {
        ArrayList<Map<String, Object>> expanded = new ArrayList<>();
        Map<String, Object> columnToValueExpanded;
        ZonedDateTime validFrom;
        ZonedDateTime validTo;

        for (Map columnToValue : rows) {
            validFrom = (ZonedDateTime) columnToValue.get(FIELD_VALID_FROM);
            validTo = (ZonedDateTime) columnToValue.get(FIELD_VALID_TO);
            for (String periode : getPeriods(validFrom, validTo)) {
                columnToValueExpanded = new HashMap<>();
                columnToValueExpanded.put(FIELD_CODE, columnToValue.get(FIELD_CODE));
                columnToValueExpanded.put(FIELD_PERIOD, periode);
                columnToValueExpanded.put(FIELD_NAME, columnToValue.get(FIELD_NAME));
                expanded.add(columnToValueExpanded);
            }
        }

        return expanded;
    }

    private List<String> getPeriods(ZonedDateTime validFrom, ZonedDateTime validTo) {
        if (periodType != PeriodType.YEAR) {
            throw new UnsupportedOperationException("Period type: " + periodType + " not supported");
        }

        List<String> periods = new ArrayList<>();

        ZonedDateTime current = validFrom;
        ZonedDateTime nextYear = ZonedDateTime.now().plusYears(1).with(DECEMBER).with(lastDayOfMonth()).withHour(23);
        while (current.isBefore(validTo) && current.isBefore(nextYear)) {
            if (current.getMonth() == JANUARY) {
                periods.add(String.valueOf(current.getYear()));
            }
            current = current.plusYears(1);
        }

        return periods;
    }

    public Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException {
        throw new ConnectorException("not supported");
    }

    static class DatasetWrapper {
        @JsonProperty
        private List<Map<String, Object>> codes;

        DatasetWrapper() {
        }

        public List<Map<String, Object>> getCodes() {
            return codes;
        }

        public void setCodes(List<Map<String, Object>> codes) {
            this.codes = codes;
        }
    }

    private static class KlassDeserializer extends StdDeserializer<Map<String, Object>> {

        KlassDeserializer() {
            this(null);
        }

        KlassDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Map<String, Object> deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException {

            HashMap<String, Object> entry = Maps.newHashMap();
            while (jp.nextValue() != JsonToken.END_OBJECT) {
                switch (jp.getCurrentName()) {
                    case FIELD_VALID_FROM:
                    case FIELD_VALID_TO:
                        ZonedDateTime value = null;
                        LocalDate localDate = jp.readValueAs(LocalDate.class);
                        if (localDate != null) {
                            value = localDate.atStartOfDay(TimeZone.getTimeZone(ZoneId.of(KLASS_TIME_ZONE)).toZoneId());
                        } else {
                            value = MAX_DATE;
                        }
                        entry.put(jp.getCurrentName(), value);
                        break;
                    case FIELD_CODE:
                    case FIELD_NAME:
                        entry.put(jp.getCurrentName(), jp.getValueAsString());
                        break;
                }
            }
            return entry;
        }
    }

    public enum PeriodType {
        YEAR
    }
}
