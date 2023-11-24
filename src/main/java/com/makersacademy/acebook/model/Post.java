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

    private Long likes = 0L; // Add this field

    @Transient
    private List<Comment> comments;
    @Lob
    private String image;

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

    //public Long getId(){
      //  return id;
    //}

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    // Getter and Setter for content

    public Long getId() {return this.id; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public List<Comment> getComments() { return this.comments; }
    public void setComments(List<Comment> comments) {this.comments = comments; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
}
