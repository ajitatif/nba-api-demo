package org.turkisi.nba.backend.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@JsonInclude(NON_EMPTY)
@Getter
class ApiException extends RuntimeException {

    ApiException(String message) {
        super(message);
    }
}
