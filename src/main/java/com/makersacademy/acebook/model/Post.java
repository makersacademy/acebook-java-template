package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.GenerationType;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Transient
    private List<Comment> comments;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }
    public Long getId() {return this.id; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public List<Comment> getComments() { return this.comments; }
    public void setComments(List<Comment> comments) {this.comments = comments; }
}
