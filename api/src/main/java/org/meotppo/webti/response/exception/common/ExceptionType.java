package org.meotppo.webti.response.exception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ExceptionType {
    EXCEPTION(INTERNAL_SERVER_ERROR, "E001", "An unexpected error has occurred."),
    BIND_EXCEPTION(BAD_REQUEST, "E004", "There was an error with the request binding."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ExceptionType(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
