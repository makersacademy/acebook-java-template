package com.makersacademy.acebook.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
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
    public String content;
    private Long user_id;
    private Long post_id;

    public Comment() {}

    public Comment(String content) {
        this.content = content;

    }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public void setUserId(Long userId){ this.user_id = userId; }
    public void setPostId(Long postId){ this.post_id = postId; }
    public Long getPostID(){return this.post_id;}
    public Long getUserID(){return this.user_id;}


}
