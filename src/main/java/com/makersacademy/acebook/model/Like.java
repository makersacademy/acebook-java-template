package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
    @Entity
    @Table(name = "POSTS")
    public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;
    private int id;
    private String username;

    public void setLike(int postId,String username){
        setId(postId);
        setUsername(username);
    }


    public Long getLike_id() {
        return like_id;
    }

    public void setLike_id(Long like_id) {
        this.like_id = like_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

 }
