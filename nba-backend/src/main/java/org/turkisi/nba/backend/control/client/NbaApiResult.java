package org.turkisi.nba.backend.control.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Data
@NoArgsConstructor
public class NbaApiResult {

    @NotNull
    private String resource;
    @NotNull
    private Map<String, Object> parameters;
    @NotNull
    private List<NbaApiResultSet> resultSets;
}
