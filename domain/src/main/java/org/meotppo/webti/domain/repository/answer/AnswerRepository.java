package org.meotppo.webti.domain.repository.answer;

import org.meotppo.webti.domain.entity.jpa.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
    
}
