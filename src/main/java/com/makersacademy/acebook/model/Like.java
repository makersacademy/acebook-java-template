package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private Long postid;

    // Constructors:
    public Like() {
    }

    public Like(Long id) {
        this.id = id;
    }

    public Like(Long id, Long userid) {
        this.id = id;
        this.userid = userid;
    }

    public Like(Long id, Long userid, Long postid) {
        this.id = id;
        this.userid = userid;
        this.postid = postid;
    }

    // Getters and Setters:
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getPostid() {
        return this.postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }
}