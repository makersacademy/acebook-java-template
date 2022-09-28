package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.Data;

@Data
@Entity
@Table(name = "FRIENDS")

public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status_code;
    private Long request_from;
    private Long request_to;
    private Timestamp sent_at;

    // Constructors:
    public Friend() {
    }

    public Friend(Long id) {
        this.id = id;
    }

    public Friend(Long id, Boolean status_code) {
        this.id = id;
        this.status_code = status_code;
    }

    public Friend(Long id, Boolean status_code, Long request_from) {
        this.id = id;
        this.status_code = status_code;
        this.request_from = request_from;
    }

    public Friend(Long id, Boolean status_code, Long request_from, Long request_to) {
        this.id = id;
        this.status_code = status_code;
        this.request_from = request_from;
        this.request_to = request_to;
    }

    public Friend(Long id, Boolean status_code, Long request_from, Long request_to, Timestamp sent_at) {
        this.id = id;
        this.status_code = status_code;
        this.request_from = request_from;
        this.request_to = request_to;
        this.sent_at = sent_at;
    }

    // Getters and Setters:
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isStatus_code() {
        return this.status_code;
    }

    public Boolean getStatus_code() {
        return this.status_code;
    }

    public void setStatus_code(Boolean status_code) {
        this.status_code = status_code;
    }

    public Long getRequest_from() {
        return this.request_from;
    }

    public void setRequest_from(Long request_from) {
        this.request_from = request_from;
    }

    public Long getRequest_to() {
        return this.request_to;
    }

    public void setRequest_to(Long request_to) {
        this.request_to = request_to;
    }

    public Timestamp getSent_at() {
        return this.sent_at;
    }

    public void setSent_at(Timestamp sent_at) {
        this.sent_at = sent_at;
    }
}
