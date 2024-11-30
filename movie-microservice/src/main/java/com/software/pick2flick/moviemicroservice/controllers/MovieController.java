package com.software.pick2flick.moviemicroservice.controllers;

import com.software.pick2flick.moviemicroservice.entities.movie.Movie;
import com.software.pick2flick.moviemicroservice.entities.movie.MovieDto;
import com.software.pick2flick.moviemicroservice.mappers.MovieMapper;
import com.software.pick2flick.moviemicroservice.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movies", produces = "application/json")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper INSTANCE = MovieMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") String id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Movie>> getMoviesByImageDescription(
            @RequestBody List<String> imageDescription
            ) {
        return ResponseEntity.ok(movieService.getAllMoviesByImageDescription(imageDescription));
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(
            @Valid @RequestBody MovieDto movieDto
    ) {
        return ResponseEntity.ok(movieService.saveMovie(INSTANCE.toMovie(movieDto)));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Movie>> saveMovies(
            @Valid @RequestBody List<MovieDto> moviesDtos
    ) {
        return ResponseEntity.ok(movieService.saveMovies(INSTANCE.toMovies(moviesDtos)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(
            @PathVariable("id") String id,
            @RequestParam(value = "soft", required = false, defaultValue = "true") Boolean soft
    ) {
        movieService.deleteMovie(id, soft);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}