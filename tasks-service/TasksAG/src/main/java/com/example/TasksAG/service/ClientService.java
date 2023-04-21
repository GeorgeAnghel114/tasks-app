package com.example.TasksAG.service;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.dto.ClientDTO;
import com.example.TasksAG.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public void addClient(ClientDTO clientDTO){
        Client client = Client.builder()
                .email(clientDTO.getEmail())
                .username(clientDTO.getUsername())
                .password(passwordEncoder.encode(clientDTO.getPassword()))
                .build();
        clientRepository.save(client);
    }

}
