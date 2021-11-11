package com.makersacademy.acebook.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Set;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.GenerationType;
import org.ocpsoft.prettytime.PrettyTime;
import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String content;
    @CreationTimestamp
    public Timestamp time;
    public byte[] contentimage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToMany(mappedBy = "likedPosts")
    Set<User> likes;
    @OneToMany(mappedBy = "post")
    List<Comment> comments;

    public Post() {
    }

    public Post(String content, Timestamp time, Long id) {
        this.content = content;
        this.time = time;
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommentsCount() {
        return this.comments.size();
    }

    public Date getDate() {
        Date date = new Date(this.time.getTime());
        return date;
    }

    public String timeFormat() {
        PrettyTime format = new PrettyTime();
        return format.format(getDate());
    }
}
