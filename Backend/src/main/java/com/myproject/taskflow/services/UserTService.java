package com.myproject.taskflow.services;

import com.myproject.taskflow.DTO.UserTDTO;
import com.myproject.taskflow.entities.Task;
import com.myproject.taskflow.entities.UserT;
import com.myproject.taskflow.repository.UserTRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTService {
    private final UserTRepository userTRepository;


    public UserTService(UserTRepository userTRepository) {
        this.userTRepository = userTRepository;

    }

    public List<UserT> findAll() {

        return userTRepository.findAll();
    }
    public List<UserTDTO> getAllUsers() {
        return userTRepository.findAll().stream()
                .map(user -> new UserTDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getMail(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }


}
