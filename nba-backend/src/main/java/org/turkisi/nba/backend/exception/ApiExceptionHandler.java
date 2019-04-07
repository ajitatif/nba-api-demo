package org.turkisi.nba.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ApiException> handleApiException(ApiException ex) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseStatus[] responseStatuses = ex.getClass().getAnnotationsByType(ResponseStatus.class);
        if (responseStatuses.length > 0) {
            status = responseStatuses[0].value();
        }

        return ResponseEntity.status(status).body(ex);
    }
}
