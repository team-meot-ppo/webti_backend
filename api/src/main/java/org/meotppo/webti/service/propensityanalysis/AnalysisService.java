package org.meotppo.webti.service.propensityanalysis;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.dto.propensityanalysis.ProfileResponseDto;
import org.meotppo.webti.domain.entity.jpa.question.Option;
import org.meotppo.webti.domain.entity.jpa.question.Question;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developertype.WebDeveloperProfileRepository;
import org.meotppo.webti.domain.repository.jpa.question.QuestionRepository;
import org.meotppo.webti.dto.propensityanalysis.AnalysisDto;
import org.meotppo.webti.dto.propensityanalysis.PropensityOptionDto;
import org.meotppo.webti.dto.propensityanalysis.QuestionDto;
import org.meotppo.webti.response.exception.common.WebDeveloperProfileNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisService {
    private final WebDeveloperProfileRepository webDeveloperProfileRepository;
    private final QuestionRepository questionRepository;

    public ProfileResponseDto analyzeType(AnalysisDto analysisDto) {
        StringBuilder type = new StringBuilder();

        type.append(analysisDto.getEXTROVERSION() > analysisDto.getINTROVERSION() ? "E" : "I");
        type.append(analysisDto.getSENSING() > analysisDto.getINTUITION() ? "S" : "N");
        type.append(analysisDto.getTHINKING() > analysisDto.getFEELING() ? "T" : "F");
        type.append(analysisDto.getJUDGING() > analysisDto.getPERCEIVING() ? "J" : "P");

        ProfileResponseDto developerProfile = webDeveloperProfileRepository.findProfileByMbtiType(
                        MbtiType.valueOf(type.toString()))
                .orElseThrow(() -> new WebDeveloperProfileNotFoundException());

        return developerProfile;
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
