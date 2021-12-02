package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
    @Entity
    @Table(name = "POSTS")
    public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

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
