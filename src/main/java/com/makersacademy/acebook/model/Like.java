package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.tool.schema.extract.spi.DatabaseInformation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.GenerationType;

import lombok.Data;

import static java.lang.Boolean.TRUE;

import java.util.ArrayList;

@Data
@Entity
@Table(name = "likes")

public class Like {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long user_id;
  private Long post_id;
  private Long like_id;
  public boolean is_like;
  public Long like_count;
  public Long dislike_count;

  public Like() {
  }

  public Like(Long user_id, Long post_id, Long like_id, boolean is_like, Long like_count, Long dislike_count) {
    this.user_id = user_id;
    this.post_id = post_id;
    this.like_id = like_id;
    this.is_like = is_like;
    this.like_count = like_count;
    this.dislike_count = dislike_count;
  }

  public boolean getIsLike() {
    return this.is_like;
  }

  public void setIsLike(boolean is_like) {
    this.is_like = is_like;
  }

  public Long getLikeCount() {
    return this.like_count;
  }

  public void setLikeCount(Long like_count) {
    this.like_count = like_count;
  }

  public Long getDislikeCount() {
    return this.dislike_count;
  }

  public void setDislikeCount(Long dislike_count) {
    this.dislike_count = dislike_count;
  }

  public void like(Long post_id) {
    Long new_like_count = getLikeCount();
    setLikeCount(new_like_count);
  }

  public void dislike(Long post_id) {
    Long new_dislike_count = getDislikeCount();
    setDislikeCount(new_dislike_count);
  }
}
