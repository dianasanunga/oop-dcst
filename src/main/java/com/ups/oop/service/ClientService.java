package com.ups.oop.service;

import com.ups.oop.dto.ClientDTO;
import com.ups.oop.entity.Client;
import com.ups.oop.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<ClientDTO> getClients() {
        Iterable<Client> clientIterable = clientRepository.findAll();
        List<ClientDTO> clientList = new ArrayList<>();
        for(Client client : clientIterable) {
            ClientDTO clientDTO = new ClientDTO(
                    client.getPersonId(),
                    (client.getName() + "-" + client.getLastname()),
                    client.getAge(),
                    client.getClientCode()
            );
            clientList.add(clientDTO);
        }
        return clientList;
    }
}
