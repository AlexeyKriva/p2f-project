package com.software.pick2flick.moviemicroservice.entities.movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "movies")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @EqualsAndHashCode.Exclude
    private String id;

    @Field("enTitle")
    private String enTitle;

    private List<String> genres;

    @Field(name = "releaseDate", type = FieldType.Date, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private String description;

    @Field("shortDescription")
    private String shortDescription;

    private Double rating;

    private List<Country> countries;

    @Field("durationMinute")
    private Integer durationMinute;

    @Field("ageLimit")
    private Integer ageLimit;

    private List<String> directors;

    private List<String> actors;

    @Field("isActive")
    private Boolean isActive = true;

    private List<String> facts;

    private List<Tag> tags;

    @Field("posterUrl")
    @EqualsAndHashCode.Exclude
    private String posterUrl;

    @Field("trailerUrl")
    @EqualsAndHashCode.Exclude
    private String trailerUrl;
}