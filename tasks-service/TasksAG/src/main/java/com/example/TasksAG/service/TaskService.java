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

    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void addTaskOfClient(TaskDTO taskDTO, String email) {
        Task task = new Task();
        Client client = clientService.findUserByEmail(email);
        task.setSubject(taskDTO.getSubject());
        task.setStatus(taskDTO.getStatus());
        task.setDuedate(taskDTO.getDuedate());
        task.setClient(client);
        task.setClientId(client.getId());
        List<Task> taskList = client.getTaskList();
        taskList.add(task);
        addTask(task);
    }

    public List<Task> getTasksOfClient(String email) {
        Client client = clientService.findUserByEmail(email);
        return taskRepository.findAllByTaskByDateDesc(client.getId());
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    public void updateTask(TaskDTO taskDTO,Long id){
        Task task = getTaskById(id);
        Client client = clientService.findUserByEmail(taskDTO.getClientEmail());
        task.setClient(client);
        task.setClientId(client.getId());
        task.setDuedate(taskDTO.getDuedate());
        task.setStatus(taskDTO.getStatus());
        task.setSubject(taskDTO.getSubject());
        addTask(task);
    }

    public List<Task> getSearchParams(TaskDTO taskDTO){
        return taskRepository.findBySearch(taskDTO.getSubject(),
                taskDTO.getDuedate(),
                taskDTO.getClientEmail());
    }
}
