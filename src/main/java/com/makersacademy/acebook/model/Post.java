package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Data
@Entity
@Table(name = "POSTS")
public class Post {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer likeCount = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // No-args constructor
    public Post() {}

    // Constructor
    public Post(String content) {
        this.content = content;
    }

    public void setContent(String content) { this.content = content; }

    public void incrementLikeCount() { this.likeCount += 1; }

}
