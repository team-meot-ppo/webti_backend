package org.meotppo.webti.dto.analysis;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuestionDto {
    private final String question;
    private final List<PropensityOptionDto> options;
}
