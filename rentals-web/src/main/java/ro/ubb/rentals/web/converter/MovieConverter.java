package ro.ubb.rentals.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.rentals.core.model.Movie;
import ro.ubb.rentals.web.dto.MovieDto;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {

    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .year(dto.getYear())
                .genre(dto.getGenre())
                .ageRestrictions(dto.getAgeRestrictions())
                .rentalPrice(dto.getRentalPrice())
                .available(dto.isAvailable())
                .build();
        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .genre(movie.getGenre())
                .ageRestrictions(movie.getAgeRestrictions())
                .rentalPrice(movie.getRentalPrice())
                .available(movie.isAvailable())
                .build();
        return dto;
    }
}
