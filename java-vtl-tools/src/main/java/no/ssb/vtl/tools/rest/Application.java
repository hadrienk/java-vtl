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
import no.ssb.vtl.connectors.SsbApiConnector;
import no.ssb.vtl.connectors.SsbKlassApiConnector;
import no.ssb.vtl.script.VTLScriptEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.script.Bindings;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.MoreObjects.firstNonNull;

/**
 * Spring application
 */
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    List<Connector> getConnectors(ObjectMapper mapper) {
        return Lists.newArrayList(

//                new RestDataConnector("http://localhost:7080", mapper),
//                new RestDataConnector("http://al-kostra-app-utv:7080", mapper),
//                new RestDataConnector("http://al-kostra-app-test:7080", mapper),
//                new RestDataConnector("http://al-kostra-app-utv.ssb.no:7080", mapper),
//                new RestDataConnector("http://al-kostra-app-test.ssb.no:7080", mapper),
//
//                new RestDataConnector("http://localhost:7090", mapper),
//                new RestDataConnector("http://al-kostra-app-utv:7090", mapper),
//                new RestDataConnector("http://al-kostra-app-test:7090", mapper),
//                new RestDataConnector("http://al-kostra-app-utv.ssb.no:7090", mapper),
//                new RestDataConnector("http://al-kostra-app-test.ssb.no:7090", mapper),

                new SsbKlassApiConnector(mapper, SsbKlassApiConnector.PeriodType.YEAR),
                new SsbApiConnector(mapper)
        );
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
