package com.example.TasksAG.controller;

import com.example.TasksAG.domain.Task;
import com.example.TasksAG.domain.dto.SearchDTO;
import com.example.TasksAG.domain.dto.TaskDTO;
import com.example.TasksAG.service.ClientService;
import com.example.TasksAG.service.TaskService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(Long.valueOf(id));
    }

    @PostMapping("/add-task")
    public void addClientTask(@RequestBody TaskDTO taskDTO) {
        taskService.addTaskOfClient(taskDTO);
    }

    @PutMapping("/update-task/{id}")
    public void updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        System.out.println("client name   "+taskDTO.getClientEmail());
        taskService.updateTask(taskDTO, Long.valueOf(id));
    }

    @PostMapping("/search")
    public List<Task> searchParams(@RequestBody SearchDTO searchDTO){
        return taskService.getSearchParams(searchDTO);
    }

    @DeleteMapping("/delete-task/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
