package ro.ubb.rentals.core.service;

import ro.ubb.rentals.core.model.Movie;
import ro.ubb.rentals.core.model.Rental;

import java.util.List;

public interface IRentalService {
    List<Rental> getAllRentals();

    Rental getRentalById(Long id);

    Rental rentAMovie(Rental rental);

    Rental updateRentalTransaction(Long rentalId, Rental rental);

    void deleteMovieRental(Long rentalId);

    Iterable<Rental> findByClientId(Long clientId);

    Iterable<Rental> orderRentalsByRentalDateAsc();

    Iterable<Rental> orderRentalsByRentalDateDesc();
}
