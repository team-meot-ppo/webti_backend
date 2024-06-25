package org.meotppo.webti.domain.repository.question;

import org.meotppo.webti.domain.entity.jpa.question.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>{
    
}
