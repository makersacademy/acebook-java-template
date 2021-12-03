package com.makersacademy.acebook.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "POSTS")
public class Post {

    private String content;
    private LocalDateTime stamp;
    private UUID userID;
    private String username;
    private Long like_count;
    private Boolean comment;
    private String post_image;
    @Id
    private UUID postID = UUID.randomUUID();
    private String profile_image;

    public Post(String content) {
        this.content = content;
    }

    public String getStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm");
        String formatDateTime = this.stamp.format(formatter);
        return formatDateTime;
    }

    public void setStamp(LocalDateTime my_Time) {
        this.stamp = my_Time;
    }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public void setUsername(String username){ this.username = username; }
    public String getUsername() { return this.username; }
    public void setLikeCount(Long likeCount) { this.like_count = likeCount; }
    public void setPostImage(String imgURL) { this.post_image = imgURL; }
    public String getPostImage() { return this.post_image; }
    public void setProfileImage(String imgURL) { this.profile_image = imgURL; }
    public String getProfileImage() { return this.profile_image;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return postID != null && Objects.equals(postID, post.postID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
