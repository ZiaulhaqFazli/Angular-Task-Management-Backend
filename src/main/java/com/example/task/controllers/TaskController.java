package com.example.task.controllers;

import com.example.task.classes.CountType;
import com.example.task.classes.Task;
import com.example.task.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/task/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id){
        return Optional.ofNullable(taskService.getTaskById(id).orElse(null));
    }

    @PostMapping("/task")
    public Task postTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable Long id) {
        if(taskService.taskExistsById(id)){
            Task existedTask = taskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Task Not Found!"));
            existedTask.setTitle(task.getTitle());
            existedTask.setDueDate(task.getDueDate());
            existedTask.setType(task.getType());
            existedTask.setDescription(task.getDescription());
            taskService.save(existedTask);
            return ResponseEntity.ok().body(existedTask);
        }else{
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id + " Task was not found or matched!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if(taskService.taskExistsById(id)){
            taskService.deleteTask(id);
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id + " Task removed successfully!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }else{
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id + " Task was not found or matched!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

}

