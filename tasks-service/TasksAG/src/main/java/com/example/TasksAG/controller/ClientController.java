package com.example.TasksAG.controller;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.dto.ClientDTO;
import com.example.TasksAG.domain.dto.LoginResponse;
import com.example.TasksAG.security.AuthenticationRequest;
import com.example.TasksAG.security.JWTTokenHelper;
import com.example.TasksAG.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(value = {"*"})
public class ClientController {
    private final ClientService clientService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenHelper jwtTokenHelper;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/all-clients")
    public List<Client> getAllClients(){
        for (Client c : clientService.getAllClients()) {
            System.out.println(c.getUsername());
        }
        return clientService.getAllClients();
    }
    @PostMapping("/register")
    public ClientDTO register(@RequestBody ClientDTO clientDTO) {
        clientService.addClient(clientDTO);
        return clientDTO;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Client client = (Client) authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(client);
        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setUserId(client.getId());
        response.setUsername(client.getUsername());
        return ResponseEntity.ok(response);
    }

}
