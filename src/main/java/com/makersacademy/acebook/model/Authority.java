
package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AUTHORITIES")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String authority;

    public Authority() {}

    public Authority(String email, String authority) {
        this.email = email;
        this.authority = authority;
    }
}
