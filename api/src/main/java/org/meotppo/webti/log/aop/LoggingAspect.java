package org.meotppo.webti.log.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final ObjectMapper objectMapper;

    @Around("within(@org.springframework.web.bind.annotation.RestController *) && "
            + "!@annotation(org.meotppo.webti.log.aop.NotLogging)")
    public Object requestLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        LogInfoDto logInfoDto = new LogInfoDto(joinPoint, joinPoint.getTarget().getClass(), objectMapper);
        try {
            Object result = joinPoint.proceed(joinPoint.getArgs());
            String logMessage = convertToJson(Map.entry("logInfo", logInfoDto));

            log.info(logMessage);
            return result;
        } catch (Exception e) {
            logInfoDto.setException(e.toString());
            String logMessage = convertToJson(logInfoDto);

            log.error(logMessage);
            throw e;
        }
    }

    private String convertToJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }
}
