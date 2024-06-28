package org.meotppo.webti.controller.propensityAnalysis;

import static org.meotppo.webti.response.ResponseUtil.createSuccessResponse;

import java.util.List;

import org.meotppo.webti.dto.PropensityAnalysis.PropensityAnalysisDto;
import org.meotppo.webti.dto.PropensityAnalysis.PropensityProfileResponseDto;
import org.meotppo.webti.dto.PropensityAnalysis.PropensityQuestionDto;
import org.meotppo.webti.response.ResponseBody;
import org.meotppo.webti.service.propensityAnalysis.PropensityAnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/propensity-analysis/v1")
public class PropensityAnalysisController {
    private final PropensityAnalysisService propensityAnalysisService;

    @PostMapping("/result")
    public ResponseEntity<ResponseBody<PropensityProfileResponseDto>> propensityAnalysis(@RequestBody @Valid PropensityAnalysisDto propensityAnalysisDto) {
        PropensityProfileResponseDto responseDto = propensityAnalysisService.analyzeType(propensityAnalysisDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createSuccessResponse(responseDto));
    }

    @GetMapping("/question")
    public ResponseEntity<ResponseBody<List<PropensityQuestionDto>>> getPropensityQuestions() {
        List<PropensityQuestionDto> questions = propensityAnalysisService.getPropensityQuestions();
        return ResponseEntity.ok(createSuccessResponse(questions));
    }
}