package com.software.pick2flick.moviemicroservice.entities.movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record MovieDto(
        @NotBlank(message = "Title cannot be blank.")
        String enTitle,

        @NotNull(message = "Genres cannot be null")
        @Size(min = 1, message = "Genres cannot be empty.")
        List<String> genres,

        @DateTimeFormat(pattern = "YYYY-MM-DD")
        @PastOrPresent(message = "Movie cannot be in the future.")
        LocalDate releaseDate,

        @NotBlank(message = "Description cannot be blank.")
        String description,

        @NotBlank(message = "Short description cannot be blank.")
        String shortDescription,

        @NotNull(message = "Rating cannot be null.")
        @Min(value = 0, message = "Rating cannot be less than 0.")
        @Max(value = 10, message = "Rating cannot be more than 10.")
        Double rating,

        @NotNull(message = "Rating cannot be null.")
        @Size(min = 1, message = "Countries cannot be empty.")
        List<Country> countries,

        @NotNull(message = "Minute duration cannot be null.")
        @Min(value = 0, message = "Minute duration cannot be less than 0.")
        Integer durationMinute,

        @NotNull(message = "Age limit cannot be null.")
        @Min(value = 0, message = "Age limit cannot be less than 0.")
        Integer ageLimit,

        @NotNull(message = "Directors cannot be null.")
        @Size(min = 1, message = "Directors cannot be empty.")
        List<String> directors,

        @NotNull(message = "Actors cannot be null.")
        @Size(min = 1, message = "Actors cannot be empty.")
        List<String> actors,

        @NotNull(message = "Facts cannot be null.")
        @Size(min = 1, message = "Facts cannot be empty.")
        List<String> facts,

        @NotNull(message = "Tags cannot be null.")
        @Size(min = 1, message = "Tags cannot be empty.")
        List<Tag> tags,

        @NotBlank(message = "Poster url cannot be blank.")
        String posterUrl,

        @NotBlank(message = "Trailer url cannot be blank.")
        String trailerUrl
) { }