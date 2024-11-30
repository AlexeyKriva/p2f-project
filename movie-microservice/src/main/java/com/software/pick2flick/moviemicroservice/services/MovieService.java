package com.software.pick2flick.moviemicroservice.services;

import com.software.pick2flick.moviemicroservice.entities.movie.Movie;
import com.software.pick2flick.moviemicroservice.exceptions.MovieNotFoundException;
import com.software.pick2flick.moviemicroservice.repositories.MovieElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.software.pick2flick.moviemicroservice.exceptions.ExceptionMessage.MOVIE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieElasticsearchRepository elasticsearchRepository;

    private final Double VALID_PERCENT = 0.75;

    public List<Movie> getAllMovies() {
        Page<Movie> moviesFromDb = (Page<Movie>) elasticsearchRepository.findAll();
        return moviesFromDb.stream().toList();
    }

    public Movie getMovieById(String id) {
        Optional<Movie> movieFromDb = elasticsearchRepository.findById(id);

        if (movieFromDb.isPresent()) {
            return movieFromDb.get();
        }

        throw new MovieNotFoundException(MOVIE_NOT_FOUND_MESSAGE) ;
    }

    public Movie saveMovie(Movie movie) {
        movie.setIsActive(true);

        return elasticsearchRepository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        for (Movie movie: movies) {
            movie.setIsActive(true);
        }

        return (List<Movie>) elasticsearchRepository.saveAll(movies);
    }

    public void deleteMovie(String id, Boolean soft) {
        Optional<Movie> movieFromDb = elasticsearchRepository.findById(id);

        if (movieFromDb.isPresent()) {
            if (soft) {
                Movie movie = movieFromDb.get();

                movie.setIsActive(false);

                elasticsearchRepository.save(movie);
            } else {
                elasticsearchRepository.deleteById(id);
            }
        }

        throw new MovieNotFoundException(MOVIE_NOT_FOUND_MESSAGE);
    }

    public List<Movie> getAllMoviesByImageDescription(List<String> imageDescriptions) {
        Map<Movie, Integer> moviesWithCount = new HashMap<>();

        for (String imageDescription: imageDescriptions) {
            List<Movie> movies = elasticsearchRepository.findByTagsNameMatchPhrase(imageDescription);

            unionMovies(movies, moviesWithCount);
        }

        moviesWithCount.entrySet().forEach(movieIntegerEntry -> System.out.println(movieIntegerEntry.getValue()));

        double coefficient = moviesWithCount.values()
                .stream()
                .max(Integer::compareTo)
                .orElse(0) * VALID_PERCENT;

        return moviesWithCount.entrySet().stream()
                .filter(entry -> (double) entry.getValue() >= coefficient)
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private void unionMovies(List<Movie> movieList, Map<Movie, Integer> movieCount) {
        for (Movie movie : movieList) {
            movieCount.put(movie, movieCount.getOrDefault(movie, 0) + 1);
        }
    }

    private void isMovieAlreadySaved(List<Movie> savedMovies, List<Movie> foundMovies) {
        for (Movie foundMovie: foundMovies) {
            Boolean isMovieWasSaved = false;

            for (Movie savedMovie: savedMovies) {
                if (savedMovie.getEnTitle() != null && foundMovie.getEnTitle() != null &&
                        savedMovie.getEnTitle().equals(foundMovie.getEnTitle())) {
                    isMovieWasSaved = true;
                    break;
                }
            }

            if (!isMovieWasSaved) {
                savedMovies.add(foundMovie);
            }
        }
    }
}