package org.meotppo.webti.domain.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StatisticDTO {
    private String result;
    private Long count;
    private Long matchCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedAt;

    @QueryProjection
    public StatisticDTO(String result, Long count, Long matchCount, LocalDateTime modifiedAt) {
        this.result = result;
        this.count = count;
        this.matchCount = matchCount;
        this.modifiedAt = modifiedAt;
    }
}
