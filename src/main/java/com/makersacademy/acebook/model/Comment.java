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
    private Long user_id;
    private Long post_id;
    private String content;

    public Comment() {}

    public Comment(Long user_id, Long post_id, String content) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.content = content;
    }

    public Long getUserId() { return this.user_id; }
    public void setUserId(Long user_id) { this.user_id = user_id; }
    public Long getPostId() { return this.post_id; }
    public void setPostId(Long post_id) { this.post_id = post_id; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
}
