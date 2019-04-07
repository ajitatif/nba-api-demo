package org.turkisi.nba.backend.control;

import org.turkisi.nba.backend.domain.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
class NbaApiResultToAthleteMapper {

    private static final AtomicInteger playerIdSequence = new AtomicInteger(0);

    private NbaApiResultToAthleteMapper() {

    }

    static Athlete getAthlete(List<Object> nbaApiResultRow) {

        String[] surnameAndName = ((String) nbaApiResultRow.get(1)).split(",", 2);
        return Athlete.builder()
                .id(playerIdSequence.incrementAndGet())
                .originalId((int) nbaApiResultRow.get(0))
                .name(surnameAndName.length > 1 ? surnameAndName[1] : surnameAndName[0])
                .surname(surnameAndName[0])
                .teamAbbreviation((String) nbaApiResultRow.get(10))
                .teamName(nbaApiResultRow.get(8) + " " + nbaApiResultRow.get(9))
                .build()
                .withSearchIndex();
    }

    static AthleteDetail getAthleteDetail(List<Object> apiResultRow) {
        return AthleteDetail.builder()
                .firstName((String) apiResultRow.get(1))
                .lastName((String) apiResultRow.get(2))
                .birthDate(LocalDateTime.parse((String) apiResultRow.get(6), DateTimeFormatter.ISO_DATE_TIME).toLocalDate())
                .school((String) apiResultRow.get(7))
                .country((String) apiResultRow.get(8))
                .height(getHeight((String) apiResultRow.get(10)))
                .position((String) apiResultRow.get(14))
                .fromYear((int) apiResultRow.get(22))
                .toYear((int) apiResultRow.get(23))
                .draft(Draft.builder()
                        .year(Integer.valueOf((String) apiResultRow.get(27)))
                        .round(Integer.valueOf((String) apiResultRow.get(28)))
                        .pick(Integer.valueOf((String) apiResultRow.get(29)))
                        .build())
                .build();
    }

    private static int getHeight(String feetAndInches) {

        String[] split = feetAndInches.split("-");
        return (int) (((Integer.valueOf(split[0]) * 12) + Integer.valueOf(split[1])) * 2.54);
    }

    static AthleteStats getAthleteStats(List<Object> objects) {
        return AthleteStats.builder()
                .season((String) objects.get(1))
                .team((String) objects.get(4))
                .age((double) objects.get(5))
                .gamesPlayed((int) objects.get(6))
                .gamesStarted((int) objects.get(7))
                .minutes((double) objects.get(8))
                .fieldGoals(GoalStats.builder()
                        .made((double) objects.get(9))
                        .attempted((double) objects.get(10))
                        .pct((double) objects.get(11))
                        .build())
                .threePointers(GoalStats.builder()
                        .made((double) objects.get(12))
                        .attempted((double) objects.get(13))
                        .pct((double) objects.get(14))
                        .build())
                .freeThrows(GoalStats.builder()
                        .made((double) objects.get(15))
                        .attempted((double) objects.get(16))
                        .pct((double) objects.get(17))
                        .build())
                .offensiveRebounds((double) objects.get(18))
                .defensiveRebounds((double) objects.get(19))
                .rebounds((double) objects.get(20))
                .assists((double) objects.get(21))
                .steals((double) objects.get(22))
                .blocks((double) objects.get(23))
                .turnovers((double) objects.get(24))
                .personalFouls((double) objects.get(25))
                .points((double) objects.get(26))
                .build();
    }

}
