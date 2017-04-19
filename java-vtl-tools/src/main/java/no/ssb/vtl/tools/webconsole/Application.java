package no.ssb.vtl.tools.webconsole;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import no.ssb.common.data.connector.RestDataConnector;
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.connectors.SsbApiConnector;
import no.ssb.vtl.connectors.SsbKlassApiConnector;
import no.ssb.vtl.script.VTLScriptEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.script.Bindings;
import java.util.List;
import java.util.function.Function;

/**
 * Spring application
 */
@SpringBootApplication
@WebAppConfiguration
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    List<Connector> getConnectors(ObjectMapper mapper) {
        return Lists.newArrayList(
                new SsbKlassApiConnector(mapper),
                new SsbApiConnector(mapper)
        );
    }

    @Bean
    public VTLScriptEngine getVTLEngine(List<Connector> connectors) {
        return new VTLScriptEngine(connectors.toArray(new Connector[]{}));
    }

    @Bean(name = "vtlBindings")
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Bindings getBindings(VTLScriptEngine vtlEngine) {
        return vtlEngine.createBindings();
    }

    /* used to create keys for */
    @Bean(name = "hasher")
    public Function<String, HashCode> getHashFunction() {
        return expression -> Hashing.murmur3_32().hashString(expression, Charsets.UTF_8);
    }

}
