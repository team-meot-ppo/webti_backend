package org.meotppo.webti.response.exception.common;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionType {
    EXCEPTION(INTERNAL_SERVER_ERROR, "E001", "An unexpected error has occurred."),
    BIND_EXCEPTION(BAD_REQUEST, "E002", "There was an error with the request binding."),
    WEB_DEVELOPER_PROFILE_NOT_FOUND(NOT_FOUND, "E003", "Web Developer Profile not found."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
