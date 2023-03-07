package com.example.task.services;

import com.example.task.classes.CountType;
import com.example.task.classes.Task;
import com.example.task.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> getAllTasks(){
        return taskRepository.getTasksByDueDateDesc();
    }

    public Task save(Task task){
        return taskRepository.saveAndFlush(task);
    }

    @Transactional(readOnly = true)
    public boolean taskExistsById(Long id){
        return taskRepository.existsById(id);
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public Task updateTask(Task task){
        return taskRepository.saveAndFlush(task);
    }

    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

}
