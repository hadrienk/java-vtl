package no.ssb.vtl.tools.webconsole;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import no.ssb.vtl.connector.Connector;
import no.ssb.vtl.connectors.SsbApiConnector;
import no.ssb.vtl.connectors.SsbKlassApiConnector;
import no.ssb.vtl.script.VTLScriptEngine;
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
                new SsbKlassApiConnector(mapper, SsbKlassApiConnector.PeriodType.YEAR),
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
