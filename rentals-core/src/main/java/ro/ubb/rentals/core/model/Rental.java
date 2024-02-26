package ro.ubb.rentals.core.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "rentals")
public class Rental extends BaseEntity<Long> implements Serializable {

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "rental_charge")
    private float rentalCharge;

    @Column(name = "rental_date")
    private LocalDate rentalDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Override
    public String toString() {
        return "Rental{" +
                "movieId=" + movieId +
                ", clientId=" + clientId +
                ", rentalCharge=" + rentalCharge +
                ", rentalDate=" + rentalDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
