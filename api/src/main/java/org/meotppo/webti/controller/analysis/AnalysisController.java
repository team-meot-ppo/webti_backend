package org.meotppo.webti.controller.analysis;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.dto.analysis.ProfileResponse;
import org.meotppo.webti.dto.analysis.AnalysisRequest;
import org.meotppo.webti.dto.analysis.QuestionDto;
import org.meotppo.webti.service.analysis.AnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analyses")
public class AnalysisController {

    private final AnalysisService analysisService;

    @PostMapping("/result")
    public ResponseEntity<ProfileResponse> propensityAnalysis(
            @RequestBody @Valid AnalysisRequest analysisRequest) {
        ProfileResponse responseDto = analysisService.analyzeType(analysisRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/question")
    public ResponseEntity<List<QuestionDto>> getPropensityQuestions() {
        List<QuestionDto> questions = analysisService.getPropensityQuestions();
        return ResponseEntity.ok(questions);
    }
}
