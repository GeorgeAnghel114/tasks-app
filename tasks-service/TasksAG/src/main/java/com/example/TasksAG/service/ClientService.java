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

import java.util.Collections;
import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public void addClient(ClientDTO clientDTO){
//        Client client = Client.builder()
//                .email(clientDTO.getEmail())
//                .username(clientDTO.getUsername())
//                .password(passwordEncoder.encode(clientDTO.getPassword()))
//                .roles(Collections.singletonList("ROLE_USER"))
//                .build();
        Client client = new Client();
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        client.setUsername(clientDTO.getUsername());
        client.setEmail(clientDTO.getEmail());
        client.getRoles().add("ROLE_USER");
        clientRepository.save(client);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client user = clientRepository.findClientByUsername(username);
        if(null == user){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}
