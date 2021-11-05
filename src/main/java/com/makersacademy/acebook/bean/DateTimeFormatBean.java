package com.makersacademy.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.makersacademy.acebook.model.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

public class DateTimeFormatBean {
  @Configuration
  public class PostConfig {
      @DateTimeFormat
      public String timestamp() {
          java.util.Date date = Calendar.getInstance().getTime();  
          DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");  
          String timestamp = dateFormat.format(date);
          return timestamp;
      }
  }
}
