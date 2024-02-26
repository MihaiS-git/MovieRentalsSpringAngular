package ro.ubb.rentals.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.rentals.core.model.Movie;
import ro.ubb.rentals.core.service.IMovieService;
import ro.ubb.rentals.web.converter.MovieConverter;
import ro.ubb.rentals.web.dto.MovieDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private MovieConverter movieConverter;


    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<MovieDto> getMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ArrayList<>(movieConverter.convertModelsToDtos(movies));
    }

    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.GET)
    public MovieDto getMovieById(@PathVariable Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return movieConverter.convertModelToDto(movie);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST, consumes = "application/json")
    public MovieDto saveMovie(@RequestBody MovieDto dto) {
        return movieConverter.convertModelToDto(
                movieService.addMovie(
                        movieConverter.convertDtoToModel(dto)
                )
        );
    }

    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.PUT, consumes = "application/json")
    public MovieDto updateMovie(
            @PathVariable final Long movieId,
            @RequestBody final MovieDto dto) {

        return movieConverter.convertModelToDto(
                movieService.updateMovie(
                        movieId,
                        movieConverter.convertDtoToModel(dto)
                )
        );
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/search/title", method = RequestMethod.GET)
    public List<MovieDto> findByTitle(String title) {
        Iterable<Movie> result = movieService.findByTitle(title);
        List<MovieDto> movieDtos = StreamSupport.stream(result.spliterator(), false)
                .map(movieConverter::convertModelToDto)
                .collect(Collectors.toList());
        return movieDtos;
    }

    @RequestMapping(value = "/movies/order/title/asc", method = RequestMethod.GET)
    public List<MovieDto> orderMoviesByTitleAsc() {
        Iterable<Movie> orderedMovies = movieService.orderMoviesByTitleAsc();
        List<MovieDto> movieDtos = StreamSupport.stream(orderedMovies.spliterator(), false)
                .map(movieConverter::convertModelToDto)
                .collect(Collectors.toList());
        return movieDtos;
    }

    @RequestMapping(value = "/movies/order/title/desc", method = RequestMethod.GET)
    public List<MovieDto> orderMoviesByTitleDesc() {
        Iterable<Movie> orderedMovies = movieService.orderMoviesByTitleDesc();
        List<MovieDto> movieDtos = StreamSupport.stream(orderedMovies.spliterator(), false)
                .map(movieConverter::convertModelToDto)
                .collect(Collectors.toList());
        return movieDtos;
    }
}
