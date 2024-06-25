package org.meotppo.webti.domain.repository.jpa.developerType;

import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebDeveloperProfileRepository extends JpaRepository<WebDeveloperProfile, Long>{
    
}
