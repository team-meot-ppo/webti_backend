package org.meotppo.webti.dto.analysis;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisRequest {

    @NotNull(message = "EXTROVERSION cannot be null")
    private int EXTROVERSION;

    @NotNull(message = "INTROVERSION cannot be null")
    private int INTROVERSION;

    @NotNull(message = "INTUITION cannot be null")
    private int INTUITION;

    @NotNull(message = "SENSING cannot be null")
    private int SENSING;

    @NotNull(message = "THINKING cannot be null")
    private int THINKING;

    @NotNull(message = "FEELING cannot be null")
    private int FEELING;

    @NotNull(message = "PERCEIVING cannot be null")
    private int PERCEIVING;

    @NotNull(message = "JUDGING cannot be null")
    private int JUDGING;
}
