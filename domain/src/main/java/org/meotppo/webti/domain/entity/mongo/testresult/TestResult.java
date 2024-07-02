package org.meotppo.webti.domain.entity.mongo.testresult;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.meotppo.webti.domain.entity.mongo.common.MongoEntityDate;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "test_result")
public class TestResult extends MongoEntityDate {

    @Id
    private String id;

    private MbtiType mbtiType;

    private boolean match;

    @Builder
    public TestResult(MbtiType mbtiType, boolean match) {
        this.mbtiType = mbtiType;
        this.match = match;
    }
}
