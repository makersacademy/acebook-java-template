package com.makersacademy.acebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

import static java.lang.Boolean.TRUE;

@Data
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled;


    public User() {
        this.enabled = TRUE;
    }

}
