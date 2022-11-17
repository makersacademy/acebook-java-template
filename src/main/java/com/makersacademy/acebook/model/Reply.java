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
@Table(name = "REPLIES")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date time_posted;
    private int post_id;

    public Reply() {}

    public Reply(String content) {
        this.content = content;
    }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public Date getTime_posted() { return this.time_posted; }
    public void setTime_posted(Date time_posted) { this.time_posted = time_posted; }

    public int getPost_id() { return this.post_id; }
    public void setPost_id(int post_id) { this.post_id = post_id; }

}
