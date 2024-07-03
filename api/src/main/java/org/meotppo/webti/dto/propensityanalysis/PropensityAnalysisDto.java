package org.meotppo.webti.dto.propensityanalysis;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PropensityAnalysisDto {
    @NotNull(message = "EXTROVERSION cannot be null")
    @JsonProperty("EXTROVERSION")
    private int EXTROVERSION;
    @NotNull(message = "INTROVERSION cannot be null")
    @JsonProperty("INTROVERSION")
    private int INTROVERSION;
    @NotNull(message = "INTUITION cannot be null")
    @JsonProperty("INTUITION")
    private int INTUITION;
    @NotNull(message = "SENSING cannot be null")
    @JsonProperty("SENSING")
    private int SENSING;
    @NotNull(message = "THINKING cannot be null")
    @JsonProperty("THINKING")
    private int THINKING;
    @NotNull(message = "FEELING cannot be null")
    @JsonProperty("FEELING")
    private int FEELING;
    @NotNull(message = "PERCEIVING cannot be null")
    @JsonProperty("PERCEIVING")
    private int PERCEIVING;
    @NotNull(message = "JUDGING cannot be null")
    @JsonProperty("JUDGING")
    private int JUDGING;
}
