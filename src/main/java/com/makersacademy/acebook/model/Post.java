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
    private Long user_id;
    private String photo;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;


    public Post(String content, Long user_id, String photo, java.sql.Timestamp createdAt) {
        this.content = content;
        this.user_id = user_id;
        this.photo = photo;
        this.createdAt = createdAt;
    }

}
