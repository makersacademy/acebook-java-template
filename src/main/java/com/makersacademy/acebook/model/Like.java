package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LIKES")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private Boolean likeStatus = false;
    @Getter
    private Long userId;
    @Getter
    private Long postId;

    public Like() {};
    public Like(Boolean likeStatus, Long userId, Long postId) {
        this.likeStatus = likeStatus;
        this.userId = userId;
        this.postId = postId;
    }

    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
