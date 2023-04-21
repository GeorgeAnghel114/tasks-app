package com.example.TasksAG.controller;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/get-client/{id}")
    public Client getClientById(@PathVariable("id") Long id){
        return clientService.getClientById(id);
    }
}
