package com.example.TasksAG;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.Task;
import com.example.TasksAG.domain.dto.ClientDTO;
import com.example.TasksAG.repository.ClientRepository;
import com.example.TasksAG.service.ClientService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {
    private List<Task> taskList;
    private Task task1;
    private Task task2;
    private final String username = "test";
    private final String email = "test";

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ClientService clientService;


    @BeforeEach
    public void init() {
        Client client = Client.builder()
                .id(1L)
                .email("test")
                .username("test")
                .taskList(taskList).build();

        taskList = new ArrayList<>();
        task1 = new Task(1L, "test", LocalDate.now(), "Done", client, false);
        task2 = new Task(2L, "test", LocalDate.now(), "New", client, false);
        taskList.add(task1);
        taskList.add(task2);

    }

    @Test
    public void getClientByIdTest() {
        Client client = Client.builder()
                .id(1L)
                .email("test")
                .username("test")
                .taskList(taskList).build();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client client1 = clientService.getClientById(1L);
        assertEquals(client, client1);
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void getAllClientsTest() {
        List<Client> clientList = new ArrayList<>();

        Client c1 = Client.builder()
                .id(1L)
                .email("test")
                .username("test")
                .taskList(taskList).build();
        Client c2 = Client.builder()
                .id(2L)
                .email("test")
                .username("test")
                .taskList(taskList).build();

        clientList.add(c1);
        clientList.add(c2);

        when(clientRepository.findAll()).thenReturn(clientList);

        List<Client> result = clientService.getAllClients();

        verify(clientRepository, times(1)).findAll();

        assertEquals(clientList.size(), result.size());
    }

//    @Test
//    public void shouldAddClient_GivenClientDTO() throws Exception {
//
//        ClientDTO clientDTO = ClientDTO.builder()
//                .password("123")
//                .email("123")
//                .username("123").build();
//
//        when(passwordEncoder.encode(clientDTO.getPassword())).thenReturn("encodedPassword");
//
//        Client result = clientService.addClient(clientDTO);
//
//        verify(clientRepository, times(1)).save(any(Client.class));
//
//        assertNotNull(result);
//    }

    @Test
    public void testFindUserByEmail(){
        Client client = new Client();
        client.setEmail(email);

        Mockito.when(clientRepository.findClientByEmail(email)).thenReturn(client);
        Client actualClient = clientService.findUserByEmail(email);


        assertEquals(client,actualClient);
        assertEquals(email,actualClient.getEmail());
    }
}
