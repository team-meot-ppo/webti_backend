package org.meotppo.webti.domain.repository.jpa.statistic;

import org.meotppo.webti.domain.dto.result.StatisticDTO;

import java.util.List;

public interface QuerydslStatisticRepository {
    List<StatisticDTO> findAllStatisticDtos();
}
