package org.meotppo.webti.domain.repository.jpa.statistic;


import static org.meotppo.webti.domain.entity.jpa.statistic.QStatistic.statistic;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.meotppo.webti.domain.dto.result.QStatisticDTO;
import org.meotppo.webti.domain.dto.result.StatisticDTO;

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
