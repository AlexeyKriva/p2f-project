package com.software.pick2flick.imageprocessor.entities.descrption;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextDescription {
    @JsonProperty("generated_text")
    private String generatedText;
}
