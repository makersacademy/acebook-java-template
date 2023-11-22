package com.makersacademy.acebook.model;


import lombok.Data;
import lombok.Getter;

import javax.persistence.*;


@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String comment;
    @Getter
    private Long postId;
    @Getter
    private Long userId;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "post_id")
//    private Post post;

    public Comment() {};

    public Comment(String comment, Long postId, Long userId) {
        this.comment = comment;
        this.postId = postId;
        this.userId = userId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setUserId(Long UserId) {
        this.userId = UserId;
    }
}
