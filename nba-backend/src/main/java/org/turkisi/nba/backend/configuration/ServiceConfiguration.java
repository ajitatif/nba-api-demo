package org.turkisi.nba.backend.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Configuration
public class ServiceConfiguration {
    @Bean
    public RestTemplateBuilder getRestTemplateBuilder() {
        return new RestTemplateBuilder()
            .interceptors((request, body, execution) -> {
            // i know this is stupid, but i also know it works
            request.getHeaders().add("User-Agent", "PostmanRuntime/0.0.0");
            return execution.execute(request, body);
        });
    }
}
