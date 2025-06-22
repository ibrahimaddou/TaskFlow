package com.myproject.taskflow.services;

import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
