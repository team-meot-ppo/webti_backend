package org.meotppo.webti.response.exception.common;


import static org.meotppo.webti.response.ResponseUtil.createFailureResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.meotppo.webti.response.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBody<Void>> businessException(BusinessException e) {
        ExceptionType exceptionType = e.getExceptionType();
        return ResponseEntity.status(exceptionType.getStatus())
                .body(createFailureResponse(exceptionType));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBody<Void>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(ExceptionType.BIND_EXCEPTION.getStatus())
                .body(createFailureResponse(ExceptionType.BIND_EXCEPTION,
                        e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBody<Void>> exception(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(ExceptionType.EXCEPTION.getStatus())
                .body(createFailureResponse(ExceptionType.EXCEPTION));
    }
}
