package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.GenerationType;
import java.util.Set;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String username;
  
    @ManyToMany(mappedBy = "likedPosts")
    Set<User> likes; // Creates a 'Set' of 'Users' called likes. 
    //Each user associated with a post represents 1 like

    public Post() {}

    public Post(String content, String username) {
        this.content = content;
        this.username = username;

    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() { return this.likes.size(); } 
    // returns number of users in set. I.e number of likes.
  
    public String getUser(){
        return this.username;
    }

    public void setUser(String username){
        this.username = username;
    }
}
