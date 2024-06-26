package org.meotppo.webti.dto.PropensityAnalysis;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PropensityProfileResponseDto {
    private String result;
    private String description;

    @Builder
    public PropensityProfileResponseDto(String result, String description) {
        this.result = result;
        this.description = description;
    }
}
