package org.turkisi.nba.backend.control.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.turkisi.nba.backend.configuration.ServiceParameters;
import org.turkisi.nba.backend.exception.DependentServiceException;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Component
public class NbaApiClientService {

    private String nbaStatsApiBaseUrl;
    private RestTemplateBuilder restTemplateBuilder;

    public NbaApiClientService(ServiceParameters configuration, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
        nbaStatsApiBaseUrl = configuration.getNbaPublicApiUrl();
    }

    public NbaApiResult getAllPlayersBySeason(String season) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        String uriString = UriComponentsBuilder.fromHttpUrl(nbaStatsApiBaseUrl).pathSegment("commonallplayers")
                .queryParam("LeagueID", "00")
                .queryParam("Season", season)
                .queryParam("IsOnlyCurrentSeason", 1)
                .build().toUriString();

        try {
            ResponseEntity<NbaApiResult> responseEntity = restTemplate.getForEntity(uriString, NbaApiResult.class);

            return responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            throw new DependentServiceException(e.getRawStatusCode());
        }
    }

    public NbaApiParamListResult getPlayerDetail(Integer playerId) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        String uriString = UriComponentsBuilder.fromHttpUrl(nbaStatsApiBaseUrl).pathSegment("commonplayerinfo")
                .queryParam("PlayerID", playerId)
                .build().toUriString();

        try {
            ResponseEntity<NbaApiParamListResult> responseEntity = restTemplate.getForEntity(uriString, NbaApiParamListResult.class);

            return responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            throw new DependentServiceException(e.getRawStatusCode());
        }
    }

    public NbaApiResult getPlayerStats(Integer playerId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String uriString = UriComponentsBuilder.fromHttpUrl(nbaStatsApiBaseUrl).pathSegment("playercareerstats")
                .queryParam("PerMode", "PerGame")
                .queryParam("PlayerID", playerId)
                .build().toUriString();

        try {
            ResponseEntity<NbaApiResult> responseEntity = restTemplate.getForEntity(uriString, NbaApiResult.class);

            return responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            throw new DependentServiceException(e.getRawStatusCode());
        }

    }

}
