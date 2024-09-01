package org.meotppo.webti.job.tasklet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.springframework.batch.test.MetaDataInstanceFactory.createStepExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.repeat.RepeatStatus;

@ExtendWith(MockitoExtension.class)
class CleanupTaskletTest {

    @InjectMocks
    private CleanupTasklet cleanupTasklet;

    @Mock
    private TestResultRepository testResultRepository;

    @Test
    void testCleanupTasklet() throws Exception {
        // given
        StepExecution stepExecution = createStepExecution();
        ChunkContext chunkContext = new ChunkContext(new StepContext(stepExecution));
        StepContribution contribution = new StepContribution(stepExecution);

        // when
        RepeatStatus status = cleanupTasklet.execute(contribution, chunkContext);

        // then
        then(testResultRepository).should().deleteAll();
        assertThat(status).isEqualTo(RepeatStatus.FINISHED);
    }
}
