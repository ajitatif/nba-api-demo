package org.turkisi.nba.backend.domain;

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
public class GoalStats {

    @NotNull
    private Double made;
    @NotNull
    private Double attempted;
    @NotNull
    private Double pct;
}
