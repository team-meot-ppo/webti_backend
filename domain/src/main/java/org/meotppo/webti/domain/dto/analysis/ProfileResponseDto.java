package org.meotppo.webti.domain.dto.analysis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.entity.type.MbtiType;

@Getter
@RequiredArgsConstructor
public class ProfileResponseDto {
    private final String result;
    private final String description;
    private final MbtiType mbtiType;
    private final String url;
}
