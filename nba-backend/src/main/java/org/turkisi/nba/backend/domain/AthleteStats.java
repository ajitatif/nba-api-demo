package org.turkisi.nba.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AthleteStats {

    @JsonIgnore
    private Long id;
    @NotNull
    private String season;
    @NotNull
    private String team;
    @NotNull
    private Double age;
    @NotNull
    private Integer gamesPlayed;
    @NotNull
    private Integer gamesStarted;
    @NotNull
    private Double minutes;
    @NotNull
    private GoalStats fieldGoals;
    @NotNull
    private GoalStats threePointers;
    @NotNull
    private GoalStats freeThrows;
    @NotNull
    private Double offensiveRebounds;
    @NotNull
    private Double defensiveRebounds;
    @NotNull
    private Double rebounds;
    @NotNull
    private Double assists;
    @NotNull
    private Double steals;
    @NotNull
    private Double blocks;
    @NotNull
    private Double turnovers;
    @NotNull
    private Double personalFouls;
    @NotNull
    private Double points;
}
