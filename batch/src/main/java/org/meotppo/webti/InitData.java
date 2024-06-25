package org.meotppo.webti;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.meotppo.webti.domain.entity.TechRole;
import org.meotppo.webti.domain.entity.mongo.statistics.TechRoleTestResult;
import org.meotppo.webti.domain.repository.mongo.statistics.TechRoleTestResultRepository;
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

    private final TechRoleTestResultRepository techRoleTestResultRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(transactionManager = DOMAIN_TRANSACTION_MANAGER)
    public void initData() {
        List<TechRoleTestResult> sampleData = Arrays.asList(
                TechRoleTestResult.builder()
                        .result(TechRole.BACKEND_ALGORITHM_EXPERT)
                        .matchesSelfAssessment(true)
                        .build(),
                TechRoleTestResult.builder()
                        .result(TechRole.FULLSTACK_PROBLEM_SOLVER)
                        .matchesSelfAssessment(false)
                        .build()
        );

        techRoleTestResultRepository.saveAll(sampleData);
    }
}
