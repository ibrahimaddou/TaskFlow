package com.myproject.taskflow.services;

import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.entities.TaskStatus;
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
    public Task getTaskById( Long id){
        return  taskRepository.findById(id).get();
    }


    public  List<Task> getTasksByStatus(String status){
        return taskRepository.findByStatus(status);
    }
}
