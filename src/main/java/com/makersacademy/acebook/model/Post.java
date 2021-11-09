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
    public byte[] contentimage;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public Post() {
    }

    public Post(String content, Timestamp time, Long id) {
        this.content = content;
        this.time = time;
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setUser(User user) {this.user = user;} 
    public void setContent(String content) {
        this.content = content;
    }
    
}
