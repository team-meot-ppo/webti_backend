package org.meotppo.webti.controller.status;

import lombok.RequiredArgsConstructor;
import org.meotppo.webti.log.aop.NotLogging;
import org.meotppo.webti.response.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.meotppo.webti.response.ResponseUtil.createSuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/status/v1")
public class StatusController {

    @NotLogging
    @GetMapping("/check")
    public ResponseEntity<ResponseBody<String>> checkStatus() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createSuccessResponse("Service is up and running"));
    }
}
