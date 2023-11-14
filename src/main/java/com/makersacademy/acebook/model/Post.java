package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    private Timestamp timestamp;

    private Long userId;

    public Post() {}

    public Post(String content, Timestamp timestamp, Long userId) {
        this.content = content;
        this.timestamp = timestamp;
        this.userId = userId;
    }
    public String getContent() {return this.content;}
    public void setContent(String content) { this.content = content; }

    public Timestamp getTimestamp() {return this.timestamp;}
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public Long getUserId() {return this.userId;}
    public void setUserId(Long userId) { this.userId = userId; }

}
