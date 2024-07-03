package org.meotppo.webti.domain.dto.file;

import lombok.Builder;
import lombok.Data;

@Data
public class ImageDto {
    private String url;

    @Builder
    public ImageDto(String url) {
        this.url = url;
    }
}
