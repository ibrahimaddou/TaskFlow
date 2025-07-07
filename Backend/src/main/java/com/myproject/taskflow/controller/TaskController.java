package com.myproject.taskflow.controller;

import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.entities.TaskStatus;
import com.myproject.taskflow.services.TaskService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(path ="/tasks/{id}")
    public Task getTaskById(@PathVariable long id){
        return  taskService.getTaskById(id);
    }
    @GetMapping(path ="/tasks/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status){
        return  taskService.getTasksByStatus(status);
    }
    @PostMapping(path = "/tasks")
    public Task createTask(@RequestBody Task task ){
        return  taskService.createTask(task);
    }

}
