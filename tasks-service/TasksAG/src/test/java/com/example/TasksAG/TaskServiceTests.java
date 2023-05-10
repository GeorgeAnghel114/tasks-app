package com.example.TasksAG;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.Task;
import com.example.TasksAG.domain.dto.TaskDTO;
import com.example.TasksAG.repository.TaskRepository;
import com.example.TasksAG.service.ClientService;
import com.example.TasksAG.service.TaskService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
    public void init(){
        taskList = new ArrayList<>();
        task1=new Task(1L,"subject", LocalDate.now(),"Done",new Client(),false);
        task2=new Task(2L,"subject2", LocalDate.now(),"New",new Client(),false);
        taskList.add(task1);
        taskList.add(task2);
    }

    @Test
    public void getTaskByIdTest(){
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        Task actualTask = taskService.getTaskById(1L);

        assertEquals(task1,actualTask);
        verify(taskRepository,times(1)).findById(1L);//verific daca s-a apelat metoda finById din taskRepo
    }


    @Test
    public void getTaskByIdTestNullReturn(){
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task actualTask = taskService.getTaskById(1L);

        assertEquals(null,actualTask);
        verify(taskRepository,times(1)).findById(1L);//verific daca s-a apelat metoda finById din taskRepo
    }


    @Test
    public void shouldAddTaskOfClient_GivenTaskDTO(){
        TaskDTO taskDTO = TaskDTO.builder()
                .subject("subject")
                .duedate(LocalDate.now())
                .status("Done")
                .clientEmail("abc").build();
        Client client = new Client();
        when(clientService.findUserByEmail(taskDTO.getClientEmail())).thenReturn(client);

        Task actualTask = taskService.addTaskOfClient(taskDTO);

        verify(taskRepository).save(any(Task.class));
        assertEquals(1,client.getTaskList().size());

    }

}
