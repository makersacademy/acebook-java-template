package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;
import lombok.Getter;

import static java.lang.Boolean.TRUE;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String username;
    @Getter
    private String password;
    private boolean enabled;
    @Getter String imageUrl;


    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password, String imageUrl) {
        this.username = username;
        this.password = password;
        this.imageUrl = imageUrl;
        this.enabled = TRUE;
    }

    public User(String username, String password, String imageUrl, boolean enabled) {
        this.username = username;
        this.password = password;
        this.imageUrl = imageUrl;
        this.enabled = enabled;
    }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

}
