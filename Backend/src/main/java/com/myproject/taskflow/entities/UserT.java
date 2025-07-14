package com.myproject.taskflow.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "userTable")
@PrimaryKeyJoinColumn(name = "idUser")
public class UserT extends Person {
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role = "EMPLOYEE";
    public UserT() {}

    public UserT(String firstName, String lastName, String mail, String tel, String username, String role) {
        super(firstName, lastName, mail, tel);
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
