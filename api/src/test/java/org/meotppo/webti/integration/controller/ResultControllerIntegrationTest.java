package org.meotppo.webti.integration.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.hamcrest.Matchers.equalTo;
import static org.meotppo.webti.fixture.ProfileFixture.createProfile;
import static org.meotppo.webti.fixture.StatisticFixture.createStatistic;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.restassured.RestAssured;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meotppo.webti.config.MongoTest;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.jpa.result.Statistic;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developertype.ProfileRepository;
import org.meotppo.webti.domain.repository.jpa.statistic.StatisticRepository;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
import org.meotppo.webti.dto.result.TestResultRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@MongoTest
@ActiveProfiles(value = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ResultControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        testResultRepository.deleteAll();
        statisticRepository.deleteAll();
        profileRepository.deleteAll();
    }

    @Test
    void testCreateTestResult() {
        // given
        TestResultRequest request = new TestResultRequest(MbtiType.INFJ, true);

        // when & then
        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/results/test-result")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        List<TestResult> savedResults = testResultRepository.findAll();
        assertSoftly(softly -> {
            softly.assertThat(savedResults).hasSize(1);
            softly.assertThat(savedResults.get(0).getMbtiType()).isEqualTo(MbtiType.INFJ);
            softly.assertThat(savedResults.get(0).isMatch()).isTrue();
        });
    }

    @Test
    void testReadStatistics() {
        // given
        Profile profile1 = createProfile(MbtiType.INFJ, "Developer 1",
                "Description for Developer 1", "https://example.com/image1.png");
        Profile profile2 = createProfile(MbtiType.ENTP, "Developer 2",
                "Description for Developer 2", "https://example.com/image2.png");
        profileRepository.saveAll(List.of(profile1, profile2));

        Statistic statistic1 = createStatistic(profile1, 5L, 3L);
        Statistic statistic2 = createStatistic(profile2, 2L, 1L);
        statisticRepository.saveAll(List.of(statistic1, statistic2));

        // when & then
        given()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/api/results/statistics")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(2))
                .body("[0].result", equalTo("Developer 1"))
                .body("[0].count", equalTo(5))
                .body("[0].matchCount", equalTo(3))
                .body("[1].result", equalTo("Developer 2"))
                .body("[1].count", equalTo(2))
                .body("[1].matchCount", equalTo(1));
    }
}
