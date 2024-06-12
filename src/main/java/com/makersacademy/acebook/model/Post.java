package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
