package org.meotppo.webti.dto.PropensityAnalysis;

import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.dto.file.ImageDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PropensityProfileResponseDto {
    private String result;
    private String description;
    private MbtiType mbtiType;
    private ImageDto image;

    @Builder
    public PropensityProfileResponseDto(String result, String description, MbtiType mbtiType, ImageDto image) {
        this.result = result;
        this.description = description;
        this.mbtiType = mbtiType;
        this.image = image;
    }
}
