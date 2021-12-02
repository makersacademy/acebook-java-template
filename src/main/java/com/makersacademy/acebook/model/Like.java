package com.makersacademy.acebook.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@RequiredArgsConstructor
@Entity
@Table(name= "LIKES")
public class Like {

    @Id
    private UUID likeID = UUID.randomUUID();
    private UUID userID;
    private String username;
    private UUID postID;
    private UUID commentID;

    public UUID getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPostID(UUID postID) {
        this.postID = postID;
    }

    public void setCommentID(UUID commentID) {
        this.commentID = commentID;
    }

    public UUID getPostID() {
        return postID;
    }

    public UUID getCommentID() {
        return commentID;
    }

    public UUID getLikeID() { return this.likeID; }
}
