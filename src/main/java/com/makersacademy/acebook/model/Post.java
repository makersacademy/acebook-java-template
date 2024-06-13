package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String content;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @Getter
    private String imageUrl;

    @Setter
    @Getter
    private LocalDateTime createdAt;

    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public Post(String content, User user, String imageUrl) {
        this.content = content;
        this.user = user;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
    }
}