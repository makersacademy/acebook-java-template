package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long likes = 0L; // Add this field

    public Post() {

    }

    public Post(String content) {
        this.content = content;
        this.likes = 0L;
    }

    // Getter and Setter for likes
    public Long getLikes() {
        return likes;
    }

    public Long getId(){
        return id;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
