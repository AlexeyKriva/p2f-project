package com.software.pick2flick.imageprocessor.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    @NotBlank(message = "Image url cannot be blank.")
    private String imageUrl;
    private Boolean isKeyWordsMode = true;
}
