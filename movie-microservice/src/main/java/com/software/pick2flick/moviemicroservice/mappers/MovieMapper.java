package com.software.pick2flick.moviemicroservice.mappers;

import com.software.pick2flick.moviemicroservice.entities.movie.Movie;
import com.software.pick2flick.moviemicroservice.entities.movie.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    Movie toMovie(MovieDto movieDto);

    List<Movie> toMovies(List<MovieDto> movieDTOs);
}
