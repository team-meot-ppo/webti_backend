package org.meotppo.webti.log.aop;

import static org.meotppo.webti.log.filter.MDCFilter.REQUEST_ID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogInfoDto {
    private String url;
    private String name;
    private String method;
    private Map<String, String> header;
    private String parameters;
    private String body;
    private String ipAddress;
    private String exception;
    private String requestId;

    public LogInfoDto(ProceedingJoinPoint joinPoint, Class<?> targetClass, ObjectMapper objectMapper) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        this.url = request.getRequestURL().toString();
        this.name = targetClass.getSimpleName() + "." + joinPoint.getSignature().getName();
        this.method = request.getMethod();
        this.header = extractHeaders(request);
        this.parameters = objectMapper.writeValueAsString(extractParameters(request));
        this.body = objectMapper.writeValueAsString(extractBody(joinPoint));
        this.ipAddress = request.getRemoteAddr();
        this.requestId = MDC.get(REQUEST_ID);
    }

    private Map<String, String> extractHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }

    private Map<String, String> extractParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            parameters.put(paramName, request.getParameter(paramName));
        }
        return parameters;
    }

    private Object extractBody(ProceedingJoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg != null && !(arg instanceof HttpServletRequest)) {
                return arg;
            }
        }
        return null;
    }
}
