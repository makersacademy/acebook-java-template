package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "POSTS")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long post_id;


    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLike(String username, Long post_id) {
        setUsername(username);
        setPost_id(post_id);
    }
}
