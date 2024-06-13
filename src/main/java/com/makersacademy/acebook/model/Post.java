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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String photo;
    @javax.persistence.Transient private Long likes;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    public Post(String content, User user, String photo, java.sql.Timestamp createdAt) {
        this.content = content;
        this.user = user;
        this.photo = photo;
        this.createdAt = createdAt;
    }
}