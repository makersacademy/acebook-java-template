package com.makersacademy.acebook.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "PROFILES")

public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long user_id;
  private String bio;
  private String pronouns;
  private Date birthday;
  private String nickname;

  public Profile() {}

  public Profile(long id, long user_id, String bio, String pronouns, Date birthday, String nickname) {
    this.id = id;
    this.user_id = user_id;
    this.bio = bio;
    this.pronouns = pronouns;
    this.birthday = birthday;
    this.nickname = nickname;
  }

  public long getUserId() { return this.user_id; }
  public void setUserId(long user_id) { this.user_id = user_id; }

  public String getBio() { return this.bio; }
  public void setBio(String bio) { this.bio = bio; }

  public String getPronouns() { return this.pronouns; }
  public void setPronouns(String pronouns) { this.pronouns = pronouns; }

  public Date getBirthday() { return this.birthday; }
  public void setBirthday(Date birthday) { this.birthday = birthday; }

  public String getNickname() { return this.nickname; }
  public void setNickname(String nickname) { this.nickname = nickname; }
}
