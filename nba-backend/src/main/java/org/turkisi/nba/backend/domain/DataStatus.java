package org.turkisi.nba.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DataStatus {

    private List<String> seasonsPopulated;
    private Integer totalNumberOfPlayers;
}
