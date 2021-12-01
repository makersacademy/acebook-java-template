package com.makersacademy.acebook.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "COMMENTS")

public class Comment {

    @Id
    private UUID commentID = UUID.randomUUID();
    private String content;
    private Integer likes;
    private LocalDateTime stamp;
    private UUID userID;
    private String username;
    private UUID postID;

    public String getStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm");
        String formatDateTime = this.stamp.format(formatter);
        return formatDateTime;
    }

    public void setStamp(LocalDateTime my_Time) {
        this.stamp = my_Time;
    }

    public void setCommentID(UUID commentID) {
        this.commentID = commentID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
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

    public UUID getCommentID() {
        return commentID;
    }

    public String getContent() {
        return content;
    }

    public Integer getLikes() {
        return likes;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public UUID getPostID() {
        return postID;
    }
}
