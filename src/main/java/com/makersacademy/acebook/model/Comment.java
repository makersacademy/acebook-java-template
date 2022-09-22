package com.makersacademy.acebook.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long post_id;
    private String content;
    private Timestamp created_at;
    
    // Constructors:
    public Comment(){
    }
    
    public Comment(Long id){
        this.id = id;
    }

    public Comment(Long id, Long post_id) {
        this.id = id;
        this.post_id = post_id;
    }
    
    public Comment(Long id, Long post_id, String content) {
        this.id = id;
        this.post_id = post_id;
        this.content = content;
    }

    public Comment(Long id, Long post_id, String content, Timestamp created_at) {
        this.id = id;
        this.post_id = post_id;
        this.content = content;
        this.created_at = created_at;
    }

    // Getters and Setters:
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPost_id() {
        return this.post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

     public Timestamp getCreated_at() {
        return this.created_at;
    }

    public void setcreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
