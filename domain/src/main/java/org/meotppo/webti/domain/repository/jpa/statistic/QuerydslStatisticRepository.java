package org.meotppo.webti.domain.repository.jpa.statistic;

import java.util.List;
import org.meotppo.webti.domain.dto.result.StatisticDTO;

public interface QuerydslStatisticRepository {
    List<StatisticDTO> findAllStatisticDtos();
}
