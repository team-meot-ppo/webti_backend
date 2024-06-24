package org.meotppo.webti.domain.entity.mongo.statistics;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.meotppo.webti.domain.entity.TechRole;
import org.meotppo.webti.domain.entity.mongo.common.MongoEntityDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "tech_preference_test_results")
public class TechRoleTestResult extends MongoEntityDate {

    @Id
    private String id;

    //@Indexed(unique = true)
    private TechRole result;

    private boolean matchesSelfAssessment;

    @Builder
    public TechRoleTestResult(TechRole result, boolean matchesSelfAssessment) {
        this.result = result;
        this.matchesSelfAssessment = matchesSelfAssessment;
    }
}
