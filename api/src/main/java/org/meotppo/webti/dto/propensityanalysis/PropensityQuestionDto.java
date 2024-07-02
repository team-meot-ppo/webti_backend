package org.meotppo.webti.dto.propensityanalysis;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PropensityQuestionDto {
    private String question;
    private List<PropensityOptionDto> options;

    @Builder
    public PropensityQuestionDto(String question, List<PropensityOptionDto> options) {
        this.question = question;
        this.options = options;
    }
}
