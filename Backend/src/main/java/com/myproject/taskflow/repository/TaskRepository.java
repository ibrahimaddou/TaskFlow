package com.myproject.taskflow.repository;

import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);
}
