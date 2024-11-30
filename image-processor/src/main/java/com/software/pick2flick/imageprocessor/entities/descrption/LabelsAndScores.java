package com.software.pick2flick.imageprocessor.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelsAndScores {
    @JsonProperty("label")
    private String label;
    @JsonProperty("score")
    private Double score;
}
