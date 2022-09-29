package com.makersacademy.acebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "messages")

public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Long messageId;
    @Column(name = "txt", nullable = false)
    private String txt;
    @Column(name = "sender_id", nullable = false)
    private Long senderId;
    @Column(name = "recipient_id", nullable = false)
    private Long recipientId;

    public Messages(Long messageId, String txt, Long senderId, Long recipientId) {
        this.messageId = messageId;
        this.txt = txt;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public long getMessageId() {
        return this.messageId;
    }

    public String getTxt() {
        return this.txt;
    }

    public long getSenderId() {
        return this.senderId;
    }

    public long getRecipientId() {
        return this.recipientId;
    }
}
