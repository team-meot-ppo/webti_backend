package org.meotppo.webti.domain.entity.jpa.answer;

import org.meotppo.webti.domain.entity.jpa.common.JpaEntityDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Answer extends JpaEntityDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String result; //개발자 유형

    @Column(nullable = false)
    private String result_point; //개발자 유형에 대한 최종 점수

    @Column(nullable = false)
    private String description; //개발자 유형에 따른 설명
}
