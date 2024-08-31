package org.meotppo.webti.domain.repository.jpa.developertype;

import java.util.Optional;
import org.meotppo.webti.domain.dto.analysis.ProfileResponseDto;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByMbtiType(MbtiType mbtiType);

    @Query("SELECT new org.meotppo.webti.domain.dto.analysis.ProfileResponseDto(p.result, p.description, p.mbtiType, i.url) "
            + "FROM Profile p "
            + "JOIN p.image i "
            + "WHERE p.mbtiType = :mbtiType")
    Optional<ProfileResponseDto> findProfileByMbtiType(MbtiType mbtiType);
}
