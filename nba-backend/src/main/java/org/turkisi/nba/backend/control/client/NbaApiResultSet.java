package org.turkisi.nba.backend.control.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Data
@NoArgsConstructor
public class NbaApiResultSet {
    @NotNull
    private String name;
    @NotNull
    private List<String> headers;
    @NotNull
    private List<List<Object>> rowSet;
}
