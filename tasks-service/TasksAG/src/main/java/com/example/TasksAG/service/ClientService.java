package com.example.TasksAG.service;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }
}
