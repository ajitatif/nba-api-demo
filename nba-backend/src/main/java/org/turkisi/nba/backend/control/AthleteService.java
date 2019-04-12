package org.turkisi.nba.backend.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.turkisi.nba.backend.configuration.ServiceParameters;
import org.turkisi.nba.backend.control.client.NbaApiClientService;
import org.turkisi.nba.backend.control.client.NbaApiParamListResult;
import org.turkisi.nba.backend.control.client.NbaApiResult;
import org.turkisi.nba.backend.control.client.NbaApiResultSet;
import org.turkisi.nba.backend.domain.*;
import org.turkisi.nba.backend.exception.EntityNotFoundException;
import org.turkisi.nba.backend.exception.InvalidRequestException;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Component
public class AthleteService {

    private static final Logger logger = LoggerFactory.getLogger(AthleteService.class);

    private List<Athlete> athletes = new LinkedList<>();
    private Set<String> seasonsPopulated = new HashSet<>();

    private NbaApiClientService nbaApiClientService;
    private ServiceParameters parameters;

    public AthleteService(NbaApiClientService nbaApiClientService, ServiceParameters parameters) {
        this.nbaApiClientService = nbaApiClientService;
        this.parameters = parameters;
    }

    @PostConstruct
    public void init() {
        String prefillSeasons = parameters.getPrefillSeasons();
        if (prefillSeasons != null && !prefillSeasons.trim().isEmpty()) {
            String[] seasons = prefillSeasons.split(",");
            for (String season : seasons) {
                logger.info("Pre-filling from season {}", season);
                populateFromSeason(season);
            }
            logger.info("Filled {} seasons and {} athletes", seasons.length, athletes.size());
        }
    }

    public void populateFromSeason(String season) {
        if (!season.matches("\\d{4}-\\d{2}")) {
            throw new InvalidRequestException("Season must be in XXXX-XX format (like 1998-99)");
        }
        if (!seasonsPopulated.contains(season)) {
            NbaApiResult playersApiResult = nbaApiClientService.getAllPlayersBySeason(season);
            playersApiResult.getResultSets()
                    .forEach(resultSet -> resultSet.getRowSet()
                            .forEach(result -> athletes.add(NbaApiResultToAthleteMapper.getAthlete(result))));
            seasonsPopulated.add(season);
        }
    }

    public DataStatus getDataStatus() {
        return DataStatus.builder().seasonsPopulated(new ArrayList<>(seasonsPopulated)).totalNumberOfPlayers(athletes.size()).build();
    }

    public List<AthleteSearchResult> search(String searchTerm) {

        String query = searchTerm.toLowerCase();

        return athletes.stream().filter(athlete -> athlete.getSearchIndex().contains(query))
                .map(athlete -> AthleteSearchResult.builder()
                                    .id(athlete.getId())
                                    .name(athlete.getName())
                                    .surname(athlete.getSurname())
                                .build())
                .collect(Collectors.toList());
    }

    public AthleteDetail getAthleteDetail(Integer athleteId) {

        @NotNull Integer originalId = getAthleteOriginalId(athleteId);
        NbaApiParamListResult apiResult = nbaApiClientService.getPlayerDetail(originalId);
        NbaApiResultSet commonPlayerInfo = apiResult.getResultSets().stream()
                .filter(nbaApiResultSet -> nbaApiResultSet.getName().equals("CommonPlayerInfo")).findAny().orElseThrow(EntityNotFoundException::new);
        return NbaApiResultToAthleteMapper.getAthleteDetail(commonPlayerInfo.getRowSet().get(0));
    }

    @NotNull
    private Integer getAthleteOriginalId(Integer athleteId) {
        return athletes.stream().filter(athlete -> athleteId.equals(athlete.getId())).findAny().orElseThrow(EntityNotFoundException::new).getOriginalId();
    }

    public List<AthleteStats> getAthleteStats(Integer athleteId) {
        @NotNull Integer originalId = getAthleteOriginalId(athleteId);

        NbaApiResult playerStats = nbaApiClientService.getPlayerStats(originalId);
        @NotNull List<List<Object>> rowSet = playerStats.getResultSets().stream()
                .filter(result -> result.getName().equals("SeasonTotalsRegularSeason")).findAny().orElseThrow(EntityNotFoundException::new).getRowSet();

        return rowSet.stream().map(NbaApiResultToAthleteMapper::getAthleteStats).sorted(Comparator.comparing(AthleteStats::getSeason)).collect(Collectors.toList());
    }
}
