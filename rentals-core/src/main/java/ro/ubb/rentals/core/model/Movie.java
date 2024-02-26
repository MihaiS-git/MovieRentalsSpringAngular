package ro.ubb.rentals.core.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "movies")
public class Movie extends BaseEntity<Long> implements Serializable {
    private String title;

    private int year;

    @Enumerated(EnumType.STRING)
    private MovieGenres genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_restriction")
    private AgeRestrictions ageRestrictions;

    @Column(name = "rental_price")
    private float rentalPrice;

    private boolean available;


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + this.getId() + ", " +
                "title='" + title + '\'' +
                ", year=" + year +
                ", genre=" + genre +
                ", ageRestrictions=" + ageRestrictions +
                ", rentalPrice=" + rentalPrice +
                ", available=" + available +
                '}';
    }
}
