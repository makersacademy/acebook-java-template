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
    private String author;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }

    // -----getter and setter provided by lombok.Data-----
    // public String getContent() { return this.content; }
    // public void setContent(String content) { this.content = content; }
    // public Long getId() { return this.id; }
    // public void setId(Long id) { this.id = id; }

}
