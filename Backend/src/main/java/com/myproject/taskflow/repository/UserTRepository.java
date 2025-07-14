package com.myproject.taskflow.repository;

import com.myproject.taskflow.DTO.UserTDTO;
import com.myproject.taskflow.entities.UserT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTRepository extends JpaRepository<UserT, Long> {

}
