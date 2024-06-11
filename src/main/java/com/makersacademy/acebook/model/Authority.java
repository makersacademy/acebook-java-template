package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AUTHORITIES")
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String authority;

    public Authority(long userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }
}
