package org.turkisi.nba.backend.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Data
@Builder
public class Athlete {

    @NotNull
    private Integer id;
    @NotNull
    private Integer originalId;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String teamName;
    @NotNull
    private String teamAbbreviation;

    private String searchIndex;

    public Athlete withSearchIndex() {
        searchIndex = MessageFormat.format("{0} {1} {2} {3}",
                name.toLowerCase(),
                surname.toLowerCase(),
                teamAbbreviation.toLowerCase(),
                teamName.toLowerCase());
        return this;
    }
}
