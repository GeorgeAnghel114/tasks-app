package com.example.TasksAG;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.Task;
import com.example.TasksAG.domain.dto.SearchDTO;
import com.example.TasksAG.domain.dto.TaskDTO;
import com.example.TasksAG.repository.TaskRepository;
import com.example.TasksAG.service.ClientService;
import com.example.TasksAG.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
    private List<Task> taskList;
    private Task task1;
    private Task task2;
    @Mock
    TaskRepository taskRepository;
    @Mock
    ClientService clientService;
    @InjectMocks
    TaskService taskService;

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
    public void getTaskByIdTest() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        Task actualTask = taskService.getTaskById(1L);

        assertEquals(task1, actualTask);
        verify(taskRepository, times(1)).findById(1L);//verific daca s-a apelat metoda finById din taskRepo
    }


    @Test
    public void getTaskByIdTestNullReturn() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task actualTask = taskService.getTaskById(1L);

        assertEquals(null, actualTask);
        verify(taskRepository, times(1)).findById(1L);//verific daca s-a apelat metoda finById din taskRepo
    }


    @Test
    public void shouldAddTaskOfClient_GivenTaskDTO() {
        TaskDTO taskDTO = TaskDTO.builder()
                .subject("subject")
                .duedate(LocalDate.now())
                .status("Done")
                .clientEmail("abc").build();
        Client client = new Client();
        when(clientService.findUserByEmail(taskDTO.getClientEmail())).thenReturn(client);

        Task actualTask = taskService.addTaskOfClient(taskDTO);

        verify(taskRepository).save(any(Task.class));
        assertEquals(1, client.getTaskList().size());

    }

    @Test
    public void shouldGetTasksOfClient_GivenEmail() {

        String clientEmail = "test";
        Client client = new Client();
        client.setId(1L);
        when(clientService.findUserByEmail(clientEmail)).thenReturn(client);

        List<Task> taskList1 = new ArrayList<>();
        taskList1.add(task1);
        taskList1.add(task2);
        when(taskRepository.findAllByTaskByDateDesc(client.getId())).thenReturn(taskList1);

        Client retrivedClient = clientService.findUserByEmail(clientEmail);
        List<Task> retrivedTaskList = taskRepository.findAllByTaskByDateDesc(retrivedClient.getId());

        verify(taskRepository, times(1)).findAllByTaskByDateDesc(retrivedClient.getId());
        assertEquals(2, retrivedTaskList.size());
    }

    @Test
    public void shouldReturnAllTasks() {
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(task1);
        expectedTasks.add(task2);

        when(taskRepository.findAllTasks()).thenReturn(expectedTasks);
        System.out.println(expectedTasks.size());

        List<Task> actualTasks = taskService.getAllTasks();

        assertEquals(2, actualTasks.size());
    }

    @Test
    public void shouldUpdateTask_GivenTaskDTOAndId() {
        Long taskId = 1L;
        String newClientEmail = "newclient@example.com";
        LocalDate dueDate = LocalDate.now();
        String status = "Completed";
        String subject = "Updated Task Subject";

        TaskDTO taskDTO = TaskDTO.builder()
                .taskId(taskId)
                .clientEmail(newClientEmail)
                .duedate(dueDate)
                .status(status)
                .subject(subject)
                .build();

        Task task = new Task();
        task.setId(taskId);
        Client client = new Client();
        client.setId(1L);

        when(taskRepository.getById(taskId)).thenReturn(task);
        when(clientService.findUserByEmail(taskDTO.getClientEmail())).thenReturn(client);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.updateTask(taskDTO, taskId);

        verify(taskRepository, times(1)).getById(taskId);
        verify(clientService, times(1)).findUserByEmail(taskDTO.getClientEmail());
        verify(taskRepository, times(1)).save(task);

        assertEquals(client, task.getClient());
    }

    @Test
    public void testGetSearchParams_GivenSearchDTO() {
        SearchDTO searchDTO = new SearchDTO(1L, "b", LocalDate.now());
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(task1);
        expectedTasks.add(task2);

        when(taskRepository.findBySearch(
                searchDTO.getSubject(),
                searchDTO.getDuedate(),
                searchDTO.getClientId()
        )).thenReturn(expectedTasks);

        List<Task> result = taskService.getSearchParams(searchDTO);
        verify(taskRepository, times(1)).findBySearch(
                searchDTO.getSubject(),
                searchDTO.getDuedate(),
                searchDTO.getClientId());

        assertEquals(expectedTasks, result);

    }

    @Test
    public void testDeleteTask_GivenId() {
        Long taskId = 1L;
        Task task = new Task(1L, "test", LocalDate.now(), "Done", new Client(), false);
        task.setDeleted(true);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(task);

        assertTrue(task.isDeleted());
        assertEquals(task, result);
        assertEquals(true, task.isDeleted());
    }

}
