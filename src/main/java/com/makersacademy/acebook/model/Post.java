package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer likeCount = 0;
    private Long user_id;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter @Setter private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // No-args constructor
    public Post() {}

    // Constructor
    public Post(String content) {
        this.content = content;
    }
    public Long getId() { return this.id; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public Integer getLikeCount() { return this.likeCount; }
    public void incrementLikeCount() { this.likeCount += 1; }
    public Long getUser_id() { return this.user_id; }
    public void setUser_id(Long user_id) { this.user_id = user_id; }

}
