package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Table(name = "COMMENTS")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Todo update Schema to BIGSERIAL and this type to long
    @Getter  @Setter private String content;
    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {}

    public Comment(String content) {this.content = content;}

    public Post getPost() {return this.post;}

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
