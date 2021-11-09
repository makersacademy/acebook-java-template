package com.makersacademy.acebook.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String content;
    @CreationTimestamp
    public Timestamp time;
    public String username;

    // @ManyToOne
    // @JoinColumn(name = "username", insertable = false, updatable = false)
    // public User user;

    public Post() {
    }

    public Post(String content, Timestamp time, Long id, String username) {
        this.content = content;
        this.time = time;
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
