package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.util.Date;  
import java.sql.Timestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Timestamp time_posted;
    private Long userid;
    private Long postid;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    */
    public Comment() {}

    public Comment(String content) {
        this.content = content;
    }
    
    public String getContent() { return content; }
    /*public Long getUser() { return userid; }
    public Long getUserId() { return id; } */
    public Long getPostid() { return postid; }
    public Long getUserid() { return userid; }
    public Long getId() { return id; }
    public Date getTimePosted() { return time_posted;}

    public void setContent(String content) { this.content = content; }
    public void setUserid(Long userid) { this.userid = userid; }
    public void setPostid(Long postid) { this.postid = postid; }
    public void setTimePosted(Timestamp time_posted) { this.time_posted = time_posted; }
 

}
