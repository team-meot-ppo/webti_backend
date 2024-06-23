package org.meotppo.webti;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.meotppo.webti.domain.entity.TechRole;
import org.meotppo.webti.domain.entity.mongo.statistics.TechPreferenceTestResult;
import org.meotppo.webti.domain.repository.mongo.statistics.TechPreferenceTestResultRepository;
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

    private final TechPreferenceTestResultRepository techPreferenceTestResultRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(transactionManager = DOMAIN_TRANSACTION_MANAGER)
    public void initData() {
        List<TechPreferenceTestResult> sampleData = Arrays.asList(
                TechPreferenceTestResult.builder()
                        .result(TechRole.BACKEND_ALGORITHM_EXPERT)
                        .matchesSelfAssessment(true)
                        .build(),
                TechPreferenceTestResult.builder()
                        .result(TechRole.FULLSTACK_PROBLEM_SOLVER)
                        .matchesSelfAssessment(false)
                        .build()
        );

        techPreferenceTestResultRepository.saveAll(sampleData);
    }
}
