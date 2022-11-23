package com.makersacademy.acebook.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private String time_posted;
    private Integer userId;
    private String username;
    private String image;

    @OneToMany(mappedBy = "post_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies;

    @OneToMany(mappedBy = "post_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public String getTime_posted() { return this.time_posted; }
    public void setTime_posted(String time_posted) { this.time_posted = time_posted; }


    public Integer getUser_id() { return this.userId; }
    public void setUser_id(Integer userId) { this.userId = userId; }

    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }

    public String getImage() { return this.image; }
    public void setImage(String image) { this.image = image; }
}
