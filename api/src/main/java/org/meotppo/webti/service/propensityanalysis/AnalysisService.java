package org.meotppo.webti.service.propensityanalysis;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.dto.analysis.ProfileResponseDto;
import org.meotppo.webti.domain.entity.jpa.question.Option;
import org.meotppo.webti.domain.entity.jpa.question.Question;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developertype.ProfileRepository;
import org.meotppo.webti.domain.repository.jpa.question.QuestionRepository;
import org.meotppo.webti.dto.analysis.AnalysisRequest;
import org.meotppo.webti.dto.analysis.PropensityOptionDto;
import org.meotppo.webti.dto.analysis.QuestionDto;
import org.meotppo.webti.response.exception.common.WebDeveloperProfileNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisService {

    private final ProfileRepository profileRepository;
    private final QuestionRepository questionRepository;

    public ProfileResponseDto analyzeType(AnalysisRequest analysisRequest) {

        String type = (analysisRequest.getEXTROVERSION() > analysisRequest.getINTROVERSION() ? "E" : "I")
                + (analysisRequest.getSENSING() > analysisRequest.getINTUITION() ? "S" : "N")
                + (analysisRequest.getTHINKING() > analysisRequest.getFEELING() ? "T" : "F")
                + (analysisRequest.getJUDGING() > analysisRequest.getPERCEIVING() ? "J" : "P");

        return profileRepository.findProfileByMbtiType(
                        MbtiType.valueOf(type))
                .orElseThrow(WebDeveloperProfileNotFoundException::new);
    }

    public List<QuestionDto> getPropensityQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private QuestionDto convertToDto(Question question) {
        List<PropensityOptionDto> options = question.getOptions().stream().map(this::convertToDto)
                .collect(Collectors.toList());
        return new QuestionDto(question.getQuestion(), options);
    }

    private PropensityOptionDto convertToDto(Option option) {
        return new PropensityOptionDto(option.getAnswer(), option.getPersonalityType(), option.getScore());
    }
}
