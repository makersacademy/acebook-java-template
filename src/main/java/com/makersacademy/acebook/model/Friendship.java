package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userRequester;
    private Long userAccepter;

    private String status;

    public Friendship() {}

    public Friendship(Long userRequester, Long userAccepter, String status) {
        this.userRequester = userRequester;
        this.userAccepter = userAccepter;
        this.status = status;
    }

    public Long getUserRequester() {
        return userRequester;
    }

    public void setUserRequester(Long userRequester) {
        this.userRequester = userRequester;
    }

    public Long getUserAccepter() {
        return userAccepter;
    }

    public void setUserAccepter(Long userAccepter) {
        this.userAccepter = userAccepter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
