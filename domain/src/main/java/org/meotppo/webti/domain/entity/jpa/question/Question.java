package org.meotppo.webti.domain.entity.jpa.question;

import java.util.List;

import org.meotppo.webti.domain.entity.jpa.common.JpaEntityDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Question extends JpaEntityDate { // 질문과 답변을 프론트로 전달 -> 프론트는 질문에 대한 답변 선택, 점수 계산 후 백엔드로 전송
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 500, nullable = false)
    private String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

    @Builder
    public Question(String question, List<Option> options) {
        this.question = question;
        this.options = options;
    }
}