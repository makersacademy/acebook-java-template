package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

import java.sql.Timestamp;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Data
@Entity
@Table(name = "Friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    private boolean accepted;
    private Timestamp createdAt;

    public Friend() {
        this.accepted = FALSE;
    }

    public Friend(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.accepted = FALSE;
    }


}
