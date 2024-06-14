package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;


@Data
@Entity
@Table(name = "NOTIFICATIONS")
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String type;
    private String message;
    private String link;
    private boolean seen;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    public Notification(User user, String type, String message, String link) {
        this.user = user;
        this.type = type;
        this.message = message;
        this.link = link;
        this.seen = false;
    }
}
