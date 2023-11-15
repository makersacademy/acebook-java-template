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

    private String comment;
    private Long postId;
    private Long userId;

    public Comment() {};

    public Comment(String comment, Long postId, Long userId) {
        this.comment = comment;
        this.postId = postId;
        this.userId = userId;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long UserId) {
        this.userId = UserId;
    }
}
