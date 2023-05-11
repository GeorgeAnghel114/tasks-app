package com.example.TasksAG.service;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.dto.ClientDTO;
import com.example.TasksAG.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client addClient(ClientDTO clientDTO) throws Exception {
        userAlreadyExists(clientDTO);

        Client client = Client.builder()
                .email(clientDTO.getEmail())
                .username(clientDTO.getUsername())
                .password(passwordEncoder.encode(clientDTO.getPassword()))
                .roles(List.of("ROLE_USER"))
                .build();
        clientRepository.save(client);
        return client;
    }

    public void userAlreadyExists(ClientDTO clientDTO) throws Exception {
        if (clientRepository.existsClientByUsername(clientDTO.getUsername())) {
            throw new Exception("Username already exists!");
        }
        if (clientRepository.existsClientByEmail(clientDTO.getEmail())) {
            throw new Exception("Email already exists!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client user = clientRepository.findClientByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }


    public Client findUserByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }
}
