package com.myproject.taskflow;

import com.myproject.taskflow.controller.TaskController;
import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void getAllTasksShouldReturnTasks() {
        // Given
        Task task1 = new Task();
        Task task2 = new Task();
        task1.setTaskName("Test Task1");
        task2.setTaskName("Test Task2");
        List<Task> tasks = Arrays.asList(task1,task2);

        when(taskService.findAll()).thenReturn(tasks);

        // When
        List<Task> result = taskController.getAllTasks();

        // Then
        assertEquals(2, result.size());
        assertEquals("Test Task1", result.get(0).getTaskName());
        assertEquals("Test Task2", result.get(1).getTaskName());
    }
    @Test
    void testGetTaskById_whenTaskExists() {
        Task task = new Task();
        task.setTaskName("Test TaskById");
        task.setId(99);
        when(taskService.getTaskById(99L)).thenReturn(task);

        Task taskFound = taskService.getTaskById(99L);

        assertNotNull(taskFound);
        assertEquals("Test TaskById", taskFound.getTaskName());
        verify(taskService, times(1)).getTaskById(99L);
    }
}