package org.meotppo.webti.job.tasklet;

import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CleanupTasklet implements Tasklet {

    private final TestResultRepository testResultRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        testResultRepository.deleteAll();
        return RepeatStatus.FINISHED;
    }
}
