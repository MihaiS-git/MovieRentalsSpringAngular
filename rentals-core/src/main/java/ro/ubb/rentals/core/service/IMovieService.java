package ro.ubb.rentals.core.service;

import ro.ubb.rentals.core.model.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();

    Movie addMovie(Movie movie);

    Movie getMovieById(Long id);

    Movie updateMovie(Long movieId, Movie movie);

    void deleteMovieById(Long id);

    Iterable<Movie> findByTitle(String title);

    Iterable<Movie> orderMoviesByTitleAsc();

    Iterable<Movie> orderMoviesByTitleDesc();
}
