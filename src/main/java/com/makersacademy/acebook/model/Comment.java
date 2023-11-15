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
    private Long post_id;
    private String content;

    public Comment() {}

    public Comment(String username, Long post_id, String content) {
        this.username = username;
        this.post_id = post_id;
        this.content = content;
    }

    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }
    public Long getPostId() { return this.post_id; }
    public void setPostId(Long post_id) { this.post_id = post_id; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
}
