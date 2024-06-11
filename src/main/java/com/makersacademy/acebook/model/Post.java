package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post() {}

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
    }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }
}
