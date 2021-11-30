package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "AUTHORITIES")
public class Authority {
    @Id
    private UUID authorityID = UUID.randomUUID();
    private String username;
    private String authority;

    public Authority() {}

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
