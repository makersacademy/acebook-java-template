package com.makersacademy.acebook.model;

import com.makersacademy.acebook.bean.DateTimeFormatBean;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.DateTimeFormatterFactoryBean;

import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    // @Configuration
    // public class PostConfig {
    //     @DateTimeFormat
    //     public String timestamp() {
    //         Date date = Calendar.getInstance().getTime();  
    //         DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");  
    //         String timestamp = dateFormat.format(date);
    //         return timestamp;
    //     }
    // }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    // https://stackoverflow.com/questions/2305973/java-util-date-vs-java-sql-date
    // https://www.baeldung.com/java-convert-localdate-sql-date
    // private Date timestampDate = (Date) Date.from(java.time.ZonedDateTime.now().toInstant());
    // private String timestamp = timestampDate.toString(); 

            // *** This one ***
    // private Date date = Calendar.getInstance().getTime();  
    // private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");  
    // private String timestamp = dateFormat.format(date);  

    private String timestamp;

    // Date date = (Date) Calendar.getInstance().getTime();  
    // DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");  

    // @Autowired
    // private ApplicationContext appContext;
    
    // @Autowired
    // private DateTimeFormatBean dateTimeFormatBean;

    // @Override
    // public void timestamp() {
        
    //     System.out.println(myBean.getMessage());
        
    //     System.out.println("List of beans:");
        
    //     String[] beans = appContext.getBeanDefinitionNames();
        
    //     for (String bean : beans) {
    //         System.out.println(bean);
    //     }


    public Post() {}
    public Post(String content) { this.content = content; }
    public Post(String content, String timestamp) { this.content = content; this.timestamp = timestamp; }
    
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public String createTimestamp() { 
        
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");  
        String timestamp = dateFormat.format(date);
        return timestamp;

     }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

}
