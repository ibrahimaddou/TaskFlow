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

    public UserT(String prenom, String nom, String mail, String tel, String username, String password, String role) {
        super(prenom, nom, mail, tel);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
