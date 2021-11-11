package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import lombok.Data;

import javax.persistence.ManyToMany;
import javax.persistence.GenerationType;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.ManyToOne;

@Data
@Table(name = "LIKES")
@Entity
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private long user_id;
  @ManyToOne
  @JoinColumn(name = "post_id")
  public Post post;

  public Like(long user_id) {
    this.user_id = user_id;
    
  }

  public Like(){
  }

  public Post getPost() {
    return this.post;
}

public void setPost(Post post) {
  this.post = post;
}



// public void setPostId() {
//   this.post_id;
// }



  // @Formula("SELECT COUNT(post_id) FROM likes l WHERE l.post_id = post_id")
  // public int likeCount;

  // public int likeCount() {
  // return likeCount;
  // }

}