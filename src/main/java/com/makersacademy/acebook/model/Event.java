package com.makersacademy.acebook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private LocalDateTime scheduledDate;

    @Setter
    @Getter
    private LocalDateTime createdAt;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
