package com.example.TasksAG.repository;

import com.example.TasksAG.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByEmail(String email);

    Client findClientByUsername(String username);
}
