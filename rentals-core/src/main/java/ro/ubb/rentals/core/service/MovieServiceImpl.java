package ro.ubb.rentals.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.rentals.core.model.Movie;
import ro.ubb.rentals.core.repository.MovieRepository;

import java.util.*;

@Service
public class MovieServiceImpl implements IMovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Movie> getAllMovies() {
        log.trace("getAllMovies --- method entered");

        List<Movie> movies = movieRepository.findAll();

        log.trace("getAllMovies: movies={}", movies);

        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        log.trace("addMovie --- method entered");

        Movie result = movieRepository.save(movie);

        log.trace("addMovie: result={}", result);

        return result;
    }

    @Override
    public Movie getMovieById(Long id) {
        log.trace("getMovieById --- method entered");

        Movie result = movieRepository.findById(id).orElse(new Movie());

        log.trace("getMovieById: movie={}", result);

        return result;
    }

    @Transactional
    @Override
    public Movie updateMovie(Long movieId, Movie movie) {
        log.trace("updateClient --- method entered");

        Optional<Movie> result = movieRepository.findById(movieId);

        result.ifPresent(o -> {
            o.setTitle(movie.getTitle());
            o.setYear(movie.getYear());
            o.setGenre(movie.getGenre());
            o.setAgeRestrictions(movie.getAgeRestrictions());
            o.setRentalPrice(movie.getRentalPrice());
            o.setAvailable(movie.isAvailable());
        });

        log.trace("updateMovie: movie={}", result.get());

        return result.orElse(null);
    }

    @Override
    public void deleteMovieById(Long id) {
        log.trace("deleteMovieById --- method entered");

        movieRepository.deleteById(id);

        log.trace("deleteMovieById: movie deleted successfully");
    }

    @Override
    public Iterable<Movie> findByTitle(String title) {
        log.trace("findByTitle --- method entered");

        Iterable<Movie> result = movieRepository.findByTitle(title);

        log.trace("findByTitle: result={}", result);

        return result;
    }

    @Override
    public Iterable<Movie> orderMoviesByTitleAsc() {
        log.trace("orderMoviesByTitleAsc --- method entered");

        Iterable<Movie> result = movieRepository.findAllByOrderByTitleAsc();

        log.trace("orderMoviesByTitleAsc: result={}", result);

        return result;
    }

    @Override
    public Iterable<Movie> orderMoviesByTitleDesc() {
        log.trace("orderMoviesByTitleDesc --- method entered");

        Iterable<Movie> result = movieRepository.findAllByOrderByTitleDesc();

        log.trace("orderMoviesByTitleDesc: result={}", result);

        return result;
    }

}
