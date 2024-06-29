package org.meotppo.webti.dto.PropensityAnalysis;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PropensityAnalysisDto {
    @NotNull(message = "E cannot be null")
    @JsonProperty("E")
    private int E;
    @NotNull(message = "I cannot be null")
    @JsonProperty("I")
    private int I;
    @NotNull(message = "N cannot be null")
    @JsonProperty("N")
    private int N;
    @NotNull(message = "S cannot be null")
    @JsonProperty("S")
    private int S;
    @NotNull(message = "T cannot be null")
    @JsonProperty("T")
    private int T;
    @NotNull(message = "F cannot be null")
    @JsonProperty("F")
    private int F;
    @NotNull(message = "P cannot be null")
    @JsonProperty("P")
    private int P;
    @NotNull(message = "j cannot be null")
    @JsonProperty("J")
    private int J;
}
