package org.meotppo.webti.controller.result;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.dto.result.StatisticDTO;
import org.meotppo.webti.dto.result.TestResultRequest;
import org.meotppo.webti.response.ResponseBody;
import org.meotppo.webti.service.result.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.meotppo.webti.response.ResponseUtil.createSuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results/v1")
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/test-result")
    public ResponseEntity<ResponseBody<Void>> createTestResult(@Valid @RequestBody TestResultRequest req) {
        resultService.createTestResult(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createSuccessResponse());
    }

    @GetMapping("/statistics")
    public ResponseEntity<ResponseBody<List<StatisticDTO>>> readStatistics() {
        List<StatisticDTO> statistics = resultService.readStatistics();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createSuccessResponse(statistics));
    }
}
