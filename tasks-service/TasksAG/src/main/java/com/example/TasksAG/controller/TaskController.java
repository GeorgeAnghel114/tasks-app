package com.example.TasksAG.controller;

import com.example.TasksAG.domain.Task;
import com.example.TasksAG.domain.dto.SearchDTO;
import com.example.TasksAG.domain.dto.TaskDTO;
import com.example.TasksAG.service.ClientService;
import com.example.TasksAG.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<List<Task>> getClientTasks(@PathVariable String email) {
        if (Objects.equals(email, "")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(taskService.getTasksOfClient(email));
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<?> getAllTasks() {
        try {
            return ResponseEntity.ok().body(taskService.getAllTasks());
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Clients not found!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(taskService.getTaskById(Long.valueOf(id)));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Task not found!");
        }
    }

    @PostMapping("/add-task")
    public ResponseEntity<?> addClientTask(@RequestBody TaskDTO taskDTO) {
        try {
            return ResponseEntity.ok().body(taskService.addTaskOfClient(taskDTO));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Can't add task, try again!");
        }

    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        try {
            return ResponseEntity.ok().body(taskService.updateTask(taskDTO, Long.valueOf(id)));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Could not update the task, try again!");
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchParams(@RequestBody SearchDTO searchDTO) {
        try {
            return ResponseEntity.ok().body(taskService.getSearchParams(searchDTO));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Something went wrong, try again!");
        }
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(taskService.deleteTask(id));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Could not delete the task! Try again!");
        }
    }
}
