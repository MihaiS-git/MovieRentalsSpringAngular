package ro.ubb.rentals.core.repository;

import ro.ubb.rentals.core.model.Rental;

public interface RentalRepository extends GeneralRepository<Rental, Long> {
    Iterable<Rental> findByClientId(Long clientId);

    Iterable<Rental> findAllByOrderByRentalDateAsc();

    Iterable<Rental> findAllByOrderByRentalDateDesc();
}
