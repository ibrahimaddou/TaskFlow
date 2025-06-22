package com.myproject.taskflow.repository;

import com.myproject.taskflow.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
