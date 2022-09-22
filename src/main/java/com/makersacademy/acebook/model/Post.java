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
    private String title;
    private String content;

    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    public String getTitle() {return this.title;}
    public String getContent() {return this.content;}

    public void setTitle(String title) {this.title = title;}
    public void setContent(String content) {this.content = content;}

}
