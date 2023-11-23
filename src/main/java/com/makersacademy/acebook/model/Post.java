package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String content;
    @Getter
    String postPhoto;
    @Getter
    private Timestamp timestamp;
    @Getter
    private Long userId;

    public Post() {}

    public Post(String content, Timestamp timestamp, Long userId, String postPhoto) {
        this.content = content;
        this.timestamp = timestamp;
        this.userId = userId;
        this.postPhoto = postPhoto;
    }
    public void setContent(String content) { this.content = content; }

    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setPostPhoto(String postPhoto) { this.postPhoto = postPhoto; }
}
