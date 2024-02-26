package ro.ubb.rentals.core.service;

import ro.ubb.rentals.core.model.Client;

import java.util.List;

public interface IClientService {
    List<Client> getAllClients();

    Client addClient(Client client);

    Client getClientById(Long id);

    Client updateClient(Long id, Client client);

    void deleteClientById(Long id);

    Iterable<Client> findByFirstName(String firstName);

    Iterable<Client> orderClientsByFirstNameAsc();

    Iterable<Client> orderClientsByFirstNameDesc();

}
