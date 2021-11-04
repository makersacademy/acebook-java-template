package com.makersacademy.acebook.lib;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

public class PostQuery {
  public String content;
  public Timestamp time;

  public PostQuery(String content, Date time){
    this.content = content;
    this.time = Timestamp.valueOf(time.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
  }
}
