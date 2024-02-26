package ro.ubb.rentals.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.rentals.core.model.Client;
import ro.ubb.rentals.core.service.IClientService;
import ro.ubb.rentals.web.converter.ClientConverter;
import ro.ubb.rentals.web.dto.ClientDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
public class ClientController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private ClientConverter clientConverter;


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public Set<ClientDto> getClients() {
        List<Client> clients = clientService.getAllClients();
        return clientConverter.convertModelsToDtos(clients);
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
    public ClientDto getClientById(@PathVariable Long clientId) {
        Client client = clientService.getClientById(clientId);
        return clientConverter.convertModelToDto(client);
    }


    @RequestMapping(value = "/clients", method = RequestMethod.POST, consumes = "application/json")
    public ClientDto saveClient(@RequestBody ClientDto dto) {
        return clientConverter.convertModelToDto(
                clientService.addClient(
                        clientConverter.convertDtoToModel(dto)
                )
        );
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT, consumes = "application/json")
    public ClientDto updateClient(
            @PathVariable final Long clientId,
            @RequestBody final ClientDto dto) {

        return clientConverter.convertModelToDto(
                clientService.updateClient(
                        clientId,
                        clientConverter.convertDtoToModel(dto)
                )
        );
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/clients/search/firstName", method = RequestMethod.GET)
    public List<ClientDto> findByFirstName(String firstName){
        Iterable<Client> result = clientService.findByFirstName(firstName);
        List<ClientDto> clientDtos = StreamSupport.stream(result.spliterator(), false)
                .map(clientConverter::convertModelToDto)
                .collect(Collectors.toList());
        return clientDtos;
    }

    @RequestMapping(value="/clients/order/firstName/asc", method = RequestMethod.GET)
    public List<ClientDto> orderClientsByFirstNameAsc(){
        Iterable<Client> orderedClients = clientService.orderClientsByFirstNameAsc();
        List<ClientDto> clientDtos = StreamSupport.stream(orderedClients.spliterator(), false)
                .map(clientConverter::convertModelToDto)
                .collect(Collectors.toList());
        return clientDtos;
    }

    @RequestMapping(value="/clients/order/firstName/desc", method = RequestMethod.GET)
    public List<ClientDto> orderClientsByFirstNameDesc(){
        Iterable<Client> orderedClients = clientService.orderClientsByFirstNameDesc();
        List<ClientDto> clientDtos = StreamSupport.stream(orderedClients.spliterator(), false)
                .map(clientConverter::convertModelToDto)
                .collect(Collectors.toList());
        return clientDtos;
    }

}
