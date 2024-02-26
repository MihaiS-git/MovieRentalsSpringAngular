package ro.ubb.rentals.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.rentals.core.model.Rental;
import ro.ubb.rentals.web.dto.RentalDto;

@Component
public class RentalConverter extends BaseConverter<Rental, RentalDto> {

    @Override
    public Rental convertDtoToModel(RentalDto dto) {
        Rental rental = Rental.builder()
                .id(dto.getId())
                .movieId(dto.getMovieId())
                .clientId(dto.getClientId())
                .rentalCharge(dto.getRentalCharge())
                .rentalDate(dto.getRentalDate())
                .dueDate(dto.getDueDate())
                .build();
        return rental;
    }

    @Override
    public RentalDto convertModelToDto(Rental rental) {
        RentalDto dto = RentalDto.builder()
                .id(rental.getId())
                .movieId(rental.getMovieId())
                .clientId(rental.getClientId())
                .rentalCharge(rental.getRentalCharge())
                .rentalDate(rental.getRentalDate())
                .dueDate(rental.getDueDate())
                .build();
        return dto;
    }
}
