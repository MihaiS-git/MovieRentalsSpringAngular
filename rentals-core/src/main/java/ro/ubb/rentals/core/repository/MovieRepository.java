package ro.ubb.rentals.core.repository;

import ro.ubb.rentals.core.model.Movie;

import java.util.List;

public interface MovieRepository extends GeneralRepository<Movie, Long> {
    Iterable<Movie> findByTitle(String title);
    Iterable<Movie> findAllByOrderByTitleAsc();
    Iterable<Movie> findAllByOrderByTitleDesc();
}


