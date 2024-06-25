package org.meotppo.webti.domain.entity.jpa.developerProfile;

import org.meotppo.webti.domain.entity.jpa.common.JpaEntityDate;
import org.meotppo.webti.domain.entity.type.MbtiType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WebDeveloperProfile extends JpaEntityDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MbtiType type;

    private String result; //개발자 유형

    private String description; //개발자 유형에 따른 설명

    @Builder
    public WebDeveloperProfile(MbtiType type, String result, String description) {
        this.type = type;
        this.result = result;
        this.description = description;
    }
}