package com.myproject.taskflow.controller;

import com.myproject.taskflow.DTO.UserTDTO;
import com.myproject.taskflow.entities.UserT;
import com.myproject.taskflow.services.UserTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserTController {
    private final UserTService userTService;

    public UserTController(UserTService userTService) {
        this.userTService = userTService;
    }
    @GetMapping(path="/users")
    public ResponseEntity<List<UserTDTO>> getAllUsers() {
        List<UserTDTO> users = userTService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
