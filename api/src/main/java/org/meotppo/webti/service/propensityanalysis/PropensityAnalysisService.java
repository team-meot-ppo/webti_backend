package org.meotppo.webti.service.propensityanalysis;

import java.util.List;
import java.util.stream.Collectors;

import org.meotppo.webti.domain.entity.jpa.question.Option;
import org.meotppo.webti.domain.entity.jpa.question.Question;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developertype.WebDeveloperProfileRepository;
import org.meotppo.webti.domain.repository.jpa.question.QuestionRepository;
import org.meotppo.webti.dto.propensityanalysis.PropensityAnalysisDto;
import org.meotppo.webti.dto.propensityanalysis.PropensityOptionDto;
import org.meotppo.webti.domain.dto.propensityanalysis.PropensityProfileResponseDto;
import org.meotppo.webti.dto.propensityanalysis.PropensityQuestionDto;
import org.meotppo.webti.response.exception.common.WebDeveloperProfileNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PropensityAnalysisService {
    private final WebDeveloperProfileRepository webDeveloperProfileRepository;
    private final QuestionRepository questionRepository;
    
    public PropensityProfileResponseDto analyzeType(PropensityAnalysisDto propensityAnalysisDto) {
        StringBuilder type = new StringBuilder();

        type.append(propensityAnalysisDto.getEXTROVERSION() > propensityAnalysisDto.getINTROVERSION() ? "E" : "I");
        type.append(propensityAnalysisDto.getSENSING() > propensityAnalysisDto.getINTUITION() ? "S" : "N");
        type.append(propensityAnalysisDto.getTHINKING() > propensityAnalysisDto.getFEELING() ? "T" : "F");
        type.append(propensityAnalysisDto.getJUDGING() > propensityAnalysisDto.getPERCEIVING() ? "J" : "P");

        PropensityProfileResponseDto developerProfile = webDeveloperProfileRepository.findProfileByMbtiType(MbtiType.valueOf(type.toString()))
                .orElseThrow(() -> new WebDeveloperProfileNotFoundException());

        return developerProfile;
    }

    public List<PropensityQuestionDto> getPropensityQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private PropensityQuestionDto convertToDto(Question question) {
        List<PropensityOptionDto> options = question.getOptions().stream().map(this::convertToDto).collect(Collectors.toList());        
        return new PropensityQuestionDto(question.getQuestion(), options);
    }

    private PropensityOptionDto convertToDto(Option option) {
        return new PropensityOptionDto(option.getAnswer(), option.getPersonalityType(), option.getScore());
    }
}