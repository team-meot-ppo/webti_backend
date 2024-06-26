package org.meotppo.webti.service.propensityAnalysis;

import java.util.List;
import java.util.stream.Collectors;

import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;
import org.meotppo.webti.domain.entity.jpa.question.Option;
import org.meotppo.webti.domain.entity.jpa.question.Question;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developerType.WebDeveloperProfileRepository;
import org.meotppo.webti.domain.repository.jpa.question.QuestionRepository;
import org.meotppo.webti.dto.PropensityAnalysis.PropensityAnalysisDto;
import org.meotppo.webti.dto.PropensityAnalysis.PropensityOptionDto;
import org.meotppo.webti.dto.PropensityAnalysis.PropensityQuestionDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropensityAnalysisService {
    private final WebDeveloperProfileRepository webDeveloperProfileRepository;
    private final QuestionRepository questionRepository;
    
    public String analyzeType(PropensityAnalysisDto propensityAnalysisDto) {
        StringBuilder type = new StringBuilder();

        type.append(propensityAnalysisDto.getE() > propensityAnalysisDto.getI() ? "E" : "I");
        type.append(propensityAnalysisDto.getS() > propensityAnalysisDto.getN() ? "S" : "N");
        type.append(propensityAnalysisDto.getT() > propensityAnalysisDto.getF() ? "T" : "F");
        type.append(propensityAnalysisDto.getJ() > propensityAnalysisDto.getP() ? "J" : "P");

        return type.toString();
    }

    public WebDeveloperProfile getDeveloperProfile(String developerTypeCode) {
        return webDeveloperProfileRepository.findByMbtiType(MbtiType.valueOf(developerTypeCode))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 개발자 유형이 없습니다."));
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