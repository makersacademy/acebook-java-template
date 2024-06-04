package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String img_url;
    private Integer likeCount = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    private Long user_id;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter @Setter private List<Comment> comments;

    // No-args constructor
    public Post() {}

    // Constructor
    public Post(String content ) {
        this.content = content;
    }
//    public Post(String content, String img_url) {
//        this.content = content;
//        this.img_url = img_url;
//    }

    // Getters and setters
    public Long getId() { return this.id; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public Integer getLikeCount() { return this.likeCount; }
    public void incrementLikeCount() { this.likeCount += 1; }
    public String getImg_url() { return this.img_url; }
    public void setImg_url(String img_url) { this.img_url = img_url; }
    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }
//    public Long getUser_id() { return this.user_id; }
//    public void setUser_id(Long user_id) { this.user_id = user_id; }
}
