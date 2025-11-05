package com.lld.bookmyshow.services;

import com.lld.bookmyshow.models.Movie;
import com.lld.bookmyshow.repositories.MovieRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImplementation implements MovieService {


    private final MovieRepository movieRepository;

    public MovieServiceImplementation(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAllMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieById(long id) {
        return this.movieRepository.findById(id);
    }
}
