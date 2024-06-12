package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;


@Data
@Entity
@Table(name = "COMMENTS")
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
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    public Comment(String content, Long user_id, Long post_id, java.sql.Timestamp createdAt) {
        this.content = content;
        this.user_id = user_id;
        this.post_id = post_id;
        this.createdAt = createdAt;
    }

}
