package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "post_id")
    private Long postId;
    private String content;

    public Comment() {}

    public Comment(String username, Long postId, String content) {
        this.username = username;
        this.postId = postId;
        this.content = content;
    }

    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }
    public Long getPostId() { return this.postId; }
    public void setPostId(Long postId) { this.postId = postId; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
}
