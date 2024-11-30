package com.software.pick2flick.imageprocessor.entities.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String id;

    @JsonProperty("enTitle")
    private String enTitle;

    private List<String> genres;

    @JsonProperty("releaseDate")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate releaseDate;

    private String description;

    @JsonProperty("shortDescription")
    private String shortDescription;

    private Double rating;

    private List<Country> countries;

    @JsonProperty("durationMinute")
    private Integer durationMinute;

    @JsonProperty("ageLimit")
    private Integer ageLimit;

    private List<String> directors;

    private List<String> actors;

    @JsonProperty("isActive")
    private Boolean isActive;

    private List<String> facts;

    private List<Tag> tags;

    @JsonProperty("posterUrl")
    private String posterUrl;

    @JsonProperty("trailerUrl")
    private String trailerUrl;
}