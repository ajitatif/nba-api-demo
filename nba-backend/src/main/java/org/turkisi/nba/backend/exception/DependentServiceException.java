package org.turkisi.nba.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DependentServiceException extends ApiException {

    private final int internalStatusCode;

    public DependentServiceException(int internalStatusCode) {
        this(internalStatusCode, "NBA Stats Public API returned error");
    }

    private DependentServiceException(int internalStatusCode, String message) {
        super(message);
        this.internalStatusCode = internalStatusCode;
    }

    public int getInternalStatusCode() {
        return internalStatusCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
