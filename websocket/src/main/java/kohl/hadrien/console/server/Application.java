package kohl.hadrien.console.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import kohl.hadrien.vtl.connector.Connector;
import kohl.hadrien.vtl.script.VTLScriptEngine;
import no.ssb.vtl.connectors.SsbApiConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.script.Bindings;
import java.util.List;

/**
 * Spring application
 */
@SpringBootApplication
@WebAppConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    List<Connector> getConnectors(ObjectMapper mapper) {
        return Lists.newArrayList(
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

}
