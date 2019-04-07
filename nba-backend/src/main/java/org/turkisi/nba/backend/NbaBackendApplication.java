package org.turkisi.nba.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class NbaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbaBackendApplication.class, args);
	}

}
