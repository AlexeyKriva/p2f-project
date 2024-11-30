package com.software.pick2flick.imageprocessor.clients;

import com.software.pick2flick.imageprocessor.entities.movie.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "movie-client")
public interface MovieClient {
    @PostMapping("/search")
    ResponseEntity<List<Movie>> getFilmsByImageDescription(
            @RequestHeader("Authorization") String apiKey,
            @RequestBody List<String> imageDescriptions
    );
}