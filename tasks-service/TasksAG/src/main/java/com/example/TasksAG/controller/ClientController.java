package com.example.TasksAG.controller;

import com.example.TasksAG.domain.dto.ClientDTO;
import com.example.TasksAG.domain.dto.LoginResponse;
import com.example.TasksAG.security.AuthenticationRequest;
import com.example.TasksAG.security.JWTTokenHelper;
import com.example.TasksAG.service.AuthenticationService;
import com.example.TasksAG.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(value = {"*"})
public class ClientController {
    private final ClientService clientService;
    private final AuthenticationService authenticationService;

    private final JWTTokenHelper jwtTokenHelper;

    public ClientController(ClientService clientService,
                            AuthenticationService authenticationService,
                            JWTTokenHelper jwtTokenHelper) {
        this.clientService = clientService;
        this.authenticationService = authenticationService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(clientService.getClientById(id));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("User not found!");
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getClientUsername(@PathVariable String email){
        try{
            return ResponseEntity.ok().body(clientService.findUserByEmail(email));
        }catch (Exception e){
            return ResponseEntity.status(401).body("Email not found!");
        }
    }

    @GetMapping("/all-clients")
    public ResponseEntity<?> getAllClients() {
        try {
            return ResponseEntity.ok().body(clientService.getAllClients());
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Users not found!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClientDTO clientDTO) {
        try {
            clientService.addClient(clientDTO);
            return ResponseEntity.ok().body(clientDTO);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            LoginResponse response = authenticationService.authenticate(authenticationRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Something went wrong,try again!");
        }
    }
}
