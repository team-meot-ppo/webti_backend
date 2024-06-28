package org.meotppo.webti.dto.PropensityAnalysis;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PropensityAnalysisDto {
    @NotNull(message = "E cannot be null")
    private int E;
    @NotNull(message = "I cannot be null")
    private int I;
    @NotNull(message = "N cannot be null")
    private int N;
    @NotNull(message = "S cannot be null")
    private int S;
    @NotNull(message = "T cannot be null")
    private int T;
    @NotNull(message = "F cannot be null")
    private int F;
    @NotNull(message = "P cannot be null")
    private int P;
    @NotNull(message = "j cannot be null")
    private int j;
}
