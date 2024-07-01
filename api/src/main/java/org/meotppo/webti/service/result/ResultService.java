package org.meotppo.webti.service.result;

import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.dto.result.StatisticDTO;
import org.meotppo.webti.domain.entity.mongo.testresult.TestResult;
import org.meotppo.webti.domain.repository.jpa.statistics.StatisticRepository;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
import org.meotppo.webti.dto.result.TestResultRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ResultService {

    private final TestResultRepository testResultRepository;
    private final StatisticRepository statisticRepository;

    @Transactional
    public void createTestResult(TestResultRequest req) {
        TestResult testResult = TestResult.builder()
                .mbtiType(req.getMbtiType())
                .match(req.getMatch())
                .build();
        testResultRepository.save(testResult);
    }

    public List<StatisticDTO> readStatistics() {
        return statisticRepository.findAllStatisticDtos();
    }
}
