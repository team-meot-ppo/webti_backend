package org.meotppo.webti.fixture;

import org.meotppo.webti.domain.entity.jpa.file.Image;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.type.MbtiType;

public class ProfileFixture {
    public static Profile createProfile(MbtiType mbtiType, String result, String description, String imageUrl) {
        Image image = Image.builder()
                .url(imageUrl)
                .build();

        return Profile.builder()
                .mbtiType(mbtiType)
                .result(result)
                .description(description)
                .image(image)
                .build();
    }
}
