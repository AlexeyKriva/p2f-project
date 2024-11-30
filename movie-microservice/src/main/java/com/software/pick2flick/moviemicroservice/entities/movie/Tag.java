package com.software.pick2flick.moviemicroservice.entities.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private String name;
    private Double probability;
}
