package com.makersacademy.acebook.model;

import javax.persistence.*;

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
    @Lob
    private String image;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }
    public Long getId() {return this.id; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public List<Comment> getComments() { return this.comments; }
    public void setComments(List<Comment> comments) {this.comments = comments; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
}
