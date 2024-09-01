package org.meotppo.webti.exception.common;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> businessException(BusinessException e) {
        ExceptionType exceptionType = e.getExceptionType();
        return ResponseEntity
                .status(exceptionType.getStatus())
                .body(new ExceptionResponse(exceptionType.getCode(), exceptionType.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(ExceptionType.BIND_EXCEPTION.getStatus())
                .body(new ExceptionResponse(ExceptionType.BIND_EXCEPTION.getCode(),
                        e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(ExceptionType.EXCEPTION.getStatus())
                .body(new ExceptionResponse(ExceptionType.EXCEPTION.getCode(), ExceptionType.EXCEPTION.getMessage()));
    }
}
