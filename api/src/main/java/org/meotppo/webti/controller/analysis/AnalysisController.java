package org.meotppo.webti.controller.analysis;

import static org.meotppo.webti.response.ResponseUtil.createSuccessResponse;

import java.util.List;

import org.meotppo.webti.dto.propensityanalysis.AnalysisDto;
import org.meotppo.webti.domain.dto.propensityanalysis.ProfileResponseDto;
import org.meotppo.webti.dto.propensityanalysis.QuestionDto;
import org.meotppo.webti.response.ResponseBody;
import org.meotppo.webti.service.propensityanalysis.AnalysisService;
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
@RequestMapping("/api/analyses")
public class AnalysisController {

    private final AnalysisService analysisService;

    @PostMapping("/result")
    public ResponseEntity<ResponseBody<ProfileResponseDto>> propensityAnalysis(@RequestBody @Valid AnalysisDto analysisDto) {
        ProfileResponseDto responseDto = analysisService.analyzeType(analysisDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createSuccessResponse(responseDto));
    }

    @GetMapping("/question")
    public ResponseEntity<ResponseBody<List<QuestionDto>>> getPropensityQuestions() {
        List<QuestionDto> questions = analysisService.getPropensityQuestions();
        return ResponseEntity.ok(createSuccessResponse(questions));
    }
}
