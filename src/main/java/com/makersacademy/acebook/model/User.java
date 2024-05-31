package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.Getter;
import javax.persistence.*;
import static java.lang.Boolean.TRUE;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String firstname;
    @Getter
    private String lastname;
    @Getter
    private String email;
    private String bio;
    @Getter
    private String username;
    @Getter
    private String password;
    private boolean enabled;

    public User() {
        this.enabled = TRUE;
    }

    public User(String firstname, String lastname, String email, String bio, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.bio = bio;
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
