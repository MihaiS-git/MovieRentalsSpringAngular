package ro.ubb.rentals.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.rentals.core.model.Client;
import ro.ubb.rentals.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .email(dto.getEmail())
                .subscribe(dto.isSubscribe())
                .build();
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .dateOfBirth(client.getDateOfBirth())
                .email(client.getEmail())
                .subscribe(client.isSubscribe())
                .build();
        return dto;
    }
}
