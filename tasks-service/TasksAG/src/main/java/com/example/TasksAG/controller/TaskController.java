package com.example.TasksAG.controller;

import com.example.TasksAG.domain.Task;
import com.example.TasksAG.domain.dto.TaskDTO;
import com.example.TasksAG.service.ClientService;
import com.example.TasksAG.service.TaskService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(value = {"*"})
public class TaskController {
    private final ClientService clientService;
    private final TaskService taskService;

    public TaskController(ClientService clientService, TaskService taskService) {
        this.clientService = clientService;
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{email}")
    public List<Task> getClientTasks(@PathVariable String email) {
        return taskService.getTasksOfClient(email);
    }

    @GetMapping("/all-tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id){
        System.out.println(taskService.getTaskById(Long.valueOf(id)).toString());
        return taskService.getTaskById(Long.valueOf(id));
    }

    @PostMapping("/add-task/{email}")
    public void addClientTask(@PathVariable String email, @RequestBody TaskDTO taskDTO) {
        System.out.println(taskDTO);
        taskService.addTaskOfClient(taskDTO, email);
    }
}
