package org.meotppo.webti.dto.PropensityAnalysis;

import org.meotppo.webti.domain.entity.type.MbtiType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PropensityProfileResponseDto {
    private String result;
    private String description;
    private MbtiType mbtiType;

    @Builder
    public PropensityProfileResponseDto(String result, String description, MbtiType mbtiType) {
        this.result = result;
        this.description = description;
        this.mbtiType = mbtiType;
    }
}
