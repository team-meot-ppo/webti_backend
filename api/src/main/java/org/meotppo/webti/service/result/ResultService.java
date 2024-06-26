package org.meotppo.webti.service.result;

import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.repository.jpa.statistics.StatisticRepository;
import org.meotppo.webti.domain.repository.mongo.result.TestResultRepository;
import org.meotppo.webti.dto.result.StatisticDTO;
import org.meotppo.webti.dto.result.TestResultRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResultService {

    private final TestResultRepository testResultRepository;
    private final StatisticRepository statisticRepository;

    public void createTestResult(TestResultRequest req) {
        TestResult testResult = TestResult.builder()
                .mbtiType(req.getMbtiType())
                .match(req.getMatch())
                .build();
        testResultRepository.save(testResult);
    }

    public List<StatisticDTO> readStatistics() {
        return statisticRepository.findAll().stream()
                .map(statistic -> new StatisticDTO(
                        statistic.getId(),
                        statistic.getDeveloperProfile().getResult(),
                        statistic.getCount(),
                        statistic.getMatchCount(),
                        statistic.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }
}
