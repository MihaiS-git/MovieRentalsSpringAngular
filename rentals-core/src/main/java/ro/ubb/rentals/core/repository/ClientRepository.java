package ro.ubb.rentals.core.repository;

import ro.ubb.rentals.core.model.Client;

public interface ClientRepository extends GeneralRepository<Client, Long> {
    Iterable<Client> findByFirstName(String firstName);
    Iterable<Client> findAllByOrderByFirstNameAsc();
    Iterable<Client> findAllByOrderByFirstNameDesc();
}
