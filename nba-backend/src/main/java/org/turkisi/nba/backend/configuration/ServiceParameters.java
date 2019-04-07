package org.turkisi.nba.backend.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Configuration
@ConfigurationProperties(prefix = "service")
@Getter @Setter @NoArgsConstructor
public class ServiceParameters {

    private String nbaPublicApiUrl;
    private String prefillSeasons;
}
