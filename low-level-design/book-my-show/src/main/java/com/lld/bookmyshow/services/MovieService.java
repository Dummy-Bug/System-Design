package com.lld.bookmyshow.services;

import com.lld.bookmyshow.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    public List<Movie> findAllMovies();

    public Optional<Movie> findMovieById(long id);
}
