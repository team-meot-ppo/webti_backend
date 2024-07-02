package org.meotppo.webti.domain.repository.jpa.file;

import org.meotppo.webti.domain.entity.jpa.file.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
