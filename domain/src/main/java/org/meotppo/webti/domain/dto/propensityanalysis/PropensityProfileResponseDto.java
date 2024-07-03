package org.meotppo.webti.domain.dto.propensityanalysis;

import lombok.Data;
import org.meotppo.webti.domain.dto.file.ImageDto;
import org.meotppo.webti.domain.entity.type.MbtiType;

import lombok.Builder;

@Data
public class PropensityProfileResponseDto {
    private String result;
    private String description;
    private MbtiType mbtiType;
    private ImageDto imageDto;

    @Builder
    public PropensityProfileResponseDto(String result, String description, MbtiType mbtiType, String imageUrl) {
        this.result = result;
        this.description = description;
        this.mbtiType = mbtiType;
        this.imageDto = ImageDto.builder().url(imageUrl).build();
    }
}