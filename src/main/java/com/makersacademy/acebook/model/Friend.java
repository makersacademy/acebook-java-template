package com.makersacademy.acebook.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GenerationType;

import static java.lang.Boolean.*;

import lombok.Data;

@Data
@Entity
@Table(name = "FRIENDS")
public class Friend {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Boolean accepted;
  private String visibility;
  private LocalDateTime time;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "friend_id", nullable = false)
  private User theFriend;

  public Friend() {
    this.time = LocalDateTime.now();
    this.accepted = FALSE;
    this.visibility = "all";
  }

  public Friend(User user, User theFriend, Boolean accepted) {
    this.time = LocalDateTime.now();
    this.accepted = accepted;
    this.visibility = "all";
    this.user = user;
    this.theFriend = theFriend;
  }

  public Long getId() {
    return id;
  }

  public Boolean getAccepted() {
    return accepted;
  }

  public void setAccepted(Boolean accepted) {
    this.accepted = accepted;
  }

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getFriend() {
    return theFriend;
  }

  public void setFriend(User theFriend) {
    this.theFriend = theFriend;
  }

}
