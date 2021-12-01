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
    private int postId;
    private String username;

    public void setLike(int postId,String username){
        setPostId(postId);
        setUsername(username);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

 }
