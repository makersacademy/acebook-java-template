package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "FRIENDS")

public class Friend {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long to_user;
  private long from_user;
  private Integer confirmed = 0;


  public Friend() {}

  public long getToUser() { return this.to_user; }
  public void setToUser(long to_user) { this.to_user = to_user; }

  public long getFromUser() { return this.from_user; }
  public void setFromUser(long from_user) { this.from_user = from_user; }

  public Integer getConfirmed() { return this.confirmed; }
  public void setConfirmed(Integer confirmed) { this.confirmed = confirmed; }
}
