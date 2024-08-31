package org.meotppo.webti.domain.dto.propensityanalysis;

import lombok.Builder;
import lombok.Data;
import org.meotppo.webti.domain.dto.file.ImageDto;
import org.meotppo.webti.domain.entity.type.MbtiType;

@Data
public class ProfileResponseDto {
    private String result;
    private String description;
    private MbtiType mbtiType;
    private ImageDto imageDto;

    @Builder
    public ProfileResponseDto(String result, String description, MbtiType mbtiType, String imageUrl) {
        this.result = result;
        this.description = description;
        this.mbtiType = mbtiType;
        this.imageDto = ImageDto.builder().url(imageUrl).build();
    }
}
