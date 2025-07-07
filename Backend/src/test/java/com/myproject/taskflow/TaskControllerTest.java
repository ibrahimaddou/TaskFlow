package com.myproject.taskflow;

import com.myproject.taskflow.controller.TaskController;
import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.entities.TaskPriority;
import com.myproject.taskflow.entities.TaskStatus;
import com.myproject.taskflow.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Task testTask;
    private Task savedTask;

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
    @Test
    void testCreateTask_Success() {
        testTask = new Task();
        testTask.setTaskName("Test Task");
        testTask.setTaskDescription("Test Description");
        testTask.setTaskStatus("NOTSTARTED");
        testTask.setTaskPriority("MEDIUM");
        testTask.setDeadline(LocalDate.now().plusDays(7));
        testTask.setCreatedBy(1);

        savedTask = new Task();
        savedTask.setIdTask(1L);
        savedTask.setTaskName("Test Task");
        savedTask.setTaskDescription("Test Description");
        savedTask.setTaskStatus("NOTSTARTED");
        savedTask.setTaskPriority("MEDIUM");
        savedTask.setDeadline(LocalDate.now().plusDays(7));
        savedTask.setCreatedBy(1);
        savedTask.setCreationDate(LocalDateTime.now());
        savedTask.setUpdatedAt(LocalDateTime.now());
        savedTask.setStartDate(LocalDateTime.now());
        when(taskService.createTask(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(testTask);

        assertNotNull(result);
        assertEquals("Test Task", result.getTaskName());
        assertEquals("Test Description", result.getTaskDescription());
        assertEquals("NOTSTARTED", result.getTaskStatus());
        assertEquals("MEDIUM", result.getTaskPriority());
        assertEquals(1, result.getCreatedBy());
        verify(taskService, times(1)).createTask(any(Task.class));
    }
}