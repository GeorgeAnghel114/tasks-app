package com.example.TasksAG.service;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.Task;
import com.example.TasksAG.domain.dto.SearchDTO;
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

    public Task addTaskOfClient(TaskDTO taskDTO) {
        Task task = new Task();
        Client client = clientService.findUserByEmail(taskDTO.getClientEmail());
        task.setSubject(taskDTO.getSubject());
        task.setStatus(taskDTO.getStatus());
        task.setDuedate(taskDTO.getDuedate());
        task.setDeleted(false);
        task.setClient(client);
        task.setClientId(client.getId());
        List<Task> taskList = client.getTaskList();
        taskList.add(task);
//        addTask(task);
        taskRepository.save(task);

        return task;
    }

    public List<Task> getTasksOfClient(String email) {
        Client client = clientService.findUserByUsername(email);
        return taskRepository.findAllByTaskByDateDesc(client.getId());
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    public Task updateTask(TaskDTO taskDTO, Long id) {
        Task task = getTaskById(id);
        Client client = clientService.findUserByEmail(taskDTO.getClientEmail());
        task.setClient(client);
        task.setClientId(client.getId());
        task.setDuedate(taskDTO.getDuedate());
        task.setStatus(taskDTO.getStatus());
        task.setSubject(taskDTO.getSubject());
        taskRepository.save(task);
        return task;
    }

    public List<Task> getSearchParams(SearchDTO searchDTO) {
        return taskRepository.findBySearch(
                searchDTO.getSubject(),
                searchDTO.getDuedate(),
                searchDTO.getClientId(),
                searchDTO.getStatus());
    }

    public Task deleteTask(Long id) {
        Task task = getTaskById(id);
        task.setDeleted(true);
        return taskRepository.save(task);
    }
}
