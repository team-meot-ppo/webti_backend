package org.meotppo.webti.domain.entity.mongo.statistics;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "tech_preference_test_results")
public class TechPreferenceTestResult { // DataBase 테스트용 TODO. 수정해야함

    @Id
    private String id;
    private String createdAt;
    private boolean matchesSelfAssessment;

    @Builder
    public TechPreferenceTestResult( String createdAt, boolean matchesSelfAssessment) {
        this.createdAt = createdAt;
        this.matchesSelfAssessment = matchesSelfAssessment;
    }
}
