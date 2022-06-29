package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Long post_id;

    private String CommentContent;

    public Comment() {}

    public Comment(String username, Long post_id, String CommentContent) {
        this.username = username;
        this.post_id = post_id;
        this.CommentContent = CommentContent;
    }

    public String getUsername() { return this.username; }

    public Long getPostId() { return this.post_id; }

    public String getCommentContent() { return this.CommentContent; }
}
