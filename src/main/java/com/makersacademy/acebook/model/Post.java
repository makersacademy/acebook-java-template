package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime postTime;

    public Post() {}

    public Post(String content, LocalDateTime postTime) {
        this.content = content; this.postTime = postTime;
    }
    public String getContent() { return this.content; }
    public LocalDateTime getDate() { return this.postTime; }
    public void setContent(String content) { this.content = content; }

}
