package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long likedpost;
    private String username;

    public Like() {}

    public Like(Long likedpost, String username) {
        this.likedpost = likedpost;
        this.username = username;
    }
    
    public Long getLikedpost() {return this.likedpost;}
    public String getUsername() {return this.username;}

    public void setLikedpost(Long likedpost) {this.likedpost = likedpost;}
    public void setUsername(String username) {this.username = username;}

}