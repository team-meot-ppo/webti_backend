package org.meotppo.webti.domain.repository.jpa.developertype;

import java.util.Optional;

import org.meotppo.webti.domain.entity.jpa.developerprofile.WebDeveloperProfile;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebDeveloperProfileRepository extends JpaRepository<WebDeveloperProfile, Long>{
    Optional<WebDeveloperProfile> findByMbtiType(MbtiType mbtiType);
}
