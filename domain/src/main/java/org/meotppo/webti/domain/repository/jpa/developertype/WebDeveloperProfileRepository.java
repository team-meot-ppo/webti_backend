package org.meotppo.webti.domain.repository.jpa.developertype;

import java.util.Optional;

import org.meotppo.webti.domain.dto.propensityanalysis.PropensityProfileResponseDto;
import org.meotppo.webti.domain.entity.jpa.developerprofile.WebDeveloperProfile;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface WebDeveloperProfileRepository extends JpaRepository<WebDeveloperProfile, Long>{
    Optional<WebDeveloperProfile> findByMbtiType(MbtiType mbtiType);

    @Query("SELECT new org.meotppo.webti.domain.dto.propensityanalysis.PropensityProfileResponseDto(wdp.result, wdp.description, wdp.mbtiType, img.url) " +
            "FROM WebDeveloperProfile wdp " +
            "JOIN wdp.image img " +
            "WHERE wdp.mbtiType = :mbtiType")
    Optional<PropensityProfileResponseDto> findProfileByMbtiType(MbtiType mbtiType);
}