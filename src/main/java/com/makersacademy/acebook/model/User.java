package com.makersacademy.acebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;    // This can be null for Google users

    private boolean enabled;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String profilePictureUrl;

    @Getter
    @Setter
    private String language;

    @Getter
    @Setter
    private String city;

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
    }

    public User(String username, String password, String email, boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;

        this.enabled = enabled;
    }


}
