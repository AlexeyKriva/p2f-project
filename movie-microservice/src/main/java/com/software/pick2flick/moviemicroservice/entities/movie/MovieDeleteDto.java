package com.software.pick2flick.moviemicroservice.entities.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieDeleteDto(
        @JsonProperty(defaultValue = "true")
        Boolean soft
) { }
