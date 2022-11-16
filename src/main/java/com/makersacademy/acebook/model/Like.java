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

    private long id;
    private long user_id;
    private Long post_id;

    public Like() {}

    public Like(long user_id, Long post_id){
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public long getUser_id() { return this.user_id; }
    public void setUser_id(long user_id) { this.user_id = user_id; }

    public long getPost_id() { return this.post_id; }
    public void setPost_id(long post_id) { this.post_id = post_id; }
}
