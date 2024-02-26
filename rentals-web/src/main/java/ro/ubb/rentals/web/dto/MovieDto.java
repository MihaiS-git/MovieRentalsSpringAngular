package ro.ubb.rentals.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ro.ubb.rentals.core.model.AgeRestrictions;
import ro.ubb.rentals.core.model.MovieGenres;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class MovieDto extends BaseDto {
    private String title;
    private int year;
    private MovieGenres genre;
    private AgeRestrictions ageRestrictions;
    private float rentalPrice;
    private boolean available;


    @java.lang.Override
    public java.lang.String toString() {
        return "MovieDto{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", genre=" + genre +
                ", ageRestrictions=" + ageRestrictions +
                ", rentalPrice=" + rentalPrice +
                ", available=" + available +
                '}';
    }
}
