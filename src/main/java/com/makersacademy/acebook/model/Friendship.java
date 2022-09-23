package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import lombok.Data;

@Data
@Entity
@Table(name = "FRIENDS")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id", nullable = false)
    private Long friendshipId;
    @Column(name = "requester_id", nullable = false)
    private Long requesterId;
    @Column(name = "requestee_id", nullable = false)
    private Long requesteeId;
    @Column(name = "request_status", nullable = false)
    private String requestStatus;

    public Friendship() {}

    public Friendship(Long requesterId, Long requesteeId) {
        this.requesterId = requesterId;
        this.requesteeId = requesteeId;
        this.requestStatus = "pending";
    }
    
    // public String getRequestStatus(Long requesterId, Long requesteeId) {return this.requestStatus;}
    // public String getContent() {return this.content;}

    // public void setRequestStatus(String requestStatus) {this.requestStatus = requestStatus;}
    // public void setContent(String content) {this.content = content;}
}
