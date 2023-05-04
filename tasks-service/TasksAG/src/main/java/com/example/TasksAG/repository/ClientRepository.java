package com.example.TasksAG.repository;

import com.example.TasksAG.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByEmail(String email);

    Client findClientByUsername(String username);

    @Query(value = "select email from client",
            nativeQuery = true)
    List<Client> getAllClientsUsername();

}
