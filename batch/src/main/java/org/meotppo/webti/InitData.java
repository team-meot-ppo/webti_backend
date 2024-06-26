package org.meotppo.webti;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;
import org.meotppo.webti.domain.repository.jpa.developerType.WebDeveloperProfileRepository;
import org.meotppo.webti.domain.repository.mongo.result.TestResultRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.meotppo.webti.config.TransactionManagerConfig.DOMAIN_TRANSACTION_MANAGER;

@Component
@RequiredArgsConstructor
@Profile("local")
@Slf4j
public class InitData {

    private final TestResultRepository testResultRepository;
    private final WebDeveloperProfileRepository webDeveloperProfileRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(transactionManager = DOMAIN_TRANSACTION_MANAGER)
    public void initData() {

//        List<WebDeveloperProfile> profileData = Arrays.asList( // TODO. 초기 데이터 구축 시스템 개발 시 삭제
//                WebDeveloperProfile.builder()
//                        .mbtiType(MbtiType.INTJ)
//                        .result("Backend Developer")
//                        .description("Prefers to work on server-side logic and algorithms.")
//                        .build(),
//                WebDeveloperProfile.builder()
//                        .mbtiType(MbtiType.ENFP)
//                        .result("Fullstack Developer")
//                        .description("Enjoys working on both frontend and backend aspects of applications.")
//                        .build()
//        );
//
//        webDeveloperProfileRepository.saveAll(profileData);

        List<TestResult> testData = Arrays.asList(
                TestResult.builder()
                        .mbtiType(MbtiType.INTJ)
                        .match(true)
                        .build(),
                TestResult.builder()
                        .mbtiType(MbtiType.ENFP)
                        .match(false)
                        .build()
        );

        testResultRepository.saveAll(testData);
    }
}
