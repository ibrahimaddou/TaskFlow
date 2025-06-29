package com.myproject.taskflow.controller;

import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.services.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping(path ="/tasks")

    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

}
