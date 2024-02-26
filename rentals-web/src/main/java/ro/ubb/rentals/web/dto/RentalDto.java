package ro.ubb.rentals.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class RentalDto extends BaseDto {
    private Long movieId;
    private Long clientId;
    private float rentalCharge;
    private LocalDate rentalDate;
    private LocalDate dueDate;

    @Override
    public String toString() {
        return "RentalDto{" +
                "movieId=" + movieId +
                ", clientId=" + clientId +
                ", rentalCharge=" + rentalCharge +
                ", rentalDate=" + rentalDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
