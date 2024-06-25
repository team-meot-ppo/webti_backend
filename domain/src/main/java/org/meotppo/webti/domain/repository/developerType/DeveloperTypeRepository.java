package org.meotppo.webti.domain.repository.developerType;

import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperTypeRepository extends JpaRepository<WebDeveloperProfile, Long>{
    
}
