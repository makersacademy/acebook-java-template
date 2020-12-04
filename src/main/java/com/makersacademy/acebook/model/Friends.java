package com.makersacademy.acebook.model;

public class Friends {
  private long id;
  private long person_id;
  private long friend_id;
  private boolean accepted;

  public Friends(long id, long person_id, long friend_id, boolean accepted) {
    this.id = id;
    this.person_id = person_id;
    this.friend_id = friend_id;
    this.accepted = accepted;
  }
}
