package org.meotppo.webti.domain.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class StatisticDTO {

    private final String result;
    private final Long count;
    private final Long matchCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime modifiedAt;

    @QueryProjection
    public StatisticDTO(String result, Long count, Long matchCount, LocalDateTime modifiedAt) {
        this.result = result;
        this.count = count;
        this.matchCount = matchCount;
        this.modifiedAt = modifiedAt;
    }
}
