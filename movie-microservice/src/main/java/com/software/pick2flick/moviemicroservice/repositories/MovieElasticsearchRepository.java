package com.software.pick2flick.moviemicroservice.repositories;

import com.software.pick2flick.moviemicroservice.entities.movie.Movie;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieElasticsearchRepository extends ElasticsearchRepository<Movie, String> {
    @Query("{\"match_phrase\": {\"tags.name\": \"?0\"}}")
    List<Movie> findByTagsNameMatchPhrase(String name);
}