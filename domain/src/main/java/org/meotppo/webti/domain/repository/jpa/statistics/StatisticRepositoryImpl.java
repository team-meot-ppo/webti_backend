package org.meotppo.webti.domain.repository.jpa.statistics;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.dto.result.QStatisticDTO;
import org.meotppo.webti.domain.dto.result.StatisticDTO;

import java.util.List;

import static org.meotppo.webti.domain.entity.jpa.statistics.QStatistic.statistic;

@RequiredArgsConstructor
public class StatisticRepositoryImpl implements QuerydslStatisticRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StatisticDTO> findAllStatisticDtos() {
        return queryFactory
                .select(new QStatisticDTO(
                        statistic.developerProfile.result,
                        statistic.count,
                        statistic.matchCount,
                        statistic.modifiedAt
                ))
                .from(statistic)
                .fetch();
    }
}
