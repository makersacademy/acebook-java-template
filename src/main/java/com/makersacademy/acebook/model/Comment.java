package com.makersacademy.acebook.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ocpsoft.prettytime.PrettyTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date created_at;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    public String username;

    public Comment() {
    }

    public Comment(String content) {
        this.content = content;
    }

    public Comment(String content, String username, Long postId) {
        this.content = content;
        this.username = username;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUser() {
        return this.username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return this.created_at;
    }

    public String getFormattedTimestamp() {
        PrettyTime p = new PrettyTime();
        return (p.format(getCreatedAt()));
    }

}
