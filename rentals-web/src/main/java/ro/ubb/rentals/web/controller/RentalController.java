package ro.ubb.rentals.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.rentals.core.model.Movie;
import ro.ubb.rentals.core.model.Rental;
import ro.ubb.rentals.core.service.IRentalService;
import ro.ubb.rentals.web.converter.RentalConverter;
import ro.ubb.rentals.web.dto.MovieDto;
import ro.ubb.rentals.web.dto.RentalDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    @Autowired
    private RentalConverter rentalConverter;


    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public List<RentalDto> getRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return new ArrayList<>(rentalConverter.convertModelsToDtos(rentals));
    }

    @RequestMapping(value = "/rentals/{rentalId}", method = RequestMethod.GET)
    public RentalDto getRentalById(@PathVariable Long rentalId) {
        Rental rental = rentalService.getRentalById(rentalId);
        return rentalConverter.convertModelToDto(rental);
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST, consumes = "application/json")
    public RentalDto saveRental(@RequestBody RentalDto dto) {
        return rentalConverter.convertModelToDto(
                rentalService.rentAMovie(
                        rentalConverter.convertDtoToModel(dto)
                )
        );
    }

    @RequestMapping(value = "/rentals/{rentalId}", method = RequestMethod.PUT, consumes = "application/json")
    public RentalDto updateRental(
            @PathVariable final Long rentalId,
            @RequestBody final RentalDto dto) {

        return rentalConverter.convertModelToDto(
                rentalService.updateRentalTransaction(
                        rentalId,
                        rentalConverter.convertDtoToModel(dto)
                )
        );
    }

    @RequestMapping(value = "/rentals/{rentalId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRental(@PathVariable Long rentalId) {
        rentalService.deleteMovieRental(rentalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rentals/search/clientId", method = RequestMethod.GET)
    public List<RentalDto> findByClientId(Long clientId) {
        Iterable<Rental> result = rentalService.findByClientId(clientId);
        List<RentalDto> rentalDtos = StreamSupport.stream(result.spliterator(), false)
                .map(rentalConverter::convertModelToDto)
                .collect(Collectors.toList());
        return rentalDtos;
    }

    @RequestMapping(value = "/rentals/order/rentalDate/asc", method = RequestMethod.GET)
    public List<RentalDto> orderRentalsByRentalDateAsc() {
        Iterable<Rental> orderedRentals = rentalService.orderRentalsByRentalDateAsc();
        List<RentalDto> rentalDtos = StreamSupport.stream(orderedRentals.spliterator(), false)
                .map(rentalConverter::convertModelToDto)
                .collect(Collectors.toList());
        return rentalDtos;
    }

    @RequestMapping(value = "/rentals/order/rentalDate/desc", method = RequestMethod.GET)
    public List<RentalDto> orderRentalsByRentalDateDesc() {
        Iterable<Rental> orderedRentals = rentalService.orderRentalsByRentalDateDesc();
        List<RentalDto> rentalDtos = StreamSupport.stream(orderedRentals.spliterator(), false)
                .map(rentalConverter::convertModelToDto)
                .collect(Collectors.toList());
        return rentalDtos;
    }
}
