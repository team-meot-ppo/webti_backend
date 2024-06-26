package org.meotppo.webti.controller.propensityAnalysis;

import static org.meotppo.webti.response.ResponseUtil.createSuccessResponse;

import java.util.List;

import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/developer-ti/v1")
public class PropensityAnalysisController {
    private final PropensityAnalysisService propensityAnalysisService;

    @PostMapping("/propensity-analysis")
    public ResponseEntity<ResponseBody<PropensityProfileResponseDto>> propensityAnalysis(@RequestBody PropensityAnalysisDto propensityAnalysisDto) {
        String developerTypeCode = propensityAnalysisService.analyzeType(propensityAnalysisDto);
        WebDeveloperProfile profile = propensityAnalysisService.getDeveloperProfile(developerTypeCode);
        PropensityProfileResponseDto responseDto = PropensityProfileResponseDto.builder()
                .result(profile.getResult())
                .description(profile.getDescription())
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createSuccessResponse(responseDto));
    }

    @GetMapping("/propensity-analysis/question")
    public ResponseEntity<ResponseBody<List<PropensityQuestionDto>>> getPropensityQuestions() {
        List<PropensityQuestionDto> questions = propensityAnalysisService.getPropensityQuestions();
        return ResponseEntity.ok(createSuccessResponse(questions));
    }
}