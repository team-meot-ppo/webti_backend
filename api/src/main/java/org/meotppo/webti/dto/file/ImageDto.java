package org.meotppo.webti.dto.file;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageDto {
    private String url;

    @Builder
    public ImageDto(String url) {
        this.url = url;
    }
}
