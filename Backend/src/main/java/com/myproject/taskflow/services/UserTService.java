package com.myproject.taskflow.services;

import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.entities.UserT;
import com.myproject.taskflow.repository.UserTRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTService {
    private final UserTRepository userTRepository;


    public UserTService(UserTRepository userTRepository) {
        this.userTRepository = userTRepository;

    }
    public List<UserT> findAll() {

        return userTRepository.findAll();
    }
}
