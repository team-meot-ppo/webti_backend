package org.meotppo.webti.domain.entity.jpa.answer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result; //개발자 유형

    private String result_point; //개발자 유형에 대한 최종 점수

    private String description; //개발자 유형에 따른 설명
}
