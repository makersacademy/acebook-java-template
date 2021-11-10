package com.makersacademy.acebook.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String content;
    @CreationTimestamp
    public Timestamp time;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    public Post post;

    public Comment() {
    }

    public Comment(String content, Timestamp time, Long id) {
        this.content = content;
        this.time = time;
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // public User getUser() {
    //     return this.user;
    // }

    public void setUser(User user) {this.user = user;}

    // public Post getPost() {
    //     return this.post;
    // }

    public void setPost(Post post) {this.post = post;} 

    // public void setUser(User user) {this.user = user;}

}
