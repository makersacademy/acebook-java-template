package com.makersacademy.acebook.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "COMMENTS")

public class Comment {

    @Id
    private UUID commentID = UUID.randomUUID();
    private String content;
    private Integer likes;
    private LocalDateTime stamp;
    private UUID userID;
    private String username;
    private UUID postID;

    public String getStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm");
        String formatDateTime = this.stamp.format(formatter);
        return formatDateTime;
    }

    public void setStamp(LocalDateTime my_Time) {
        this.stamp = my_Time;
    }

}
