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
    private Long user_id;
    private String content;
    private Timestamp date;
    
    // Constructors:
    public Comment(){
    }
    
    public Comment(Long id){
        this.id = id;
    }

    public Comment(Long id, Long user_id) {
        this.id = id;
        this.user_id = user_id;
    }
    
    public Comment(Long id, Long user_id, String content) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
    }

    public Comment(Long id, Long user_id, String content, Timestamp date) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.date = date;
    }

    // Getters and Setters:
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

     public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
