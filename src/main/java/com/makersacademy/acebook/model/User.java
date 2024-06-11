package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

import static java.lang.Boolean.TRUE;

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
    private String profilePictureUrl;

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password, String profilePictureUrl) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
        this.profilePictureUrl = profilePictureUrl;
    }

    public User(String username, String password, boolean enabled, String profilePictureUrl) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getProfilePictureUrl() { return this.profilePictureUrl; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
}
