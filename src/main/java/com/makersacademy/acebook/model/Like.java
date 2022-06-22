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

    private Long user_id;

    private Long post_id;

    public Like(Long user_id, Long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public Long getUserId() { return this.user_id; }

    public Long getPostId() { return this.post_id; }

}
