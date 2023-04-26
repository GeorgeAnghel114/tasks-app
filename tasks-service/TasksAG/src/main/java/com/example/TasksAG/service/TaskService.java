package com.example.TasksAG.service;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.Task;
import com.example.TasksAG.domain.dto.TaskDTO;
import com.example.TasksAG.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ClientService clientService;

    public TaskService(TaskRepository taskRepository, ClientService clientService) {
        this.taskRepository = taskRepository;
        this.clientService = clientService;
    }

    public Task getTaskById(Long id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public void addTask(Task task){
        taskRepository.save(task);
    }

    public void addTaskOfClient(TaskDTO taskDTO,String email){
        Task task = new Task();
        Client client = clientService.findUserByEmail(email);
        task.setSubject(taskDTO.getSubject());
        task.setStatus(taskDTO.getStatus());
        task.setDate(taskDTO.getDate());
        task.setClient(client);
        List<Task> taskList = client.getTaskList();
        taskList.add(task);
        addTask(task);
    }

    public List<Task> getTasksOfClient(String email){
        Client client = clientService.findUserByEmail(email);
        List<Task> taskList = client.getTaskList();
        return taskList;
    }

}
