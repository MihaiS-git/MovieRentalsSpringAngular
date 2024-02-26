package ro.ubb.rentals.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.rentals.core.model.Client;
import ro.ubb.rentals.core.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients --- method entered");

        List<Client> result = clientRepository.findAll();

        log.trace("getAllClients: result={}", result);

        return result;
    }

    @Override
    public Client addClient(Client client) {
        log.trace("addClient --- method entered");

        Client result = clientRepository.save(client);

        log.trace("addClient: client={}", result);

        return result;
    }

    @Override
    public Client getClientById(Long id) {
        log.trace("getClientById --- method entered");

        Client result = clientRepository.findById(id).orElse(new Client());

        log.trace("getClientById: client={}", result);

        return result;
    }

    @Transactional
    @Override
    public Client updateClient(Long clientId, Client client) {
        log.trace("updateClient --- method entered");

        Optional<Client> result = clientRepository.findById(clientId);

        result.ifPresent(o -> {
            o.setFirstName(client.getFirstName());
            o.setLastName(client.getLastName());
            o.setDateOfBirth(client.getDateOfBirth());
            o.setEmail(client.getEmail());
            o.setSubscribe(client.isSubscribe());
        });

        log.trace("updatedClient: result={}", result.get());

        return result.orElse(null);
    }

    @Override
    public void deleteClientById(Long id) {
        log.trace("deleteClientById --- method entered");

        clientRepository.deleteById(id);

        log.trace("deleteClientById: client deleted successfully");
    }

    @Override
    public Iterable<Client> findByFirstName(String firstName) {
        log.trace("findByFirstName --- method entered");

        Iterable<Client> result = clientRepository.findByFirstName(firstName);

        log.trace("findByFirstName: result={}", result);

        return result;
    }

    @Override
    public Iterable<Client> orderClientsByFirstNameAsc() {
        log.trace("orderClientsByFirstNameAsc --- method entered");

        Iterable<Client> orderedClients = clientRepository.findAllByOrderByFirstNameAsc();

        log.trace("orderClientsByFirstNameAsc: result={}", orderedClients);

        return orderedClients;
    }

    @Override
    public Iterable<Client> orderClientsByFirstNameDesc() {
        log.trace("orderClientsByFirstNameDesc --- method entered");

        Iterable<Client> orderedClients = clientRepository.findAllByOrderByFirstNameDesc();

        log.trace("orderClientsByFirstNameDesc: result={}", orderedClients);

        return orderedClients;
    }

}
