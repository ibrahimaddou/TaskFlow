package com.myproject.taskflow.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Task {
    public Task(long idTask, String taskName, String taskDescription, TaskStatus taskStatus, LocalDate deadline, TaskPriority taskPriority) {
        this.idTask = idTask;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.taskPriority = taskPriority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTask ;
    @Column
    private String taskName ;
    @Column
    private String taskDescription;
    @Column
    private TaskStatus taskStatus = TaskStatus.NOTSTARTED;
    @Column
    private LocalDate deadline;
    @Column
    private TaskPriority taskPriority = TaskPriority.MEDIUM;
    //private Integer createdBy; //  references userTable.idUser
    private LocalDateTime creationDate = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDateTime startDate = LocalDateTime.now();
}
