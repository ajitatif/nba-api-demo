package org.turkisi.nba.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AthleteDetail {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String school;
    @NotNull
    private String country;
    @NotNull
    private Integer height;
    @NotNull
    private String position;
    @NotNull
    private Integer fromYear;
    @NotNull
    private Integer toYear;
    private Draft draft;
}
