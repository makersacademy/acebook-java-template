package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "FRIENDS")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private Long requesterId;
    @Getter
    private Long receiverId;
    @Getter
    private String status;

    public Friend() {}

    public Friend(Long requester_id, Long receiver_id, String status) {
        this.requesterId = requester_id;
        this.receiverId = receiver_id;
        this.status = status;
    }

    public void setRequesterId(Long requester_id) { this.requesterId = requester_id; }
    public void setReceiverId(Long receiver_id) { this.receiverId = receiver_id; }
    public void setStatus(String status) { this.status = status; }

}
