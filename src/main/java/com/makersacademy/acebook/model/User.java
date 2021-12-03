package com.makersacademy.acebook.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

import java.util.Objects;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Getter
@Setter
@ToString
@Entity
@Table(name = "USERS")
public class User {

    private String username;
    private String password;
    private boolean enabled;
    private String userimage;
    @Id
    private UUID userID = UUID.randomUUID();

    public User() {
        this.enabled = TRUE;
    }


    public User(String username, String password, String userimage) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
        this.userimage = userimage;

    }

    public User(String username, String password, boolean enabled, String userimage) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userimage = userimage;
    }

    public UUID getUserID() { return this.userID; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);
        String encodedPassword = encoder.encode(password);
        this.password = encodedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userID != null && Objects.equals(userID, user.userID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setuserimage(String userimage) {
        this.userimage = userimage;
    }
    public String getuserimage() { return this.userimage; }
}
