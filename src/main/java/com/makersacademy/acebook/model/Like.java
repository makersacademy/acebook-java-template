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
    private Long user_id;
    private Long post_id;

    
    public Like(){
    }
    
    public Like(Long id){
        this.id = id;
    }

    public Like(Long id, Long user_id) {
        this.id = id;
        this.user_id = user_id;
    }
    
    public Like(Long id, Long user_id, Long post_id) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPost_id() {
        return this.post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }
    
}
