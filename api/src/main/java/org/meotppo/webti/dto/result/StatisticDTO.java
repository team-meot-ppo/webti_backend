package org.meotppo.webti.dto.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.meotppo.webti.domain.entity.type.MbtiType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDTO {
    private Long id;
    private String result;
    private Long count;
    private Long matchCount;
}
