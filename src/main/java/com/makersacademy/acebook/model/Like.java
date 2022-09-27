package com.makersacademy.acebook.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private Long userid;
    private Long postid;
    private Timestamp date;
    private String formatted_date;
    private String formatted_time;

    // Constructors:
    public Like() {
    }

    public Like(Long id) {
        this.id = id;
    }

    public Like(Long id, Long userid) {
        this.id = id;
        this.userid = userid;
    }

    public Like(Long id, Long userid, Long postid, Timestamp date) {
        this.id = id;
        this.userid = userid;
        this.postid = postid;
        this.date = date;
    }

    // Getters and Setters:
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getPostid() {
        return this.postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }

    public String formatDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return dtf.format(now);
    }

    public String getTimeString() {
        LocalDateTime time = this.date.toLocalDateTime(); // current time
        DateTimeFormatter format_t = DateTimeFormatter.ofPattern("HH:mm"); // format to extract
        String t = time.format(format_t);
        return t;
    }

    public String getDateString() {
        LocalDateTime date = this.date.toLocalDateTime(); // current date
        DateTimeFormatter format_t = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // format to extract
        String t = date.format(format_t);
        return t;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
        this.formatted_time = this.getTimeString();
        this.formatted_date = this.getDateString();
    }

}