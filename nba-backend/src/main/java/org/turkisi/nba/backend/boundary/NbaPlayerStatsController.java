package org.turkisi.nba.backend.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.turkisi.nba.backend.control.AthleteService;
import org.turkisi.nba.backend.domain.AthleteDetail;
import org.turkisi.nba.backend.domain.AthleteSearchResult;
import org.turkisi.nba.backend.domain.AthleteStats;
import org.turkisi.nba.backend.domain.DataStatus;

import java.util.List;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Controller
@RequestMapping(path = "players/v1")
public class NbaPlayerStatsController {

    private AthleteService athleteService;

    public NbaPlayerStatsController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping("status")
    public @ResponseBody DataStatus getDataStatus() {
        return athleteService.getDataStatus();
    }

    @GetMapping("season")
    public @ResponseBody void populateFromSeason(@RequestParam("season") String season) {
        athleteService.populateFromSeason(season);
    }

    @GetMapping("search")
    public @ResponseBody List<AthleteSearchResult> search(@RequestParam("q") String searchTerm) {
        return athleteService.search(searchTerm);
    }

    @GetMapping("{id}")
    public @ResponseBody AthleteDetail getDetail(@PathVariable("id") Integer athleteId) {
        return athleteService.getAthleteDetail(athleteId);
    }

    @GetMapping("{id}/stats")
    public @ResponseBody List<AthleteStats> getStatsForAthlete(@PathVariable("id") Integer athleteId) {
        return athleteService.getAthleteStats(athleteId);
    }
}
