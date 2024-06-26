package org.meotppo.webti.domain.entity.jpa.developerProfile;

import jakarta.persistence.*;
import org.meotppo.webti.domain.entity.jpa.common.JpaEntityDate;
import org.meotppo.webti.domain.entity.type.MbtiType;

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
    @Column(unique = true)
    private MbtiType mbtiType;

    @Column(nullable = false)
    private String result; //개발자 유형

    @Column(nullable = false)
    private String description; //개발자 유형에 따른 설명

    @Builder
    public WebDeveloperProfile(MbtiType mbtiType, String result, String description) {
        this.mbtiType = mbtiType;
        this.result = result;
        this.description = description;
    }
}
