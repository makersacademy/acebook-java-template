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
    private Long postid;
    private String comment;
    private String commenter;

    public Comment(String comment) {
        this.comment = comment;
    }

   public Comment(Long postid, String comment, String commenter) {
        this.postid = postid;
        this.comment = comment;
        this.commenter=commenter;
    }

    public Comment() {
    }

    public String getComment(){
        return comment;
    }

    public Long getPostid(){
        return postid;
    }

    public String getCommenter(){
        return commenter;
    }
}

