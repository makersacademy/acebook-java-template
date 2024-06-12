package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "POSTS")
@NoArgsConstructor
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long userId;
    private String photo;
    @javax.persistence.Transient private Long likes;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    public Post(String content, Long userId, String photo, java.sql.Timestamp createdAt) {
        this.content = content;
        this.userId = userId;
        this.photo = photo;
        this.createdAt = createdAt;
    }
}