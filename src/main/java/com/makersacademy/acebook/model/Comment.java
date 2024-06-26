package com.makersacademy.acebook.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String content;

    @Setter
    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Comment () {
    }

    public String getFormattedCreatedAt() {
        return formatDateTime(String.valueOf(createdAt));
    }

    private String formatDateTime(String dateTime) {
        if (dateTime != null && !dateTime.isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, INPUT_FORMATTER);
            return localDateTime.format(OUTPUT_FORMATTER);
        }
        return null;
    }
}

