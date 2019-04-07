package org.turkisi.nba.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Draft {

    private Integer year;
    private Integer round;
    private Integer pick;
}
