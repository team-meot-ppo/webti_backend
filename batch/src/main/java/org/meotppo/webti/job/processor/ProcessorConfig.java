package org.meotppo.webti.job.processor;

import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.jpa.result.Statistic;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.repository.jpa.developertype.ProfileRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProcessorConfig {

    public static final String STATISTIC_PROCESSOR = "statisticProcessor";

    @Bean(name = STATISTIC_PROCESSOR)
    public ItemProcessor<TestResult, Statistic> statisticProcessor(
            ProfileRepository profileRepository) {
        return item -> {
            Profile profile = profileRepository.findByMbtiType(item.getMbtiType())
                    .orElseThrow(
                            () -> new IllegalArgumentException("No profile found for type: " + item.getMbtiType()));

            return Statistic.builder()
                    .profile(profile)
                    .count(1L)
                    .matchCount(item.isMatch() ? 1L : 0L)
                    .build();
        };
    }
}
