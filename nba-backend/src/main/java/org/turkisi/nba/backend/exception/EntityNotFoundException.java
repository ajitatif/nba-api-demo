package org.turkisi.nba.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends ApiException {

    public EntityNotFoundException() {
        super("Could not find requested entity");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
