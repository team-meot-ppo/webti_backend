package org.meotppo.webti.service.propensityAnalysis;

import java.util.List;
import java.util.stream.Collectors;

import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;
import org.meotppo.webti.domain.entity.jpa.question.Option;
import org.meotppo.webti.domain.entity.jpa.question.Question;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developerType.WebDeveloperProfileRepository;
import org.meotppo.webti.domain.repository.jpa.question.QuestionRepository;
import org.meotppo.webti.dto.file.ImageDto;
import org.meotppo.webti.dto.propensityAnalysis.PropensityAnalysisDto;
import org.meotppo.webti.dto.propensityAnalysis.PropensityOptionDto;
import org.meotppo.webti.dto.propensityAnalysis.PropensityProfileResponseDto;
import org.meotppo.webti.dto.propensityAnalysis.PropensityQuestionDto;
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

        type.append(propensityAnalysisDto.getE() > propensityAnalysisDto.getI() ? "E" : "I");
        type.append(propensityAnalysisDto.getS() > propensityAnalysisDto.getN() ? "S" : "N");
        type.append(propensityAnalysisDto.getT() > propensityAnalysisDto.getF() ? "T" : "F");
        type.append(propensityAnalysisDto.getJ() > propensityAnalysisDto.getP() ? "J" : "P");

        WebDeveloperProfile developerProfile = webDeveloperProfileRepository.findByMbtiType(MbtiType.valueOf(type.toString()))
                .orElseThrow(() -> new WebDeveloperProfileNotFoundException());

        ImageDto imageDto = ImageDto.builder()
                .url(developerProfile.getImage().getUrl())
                .build();
        
        PropensityProfileResponseDto responseDto = PropensityProfileResponseDto.builder()
                .result(developerProfile.getResult())
                .description(developerProfile.getDescription())
                .mbtiType(developerProfile.getMbtiType())
                .imageDto(imageDto)
                .build();

        return responseDto;
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