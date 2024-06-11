package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "AUTHORITIES")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String content;
    private Long user_id;
    private Long post_id;
    private LocalDateTime created_at;

    public Comment(String content, Long user_id, Long post_id, LocalDateTime created_at) {
        this.content = content;
        this.user_id = user_id;
        this.post_id = post_id;
        this.created_at = created_at;
    }
}
