package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.TrueFalseType;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;

// import com.makersacademy.acebook.repository.CredentialsRepository;
import com.makersacademy.acebook.repository.UserRepository;

import javax.persistence.GenerationType;

import lombok.Data;

import static java.lang.Boolean.TRUE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public void getAllUsernames() {
    // String url =
    // "jdbc:postgresql://localhost:5432/acebook_sprintboot_development";

    // }

    // public String isUnique(String username) {
    // Optional<Credential> credentials =
    // CredentialsRepository.findByUsername(username);

    // return credentials.isPresent() ? "exist" : "doesnt exist";
    // }
}
