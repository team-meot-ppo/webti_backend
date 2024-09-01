package org.meotppo.webti.service.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meotppo.webti.domain.dto.result.StatisticDTO;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.statistic.StatisticRepository;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
import org.meotppo.webti.dto.result.TestResultRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    @InjectMocks
    private ResultService resultService;

    @Mock
    private TestResultRepository testResultRepository;

    @Mock
    private StatisticRepository statisticRepository;

    @Test
    void testCreateTestResult() {
        // given
        TestResultRequest request = new TestResultRequest(MbtiType.INFJ, true);

        // when
        resultService.createTestResult(request);

        // then
        then(testResultRepository).should().save(any(TestResult.class));
    }

    @Test
    void testReadStatistics() {
        // given
        List<StatisticDTO> statistics = List.of(
                new StatisticDTO("철저한 데이터 관리형 백엔드 개발자", 5L, 3L, LocalDateTime.now())
        );
        given(statisticRepository.findAllStatisticDtos()).willReturn(statistics);

        // when
        List<StatisticDTO> result = resultService.readStatistics();

        // then
        assertThat(result).isEqualTo(statistics);
        then(statisticRepository).should().findAllStatisticDtos();
    }
}
