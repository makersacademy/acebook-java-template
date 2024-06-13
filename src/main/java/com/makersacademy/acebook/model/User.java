package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import static java.lang.Boolean.TRUE;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String password;
    private boolean enabled;

    @Setter
    @Getter
    private String profilePictureUrl;

    @Setter
    @Getter
    private String bio;

    @Getter
    @Transient
    private MultipartFile profilePicture;

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password, String profilePictureUrl) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
    }

    public User(String username, String password, boolean enabled, String profilePictureUrl) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setProfilePicture(MultipartFile profilePicture) { this.profilePicture = profilePicture; }
}
