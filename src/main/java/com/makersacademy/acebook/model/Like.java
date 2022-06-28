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

    private String username;

    private Long post_id;

    public Like() {}

    public Like(String username, Long post_id) {
        this.username = username;
        this.post_id = post_id;
    }

    public String getUsername() { return this.username; }

    public Long getPostId() { return this.post_id; }

}
