package no.ssb.vtl.tools.rest;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import no.ssb.vtl.connectors.Connector;
import no.ssb.vtl.connectors.spring.RestTemplateConnector;
import no.ssb.vtl.connectors.spring.converters.DataHttpConverter;
import no.ssb.vtl.connectors.spring.converters.DataStructureHttpConverter;
import no.ssb.vtl.connectors.spring.converters.DatasetHttpMessageConverter;
import no.ssb.vtl.connectors.utils.RegexConnector;
import no.ssb.vtl.connectors.utils.TimeoutConnector;
import no.ssb.vtl.script.VTLScriptEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.script.Bindings;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.google.common.base.MoreObjects.firstNonNull;

/**
 * Spring application
 */
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class,
                ManagementWebSecurityAutoConfiguration.class
        }
)
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    List<Connector> getConnectors(ObjectMapper mapper) {
        List<Connector> connectors = Lists.newArrayList();
        ServiceLoader<Connector> loader = ServiceLoader.load(Connector.class);
        for (Connector connector : loader) {
            connectors.add(connector);
        }

        connectors.add(getKompisConnector(mapper));

        return Lists.transform(connectors, c -> {
            // TODO: Remove when old API is deprecated.
            Connector hackConnector = RegexConnector.create(c,
                    Pattern.compile("(?<host>(?:http|https)://.*?)/api/data/(?<id>.*)/latest(?<param>[?|#].*)"),
                    "${host}/api/v3/data/${id}${param}"
            );

            return TimeoutConnector.create(hackConnector, 10, TimeUnit.SECONDS);
        });
    }

    Connector getKompisConnector(ObjectMapper mapper) {

        SimpleClientHttpRequestFactory schrf = new SimpleClientHttpRequestFactory();
        schrf.setBufferRequestBody(false);

        schrf.setTaskExecutor(new ConcurrentTaskExecutor());

        schrf.setConnectTimeout(200);
        schrf.setReadTimeout(1000);

        ExecutorService executorService = Executors.newCachedThreadPool();

        RestTemplate template = new RestTemplate(schrf);

//        template.getInterceptors().add(
//                new AuthorizationTokenInterceptor()
//        );

        //ObjectMapper mapper = new ObjectMapper();
        // mapper.registerModule(new JavaTimeModule());
        template.getMessageConverters().add(
                0, new DataHttpConverter(mapper)
        );
        template.getMessageConverters().add(
                0, new DataStructureHttpConverter(mapper)
        );
        template.getMessageConverters().add(
                0, new DatasetHttpMessageConverter(mapper)
        );

        RestTemplateConnector restTemplateConnector = new RestTemplateConnector(
                template,
                executorService
        );
        return restTemplateConnector;
    }

    @Bean
    public VTLScriptEngine getVTLEngine(List<Connector> connectors) {
        return new VTLScriptEngine(connectors.toArray(new Connector[]{}));
    }

    @Bean(name = "vtlBindings")
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Bindings getBindings(VTLScriptEngine vtlEngine) {
        return vtlEngine.createBindings();
    }

    /* used to create keys for */
    @Bean(name = "hasher")
    public Function<String, HashCode> getHashFunction() {
        return expression -> Hashing.murmur3_32().hashString(firstNonNull(expression, ""), Charsets.UTF_8);
    }

}
