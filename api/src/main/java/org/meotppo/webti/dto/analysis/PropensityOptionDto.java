package org.meotppo.webti.dto.analysis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.entity.type.PersonalityType;

@Getter
@RequiredArgsConstructor
public class PropensityOptionDto {

    private final String answer;

    private final PersonalityType personalityType;

    private final int score;
}
