package com.example.task.repositories;

import com.example.task.classes.CountType;
import com.example.task.classes.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(value="Select * from tasks order by dueDate desc", nativeQuery = true)
    List<Task> getTasksByDueDateDesc();

}
