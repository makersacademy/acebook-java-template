package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Post() {}

    public Post(String content, String title, User user) {
        this.content = content;
        this.title = title;
        this.user = user;
    }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }


    public String getTitle() { return this.title; }
    public void setTitle(String content) { this.title = title; }

    public String getUsername() {
        return this.user != null ? this.user.getUsername() : null;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
