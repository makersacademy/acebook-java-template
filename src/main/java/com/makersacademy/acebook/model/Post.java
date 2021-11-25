package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer likes;
    private LocalDateTime stamp;

    public Post() {}
    public Post(String content) {
        this.content = content;
    }

    public LocalDateTime getStamp() {return this.stamp;}


    public void setStamp(LocalDateTime my_Time) {
        this.stamp = my_Time;
    }

    public Integer getLikes(){return this.likes;}
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

}
